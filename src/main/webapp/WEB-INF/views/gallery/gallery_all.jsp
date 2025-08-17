<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<title>Title</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 	부트스트랩 css  -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<!-- 	개발자 css -->
	<link rel="stylesheet" href="/css/style.css">


</head>
<body>
	<jsp:include page="/common/header.jsp" />
	<form class="page mt3" id="listForm" name="listForm" method="get">
		<%-- TODO: csrf 인증 토큰(중요): 안하면 로그인페이지로 redirect 됨 --%>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<!-- 수정페이지 열기때문에 필요 -->
	    <input type="hidden" id="uuid" name="uuid">
		<!-- TODO: 컨트롤러로 보낼 페이지번호 -->
		<input type="hidden" id="page" name="page" value="0">
		<div class="input-group mb3 mt3">
			<input type="text" 
			       class="form-control"
			       id="searchKeyword"
		           name="searchKeyword"
				   value="${param.searchKeyword}"
				placeholder="검색어입력"
				>
			<button class="btn btn-primary" 
			        type="button"
			        onclick="fn_egov_selectList(0)"
			        >검색</button>
		</div>
		<!-- 카드 디자인: 반복문 -->
		<c:forEach var="data" items="${gallerys}">
		   <div class="col3">
				<div class="card">
					<img src="<c:out value='${data.galleryFileUrl}' />" class="card-img-top"
						alt="이미지">
					<div class="card-body">
						<h5 class="card-title"><c:out value='${data.galleryTitle}' /></h5>
						<a href="#" class="btn btn-danger"
							onclick="fn_delete('${data.uuid}')"
						>삭제</a>
                        <a href="#" class="btn btn-warning"
                            onclick="fn_like('<sec:authentication property="name"/>','${data.uuid}')"
                        >좋아요</a>
					</div>
				</div>
		   </div>
		</c:forEach>
		<c:if test="${empty gallerys}">
			데이터가 없습니다.
		</c:if>
		<!-- 여기: 페이지번호 -->
		<div class="flex-center clear">
			<ul class="pagination" id="pagination"></ul>
		</div>
	</form>
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<!-- 부트스트랩 js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<!-- 페이징 라이브러리 -->
<script src="/js/jquery.twbsPagination.js" type="text/javascript"></script>


<script type="text/javascript">
	/* 전체조회 */
	function fn_egov_selectList(page) {
		 $("#page").val(page);    // 현재페이지: 벡엔드로 보낼때 첫페이지는 0입니다.
		$("#listForm").attr("action",'/gallery')
					.submit();
	}
    /* 삭제: /gallery/delete */
    function fn_delete(uuid) {
        /* 전체조회: method="get" -> "post" 변경해서 전달 */
        $("#uuid").val(uuid);
        $("#listForm").attr("action",'/gallery/delete')
                      .attr("method","post")
        .submit();
    }
    function fn_like(email, uuid) {
        $.ajax({
            url: "/api/gallery/likes/add",
            method: "POST",
            data: {
                email: email,
                uuid: uuid,
                likeCount:1,
                '${_csrf.parameterName}': '${_csrf.token}' // CSRF 토큰을 폼 데이터에 포함
            },
            success: function(response) {
                console.log("좋아요를 눌렀습니다.");
                alert("좋아요를 눌렀습니다.")
            },
            error: function(xhr) {
                console.error("에러", xhr.status, xhr.responseText);
                alert(xhr.responseText);
            }
        });
    }
</script>

<script type="text/javascript">
	/* 페이징 처리 */
	$('#pagination').twbsPagination({
		totalPages: ${pages.totalPages},
		startPage:${pages.number+1},            // 현재페이지: 화면에 표시할때는 +1 해서 보입니다.
		visiblePages: ${pages.size},
		initiateStartPageClick: false,
		onPageClick: function (event, page) {
			/* 재조회 함수 실행 */
			fn_egov_selectList(page-1);           // 현재페이지: 벡엔드로 보낼때는 -1 해서 보냅니다.
		}
	});
</script>

<jsp:include page="/common/footer.jsp" />

</body>
</html>
