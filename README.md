# Yorizori

✨## 프로젝트 목표
1. Singleton패턴이란 무엇인가, 웹 설계는 어떠한 흐름으로 진행되는 가에 대해서 체험해보는 프로젝트
2. JSP, Servlet을 활용함으로써 백엔드가 하는 일을 본격적으로 체험해보기
3. JSP, Servlet을 이해함으로서 나중에 배우게될 Mybatis와 Springboot를 왜 사용해야하는 가를 알 수 있게 해줌

### ERD
[데이터 모델링](https://www.erdcloud.com/d/k2rWQmg4RzCMGrLyj)

### 사용된 스킬  
프론트 엔드: 부트스트랩  
백엔드: JSP, Servlet, JDBC

## 기능
기능 번호|주요 기능|설명|
---|---|---|
기능1|메인화면에 리스트 보여주기|최신날짜 순서로 메인화면에 요리책을 나열합니다.|
기능2|로그인|아이디 저장 기능과 함께 로그인 로그아웃 기능을 구현합니다.|
기능3|요리책 등록|회원은 요리책을 등록할 수 있습니다.|
기능4|요리책 목록|회원이 아니어도 현재 웹에 등록되어 있는 요리책 목록을 볼 수 있습니다.|
기능5|레시피 목록|각 요리책에 등록되어있는 레시피들의 목록들을 볼 수 있습니다.|
기능6|레시피 상세보기|등록되어 있는 레시피의 내용을 상세하게 봅니다.|

## 화면  
### Main 화면  

![Main](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/d433f189-3cb2-4eba-8a85-fdacbd5e23be/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221206%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221206T110520Z&X-Amz-Expires=86400&X-Amz-Signature=7ffa713cd3af34cd3df1b12aed712980a40fe7df2d3c28c00fb1805070f70e48&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Untitled.png%22&x-id=GetObject)
> +기능1의 내용과 같이 최신 날짜 순서대로 나열한 모습  

### 요리책 등록 화면  
![요리책 등록](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/2478b20a-ab3d-4e24-bd6c-ff9cc5a685f9/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221206%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221206T110922Z&X-Amz-Expires=86400&X-Amz-Signature=fe0bd0c09db7a64acd1307129e066a04dadfb75ab811e43549b210be61725279&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Untitled.png%22&x-id=GetObject)
> 로그인한 사람만 등록이 가능한 페이지  

### 요리책 목록 화면    
![요리책 목록](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/3ef21f8b-c02d-4830-91bd-430cef7be953/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221206%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221206T110752Z&X-Amz-Expires=86400&X-Amz-Signature=9414550cfd08cc56378eefab62e4867eb3ce4c525bcf022470ab87e1c80689c4&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Untitled.png%22&x-id=GetObject)
>등록되어잇는 요리책 리스트를 보여줍니다.
이 화면에서는 새로운 레시피 내용을 등록 또는 요리책에 등록되어 있는 레시피 총 목록도 확인 가능합니다.  

### 레시피 목록 조회  
![레시피 목록](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/ddff5fd0-f747-4fe5-af07-47c92c542655/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221206%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221206T110847Z&X-Amz-Expires=86400&X-Amz-Signature=688d21900cfb533534811b6465ed28bfce792dd6e6b1ad934c7f5c4c39d1b2a9&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Untitled.png%22&x-id=GetObject)
>요리책에 등록되어 있는 레시피 리스트들을 보여줍니다.  

### 레시피 상세 화면    
![레시피 상세](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/fd0f07de-370e-49d4-8d92-d6b637933dae/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221206%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221206T111003Z&X-Amz-Expires=86400&X-Amz-Signature=7b4acd551c8f738383ebc5eec517282f118b0b4a6acb47ef7710d5a663c17e06&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Untitled.png%22&x-id=GetObject)


## 🤔어려웠던 점

+ 쿠키 사용법

```java
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
			response.sendRedirect("/");

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
```
**쿠키와 세션의 차이점이 무엇인지, 어떻게 사용해야할지를 알게해주는 코드**
>쿠키를 이용해서 웹에 저장해두고, 필요할때마다 꺼내서 사용하는 로직을 배움
추가적으로, Session을 이용해서 비밀번호를 저장해두는 방법도 응용해서 사용해 봄.
→ session을 사용하는 이유는 보안을 위해서!
쿠키를 사용할 경우 브라우저에서 쉽게 확인이 가능하기 때문에 서버에 저장하는 session방법으로 패스워드를 저장함
[보안을 위해서는 cookie를 사용하지 않는게 좋지만, 쿠키와 세션의 차이를 알기 위해서 적극 사용해봄]-

+로그인에 따른 화면 표시
```html
<c:choose>
				<c:when test="${not empty cookie.loginid}">
					<div class="col-4">
						${cookie.loginid.value}님 환영합니다.
						<button type="button" class="btn btn-danger"
							onclick="location.href='/user/login.do'">로그아웃</button>
					</div>
				</c:when>
				<c:when test="${empty cookie.loginid}">
					<c:if test="${empty cookie.saveid}">
						<form class="row row-cols-lg-auto g-3 align-items-center"
							method="post" action="/user/login.do">
							<div class="col-12">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" id="saveid"
										name="saveid" value="save"> <label
										class="form-check-label" for="saveid">아이디저장</label>
								</div>
							</div>
							<div class="col-12">
								<input type="text" size="8" maxlength="8" class="form-control"
									id="id" name="id" placeholder="아이디">
							</div>
							<div class="col-12">
								<input type="password" size="8" maxlength="8"
									class="form-control" id="passwd" name="passwd"
									placeholder="비밀번호">
							</div>
```
> <로그인에 성공한 경우> / <로그인이 안된 경우>
라고 하는 로직을 생각하는데 시간이 생각보다 오래걸림.
+) saveId 라고 하는 쿠키도 추가 적용을 해야했기 때문에 많은 시간이 소요됬던 부분

