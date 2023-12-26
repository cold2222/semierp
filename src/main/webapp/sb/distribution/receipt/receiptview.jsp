<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>運送部　配送完了(購買)ページ</title>
<link rel="stylesheet"
	href="sb/distribution_css/deliverydata/deliverydataview.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="content">
		<div class="detail-info">
			<div class="info-row">
				<span class="info-label">契約書番号:</span> <span class="info-value">${rec.c_contract_no }</span>
			</div>
			<div class="info-row">
				<span class="info-label">契約書作成日:</span> <span class="info-value">${rec.c_created_date }</span>
			</div>
			<div class="info-row">
				<span class="info-label">取引先名:</span> <span class="info-value">${rec.c_name }</span>
			</div>
			<div class="info-row">
				<span class="info-label">取引先担当者名:</span> <span class="info-value">${rec.c_keeper }</span>
			</div>
			<div class="info-row">
				<span class="info-label">取引先番号:</span> <span class="info-value">${rec.c_phone }</span>
			</div>
			<div class="info-row">
				<span class="info-label">配送場所:</span> <span class="info-value">長崎港</span>
			</div>
			<div class="info-row">
				<span class="info-label">納期日:</span> <span class="info-value"
					id="c_due_date">${rec.c_due_date }</span>
			</div>
		</div>
		<div class="item-list">
			<h2 class="contract_items">契約書詳細</h2>
			<c:forEach var="i" items="${itemList }">
				<div class="item">
					<span class="item-name">${i.p_name }</span> <span
						class="item-details">${i.p_type } ${i.p_quantity }${i.p_si }
						数: ${i.ci_count} 値段: ${i.ci_unit_price}</span>
				</div>
			</c:forEach>
		</div>
		 <c:if test="${param.page eq 'clearList' }">
		<div class="item-list">
			<h2 class="contract_items">配送情報</h2>
				<div class="item">
					<span class="item-name">配送担当者</span> 
					<span class="item-details">${deliveryData.e_name }</span>
				</div>
				<div class="item">
					<span class="item-name">配送日</span> 
					<span class="item-details">${deliveryData.c_delivery_date }</span>
				</div>
		</div>
		</c:if>
		<!-- 버튼을 클릭하여 모달 열기 -->
		<c:if test="${param.page eq 'List' }">
			<c:if test="${dec.c_status < 2 }">
				<button id="openModal" class="modal-btn">配送担当者指定</button>
			</c:if>
		</c:if>

		<!-- 오버레이 및 모달 창 -->
		<div class="overlay" id="overlay"></div>
		<div class="modal" id="modal">
			<!-- 모달 창 내용 -->
			<h1>配送担当登録ページ</h1>
			<h3>出勤する社員リスト</h3>
			<div>
				<form action="DistributionReceiptDesignationC" method="post"
					id="myForm">
					<input type="hidden" name="c_contract_no"
						value="${rec.c_contract_no }">
					<div style="margin-bottom: 20px;">
						配送日選択 <input type="date" name="c_delivery_date"
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
	<script type="text/javascript"
		src="sb/distribution_js/receiptview.js"></script>
	<script type="text/javascript" src="sb/distribution_js/worklistapi.js"></script>
</body>
</html>