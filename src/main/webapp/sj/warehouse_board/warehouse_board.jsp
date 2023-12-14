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
	width: 70px;
}
</style>
</head>
<body>



	<h1 style="font-size: 30pt;">창고 테이블</h1>

	 <form action="WarehouseBoardTestC" method="get">
		<div class="input-container">
			<label for="operationType">창고별 :</label> 
			<select name="operationType" id="operationType">
				<option value="all">전체</option>
				<option value="one">1창고</option>
				<option value="two">2창고</option>
				<option value="three">3창고</option>
			</select>
			<button type="submit">확인</button>
		</div>
	</form> 


	<div class="input-box input-container">

		<div class="in_name">날짜</div>
		<div class="in_name">제품 이름</div>
		<div class="in_name">타입</div>
		<div class="in_name">단위</div>
		<div class="in_name">p_quantity</div>
		<div class="in_name">제조사</div>
		<div class="in_name">재고수량</div>
		<div class="in_name">창고</div>
		<div class="in_name">비고</div>
	</div>
	<!-- 1줄씩 나타내줄 것들 -->
			<!-- 입고 데이터 표시 -->
			<c:forEach var="wb" items="${warehouseBoard}">
				<div class="input-box input-container">
					<div class="in_name">${wb.today_date}</div>
					<div class="in_name">${wb.p_name}</div>
					<div class="in_name">${wb.p_type}</div>
					<div class="in_name">${wb.p_si}</div>
					<div class="in_name">${wb.p_quantity}</div>
					<div class="in_name">${wb.manufacture_name}</div>
					<div class="in_name">${wb.stock}</div>
					<div class="in_name">${wb.warehouse_name}</div>
					<div class="in_name"></div>
				</div>
			</c:forEach>
			
			
	<div>창고 확인 완료</div>
</body>
</html>