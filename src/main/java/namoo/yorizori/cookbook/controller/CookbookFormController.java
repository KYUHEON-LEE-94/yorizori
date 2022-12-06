package namoo.yorizori.cookbook.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import namoo.yorizori.common.factory.ServiceFactoryImpl;
import namoo.yorizori.common.web.YzRuntimeException;
import namoo.yorizori.cookbook.dto.Cookbook;
import namoo.yorizori.cookbook.service.CookbookService;

/**
 * 요리책 등록 화면 및 등록 처리 서블릿 컨트롤러
 */
@WebServlet("/cookbook/register.do")
public class CookbookFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 등록 화면 처리
		//저장된 쿠키를 읽어서, 로그인이 되어있을 경우에만 등록 가능
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		String loginId = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase("loginId")) {
					loginId = cookie.getValue();
				}
			}
		}

		if (loginId == null) {
			throw new YzRuntimeException("요리책 등록하려면 로그인이 필요합니다.", "/user/loginform.do");
		} else {
			request.getRequestDispatcher("/WEB-INF/views/cookbook/cookbookForm.jsp").forward(request, response);
		}
	}

	// 등록 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String bookName = request.getParameter("bookname");
		String authorId = request.getParameter("authorid");
		String bookDesc = request.getParameter("bookdesc");
		
		Cookbook cookbook = new Cookbook();
		cookbook.setBookName(bookName);
		cookbook.setAuthorId(authorId);
		cookbook.setBookDesc(bookDesc);
				
		CookbookService service = ServiceFactoryImpl.getInstance().getCookbookService();
		service.registerCookbook(cookbook);
		
		// 임시 메인으로
		response.sendRedirect("/cookbook/indexYorizori.do");
	}
}









