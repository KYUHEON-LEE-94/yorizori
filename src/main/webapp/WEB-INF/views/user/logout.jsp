<%@page import="java.net.URLEncoder"%>
<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%
Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(Cookie cookie:cookies){
		String name = cookie.getName();
		if(name.equalsIgnoreCase("loginId")){
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}
	response.sendRedirect("/");
}

%>



