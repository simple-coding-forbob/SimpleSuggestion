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
<!-- 업로드 시 추가(첨부파일이라는 요청): enctype="multipart/form-data" -->
<!-- 스프링 업로드 파일 제한(기본): 1M -> 10M -->
	<form id="addForm"
	      name="addForm"
	      method="post"
	      enctype="multipart/form-data"
		  action="/fileDb/add"
	      >
		<%-- TODO: csrf 인증 토큰(중요): 안하면 로그인페이지로 redirect 됨 --%>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="mb3">
            <label for="fileTitle" class="form-label">fileTitle</label>
            <input  
            		class="form-control"
            		id="fileTitle"
            		name="fileTitle"
            		placeholder="제목" />
        </div>
        <div class="mb3">
            <label for="fileContent" class="form-label">fileContent</label>
            <input type="text"
                   class="form-control"
            	   id="fileContent"
            	   name="fileContent"                   
                   placeholder="내용입력" />
        </div>
		<div class="input-group">
		<!-- type="file": 파일대화상자가 화면에 보입니다. -->
		  <input type="file" 
		         class="form-control" 
            	 id="image"
            	 name="image" 		         
		         >
		  <button class="btn btn-primary" 
		          type="submit"
		          >저장</button>
		</div>
    </form>
</div>
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<!-- 부트스트랩 js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<!-- 유효성체크 플러그인 -->
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.21.0/dist/jquery.validate.min.js"></script>
<script src="/js/fileDb/fileDb-validation-config.js"></script>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>
