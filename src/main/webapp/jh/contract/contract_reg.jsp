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
<link rel="stylesheet" href="jh/css/contract_modal.css">
<script>
	function toggleDateField() {
		var transactionType = document.getElementById("transactionType");
		var dateField = document.getElementById("dateField");
		var labelDate = document.getElementById("labelDate");

		if (transactionType.value === "1") { // 선택된 값이 '1'인 경우(입항)
			labelDate.innerText = "入港日"; // 필드 라벨 변경
		} else if (transactionType.value === "2") { // 선택된 값이 '2'인 경우(납기)
			labelDate.innerText = "納期日"; // 필드 라벨 변경
		}
	}
</script>
</head>
<body>
	<!-- 사원 검색용모달 -->
	<div id="empSearchModal" class="modal-background">
		<div id="empModal" class="modal-content">
			<div>
				<div class="search-input">
					<input class="search-btn" placeholder="本社員検索" id="empSearchBtn" >
					<button class="close-button" id="empCloseModalBtn">
						<div class="close-icon"></div>
					</button>
				</div>
				<div class="empList"></div>
			</div>
			<div class="modal-foot">
			</div>
		</div>
	</div>
	<div id="modalBackground" class="modal-background">
		<!-- 모달 내용 -->
		<div id="myModal" class="modal-content">
			<div>
				<div class="search-input">
					<input class="search-btn" placeholder="取引先検索" id="search-btn">
					<button class="close-button" id="closeModalBtn">
						<div class="close-icon"></div>
					</button>
				</div>
				<div class="list"></div>
			</div>
			<div class="modal-foot"></div>
		</div>
	</div>

	<!-- 상품 검색용 모달 -->
	<div id="productModalBackground" class="modal-background">
		<div id="productModal" class="modal-content">
			<div>
				<div class="search-input">
					<input class="search-btn" placeholder="アイテム検索" id="productSearchInput">
					<button class="close-button" id="closeProductModalBtn">
						<div class="close-icon"></div>
					</button>
				</div>
				<div class="listProduct"></div>
			</div>
			<div class="modal-foot">
			</div>
		</div>
	</div>

	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">契約書作成</div>
		</div>
		<div class="content-body">
			<form action="MakeContractC" method="post">
				<div class="bbs-content1">
					<div class="content1-main">
						<div class="input-container-date">
							作成日 <input type="date" readonly="readonly" name="c_created_date"
								id="c_created_date">
						</div>
						<div class="content2-main">
							<div class="input-container-date" id="dateField">
								<label id="labelDate">入港日</label> <input type="date"
									name="c_due_date" required="required">
							</div>
							<div class="input-container-date2">
								輸入／販売 <select id="transactionType" name="c_type"
									onchange="toggleDateField()">
									<option value="1">輸入</option>
									<option value="2">販売</option>
								</select>
							</div>
						</div>
					</div>
					<input type="hidden" name="c_c_no" readonly="readonly"
						id="selectedValue">
					<div class="text-input">
						<div class="text-input-graph">
							<div>取引先の検索</div>
							<div class="id-search">取引担当社員の検索</div>
						</div>
						<div class="text-input-graph2">
							<div class="input-container">
								<input readonly="readonly" id="displayName" placeholder="取引先検索"
									required="required" type="text">
							</div>
							<div class="input-container">
								<input name="e_name" placeholder="社員検索" id="employeeSearch"
									type="text" readonly="readonly" required="required">
							</div>
						</div>
					</div>
					<input type="hidden" name="c_e_id" id="e_id" required="required">
					<div class="input-container"></div>
				</div>

				<input type="hidden" name="c_status" placeholder="取引状態" value="1">


				<div class="contract-reg-main">
					<div class="company-item-title">アイテム名</div>
					<div class="company-main-title">数量</div>
					<div class="company-main-title">単価</div>
				</div>
				<div id="contractTable">
					<div style="display: none;" class="bbs-content2">
						<input name="ci_c_contract_no" type="hidden"> <input
							type="hidden" name="ci_p_id" class="selectedValueP">
						<div class="input-container2">
							<input readonly="readonly" class="displayNameP" type="text" required="required"
								placeholder="アイテム名">
						</div>
						<div class="input-container2">
							<input name="ci_count" placeholder="数量" type="text" required="required">
						</div>
						<div class="input-container2">
							<input name="ci_unit_price" placeholder="単価" type="text" required="required"	>
						</div>
					</div>
				</div>
				<div class="button">
					<button class="insert-btn" type="button" onclick="addRow()">アイテム欄の追加</button>
				</div>
				<div class="button">
					<button class="insert-btn">作成</button>
				</div>
			</form>
		</div>
	</div>
</body>

<script type="text/javascript" src="jh/js/modal.js"></script>
<script type="text/javascript" src="jh/js/contract_company.js"></script>
<script type="text/javascript" src="jh/js/employee_search.js"></script>
</html>
