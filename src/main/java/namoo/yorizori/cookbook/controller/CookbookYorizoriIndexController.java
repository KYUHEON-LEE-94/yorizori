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
import namoo.yorizori.cookbook.dto.Cookbook;
import namoo.yorizori.cookbook.service.CookbookService;

/**
 * 요리조리 메인사이트 서블릿
 */
@WebServlet("/cookbook/indexYorizori.do")
public class CookbookYorizoriIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 등록 화면 처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		CookbookService service = ServiceFactoryImpl.getInstance().getCookbookService();
		
		List<Map<String, Object>> list = service.MainIndexList();

		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/views/yorizori.jsp").forward(request, response);
		
	}
	



}









