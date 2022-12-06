<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					레시피 목록 <a href="/recipe/register.do?bookId=${bookId}"
						class="btn btn-md btn-warning">레시피 등록</a>
				</div>
				<input type='hidden' name='bookId' value='${bookId}'>
			</div>
			<div class="row" style="height: 15px">
				<p class="col">
					<span class="fs-4 fw-bold text-warning bg-dark align-middle">
						[한식 요리책 ] </span> 한식 요리를 맛있게 만드는 방법을 설명합니다.
				</p>
			</div>
		</div>

		<div class="container px-4 px-lg-5 mt-5">
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<c:choose>
					<c:when test="${count != 0}">
						<c:forEach items="${RecipeList}" var="RecipeList">
							<!-- book details-->
							<div class="col mb-5">
								<div class="card h-70">
									<img class="card-img-top"
										src="../assets/${RecipeList.imgFileName}"
										alt="${RecipeList.imgFileName}" />
									<!-- 레시피 details-->
									<div class="card-body p-4">
										<div class="text-center">
											<!-- 레시피 name-->
											<h5 class="fw-bolder">${RecipeList.recipeName}</h5>
											<!-- 요리시간, 난이도 -->
											<c:choose>
												<c:when test="${RecipeList.recipeLevel == 1}">
													<td>${RecipeList.recipeTime}분 / 난이도: 하</td>
												</c:when>
												<c:when test="${RecipeList.recipeLevel == 2}">
													<td>${RecipeList.recipeTime}분 / 난이도:중</td>
												</c:when>
												<c:when test="${RecipeList.recipeLevel == 3}">
													<td>${RecipeList.recipeTime}분 / 난이도:상</td>
												</c:when>
											</c:choose>
										</div>
										<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
											<div class="text-center">
												<a class="btn btn-md btn-warning"
													href="/recipe/detail.do?bookId=${RecipeList.bookId}">요리책
													상세보기 </a>
											</div>
										</div>
									</div>
									<!-- Product actions-->
								</div>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div>
							<h2 style="color: red; text-align: center">목록이 존재하지 않습니다.</h2>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="pagination">
				<c:if test="${paging.isShowFirst()}">
					<a href="${paging.getQueryString(1)}">처음으로</a>
				</c:if>
				<c:if test="${paging.isShowPrevious()}">
					<a href="${paging.getQueryString(paging.getPreviousStartPage())}">
						> </a>
				</c:if>

				<c:forEach var="j" begin="${paging.getStartPage()}"
					end="${paging.getEndPage()}" step="1">

					<c:choose>
						<c:when test="${j == params.getRequestPage() }">
							<a class='active'>${j}</a>
						</c:when>
						<c:otherwise>
							<a href="${paging.getQueryString(j) }">${j}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${paging.isShowNext()}">
					<a href="${paging.getQueryString(paging.getNextStartPage())}">
						> </a>
				</c:if>
				<c:if test="${paging.isShowLast() }">
					<a href="${paging.getQueryString(paging.getPageCount())} %>">끝으로</a>
				</c:if>
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
