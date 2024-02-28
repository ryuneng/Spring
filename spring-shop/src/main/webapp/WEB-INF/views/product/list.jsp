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
<title>20240226 Day5</title>
</head>
<body>
<%@ include file="../common/navbar.jsp" %>
<div class="container">
	<div class="row mb-3">
		<div class="col-12">
			<h1 class="fs-3">상품관리 - 상품 목록</h1>
			
			<!-- !주의 : form은 길게 다 감싸서 작성해도 됨. 다만, form 안에 또 다른 form을 넣을 수는 없음 -->
			<form id="form-products" method="get" action="list">
				<div class="mb-3 d-flex justify-content-between">
					<select class="form-control w-25" name="rows" onchange="changeRows()"> <!-- select박스, check박스, radio버튼에서는 onchange이벤트가 가장 적절 -->
						<option value="5" ${param.rows eq 5 ? 'selected' : '' }> 5개씩보기</option>
						<option value="10" ${empty param.rows or param.rows eq 10 ? 'selected' : '' }> 10개씩보기</option> <!-- param.rows가 없으면 기본값은 10 -->
						<option value="20" ${param.rows eq 20 ? 'selected' : '' }> 20개씩보기</option>
						<option value="50" ${param.rows eq 50 ? 'selected' : '' }> 50개씩보기</option>
					</select>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input"
								type="radio"
								name="sort"
								value="date"
								${empty param.sort or param.sort eq 'date' ? 'checked' : '' }
								onchange="changeSort()"/>
							<label class="form-check-label">최신순</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input"
								type="radio"
								name="sort"
								value="name"
								${param.sort eq 'name' ? 'checked' : '' }
								onchange="changeSort()"/>
							<label class="form-check-label">이름순</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input"
								type="radio"
								name="sort"
								value="lowprice"
								${param.sort eq 'lowprice' ? 'checked' : '' }
								onchange="changeSort()"/>
							<label class="form-check-label">낮은가격순</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input"
								type="radio"
								name="sort"
								value="highprice"
								${param.sort eq 'hightprice' ? 'checked' : '' }
								onchange="changeSort()"/>
							<label class="form-check-label">높은가격순</label>
						</div>
					</div>
				</div>
				
				<table class="table">
					<colgroup>
						<col width="5%">
						<col width="*">
						<col width="15%">
						<col width="15%">
						<col width="10%">
						<col width="25%">
					</colgroup>
					<thead>
						<tr>
							<th><input type="checkbox"></th>
							<th>상품명</th>
							<th>가격</th>
							<th>재고수량</th>
							<th>상태</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody>
						<!-- jstl의 if문 같은 태그 -->
						<c:choose> 
							<c:when test="${empty productList }">
								<tr>
									<td colspan="6" class="text-center">조회결과가 없습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="product" items="${productList }"> <!-- 반복할 때 사용하는 태그, var는 내가 원하는 변수명으로 지정하면 됨 -->
									<tr>
										<td><input type="checkbox"></td>
										<td><a href="detail?no=${product.no }">${product.name }</a></td>
										<td><fmt:formatNumber value="${product.price }"/> 원</td> <!-- formatNumber : 3자리마다 콤마 찍어줌 -->
										<td><fmt:formatNumber value="${product.stock }"/> 개</td>
										<td>${product.statusText }</td>
										<td>
											<a href="" class="btn btn-outline-primary btn-sm">입고</a>
											<a href="" class="btn btn-outline-danger btn-sm">판매중지</a>
											<a href="" class="btn btn-outline-success btn-sm">판매재개</a>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				
				<div class="row row-cols-lg-auto g-3">
					<div class="col-12">
						<select class="form-select" name="opt">
							<option value="name" ${param.opt eq 'name' ? 'selected' : '' }> 상품이름</option>
							<option value="price" ${param.opt eq 'price' ? 'selected' : '' }> 상품가격</option>
						</select>
					</div>
					<div class="col-12">
						<input type="text" class="form-control" name="keyword" value="${param.keyword }"/>
					</div>
					<div class="col-12">
						<button type="submit" class="btn btn-outline-primary btn-sm">검색</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col-12">
			<div class="text-end">
				<a href="create" class="btn btn-primary">신규 상품</a>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
/* 이벤트 생성 시 가장 먼저 alert('아무거나 입력'); 해서 일단 이벤트가 잘 작동하는지부터 확인! */
function changeRows() {
	let form = document.getElementById("form-products");
	form.submit();
}

function changeSort() {
	
}

function changePage() {
	
}
</script>
</body>
</html>