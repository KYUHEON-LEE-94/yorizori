package namoo.yorizori.cookbook.controller;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import namoo.yorizori.common.factory.ServiceFactoryImpl;
import namoo.yorizori.common.web.YzRuntimeException;
import namoo.yorizori.cookbook.service.CookbookService;
import namoo.yorizori.receipe.dto.Reciepe;
import namoo.yorizori.receipeProcedure.dto.ReciepeProcedure;

/**
 * Cookbook 안에 recipe가 포함되어 있기 때문에 따로 분리 안함 레시피 등록 처리 서블릿 컨트롤러
 */
@WebServlet("/recipe/register.do")

@MultipartConfig(
		// 이 파일 용량 이상일때만 임시 폴더에 파일 생성 - fileSizeThreshold
		fileSizeThreshold = 1024 * 1024 * 1, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024
				* 15, 
				location = "D:/WebDevel/workspace/Yorizori2/src/main/webapp/assets")
public class RecipeRegistertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** 레시피 등록 화면 조회 */
	// 등록은 로그인을 해야지만 가능하기 떄문에 로그인 여부 확인
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//쿼리 스트링으로 넘어온 bookid값을 읽어서 recipeForm.jsp의 value에 기록할 수 있게 전달
		String bookId = request.getParameter("bookId");	
		request.setAttribute("bookId", Integer.parseInt(bookId));
		//REST API 사항에 따라 대문자는 사용하지 않도록.
		Cookie[] cookies = request.getCookies();
		String loginId = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase("loginId")) {
					loginId = cookie.getValue();
				}
			}
		}
		// 로그인 하지 않은 경우
		if (loginId == null) {
			throw new YzRuntimeException("레시피를 등록하려면 로그인이 필요합니다.", "/user/loginform.do");
		} else {
			request.getRequestDispatcher("/WEB-INF/views/reciepe/recipeForm.jsp").forward(request, response);
		}
	}

	/** 레시피 등록 요청 처리 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		

		String recipeName = request.getParameter("recipe_name");

		String writerId = request.getParameter("user_id");
		
		
		String ingredients = request.getParameter("ingredients");
		

		String recipeTime = request.getParameter("recipe_time");
		String recipeLevel = request.getParameter("recipe_level");
		String bookId = request.getParameter("bookId");

		int bookInt, recipeTimeint, recipeLevelint;
		if(bookId != null) {
			recipeTimeint = Integer.parseInt(recipeTime);	
			recipeLevelint = Integer.parseInt(recipeLevel);
			bookInt = Integer.parseInt(bookId);

		}else {

			throw new YzRuntimeException("다시 한번 확인해주세요");

		}

		// 파일은 Part객체로 받아온다. //name
		Part part = request.getPart("img_file_name");
		String imgFileName = part.getSubmittedFileName();
		String contentType = part.getContentType();

		String[] Procedures = request.getParameterValues("seq_num");
		int number = Procedures.length;

		Reciepe reciepe = new Reciepe();

		//레시피 아이디 랜덤 발생함수 사용
		int recipeId = recipeIDMake();
		
		reciepe.setReceipeId(recipeId);
		//recipe 입력받는 부분
		
		reciepe.setBookId(bookInt);
		reciepe.setReceipeName(recipeName);
		reciepe.setWriterId(writerId);
		reciepe.setReceipeTime(recipeTimeint);
		reciepe.setReceipeLevel(recipeLevelint);
		reciepe.setIngredients(ingredients);
		reciepe.setImgContType(contentType);
		reciepe.setImgFileName(imgFileName);

		// 레시피 DB에 저장
		CookbookService service = ServiceFactoryImpl.getInstance().getReceipeService();
		service.registerRecipe(reciepe);

		//--------------------------------------
		
		
		// 레시피프로시저 DB에 저장하는 프로시저
		ReciepeProcedure receipeProcedure = new ReciepeProcedure();
		CookbookService ProcedureService = ServiceFactoryImpl.getInstance().getReceipeProcedureService();

		receipeProcedure.setReceipeId(recipeId);
		receipeProcedure.setProcedure(Procedures);
		receipeProcedure.setSeqNum(number);

		ProcedureService.registerRecipeProcedure(receipeProcedure);
		File file = new File("D:/WebDevel/workspace/Yorizori2/src/main/webapp/assets");
		// file이 없으면 파일을 생성해라~
		if (!file.exists()) {
			file.mkdirs();
		}
		// 업로드 파일 저장
		part.write(file.getAbsolutePath() + File.separator + imgFileName);

		// DB 테이블 저장

		// 임시 메인
		response.sendRedirect("/cookbook/list.do");

	}

	/**
	 * recipeID를 랜덤하게 넣어주기 위해서 생성
	 * @return
	 */
	private static int recipeIDMake() {
		
        Random rd = new Random();//랜덤 객체 생성     
        int RecipeId = (rd.nextInt(500)+1);		
		return RecipeId;
	}
	
}
