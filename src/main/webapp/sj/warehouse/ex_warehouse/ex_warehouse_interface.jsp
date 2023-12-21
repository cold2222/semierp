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
<h1 style="font-size: 30pt;">출고 등록 컨펌 페이지 </h1>




		<div class="input-box input-container">
			<div class="in_name">계약 ID</div>
			<div class="in_name">계약 날짜</div>
			<div class="in_name">거래처 회사명</div>
			<div class="in_name">담당자 이름</div>
			<div class="in_name">상세페이지</div>
		</div>
		
		<c:forEach var="t" items="${exWarehouse}">
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
					<a href="ExWarehouseDetailC?c_contract_no=${t.c_contract_no}"><button>상세페이지</button></a>			
				</div>
				</div>
		</c:forEach>

	      <a href="InExBoardC">입고출고 내역 확인하러 가기</a> 

	
</body>
</html>

