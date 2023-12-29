<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="jh/css/contract_reg_modal.css">
<link rel="stylesheet" href="jh/css/contract_reg.css">
</head>
<body>
	<!-- 사원 검색용모달 -->
	<div id="empSearchModal" class="modal-background">
		<div id="empModal" class="modal-content">
			<div>
				<div>
					<input placeholder="本社員検索" id="empSearchBtn">
				</div>
				<div class="empList"></div>
			</div>
			<div class="modal-foot">
				<button id="empCloseModalBtn">閉じる</button>
			</div>
		</div>
	</div>
	<div id="modalBackground" class="modal-background">
		<!-- 모달 내용 -->
		<div id="myModal" class="modal-content">
			<div>
				<div>
					<input placeholder="取引先検索" id="search-btn">
				</div>
				<div class="list"></div>
			</div>
			<div class="modal-foot">
				<button id="closeModalBtn">閉じる</button>
			</div>
		</div>
	</div>

	<!-- 상품 검색용 모달 -->
	<div id="productModalBackground" class="modal-background">
		<div id="productModal" class="modal-content">
			<div>
				<div>
					<input placeholder="アイテム検索" id="productSearchInput">
				</div>
				<div class="listProduct"></div>
			</div>
			<div class="modal-foot">
				<button id="closeProductModalBtn">閉じる</button>
			</div>
		</div>
	</div>

	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">契約書作成</div>
		</div>
		<div class="content-body">
			<div class="bbs-content">
				<form action="MakeContractC" method="post">
					<div class="bbs-main">
						<div class="bbs-content1">
							<div class="content1-main">
								<div class="input-container">
									作成日: <input type="date" readonly="readonly"
										name="c_created_date" id="c_created_date">
								</div>
							</div>
							<input type="hidden" name="c_c_no" readonly="readonly"
								id="selectedValue">
							<div class="input-container">
								取引先: <input readonly="readonly" id="displayName"
									placeholder="取引先検索" required="required" type="text">
							</div>
							<input type="hidden" name="c_e_id" id="e_id" required="required">
							<div class="input-container">
								輸入／販売部門の担当社員: <input name="e_name" placeholder="社員検索"
									id="employeeSearch" type="text" readonly="readonly"
									required="required">
							</div>
							<div class="input-container">
								納期日: <input type="date" name="c_due_date" required="required">
							</div>
							<input type="hidden" name="c_status" placeholder="取引状態" value="1">
							<div class="input-container">
								輸入／販売: <select id="transactionType" name="c_type">
									<option value="1" selected="selected">輸入</option>
									<option value="2">販売</option>
								</select>
							</div>
						</div>
						<div class="button">
							<button class="insert-btn" type="button" onclick="addRow()">アイテム欄の追加</button>
						</div>
						<div class="contract-reg-main">		
							<div class="company-item-title">アイテム名</div>
							<div class="company-main-title">買い求める数</div>
							<div class="company-main-title">買い求める価格</div>
						</div>
						<div id="contractTable">
							<div style="display: none;" class="bbs-content2">
								<input name="ci_c_contract_no" type="hidden"> <input
									type="hidden" name="ci_p_id" class="selectedValueP">
								<div class="input-container2">
									<input readonly="readonly" class="displayNameP" type="text"
										placeholder="アイテム名">
								</div>
								<div class="input-container2">
									<input name="ci_count" placeholder="買い求める数" type="text">
								</div>
								<div class="input-container2">
									<input name="ci_unit_price" placeholder="買い求める価格" type="text">
								</div>
							</div>
						</div>
						<div class="button">
							<button class="insert-btn">作成</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="jh/js/modal.js"></script>
<script type="text/javascript" src="jh/js/contract_company.js"></script>
<script type="text/javascript" src="jh/js/employee_search.js"></script>
</html>
