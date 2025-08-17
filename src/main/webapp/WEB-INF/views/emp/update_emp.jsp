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
<div class="container">
    <form id="addForm" name="addForm" method="post">
        <%-- TODO: csrf 인증 토큰(중요): 안하면 로그인페이지로 redirect 됨 --%>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <input type="hidden" name="eno" value="<c:out value='${emp.eno}' />">
        <div class="mb3">
            <label for="ename" class="form-label">ename</label>
            <input type="text"
                   class="form-control"
                   id="ename"
            	   name="ename"
            	   value="<c:out value='${emp.ename}' />"                   
                   placeholder="ename" />
        </div>
        <div class="mb3">
            <label for="job" class="form-label">job</label>
            <input type="text"
                   class="form-control"
                   id="job"
            	   name="job"
            	   value="<c:out value='${emp.job}' />"                     
                   placeholder="job" />
        </div>
        <div class="mb3">
            <label for="manager" class="form-label">manager</label>
            <input type="text"
                   class="form-control"
                   id="manager"
            	   name="manager"
            	   value="<c:out value='${emp.manager}' />"                        
                   placeholder="manager" />
        </div>
        <div class="mb3">
            <label for="hiredate" class="form-label">hiredate</label>
            <input type="date"
                   class="form-control"   
                   id="hiredate"
            	   name="hiredate"
            	   value="<c:out value='${emp.hiredate}' />"                      
                   placeholder="hiredate" />
        </div>
        <div class="mb3">
            <label for="salary" class="form-label">salary</label>
            <input type="text"
                   class="form-control"
                   id="salary"
            	   name="salary"
            	   value="<c:out value='${emp.salary}' />"                    
                   placeholder="salary" />
        </div>
        <div class="mb3">
            <label for="commission" class="form-label">commission</label>
            <input type="text"
                   class="form-control"
                   id="commission"
            	   name="commission"
            	   value="<c:out value='${emp.commission}' />"                    
                   placeholder="commission" />
        </div>
        <div class="mb3">
            <label for="dno" class="form-label">dno</label>
            <input type="text"
                   class="form-control"
                   id="dno"
            	   name="dno"
            	   value="<c:out value='${emp.dno}' />"            
                   placeholder="dno" />
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

<script type="text/javascript">
	function fn_save() {
		/* 저장 함수: 저장 URL(/emp/edit) */
		$("#addForm").attr("action",'/emp/edit')
		.submit();
	}
	/* 삭제: /emp/delete */
	function fn_delete() {
		$("#addForm").attr("action",'/emp/delete')
		.submit();
	}
</script>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>
