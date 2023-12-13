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

	<div class="input-box input-container">

		<div class="in_name">구분</div>
		<div class="in_name">제품 이름</div>
		<div class="in_name">타입</div>
		<div class="in_name">p_quantity</div>
		<div class="in_name">단위</div>
		<div class="in_name">수량</div>
		<div class="in_name">날짜</div>
		<div class="in_name">창고</div>
	</div>
	<!-- 1줄씩 나타내줄 것들 -->
			<!-- 입고 데이터 표시 -->
			<c:forEach var="in" items="${inWarehouse}">
				<div class="input-box input-container">
					<div class="in_name">입고</div>
					<div class="in_name">${in.p_name}</div>
					<div class="in_name">${in.p_type}</div>
					<div class="in_name">${in.in_warehouse_quantity}</div>
					<div class="in_name">${in.p_si}</div>
					<div class="in_name">${in.p_quantity}</div>
					<div class="in_name">${in.in_warehouse_date}</div>
					<div class="in_name">${in.warehouse_name}</div>
				</div>
			</c:forEach>
			<!-- 출고 데이터 표시 -->
			<c:forEach var="ex" items="${exWarehouse}">
				<div class="input-box input-container">
					<div class="in_name">출고</div>
					<div class="in_name">${ex.p_name}</div>
					<div class="in_name">${ex.p_type}</div>
					<div class="in_name">${ex.ex_warehouse_quantity}</div>
					<div class="in_name">${ex.p_si}</div>
					<div class="in_name">${ex.p_quantity}</div>
					<div class="in_name">${ex.ex_warehouse_date}</div>
					<div class="in_name">${ex.warehouse_name}</div>
				</div>
			</c:forEach>
	<div>일단 확인</div>
</body>
</html>