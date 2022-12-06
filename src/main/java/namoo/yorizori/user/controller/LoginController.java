package namoo.yorizori.user.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import namoo.yorizori.common.factory.ServiceFactoryImpl;
import namoo.yorizori.user.dto.User;
import namoo.yorizori.user.service.UserService;

/**
 * 회원 관련 브라우저 요청 처리 서블릿 컨트롤러입니다.
 */
@WebServlet("/user/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 로그아웃 처리

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 쿠키들을 먼저 찾아온다.
		Cookie[] cookies = request.getCookies();
		// 쿠키들이 있다면!
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				// 쿠키의 이름을 가져와서 비교한다.
				String name = cookie.getName();
				if (name.equalsIgnoreCase("loginId")) {
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
			response.sendRedirect("/cookbook/indexYorizori.do");

		}
	}

	// 로그인 처리
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");

		String saveid = request.getParameter("saveid");

		UserService userSevService = ServiceFactoryImpl.getInstance().getUserService();
		User loginUser = userSevService.login(id, passwd);

		
		if (loginUser != null) {
			Cookie loginCookie = new Cookie("loginid", loginUser.getId());
			loginCookie.setPath("/");
			response.addCookie(loginCookie);
			
			HttpSession session = request.getSession();
			session.setAttribute("passwd", loginUser.getPasswd());
			String test =(String) session.getAttribute("passwd");
						
			if (saveid != null) {
				Cookie saveidCookie = new Cookie("saveid", loginUser.getId());
				saveidCookie.setPath("/");
				saveidCookie.setMaxAge(60 * 60 * 24 * 30);
				response.addCookie(saveidCookie);
				
			}
			response.sendRedirect("/cookbook/indexYorizori.do");
		} else {
			// 회원가입이 안되어있으면 로그인 화면으로 다시 유도
			response.sendRedirect("/user/loginform.do");
		}

	}

}
