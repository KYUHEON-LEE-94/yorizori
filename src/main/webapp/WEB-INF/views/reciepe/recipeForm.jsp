<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
	<!DOCTYPE html>
	<html lang="ko">

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
		<title>요리조리 요리책</title>
		<!-- Favicon-->
		<link rel="icon" type="image/x-icon" href="includes/favicon.ico" />
		<!-- Bootstrap icons-->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
		<!-- Core theme CSS (includes Bootstrap)-->
		<link href="/css/styles.css" rel="stylesheet" />
	</head>

	<body>
		<!-- Navigation-->
		<jsp:include page="/WEB-INF/views/Include/nav.jsp" />
		<!-- Navigation End-->
		<!-- Section-->
		<section class="py-5">
			<div class="container">
				<div class="row">
					<div class="col-lg-9">
						<div class="col-auto h2">레시피 등록</div>
						<!-- recipe Form -->
						<form class="row g-3" action="/recipe/register.do" method="post" enctype="multipart/form-data">
							<div class="col-md-6">
								<label for="recipe_name" class="form-label">레시피명</label> <input type="text"
									class="form-control" id="recipe_name" name="recipe_name">
							</div>
							<div class="col-md-6">
								<label for="writer_name" class="form-label">등록정보</label> <input type="text"
									class="form-control" id="user_id" value="${cookie.loginid.value}" disabled> <input
									type="hidden" value="${cookie.loginid.value}" name="user_id">

								<label for="writer_name" class="form-label">쿡북코드</label>
								<input type="text" class="form-control" id="bookId" value="${bookId}" disabled>
								<input type="hidden" value="${bookId}" name="bookId">
							</div>
							<div class="col-md-6">
								<label for="recipe_time" class="form-label">조리시간</label>
								<input type="number" class="form-control" id="recipe_time" name="recipe_time">
							</div>
							<div class="col-md-4">
								<label for="recipe_level" class="form-label">난이도</label> <select id="recipe_level"
									class="form-select" name="recipe_level">
									<option value="3" selected>상</option>
									<option value="2">중</option>
									<option value="1">하</option>
								</select>
							</div>

							<div class="col-12">
								<label for="ingredients" class="form-label">재료</label>
								<textarea class="form-control" rows="5" id="ingredients" name="ingredients"
									placeholder="ex) 파,물, 된장 .."></textarea>
							</div>

							<div class="col-12" id="recipe_sequence">
								<label for="seq_num1" class="form-label">조리절차</label>
								<input type="text" class="form-control seq_blank seq_blank" id="seq_num" name="seq_num">
								<input type="text" class="form-control seq_blank seq_blank" id="seq_num" name="seq_num">
							</div>
							<input type="button" class="btm btnAdd btn-info btn-sm" value="추가">

							<div class="col-12">
								<label for="img_file_name" class="form-label">조리사진</label> <input type="file"
									class="form-control" id="img_file_name" name="img_file_name">
							</div>

							<div class="col-12">
								<button type="submit" class="btn btn-primary"> 등록</button>
								<a href="" class="btn btn-success">목록</a>
							</div>
						</form>


					</div>
				</div>

			</div>
		</section>
		<!-- Footer-->
		<jsp:include page="/WEB-INF/views/Include/footer.jsp" />
		<!-- Footer End-->
		<!-- Bootstrap core JS-->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
		<script src="/js/recipeForm.js" defer></script>
	</body>

	</html>