+ 3개의 테이블을 조인했어야 했던 SQL문
```SQL
SELECT id,book_name, book_id, recipename, recipetime, recipelevel, book_desc, imgfilename, regdate
            FROM ( SELECT CEIL(rownum / 8), s.id,s.book_name, s.book_id, s.recipename, s.recipetime, s.recipelevel, s.book_desc, s.imgfilename, s.regdate
                   FROM   (SELECT u.id,c.book_name, c.book_id, r.recipename, r.recipetime, r.recipelevel, c.book_desc, r.imgfilename, u.regdate
                           FROM cookbook c
                                    JOIN recipe r ON c.book_id = r.bookid
                                    JOIN users u ON r.writerid = u.id)s
            ORDER BY regdate)
```
> User 테이블의 유저이름, Cookbook 테이블의 쿡북아이디와 이름, Recipe테이블의 파일명과 난이도 등을 조회하기 위한 SQL문을 처음 만들려고 했을 때, 이 프로젝트에서 가장 긴 시간을 소요했었습니다.
2개의 테이블을 조인하는건 어렵지 않았지만 테이블 3개로 원하는 결과를 얻기 위해서 생각해야하는 SQL문은 생각보다 어려웠습니다.

+ JS로 코드로 하는 DOM 컨트롤
```JS
/**
 * recipeForm 추가 버튼을 누르면 추가하는 기능
 */

let tr = document.querySelectorAll('.seq_blank');
let btnAdd = document.querySelector('.btnAdd');

function addTr() {
    let clone = tr[0].cloneNode(true);
    clone.value = '';
    tr[0].parentElement.appendChild(clone);
}

btnAdd.addEventListener('click', () => {
    addTr();
})
```
> 레시피 등록단계에서 사용자의 니즈에 맞게 버튼을 누를때마다 한칸씩 늘려주는 DON 컨트롤을 해야하는데, 생각보다 어려웠던 기억이 있습니다.

## 피드백 받은 점
1. 프로젝트 진행하는 기간중에 REST API의 개념에 대해 배웠음에도 불구하고, 해당 프로젝트에 REST API를 적용하려는 시도를 하지 못했다는 점
예) bookId 파라미터를 받을때 대문자를 사용한점.

2. bookid를 getparameter로 받아서 int형으로 형변환 해서 사용할때 exception이 떨어진다. 그때의 나는 try~catch로 잡을려고 했다.
 → 선생님의 피드백은 이런 오류는 개발자의 실수로 발생하는 오류이므로, try-catch가 아닌 if로 조건을 붙이고, 그 조건에 부합하지 않는 경우가 발생할때 throw new 로 자기가 만든 exception을 발생시키는 것이 맞다. 라고 피드백해주셨다.

```java
//피드백을 받고 수정된 코드
int bookInt, recipeTimeint, recipeLevelint;
		if(bookId != null) {
			recipeTimeint = Integer.parseInt(recipeTime);	
			recipeLevelint = Integer.parseInt(recipeLevel);
			bookInt = Integer.parseInt(bookId);
		}else {
			throw new YzRuntimeException("다시 한번 확인해주세요");
		}
```

## 아쉬운 점
1. 데이터 모델링이 이미 완성되어 있는 상태에서 시작되었던 프로젝트였기 때문에 SQL을 직접 작성하는데 큰 어려움은 없었습니다. 
그러나, 데이터 모델링부터 한번 해봤으면 더 많은 것을 배울 수 있지 않았을까 싶습니다.
2. JDBC의 특성때문인지, SQL문 실행을 위해서는 너무많은 중복코드가 필요했습니다.
나름 프로젝트를 하면서 다른 방법이 없을까 고민해봤지만, 따로 방법이 떠오르지는 않았습니다.


