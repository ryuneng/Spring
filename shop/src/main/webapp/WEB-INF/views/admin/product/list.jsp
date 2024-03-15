<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/tags.jsp" %>
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
<%@ include file="../../common/navbar.jsp" %>
<div class="container">
	<div class="row mb-3">
		<div class="col-12">
			<h1>상품 관리</h1>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col-3">
			<div class="card">
				<div class="card-header">관리자 메뉴</div>
				<div class="list-group list-group-flush">
					<a href="/admin/user/list" class="list-group-item list-group-item-action">사용자 관리</a>
					<a href="/admin/product/list" class="list-group-item list-group-item-action active">상품 관리</a>
					<a href="" class="list-group-item list-group-item-action">주문 관리</a>
					<a href="" class="list-group-item list-group-item-action">결재 관리</a>
					<a href="" class="list-group-item list-group-item-action">공지사항 관리</a>
				</div>
			</div>
		</div>
		<div class="col-9">
			<h3>상품 목록 <a href="create" class="btn btn-primary btn-sm float-end">신규 상품 등록</a></h3>
			<table class="table">
				<colgroup>
					<col width="10%">
					<col width="15%">
					<col width="38%">
					<col width="12%">
					<col width="15%">
					<col width="10%">
				</colgroup>
				<thead>
					<tr>
						<th>순번</th>
						<th>카테고리</th>
						<th>상품이름</th>
						<th>제조사</th>
						<th>가격</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="product" items="${products }" varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td><span id="pro-cat-${product.no }">${product.category.name }</span></td>
							<td><span id="pro-name-${product.no }">${product.name }</span></td>
							<td><span id="pro-company-${product.no }">${product.company.name }</span></td>
							<td><span id="pro-price-${product.no }"><fmt:formatNumber value="${product.price }" /></span> 원</td>
							<td>
								<button class="btn btn-outline-primary btn-sm" onclick="showProductForm(${product.no})">수정하기</button>
								<%-- <button class="btn btn-outline-primary btn-sm" onclick="showProductInfo(${product.no})">상세보기</button> --%>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

<!-- 상품정보 수정 모달창 -->
<div class="modal modal-lg" id="modal-product-form">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">상품정보 수정폼</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form class="border bg-light p-3">
					<sec:csrfInput/> <!-- 시큐리티 csrf 토큰 사용 -->
					<input type="hidden" name="no" /> <!-- submitForm() 정의할 때 추가함 -->
		        	<div class="row mb-3">
		            	<div class="col-6">
		                	<div class="form-group">
		                    	<label class="form-label">상위 카테고리</label>
		                    	<select class="form-select" name="parentCategoryNo" onchange="changeCategory()">
		                        	<c:forEach var="cat" items="${productCategories }">
		                        		<option value="${cat.no }"> ${cat.name }</option>
		                        	</c:forEach>
		                    	</select>
		                    </div>
		                </div>
		                <div class="col-6">
							<div class="form-group">
								<label class="form-label">하위 카테고리</label>
		                        <select class="form-select" name="categoryNo">
		                        
		                        </select>
							</div>
						</div>
					</div>
					<div class="row mb-3">
						<div class="col-6">
							<div class="form-group">
								<label class="form-label">상품명</label>
		                        <input type="text" class="form-control" name="name" />
							</div>
						</div>
						<div class="col-6">
							<div class="form-group">
								<label class="form-label">제조사</label>
		                        <select class="form-select" name="companyNo">
									<c:forEach var="company" items="${companies }">
										<option value="${company.no }">${company.name }</option>
		                        	</c:forEach>
		                        </select>
							</div>
						</div>
					</div>
					<div class="row mb-3">
						<div class="col-6">
							<div class="form-group">
								<label class="form-label">가격</label>
		                        <input type="text" class="form-control" name="price" />
							</div>
						</div>
						<div class="col-6">
							<div class="form-group">
								<label class="form-label">수량</label>
		                        <input type="text" class="form-control" name="stock" />
							</div>
						</div>
					</div>
					<div class="row mb-3">
						<div class="form-group">
							<label class="form-label">설명</label>
			                <textarea class="form-control" rows="3" name="description"></textarea>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-primary" onclick="submitForm()">수정</button>
			</div>
		</div>
	</div>
