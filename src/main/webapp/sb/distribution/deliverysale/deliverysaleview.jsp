<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="sb/distribution_css/receipt/receiptview.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous">
</script>	
<script type="text/javascript" src="sb/distribution_js/worklistapi.js"></script>
</head>
<body>
	<div class="content">
		<table border="1">
			<tr>
				<td>판매이력번호</td>
				<td>계약서작성일</td>
				<td>거래처명</td>
				<td>판매담당자이름</td>
				<td>판매담당자폰번호</td>
				<td>장소</td>
				<td>납기일</td>
			</tr>
			<tr>
				<td>${dec.c_contract_no }</td>
				<td>${dec.c_created_date }</td>
				<td>${dec.c_name }</td>
				<td>${dec.c_keeper }</td>
				<td>${dec.c_phone }</td>
				<td>${dec.c_addr }</td>
				<td id="c_due_date">${dec.c_due_date }</td>
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
			<h1>配送担当登録ページ</h1>
			<h3>出勤する社員リスト</h3>
			<div>
				<form action="DistributionDeliverySaleDesignationC" method="post" id="myForm">
					<input type="hidden" name="c_contract_no" value="${dec.c_contract_no }">
					<div style="margin-bottom: 20px;">
					배송날짜선택
					<input type="date" name="c_delivery_date" id="c_delivery_date">
					</div>
					<div id="radioDiv">
					</div>
					<textarea class="note-input" name="s_memo" placeholder="メモ欄"></textarea>
    				<button class="modal-btn" type="submit">登録</button>
				</form>
			</div>
			<button onclick="closeModal()" class="modal-btn">모달 닫기</button>
		</div>
	</div>
	<script type="text/javascript" src="sb/distribution_js/deliverysaleview.js"></script>
</body>
</html>