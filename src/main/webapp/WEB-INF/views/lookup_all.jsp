<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 목록</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 	부트스트랩 css  -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<!-- 	개발자 css -->
	<link rel="stylesheet" href="/css/style.css">
</head>
<body>
<jsp:include page="/common/header.jsp"></jsp:include>
<div class="page mt3">
	<form id="listForm" name="listForm" method="get" action="/search">
		<!-- TODO: 컨트롤러로 보낼 페이지번호 -->
		<input type="hidden" id="page" name="page" value="0">
        <h1 class="mt5 mb5">통합검색 결과</h1>
        <!-- 반복문 -->
            <ul class="list-group">
              <c:forEach var="data" items="${lookups}">
                  <li class="list-group-item active" aria-current="true"><c:out value="${data.type}"></c:out></li>
                  <c:if test="${not empty data.title}">
                    <li class="list-group-item"><c:out value="${data.title}"></c:out></li>
                  </c:if>
                  <c:if test="${not empty data.content}">
                    <li class="list-group-item"><c:out value="${data.content}"></c:out></li>
                  </c:if>
                  <c:if test="${not empty data.question}">
                    <li class="list-group-item"><c:out value="${data.question}"></c:out></li>
                  </c:if>
                  <c:if test="${not empty data.answer}">
                    <li class="list-group-item"><c:out value="${data.answer}"></c:out></li>
                  </c:if>
              </c:forEach>
            </ul>
		<c:if test="${empty lookups}">
			데이터가 없습니다.
		</c:if>
		<!-- 여기: 페이지번호 -->
		<div class="flex-center mt5">
			<ul class="pagination" id="pagination"></ul>
		</div>

	</form>

</div>
<!-- 꼬리말 -->
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<!-- 부트스트랩 js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<!-- TODO: 페이징 라이브러리(CDN 사용) -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.min.js"></script>

<script type="text/javascript">
	/* 페이징 처리 */
	$('#pagination').twbsPagination({
		totalPages: ${pages.totalPages},
		startPage:${pages.number+1},            // 현재페이지: 화면에 표시할때는 +1 해서 보입니다.
		visiblePages: ${pages.size},
		initiateStartPageClick: false,
		onPageClick: function (event, page) {
			$("#page").val(page-1);
			$("#listForm").submit();
		}
	});
</script>

<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>