</div>

<%--
<!-- 부트스트랩 팝업 -->
<div class="modal" tabindex="-1" id="model-product-info">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">상품 상세정보</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<table class="table">
					<tbody>
						<tr>
							<th>번호</th>
							<td><span id="product-no"></span></td>
							<th>상품이름</th>
							<td><span id="product-name"></span></td>
						</tr>
						<tr>
							<th>카테고리</th>
							<td><span id="category-name"></span></td>
							<th>제조사</th>
							<td><span id="company-name"></span></td>
						</tr>
						<tr>
							<th>가격</th>
							<td><span id="product-price"></span> 원</td>
							<th>상태</th>
							<td><span id="product-status"></span></td>
						</tr>
						<tr>
							<th>수량</th>
							<td><span id="product-stock"></span> 개</td>
							<th>상품등록일</th>
							<td><span id="product-createdDate"></span></td>
						</tr>
						<tr>
							<th>상품설명</th>
							<td colspan="3"><span id="product-description"></span></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
            	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
         	</div>
		</div>
	</div>
</div>
--%>
<script type="text/javascript">
	// const 키워드는 해당 변수가 상수임을 나타낸다. (자바의 final과 동일)
	// productFormModal에는 위에서 정의한 Modal을 표현하는 Modal객체가 대입되어 있고, 그 객체는 변경할 일이 없다.
	const productFormModal = new bootstrap.Modal("#modal-product-form");
	
	// async 키워드는 함수 내부에 await 키워드가 있는 경우에만 적고, 그 외에는 생략
	function showProductForm(productNo) {
		setFormValue(productNo);
		
		productFormModal.show();
	}
	
	// showProductForm 함수가 너무 길어져서 분리하기 위해 별도로 정의한 함수
	async function setFormValue(productNo) {
		let response = await fetch("/admin/product/" + productNo); // 상품 상세정보 반환하는 요청핸들러 메소드를 응답으로 받기
		let product = await response.json(); // 응답으로 받은 json을 객체로 변환(역직렬화)해서 반환
		
		// 상위카테고리 받아서 하위카테고리 반환
		setCategory(product.category.parentNo, product.category.no);
		
		// 수정하려는 상품의 기존 값 넣어주기
		document.querySelector("input[name=no]").value = product.no;
		document.querySelector("input[name=name]").value = product.name;
		document.querySelector("select[name=companyNo]").value = product.company.no;
		document.querySelector("input[name=price]").value = product.price;
		document.querySelector("input[name=stock]").value = product.stock;
		document.querySelector("textarea[name=description]").value = product.description;
	}
	
	async function setCategory(parentNo, no) {
		// 수정 폼 눌렀을 때 해당 상품 정보에 맞는 상위 카테고리 select되도록 표현
		document.querySelector("select[name=parentCategoryNo]").value = parentNo;
		
		let response = await fetch("/admin/category?catNo=" + parentNo);
		let subCategories = await response.json();
		
		// 하위카테고리 표현
		let options = "";
		for (let index = 0; index < subCategories.length; index++) {
			let subCategory = subCategories[index];
			options += `<option value="\${subCategory.no}" \${subCategory.no == no ? "selected" : ""}> \${subCategory.name}</option>`
															// 수정하려는 상품 기존에 해당하는 카테고리가 선택되도록
		}
		document.querySelector("select[name=categoryNo]").innerHTML = options;
	}
	
	// 수정 폼에서 상위 카테고리 클릭 시 표현되는 하위 카테고리 값 변경
	function changeCategory() {
		let parentNo = document.querySelector("select[name=parentCategoryNo]").value;
		setCategory(parentNo);
	}
	
	// 수정 폼 Ajax로 보내기
	async function submitForm() {
		// 1. 값 하나씩 읽어오기
		//    json으로 직렬화(전송에 용이한 형태로 변환)할 자바스크립트 객체 생성
		let data = {
			no: document.querySelector("input[name=no]").value,
			category: { // category.no로 바꿔줌
				       no: document.querySelector("select[name=categoryNo]").value
			},
			name: document.querySelector("input[name=name]").value,
			company: {
				      no: document.querySelector("select[name=companyNo]").value
			},
			price: document.querySelector("input[name=price]").value,
			stock: document.querySelector("input[name=stock]").value,
			description: document.querySelector("textarea[name=description]").value
		};
		
		// 2. 위의 data 객체를 직렬화(전송에 용이한 형태로 변환)
		let jsonText = JSON.stringify(data);

		// 3. POST 방식으로 JSON 데이터를 서버로 전송
		let response = await fetch("/admin/product/modify", {
			method: "POST",
			headers: {
				"Content-Type": "application/json", // json에 대한 컨텐츠 타입
				"X-CSRF-TOKEN": document.querySelector("input[name='_csrf']").value
			},
			body: jsonText
		});
		
		// 응답이 true라면
		if (response.ok) {
			
			// 상품 목록 화면에 수정완료된 값 즉시 반영
			document.getElementById("pro-name-" + data.no).textContent = data.name;
			document.getElementById("pro-price-" + data.no).textContent = parseInt(data.price).toLocaleString();
			
			// 상품 목록 화면에 수정완료된 값 즉시 반영 - 다른 테이블
			// select박스 엘리먼트를 선택한다.
			let categorySelect = document.querySelector("select[name=categoryNo]");
			// select.options는 셀렉터박스의 모든 옵션을 배열로 반환한다.
			// select.selectedIndex는 셀렉터박스의 옵션 중에서 현재 선택된 옵션의 index를 반환한다.
			// select.options[select.selectIndex]는 셀렉터박스의 옵션 중에서 현재 선택된 옵션 엘리먼트를 반환한다.
			// select.options[select.selectIndex].textContent는 현재 선택된 옵션 엘리먼트의 텍스트 컨텐츠를 반환한다.
			let categoryName = categorySelect.options[categorySelect.selectedIndex].textContent;
			document.getElementById("pro-cat-" + data.no).textContent = categoryName;
			
			let companySelect = document.querySelector("select[name=companyNo]");
			let companyName = companySelect.options[companySelect.selectedIndex].textContent;
			document.getElementById("pro-company-" + data.no).textContent = companyName;
			
			// 모달 닫기
			productFormModal.hide();
		}
	}

