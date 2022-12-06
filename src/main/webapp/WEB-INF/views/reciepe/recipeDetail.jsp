<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String bookId = request.getParameter("bookId"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>요리조리 요리책</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="includes/favicon.ico" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="/css/styles.css" rel="stylesheet" />
</head>
<body>
	<!-- Navigation-->
	<!-- Navigation-->
	<jsp:include page="/WEB-INF/views/Include/nav.jsp" />
	<!-- Navigation End-->

	<!-- Section-->
	<section>
		<div class="container px-4 px-lg-5 mt-5">
			<div class="row">
				<div class="col h2 fw-bold">
					레시피 상세 <a href="/recipe/register.do?bookId=<%=bookId %>" class="btn btn-md btn-warning">레시피 등록</a>
				</div>
			</div>
		</div>
		<div class="container px-4 px-lg-5">
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<!-- 레시피 상세 -->
				<table class="table table-bordered border-dark table-light">
				<c:if test="${not empty RecipeAll}">
						<!-- book details-->
						<tr>
							<td class="table-active">레시피명</td>
							<td>${RecipeAll[0].recipeName} </td>
							<td class="table-active">등록자</td>
							<td>${RecipeAll[0].writerId}</td>
							
						</tr>
						<tr>
							<td class="table-active">조리시간</td>
							<td>${RecipeAll[0].recipeTime}분</td>
							<td class="table-active">난이도</td>
							<c:choose>
							<c:when test="${RecipeAll[0].recipeLevel == 1}"><td>하</td></c:when>
							<c:when test="${RecipeAll[0].recipeLevel == 2}"><td>중</td></c:when>
							<c:when test="${RecipeAll[0].recipeLevel == 3}"><td>상</td></c:when>
							</c:choose>
						</tr>
						<tr>
							<td class="table-active">재료</td>
							<td colspan="3">${RecipeAll[0].ingredients}</td>
						</tr>
						<tr>
							<td class="table-active">조리절차</td>
							<td colspan="3">
						<c:forEach items="${RecipeAll}" var="RecipeAll" varStatus="status">
							${RecipeAll.seqNum}. ${RecipeAll.procedure}
						</c:forEach>				
							<br> 
							</td>			
						</tr>
						<tr>
							<td class="table-active">조리예</td>
							<td colspan="3">
								<div class="text-center">
									<img class="rounded img-thumbnail" style="width: 50rem"
										src="../assets/${RecipeAll[0].imgFileName}" alt="${RecipeAll[0].imgFileName}"/>
								</div>
							</td>
						</tr>
				</c:if>
				<c:if test="${empty RecipeAll}"><h2 style="color:red; width:100%">등록된 레시피가 없습니다.</h2></c:if>
				</table>
				
			

			</div>
		</div>




	</section>
	<!-- Footer-->
	<jsp:include page="/WEB-INF/views/Include/footer.jsp" />
	<!-- Footer End-->
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
</body>
</html>
