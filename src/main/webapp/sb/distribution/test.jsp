<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세정보</title>
<link rel="stylesheet" href="sb/distribution_css/receipt/receiptview.css">
<!-- 필요한 스타일시트 추가 -->

<!-- jQuery 추가 -->
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<!-- 자바스크립트 파일 추가 -->
<script type="text/javascript" src="sb/distribution_js/worklistapi.js"></script>
<script type="text/javascript" src="sb/distribution_js/deliverysaleview.js"></script>
</head>
<body>
	<div class="content">
		<div class="detail-info">
			<div class="info-row">
				<span class="info-label">판매이력번호:</span>
				<span class="info-value">${dec.c_contract_no }</span>
			</div>
			<div class="info-row">
				<span class="info-label">계약서작성일:</span>
				<span class="info-value">${dec.c_created_date }</span>
			</div>
			<!-- 다른 정보들도 동일하게 추가 -->
		</div>
		<div class="item-list">
			<h3>품목 목록</h3>
			<c:forEach var="i" items="${itemList }">
				<div class="item">
					<span class="item-name">${i.p_name }</span>
					<span class="item-type">${i.p_type }${i.p_quantity }${i.p_si }</span>
					<span class="item-count">${i.ci_count }</span>
					<span class="item-price">${i.ci_unit_price }</span>
				</div>
			</c:forEach>
		</div>
		<!-- 버튼을 클릭하여 모달 열기 -->
		<c:if test="${dec.c_status < 2 }">
		<button id="openModal" class="modal-btn">배송인 지정</button>
		</c:if>

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