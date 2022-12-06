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
<link href="/css/styles.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/pagination.css">
</head>
<body>
	<!-- Navigation-->
	<jsp:include page="/WEB-INF/views/Include/nav.jsp" />
	<!-- Navigation End-->
	<!-- Section-->
	<section class="py-5">

		<div class="container px-4 px-lg-5 mt-5">
			<div class="row">
				<div class="col h2">
					요리~조리~ <a href="/cookbook/register.do"
						class="btn btn-md btn-primary">요리책 등록</a>
				</div>
			</div>
			<div class="row" style="height: 15px">
				<p class="col">
					요리조리는 함께 참여하여 만들어가는 요리책 서비스 입니다.<br> 요리조리와 함께 나만의 레시피를 요리
					매니아들과 공유해 보세요.
				</p>
			</div>
		</div>


		<div class="container px-4 px-lg-5 mt-5">
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<!-- 요리책 목록 -->
				<c:choose>
					<c:when test="${count != 0}">
						<c:forEach items="${CookbookList}" var="CookbookList">		
							<!-- book details-->
							<div class="col mb-5">
								<div class="card h-70">
									<div class="card-body p-4">
										<div class="text-center">
											<!-- book name-->
											<h5 class="fw-bolder">${CookbookList.bookName}</h5>
											<!-- author-->
											- ${CookbookList.authorName} - <br>
										</div>
									</div>
									<!-- Product actions-->
									<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
										<div class="text-center">
											<a class="btn btn-outline-dark mt-auto"
												href="/recipe/recipeList.do?bookId=${CookbookList.bookId}">레시피
												목록조회</a>
											<a class="btn btn-danger" style="margin-top:10px" href="/recipe/register.do?bookId=${CookbookList.bookId}">레시피
												등록</a>
										</div>
									</div>
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
					end="${paging.getEndPage()}">
					<c:choose>
						<c:when test="${j == params.getRequestPage() }">
							<a class='active'>${j}</a>
						</c:when>
						<c:otherwise>
							<a href="${paging.getQueryString(j)}">${j}</a>
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