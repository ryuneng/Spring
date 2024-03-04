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
			<h1 class="fs-3">직원관리 - 직원 상세정보</h1>
			
			<div class="row">
				<div class="col-12">
					<table class="table">
						<tr>
							<th>이름</th>
							<td colspan="3">${emp.name }</td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td colspan="3">${emp.tel }</td>
						</tr>
						<tr>
							<th>이메일</th>
							<td colspan="3">${emp.email }</td>
						</tr>
						<tr>
							<th>급여</th>
							<td colspan="3">${emp.salary }</td>
						</tr>
						<tr>
							<th>입사일</th>
							<td colspan="3"><fmt:formatDate value="${emp.hireDate }" pattern="yyyy-MM-dd"/></td>
						</tr>
						<tr>
							<th>부서번호</th>
							<td colspan="3">${emp.dept.no }</td>
						</tr>
					</table>
				</div>
				<div class="text-end">
						<a href="modify?no=${emp.no }" class="btn btn-primary">수정</a>
						<a href="delete?no=${emp.no }" class="btn btn-danger">삭제</a>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>