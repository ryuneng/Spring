<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/tags.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" ></script>
<title>bootstrap</title>
</head>
<body>
<%@ include file="../common/navbar.jsp" %>
<div class="container">
	<div class="row mb-3">
		<div class="col-12">
			<h1 class="fs-3">직원관리 - 직원정보수정</h1>
			
			<!-- 
				* 수정한 직원번호 넘겨주는 방법
				1. form의 action 속성의 값을 action="modify?no=${emp.no } 로 넘겨주기 (요청객체의 속성으로 만들어진 Employee 객체에 저장된 값 꺼내옴)
				2. form의 action 속성의 값을 action="modify?no=${param.no } 로 넘겨주기 (요청객체의 요청파라미터에 저장되어 있던 값 꺼내옴)
				3. input태그의 속성값을 type="hidden" name="no" value="${emp.no}" 로 넘겨주기
			 -->
			<form class="border bg-light p-3" method="post" action="modify?no=${emp.no }"> <!-- 수정한 직원번호 넘겨주기 -->
				<div class="form-group mb-3">
					<label class="form-label">부서</label>
					<select class="form-select" name="deptNo" >
						<c:forEach var="dept" items="${deptList }">
							<option value="${dept.no }" ${dept.no eq emp.dept.no ? 'selected' : '' }> ${dept.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group mb-3">
					<label class="form-label">이름</label>
					<input type="text" class="form-control" name="name" value="${emp.name }" />
				</div>
				<div class="form-group mb-3">
					<label class="form-label">전화번호</label>
					<input type="text" class="form-control" name="tel" value="${emp.tel }" />
				</div>
				<div class="form-group mb-3">
					<label class="form-label">이메일</label>
					<input type="text" class="form-control" name="email" value="${emp.email }" />
				</div>
				<div class="form-group mb-3">
					<label class="form-label">급여</label>
					<input type="text" class="form-control" name="salary" value="${emp.salary }"/>
				</div>
				<div class="form-group mb-3">
					<label class="form-label">입사일</label>
					<fmt:formatDate value="${emp.hireDate }" pattern="yyyy-MM-dd" var="hireDate"/>
					<input type="date" class="form-control" name="hireDate" value="${hireDate }" />
				</div>
				<div class="text-end">
					<button type="submit" class="btn btn-primary">수정</button>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>