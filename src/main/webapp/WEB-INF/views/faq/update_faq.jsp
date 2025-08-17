<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>

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
<jsp:include page="/common/header.jsp"/>
<div class="page mt3">
    <form id="addForm" name="addForm" method="post">
        <%-- TODO: csrf 인증 토큰(중요): 안하면 로그인페이지로 redirect 됨 --%>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    	<input type="hidden" name="fno" value="<c:out value="${faq.fno}" />">
        <div class="mb3">
            <label for="title" class="form-label">title</label>
            <input type="text"
                   class="form-control"
                   id="title"
                   name="title"
                   value="<c:out value="${faq.title}" />"
                   placeholder="title" />
        </div>
        <div class="mb3">
            <label for="content" class="form-label">content</label>
            <input type="text"
                   class="form-control"
                   id="content"
                   name="content"
                   value="<c:out value="${faq.content}" />"
                   placeholder="content" />
        </div>
        <div class="mb3">
            <button type="button"
                    class="btn btn-warning"
                    onclick="fn_save()"
            >수정</button>

            <button type="button"
                    class="btn btn-danger"
                    onclick="fn_delete()"
            >삭제</button>
        </div>
    </form>
</div>
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<!-- 부트스트랩 js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<!-- 유효성체크 라이브러리 -->
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.21.0/dist/jquery.validate.min.js"></script>
<!-- 페이징 라이브러리 -->
<script src="/js/jquery.twbsPagination.js" type="text/javascript"></script>

<script src="/js/faq/faq-validation-config.js"></script>
<script type="text/javascript" defer="defer">
  	function fn_save() {
		$("#addForm").attr("action",'/faq/edit')
					.submit();
	}
	function fn_delete() {
		$("#addForm").attr("action",'/faq/delete')
						.submit();
	}
</script>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>
