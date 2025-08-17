<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Home</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 	부트스트랩 css  -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<!-- 	개발자 css -->
	<link rel="stylesheet" href="/css/style.css">

</head>
<body>
<jsp:include page="/common/header.jsp"></jsp:include>
<div class="page mt3">
		<form id="listForm" name="listForm" method="get" action="/emp">
		<!-- 수정페이지 열기때문에 필요 -->
	    <input type="hidden" id="eno" name="eno">
	    <!-- TODO: 컨트롤러로 보낼 페이지번호 -->
		<input type="hidden" id="page" name="page" value="0">
	    
		<div class="input-group mb3 mt3">
		  <input type="text" 
		         class="form-control" 
		         id="searchKeyword"
		         name="searchKeyword"
		         placeholder="사원명입력"
				 value="${param.searchKeyword}"
		  >
		  <button class="btn btn-primary"
		          type="submit"
		  >
		          검색
		  </button>
		</div>
	    <div id="result"></div>
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">eno</th>
		      <th scope="col">ename</th>
		      <th scope="col">job</th>
		      <th scope="col">manager</th>
		      <th scope="col">hiredate</th>
		      <th scope="col">salary</th>
		      <th scope="col">commission</th>
		      <th scope="col">dno</th>
		    </tr>
		  </thead>
		  <tbody>
			<!-- 반복문 -->
		  	<c:forEach var="data" items="${emps}">  	
		  		<tr>
		  		  <td>
		  		  	<a href="/emp/edition?eno=${data.eno}">
			          <c:out value="${data.eno}"></c:out>
			        </a>
		  		  </td>
			      <td><c:out value="${data.ename}"></c:out></td>
			      <td><c:out value="${data.job}"></c:out></td>
			      <td><c:out value="${data.manager}"></c:out></td>
			      <td><c:out value="${data.hiredate}"></c:out></td>
			      <td><c:out value="${data.salary}"></c:out></td>
			      <td><c:out value="${data.commission}"></c:out></td>
			      <td><c:out value="${data.dno}"></c:out></td>
			    </tr>
		  	</c:forEach>

		  </tbody>
		</table>
		<c:if test="${empty emps}">
			데이터가 없습니다.
		</c:if>
		<!-- 페이지 번호 태그 -->
		<ul class="pagination" id="pagination"></ul>
	</form>
	
</div>
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<!-- 부트스트랩 js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<!-- 페이지 플로그인 추가 -->
<script src="/js/jquery.twbsPagination.js" type="text/javascript"></script>

<script type="text/javascript">
	/* 페이징 처리 */
	$('#pagination').twbsPagination({
		totalPages: ${pages.totalPages},
		startPage:${pages.number+1},            // 현재페이지: 화면에 표시할때는 +1 해서 보입니다.
		visiblePages: ${pages.size},
		initiateStartPageClick: false,
		onPageClick: function (event, page) {
			/* 재조회 */
			$("#page").val(page-1);
			$("#listForm").submit();
		}
	});

</script>


<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>
