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
import namoo.yorizori.common.web.Page;
import namoo.yorizori.common.web.Params;
import namoo.yorizori.cookbook.service.CookbookService;

/**
 * 요리책 등록 화면 및 등록 처리 서블릿 컨트롤러
 */
@WebServlet("/cookbook/list.do")
public class CookbookListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 등록 화면 처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		if (type == null) {
			type = "";
		}
		
		String value = request.getParameter("value");
		if (value == null) {
			value = "";
		}
		/**
		 * Default로 값을 정해놓는다.
		 */
		//한페이지당 3개씩 보여주겠다.
		int pageSize = 3;
		//페이지 번호 5개씩~
		int pageCount = 3;
		//요청 페이지 - default값으로 1번째 페이지 보여주겠다.
		int requestPage = 1;
		
		String size = request.getParameter("size");
		
		//size != null라는 건, 사용자가 요청한 사이즈가 있다는 것. 그래서 pageSize값을 변경
		if (size != null) {
			pageSize = Integer.parseInt(size);
		}
		
		//몇번 페이지를 보여줄것이냐
		String selectPage = request.getParameter("page");
		if (selectPage != null) {
			requestPage = Integer.parseInt(selectPage);
		}
		
		Params params = new Params(type, value, pageSize, pageCount, requestPage);
		
		CookbookService service = ServiceFactoryImpl.getInstance().getCookbookService();
		
		List<Map<String, Object>> list = service.findAllBySearchOption(params);
		
		request.setAttribute("CookbookList", list);

		
		//전체 개수를 JSP에서 보여주기 위해서 set함
		int count = service.searchCount(type, value);
		request.setAttribute("count", count);;
		
		
		Page paging = new Page(params, count);
		paging.build();
		//JSP로 paging을 전달해서 paging처리할 수 있도록
		request.setAttribute("paging", paging);	
		
		
		request.getRequestDispatcher("/WEB-INF/views/cookbook/cookbookList.jsp").forward(request, response);

	}
	


}









