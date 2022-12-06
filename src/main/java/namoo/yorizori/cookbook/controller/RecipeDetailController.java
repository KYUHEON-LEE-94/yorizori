package namoo.yorizori.cookbook.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import namoo.yorizori.common.factory.ServiceFactoryImpl;
import namoo.yorizori.cookbook.service.CookbookService;

/**
 * Cookbook 안에 recipe가 포함되어 있기 때문에 따로 분리 안함
 * 상세 레시피 컨트롤러
 */
@WebServlet("/recipe/detail.do")


public class RecipeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/** 레시피 등록 화면 조회 */
		//등록은 로그인을 해야지만 가능하기 떄문에 로그인 여부 확인
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String bookid = request.getParameter("bookId");
		Integer bookIdInt = Integer.parseInt(bookid);
		
		CookbookService service = ServiceFactoryImpl.getInstance().getReceipeProcedureService();
		List<Map<String, Object>> RecipeAll = service.findRecipeContents(bookIdInt);
		request.setAttribute("RecipeAll", RecipeAll);

			request.getRequestDispatcher("/WEB-INF/views/reciepe/recipeDetail.jsp").forward(request, response);
		
	}


}
