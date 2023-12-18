<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

.right-align {
	margin-left: auto;
}
</style>
</head>
<body>



	<h1 style="font-size: 30pt;">재고 현황</h1>

	<form action="WarehouseStockBoardTestC" method="get">
		<div class="input-container">
			<div>
				검색 : <input name="search" value="검색창넣을 곳 ">
			</div>
			<button type="submit">확인</button>
			</div>
	</form>

	<div class="input-box input-container">
		
		<div class="in_name">제품 이름</div>
		<div class="in_name">타입</div>
		<div class="in_name">p_quantity</div>
		<div class="in_name">단위</div>
		<div class="in_name">제조사</div>
		<div class="in_name">단가</div>
		<div class="in_name">재고수량</div>
		<div class="in_name">현 재고 값</div>
		<div class="in_name">비고</div>
	</div>
	<!-- 1줄씩 나타내줄 것들 -->
	<!-- 입고 데이터 표시 -->
 	<c:forEach var="wsb" items="${warehouseStockBoard}">
		<div class="input-box input-container">
			<div class="in_name">${wsb.p_name}</div>
			<div class="in_name">${wsb.p_type}</div>
			<div class="in_name">${wsb.p_quantity}</div>
			<div class="in_name">${wsb.p_si}</div>
			<div class="in_name">${wsb.manufacture_name}</div>
			<div class="in_name"><fmt:formatNumber value="${wsb.p_unicost}" pattern="#,###"/></div>
			<div class="in_name">${wsb.stock}</div>
			<div class="in_name"><fmt:formatNumber value="${wsb.stock * wsb.p_unicost}" pattern="#,###"/></div>
			<div class="in_name"></div>
		</div>
	</c:forEach>

	
	<div>재고현황 확인</div>
</body>
</html>