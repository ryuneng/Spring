<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			<h1 class="fs-3">직원관리 - 직원 목록 정보</h1>
			
			<form id="form-emps" method="get" action="list">
				<input type="hidden" name="page">
				<div class="mb-3 d-flex justify-content-between">
					<select class="form-control w-25" name="rows">
						<option value="5"> 5개씩보기</option>
						<option value="10"> 10개씩보기</option>
						<option value="15"> 15개씩보기</option>
						<option value="20"> 20개씩보기</option>
					</select>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input"
								type="radio"
								name="sort"
								value="date"/>
							<label class="form-check-label">최신순</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input"
								type="radio"
								name="sort"
								value="date"/>
							<label class="form-check-label">이름순</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input"
								type="radio"
								name="sort"
								value="date"/>
							<label class="form-check-label">입사일 오름차순</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input"
								type="radio"
								name="sort"
								value="date"/>
							<label class="form-check-label">입사일 내림차순</label>
						</div>
					</div>
				</div>
				<table class="table">
					<colgroup>
						<col width="">
					</colgroup>
					<thead>
						<tr>
							<th>직원번호</th>
							<th>직원이름</th>
							<th>전화번호</th>
							<th>이메일</th>
							<th>입사일</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty empList }">
								<tr>
									<td colspan="6" class="text-center">조회결과가 없습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="emp" items="${empList }">
									<tr>
										<td>${emp.no }</td>
										<td><a href="detail?no=${emp.no }">${emp.name }</a></td>
										<td>${emp.tel }</td>
										<td>${emp.email }</td>
										<td><fmt:formatDate value="${emp.hireDate }" pattern="yyyy-MM-dd" /></td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				
				<div class="row">
					<div class="col-4">
						<div class="row row-cols-lg-auto g-3">
							<div>
								<select class="form-select" name="opt">
									<option value="">직원번호</option>
									<option value="">직원이름</option>
									<option value="">전화번호</option>
									<option value="">이메일</option>
								</select>
							</div>
							<div class="col-12">
								<input type="text" class="form-control" name="keyword">
							</div>
							<div class="col-12">
								<button type="submit" class="btn btn-outline-primary btn-sm">검색</button>
							</div>
						</div>
					</div>
					<div class="col-4">
					
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col-12">
			<div class="text-end">
				<a href="create" class="btn btn-primary">신규 직원</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>