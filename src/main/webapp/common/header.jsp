<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">
		<img src="<c:url value='/images/simple-coding.png'/>" width="20" height="20" />
    	simple-coding 
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/">Home</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            부서
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="<c:url value='/dept'/>">Dept</a></li>
            <li><a class="dropdown-item" href="<c:url value='/dept/addition'/>">Add Dept</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            사원(퀴즈)
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="<c:url value='/emp'/>">Emp</a></li>
            <li><a class="dropdown-item" href="<c:url value='/emp/addition'/>">Add Emp</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Faq(마무리퀴즈)
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="<c:url value='/faq'/>">Faq</a></li>
            <li><a class="dropdown-item" href="<c:url value='/faq/addition'/>">Add Faq</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            fileDb
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="<c:url value='/fileDb'/>">fileDb</a></li>
            <li><a class="dropdown-item" href="<c:url value='/fileDb/addition'/>">Add fileDb</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            gallery
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="<c:url value='/gallery'/>">gallery</a></li>
            <li><a class="dropdown-item" href="<c:url value='/gallery/addition'/>">Add gallery</a></li>
          </ul>
        </li>
      </ul>

      <!-- 메뉴(오른쪽) -->
      <ul class="navbar-nav">
        <li>
          <!-- 통합검색: 퀴즈  -->
          <form id="lookupForm" name="lookupForm"  class="d-flex mr2" action="/lookup" method="get">
            <div class="input-group mb-3">
              <input type="text"
                     class="form-control"
                     id="totalKeyword"
                     name="totalKeyword"
                     placeholder="FAQ/QNA 통합검색">
              <button type="submit" class="btn btn-success">검색</button>
            </div>
          </form>
        </li>
        <li>
         <!-- 통합검색  -->
          <form id="searchForm" name="searchForm"  class="d-flex" action="/search" method="get">
            <div class="input-group mb-3">
              <input type="text"
                    class="form-control"
                    id="totalKeyword"
                    name="totalKeyword"
                    placeholder="부서/사원 통합검색">
              <button type="submit" class="btn btn-success">검색</button>
            </div>
          </form>
        </li>
        <!-- {/* TODO: 로그인 시작 */} -->
        <!-- memberVO 가 세션에 없으면 메뉴을 보이고, 있으면 안보임 -->
        <sec:authorize access="hasAuthority('ROLE_ADMIN')">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/admin/register">회원가입(권한등록)</a>
          </li>
        </sec:authorize>

        <sec:authorize access="isAnonymous()">
        	<li class="nav-item">
	          <a class="nav-link active" href="/auth/register"> 회원가입 </a>
	        </li>
	        <li class="nav-item">
	           <a class="nav-link active" href="/auth/login"> 로그인 </a>
	        </li>
        </sec:authorize>

        <!-- {/* 로그인 끝 */} -->

        <!-- {/* 로그아웃 시작 */} -->
        <!-- memberVO 가 세션에 있으면 메뉴을 보이고, 없으면 안보임 -->
        <sec:authorize access="isAuthenticated()">
            <li class="nav-item">
              <a class="nav-link active" href="<c:url value='/mypage'/>"> 마이페이지 </a>
            </li>
	         <li class="nav-item">
               <form id="logoutForm" name="logoutForm" action="/auth/logout" method="post">
                 <button type="submit" class="btn">Logout</button>
                   <%--  TODO: csrf 보안 토큰 : 해킹 방지 토큰 --%>
                 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
               </form>
	           </a>
	         </li>
        </sec:authorize>
        <!-- {/* 로그아웃 끝 */} -->
      </ul>
    </div>
  </div>
</nav>