<%--
	const myModal = new bootstrap.Modal(document.getElementById('model-product-info'));
	
	function showProductInfo(no) {
		
		let xhr = new XMLHttpRequest();							// 1. 서버와 HTTP 통신을 하는 XMLHttpRequest 객체를 생성한다. (AJAX 요청 객체 생성)
		xhr.onreadystatechange = function() {					// 2. xhr객체의 readyState값이 변경될 때마다 실행되는 이벤트핸들러 함수를 등록한다.
			if (xhr.readyState == 4 && xhr.status == 200) {		// 5. readyState=4:응답이 왔다. status=200:성공
				let text = xhr.responseText;					// 6. 직렬화된 데이터 조회) xhr의 responseText 프로퍼티에서 텍스트응답데이터를 조회한다.
				let product = JSON.parse(text);					// 7. 역직렬화된 데이터 조회) 자바스크립트 객체로 변환
				
				// 화면의 일부분을 갱신하는 작업
				document.getElementById("product-no").textContent = product.no;
				document.getElementById("product-name").textContent = product.name;
				document.getElementById("category-name").textContent = product.category.name;
				document.getElementById("company-name").textContent = product.company.name;
				document.getElementById("product-price").textContent = product.price.toLocaleString();
				document.getElementById("product-status").textContent = product.status;
				document.getElementById("product-stock").textContent = product.stock;
				document.getElementById("product-description").textContent = product.description;
				
				// 날짜 포맷 변환
				var originalDate = new Date(product.createdDate);
				var formattedDate = originalDate.getFullYear() + '년 ' +
					                (originalDate.getMonth() + 1) + '월 ' +
					                originalDate.getDate() + '일 ';
				document.getElementById("product-createdDate").textContent = formattedDate;
				
				// 모달 창 보여주기
				myModal.show();
			}
		}
		xhr.open("GET", "/admin/product/" + no);					// 3. xhr객체를 초기화한다. 요청방식, 요청URL을 지정한다.
		xhr.send(null);												// 4. 서버로 요청을 보낸다.
	}
--%>
</script>
</body>
</html>