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
</head>
<body>

	<form action="TestwarehouseC" method="post"">

		<div class="input-box input-container">
			<div class="in_name">품목 ID</div>
			<div class="in_name">타입</div>
			<div class="in_name">제품 이름</div>
			<div class="in_name">수량</div>
			<div class="in_name">단위</div>
			<div class="in_name">날짜</div>
			<div class="in_name">창고</div>
		</div>

		<c:forEach var="t" items="${testWarehouse}">
			<div class="input-box input-container">
				<div>
					<input name="p_id" value="${t.p_id}" readonly="readonly">
				</div>
				<div>
					<input name="p_type" value="${t.p_type}" readonly="readonly">
				</div>
				<div>
					<input name="p_name" value="${t.p_name}" readonly="readonly">
				</div>
				<div>
					<input name="record_count" value="${t.record_count}"
						readonly="readonly">
				</div>
				<div>
					<input name="p_si" value="${t.p_si}" readonly="readonly">
				</div>
				<div>
					<input name="in_warehouse_date" value="${t.in_warehouse_date}"
						readonly="readonly">
				</div>
				<div>
					<select name="warehouse_id">
						<option value="warehouse_one">인천1창고</option>
						<option value="warehouse_two">인천2창고</option>
						<option value="warehouse_three">인천3창고</option>
					</select>
				</div>
			</div>
		</c:forEach>
		<div>일단 확인</div>
		<button>수령확인</button>
	</form>
</body>
</html>