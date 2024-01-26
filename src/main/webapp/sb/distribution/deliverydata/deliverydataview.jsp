<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세정보</title>
<link rel="stylesheet"
	href="sb/distribution_css/deliverydata/deliverydataview.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="content-head">
		<div class="content-head-text">配送情報確認</div>
	</div>
	<div class="content">
		<div class="detail-info">
			<input type="hidden" id="contractType" value="${dec.c_type }">
			<div class="info-row">
				<span class="info-label">契約書番号:</span> <span class="info-value">${dec.c_contract_no }</span>
			</div>
			<div class="info-row">
				<span class="info-label">契約書作成日:</span> <span class="info-value">${dec.c_created_date }</span>
			</div>
			<div class="info-row">
				<span class="info-label">取引先名:</span> <span class="info-value">${dec.c_name }</span>
			</div>
			<div class="info-row">
				<span class="info-label">取引先担当者名:</span> <span class="info-value">${dec.c_keeper }</span>
			</div>
			<div class="info-row">
				<span class="info-label">取引先番号:</span> <span class="info-value">${dec.c_phone }</span>
			</div>
			<div class="info-row">
				<c:if test="${deliveryData.c_type eq '購買'}">
					<span class="info-label">配送場所:</span>
					<span class="info-value">長崎港</span>
				</c:if>
				<c:if test="${deliveryData.c_type eq '販売'}">
					<span class="info-label">配送場所:</span>
					<span class="info-value">${dec.c_addr }</span>
				</c:if>
			</div>
			<div class="info-row">
				<c:if test="${deliveryData.c_type eq '購買'}">
					<span class="info-label">入港日:</span>
					<span class="info-value" id="c_due_date">${dec.c_due_date }</span>
				</c:if>
				<c:if test="${deliveryData.c_type eq '販売'}">
					<span class="info-label">納期日:</span>
					<span class="info-value" id="c_due_date">${dec.c_due_date }</span>
				</c:if>
			</div>
		</div>
		<div class="item-list">
			<h2 class="contract_items">契約書詳細</h2>
			<c:forEach var="i" items="${itemList }">
				<div class="item">
					<span class="item-name">アイテム名 : ${i.p_name }</span> <span
						class="item-details"><span class="item-details-span">タイプ:
					</span> ${i.p_type } <span class="item-details-span">単位: </span>${i.p_si }
						<span class="item-details-span">単位量: </span>${i.p_quantity }</span> <span
						class="item-details"><span class="item-details-span">数量:
					</span>${i.ci_count} <span class="item-details-span">単価: </span>${i.ci_unit_price}</span>
				</div>
			</c:forEach>
		</div>
		<div class="item-list">
			<h2 class="contract_items">配送情報</h2>
			<div class="item">
				<span class="item-name">配送担当者</span> <span class="item-details">${deliveryData.e_name }</span>
			</div>
			<div class="item">
				<span class="item-name">配送日</span> <span class="item-details">${deliveryData.c_delivery_date }</span>
			</div>
		</div>
		<!-- 버튼을 클릭하여 모달 열기 -->
		<button id="openModal" class="modal-btn">配送情報修正</button>

		<!-- 오버레이 및 모달 창 -->
		<div class="overlay" id="overlay"></div>
		<div class="modal" id="modal">
			<!-- 모달 창 내용 -->
			<h1>配送担当登録ページ</h1>
			<h3>出勤する社員リスト</h3>
			<div>
				<form action="DistributionDeliveryDataUpdateC" method="post"
					id="myForm">
					<input type="hidden" name="c_contract_no"
						value="${dec.c_contract_no }">
					<div style="margin-bottom: 20px;">
						배송날짜선택 <input type="date" name="c_delivery_date"
							id="c_delivery_date">
					</div>
					<div id="radioDiv"></div>
					<textarea class="note-input" name="s_memo" placeholder="メモ欄"></textarea>
					<button class="modal-btn" type="submit">登録</button>
				</form>
			</div>
			<button onclick="closeModal()" class="modal-btn">閉じる</button>
		</div>
	</div>
	<script type="text/javascript" src="sb/distribution_js/deliverydata.js"></script>
	<script type="text/javascript" src="sb/distribution_js/worklistapi.js"></script>
</body>
</html>