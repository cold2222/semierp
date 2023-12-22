<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="sj/warehouse/warehouse_js/search.js"></script>
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
	<div class="search-container">
		<form action="ExWarehouseC" method="GET">
			<select name="field" id="searchField" class="search-select"
				onchange="showInput()">
				<option value="all">全体検索</option>
				<option value="b.c_name">取引先名</option>
				<option value="c.e_name">取引担当者</option>
			</select> <input type="text" placeholder="検索するキーワードを入力してください" name="word"
				id="searchWord" class="search-input" style="display: none;">
			<button type="submit" class="search-button">検索</button>
		</form>
	</div>
	<div class="input-box input-container">
		<div class="in_name">계약 ID</div>
		<div class="in_name">계약 날짜</div>
		<div class="in_name">거래처 회사명</div>
		<div class="in_name">담당자 이름</div>
		<div class="in_name">상세페이지</div>
	</div>

	<c:forEach var="t" items="${exWarehouses}">
		<div class="input-box input-container">
			<div>
				<input name="c_contract_no_${t.c_contract_no}"
					value="${t.c_contract_no}">
			</div>
			<div>
				<input name="c_contract_no_${t.c_created_date}"
					value="${t.c_created_date}">
			</div>
			<div>
				<input name="c_contract_no_${t.c_name}" value="${t.c_name}">
			</div>
			<div>
				<input name="c_contract_no_${t.e_name}" value="${t.e_name}">
			</div>
			<div>
				<a href="ExWarehouseDetailC?c_contract_no=${t.c_contract_no}"><button>상세페이지</button></a>
			</div>
		</div>
	</c:forEach>

	<a href="InExBoardC">입고출고 내역 확인하러 가기</a>
	<div class="paging">
		<c:choose>
			<c:when test="${pageNum != 1}">
				<button
					onclick="location.href='ExWarehousePageC?pageNum=${pageNum - 1}&field=${param.field }&word=${param.word }'">prev</button>
			</c:when>
		</c:choose>

		<c:forEach var="i" begin="${pageNum - 3 > 0 ? pageNum - 3 : 1}"
			end="${pageNum + 3 <= totalPage ? pageNum + 3 : totalPage}" step="1">
			<c:choose>
				<c:when test="${i eq pageNum}">
					<a
						href="ExWarehousePageC?pageNum=${i}&field=${param.field }&word=${param.word }"
						style="color: black; font-weight: bold;">${i}</a>
				</c:when>
				<c:otherwise>
					<a
						href="ExWarehousePageC?pageNum=${i}&field=${param.field }&word=${param.word }">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:choose>
			<c:when test="${pageNum != totalPage && totalPage != 0}">
				<button
					onclick="location.href='ExWarehousePageC?pageNum=${pageNum + 1}&field=${param.field }&word=${param.word }'">next</button>
			</c:when>
		</c:choose>
	</div>

</body>
</html>

