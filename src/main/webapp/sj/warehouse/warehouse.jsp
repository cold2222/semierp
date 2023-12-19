<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>

.input-container {
	display: flex;
	margin-bottom: 10px;
}

.input-box {
	border: 1px solid #ccc;
}

.in_name {
	border: 1px solid #ccc;
	width: 73px;
}

.input-container div {
	/* margin-right: 10px; */
	
}

.input-container input, .input-container select {
	width: 69px; /* Adjust the width as needed */
}
</style>

<script>
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('searchOption').addEventListener(
				'change',
				function() {
					var input = document.querySelector('.searchInput');
					input.style.display = this.value === 'x' ? 'none'
							: 'inline-block';
				});

	});
</script>



</head>
<body>


				<h1 style="font-size: 30pt;">입고 테이블 </h1>
		
		<form action="TestwarehouseC" method="post">
			<div class="input-container">
			 검색 : <select id="searchOption" name="searchOption">
				<option value="x">전체</option>
				<option value="p_name">제품명</option>
				<option value="p_type">타입</option>
			</select> <input type="text" name="word" class="searchInput"
				style="display: none;">
			<button type="submit">확인</button>
		</div>
	</form>
		
		<div class="input-box input-container">
		
			<div class="in_name">제품 이름</div>
			<div class="in_name">타입</div>
			<div class="in_name">p_quantity</div>
			<div class="in_name">단위</div>
			<div class="in_name">수량</div>
			<div class="in_name">날짜</div>
			<div class="in_name">창고</div>
		</div>

		<c:forEach var="in" items="${inWarehouse}">
			<div class="input-box input-container">
				<div>
					<input name="p_name" value="${in.p_name}" readonly="readonly">
				</div>
				<div>
					<input name="p_type" value="${in.p_type}" readonly="readonly">
				</div>
				<div>
					<input name="in_warehouse_quantity" value="${in.in_warehouse_quantity}"
						readonly="readonly">
				</div>
				<div>
					<input name="p_si" value="${in.p_si}" readonly="readonly">
				</div>
				<div>
					<input name="p_quantity" value="${in.p_quantity}" readonly="readonly">
				</div>
				<div>
					<input name="in_warehouse_date" value="${in.in_warehouse_date}"
						readonly="readonly">
				</div>
				<div>
					<input name="warehouse_name" value="${in.warehouse_name}"
						readonly="readonly">
				</div>
			</div>
		</c:forEach>
		<div>일단 확인</div>
</body>
</html>