<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="sb/distribution_css/receipt/receiptview.css">
</head>
<body>
	<div class="content">
		<table border="1">
			<tr>
				<td>구매이력번호</td>
				<td>계약서작성일</td>
				<td>거래처명</td>
				<td>구매담당자이름</td>
				<td>구매담당자폰번호</td>
				<td>장소</td>
				<td>항구도착일</td>
			</tr>
			<tr>
				<td>${rec.c_contract_no }</td>
				<td>${rec.c_created_date }</td>
				<td>${rec.c_name }</td>
				<td>${rec.c_keeper }</td>
				<td>${rec.c_phone }</td>
				<td>부산항</td>
				<td>${rec.c_due_date }</td>
			</tr>
			<tr>
				<td>품명</td>
				<td>종류</td>
				<td>개수</td>
				<td>단가</td>
			</tr>
			<c:forEach var="i" items="${itemList }">
				<tr>
					<td>${i.p_name }</td>
					<td>${i.p_type }${i.p_quantity }${i.p_si }</td>
					<td>${i.ci_count }</td>
					<td>${i.ci_unit_price }</td>
				</tr>
			</c:forEach>
		</table>
		<!-- 버튼을 클릭하여 모달 열기 -->
		<button id="openModal" class="modal-btn">배송인 지정</button>

		<!-- 오버레이 및 모달 창 -->
		<div class="overlay" id="overlay"></div>
		<div class="modal" id="modal">
			<!-- 모달 창 내용 -->
			<h2>출근사원 리스트</h2>
			<p>모달 창 내용을 이곳에 추가하세요.</p>
			<button onclick="closeModal()" class="modal-btn">모달 닫기</button>
		</div>
	</div>
	<script type="text/javascript" src="sb/distribution_js/receiptview.js"></script>
</body>
</html>