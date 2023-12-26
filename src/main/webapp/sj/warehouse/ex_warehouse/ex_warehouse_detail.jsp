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
	border: 1px solid black;
}

.in_name {
	border: 1px solid black;
	width: 73px;
}

.input-container div {
	/* margin-right: 10px; */
	
}

.input-container input, .input-container select {
	width: 69px;
}
</style>
</head>
<body>

	<h1 style="font-size: 30pt;">출고 등록 확인 컨펌</h1>
	<div class="input-box input-container">
		<div class="in_name">계약 ID</div>
		<div class="in_name">계약 날짜</div>
		<div class="in_name">거래처 회사명</div>
		<div class="in_name">담당자 이름</div>
	</div>
	<div class="input-box input-container">
		<div>
			<input value="${contract.c_contract_no}" readonly="readonly">
		</div>
		<div>
			<input value="${contract.c_created_date}" readonly="readonly">
		</div>
		<div>
			<input value="${contract.c_name}" readonly="readonly">
		</div>
		<div>
			<input  value="${contract.e_name}" readonly="readonly">
		</div>
	</div>
	<form action="ExWarehouseDetailC" method="post" id="warehouseForm">
		<div class="input-box input-container">
			<div class="in_name">품목 ID</div>
			<div class="in_name">타입</div>
			<div class="in_name">제품 이름</div>
			<div class="in_name">p_quantity</div>
			<div class="in_name">단위</div>
			<div class="in_name">수량</div>
			<div class="in_name">창고</div>
		</div>

		<c:forEach var="t" items="${exWarehouse}">
			<div class="input-box input-container">
				<input type="hidden" name="c_contract_no"
					value="${param.c_contract_no }">
				<div>
					<input name="ci_p_id" value="${t.ci_p_id}" readonly="readonly">
				</div>
				<div>
					<input name="p_type" value="${t.p_type}" readonly="readonly">
				</div>
				<div>
					<input name="p_name" value="${t.p_name}" readonly="readonly">
				</div>
				<div>
					<input name="p_quantity" value="${t.p_quantity}"
						readonly="readonly">
				</div>
				<div>
					<input name="p_si" value="${t.p_si}" readonly="readonly">
				</div>
				<div>
					<input name="ci_count" value="${t.ci_count}" readonly="readonly">
				</div>
				<div>
					<select name="warehouse_id">
						<option value="1">1창고</option>
						<option value="2">2창고</option>
						<option value="3">3창고</option>
					</select>
				</div>
			</div>
		</c:forEach>
		<button id="fromBtn">출고확인</button>
	</form>
	<button onclick="history.back()">돌아가기</button>

	<a href="InExBoardC">입고출고 내역 확인하러 가기</a>
</body>
<script type="text/javascript"
	src="sj/warehouse/warehouse_js/warehouse_detail.js"></script>
</html>