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

	<h1 style="font-size: 30pt;">입고 등록 확인 컨펌 </h1>


		<div class="input-box input-container">
			<div class="in_name">계약 ID</div>
			<div class="in_name">계약 날짜</div>
			<div class="in_name">거래처 회사명</div>
			<div class="in_name">담당자 이름</div>
			<div class="in_name">상세페이지</div>
		</div>
		
		<c:forEach var="t" items="${inWarehouse}">
				<div class="input-box input-container">
				<div>
					<input name="c_contract_no_${t.c_contract_no}" value="${t.c_contract_no}">				
				</div>
				<div>
					<input name="c_contract_no_${t.c_created_date}" value="${t.c_created_date}">				
				</div>
				<div>
					<input name="c_contract_no_${t.c_name}" value="${t.c_name}">				
				</div>
				<div>
					<input name="c_contract_no_${t.e_name}" value="${t.e_name}">				
				</div>
				<div>
					<a href="InWarehouseDetailC?c_contract_no=${t.c_contract_no}"><button>상세페이지</button></a>			
				</div>
				</div>
		</c:forEach>
	 <a href="InExBoardC">입고출고 내역 확인하러 가기</a> 
</body>
</html>