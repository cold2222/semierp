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


<style>
/* 스타일을 추가하여 모달을 가리키게 합니다. */
.modal {
	display: none;
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	padding: 20px;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}

/* 배경에 어두운 레이어를 추가하여 포커스가 모달 안으로 가게 합니다. */
.modal-background {
	display: none;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.5);
}
</style>
</head>
<body>
	<h1 style="font-size: 30pt;">출고 등록 컨펌 페이지</h1>



	<form action="ExWarehouseDetailC" method="post"
		onsubmit="return submitForm()" id="warehouseForm">
		<div class="input-box input-container">
			<div class="in_name">품목 ID</div>
			<div class="in_name">타입</div>
			<div class="in_name">제품 이름</div>
			<div class="in_name">p_quantity</div>
			<div class="in_name">단위</div>
			<div class="in_name">수량</div>
			<div class="in_name">날짜</div>
			<div class="in_name">창고</div>
		</div>

		<c:forEach var="t" items="${exWarehouse}">
			<div class="input-box input-container">
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
					<input name="ci_count" value="${t.ci_count}"
						readonly="readonly">
				</div>
				<div>
					<!-- warehouse_id 뒤에 t.p_id 를 툼으로써 창고번호 가져갈 수 있게함  -->
					<select name="warehouse_id" id="warehouse_id_${t.ci_p_id}">
						<option value="1">1창고</option>
						<option value="2">2창고</option>
						<option value="3">3창고</option>
					</select>
				</div>
			</div>
		</c:forEach>
		<button>수령확인</button>
	</form>




	<a href="InExBoardC">입고출고 내역 확인하러 가기</a>




	<!-- <form action="ExWarehouseC" method="get">
			<div class="input-container">
		 검색 : <select id="searchOption" name="searchOption">
					<option value="x">전체</option>
					<option value="p_name">제품명</option>
					<option value="p_type">타입</option>
				</select> <input type="text" name="word" class="searchInput"
					style="display: none;">
				<button type="submit">확인</button>
			</div>
		</form> -->



</body>
</html>

