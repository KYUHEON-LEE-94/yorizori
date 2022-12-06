package namoo.yorizori.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import namoo.yorizori.common.factory.ServiceFactoryImpl;
import namoo.yorizori.user.dto.User;
import namoo.yorizori.user.service.UserService;

/**
 * 회원 로그인 관련 브라우저 요청 처리 서블릿 컨트롤러
 */
@WebServlet("/user/loginform.do")
public class LoginFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 로그아웃 처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
	}
}
