<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="jh/css/contract_reg_modal.css">
<link rel="stylesheet" href="jh/css/contract_reg.css"></head>
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
			<form action="MakeContractC" method="post">
				<div class="bbs-content1">
					<div class="content1-main">
						<div class="input-container-date">
							作成日 <input type="date" readonly="readonly" name="c_created_date"
								id="c_created_date" value="${contract.c_created_date }">
						</div>
						<div class="content2-main">
							<div class="input-container-date">
								納期日 <input type="date" name="c_due_date" required="required"
									value="${contract.c_due_date}">
							</div>
							<div class="input-container-date2">
								輸入／販売 <select id="transactionType" name="c_type">
									<option value="1" selected="selected">輸入</option>
									<option value="2">販売</option>
								</select>
							</div>
						</div>
					</div>
					<input type="hidden" name="c_c_no" readonly="readonly"
						id="selectedValue" value="${contract.c_c_no}">
					<div class="text-input">
						<div class="text-input-graph">
							<div>取引先の検索</div>
							<div class="id-search">取引担当社員の検索</div>
						</div>
						<div class="text-input-graph2">
							<div class="input-container">
								<input readonly="readonly" id="displayName" placeholder="取引先検索"
									required="required" type="text" value="${contract.c_name}">
							</div>
							<div class="input-container">
								<input name="e_name" placeholder="社員検索" id="employeeSearch"
									type="text" readonly="readonly" required="required"
									value="${contract.e_name}">
							</div>
						</div>
					</div>
					<input type="hidden" name="c_e_id" id="e_id" required="required"
						value="${contract.c_e_id}">
					<div class="input-container"></div>
				</div>

				<input type="hidden" name="c_status" placeholder="取引状態"
					value="${contract.c_status}">


				<div class="contract-reg-main">
					<div class="company-item-title">アイテム名</div>
					<div class="company-main-title">品の個数</div>
					<div class="company-main-title">単価</div>
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
							<input name="ci_count" placeholder="品の個数" type="text">
						</div>
						<div class="input-container2">
							<input name="ci_unit_price" placeholder="単価" type="text">
						</div>
					</div>
					<c:forEach var="items" items="${contract.items}">
						<div style="display: block;" class="bbs-content2">
							<input name="ci_no" type="hidden" value="${items.ci_no}">
							<input name="ci_c_contract_no" type="hidden"
								value="${items.ci_c_contract_no}"> <input type="hidden"
								name="ci_p_id" class="selectedValueP" value="${items.ci_p_id}">
							<div class="input-container2">
								<input readonly="readonly" name="p_name" class="displayNameP"
									type="text" value="${items.p_name}">
							</div>
							<div class="input-container2">
								<input name="ci_count" type="text" value="${items.ci_count}">
							</div>
							<div class="input-container2">
								<input name="ci_unit_price" placeholder="単価" type="text"
									value="${items.ci_unit_price}">
							</div>
							<button type="button"
								onclick="deleteContractItem('${items.ci_no}','${contract.c_contract_no}')">削除</button>
						</div>
					</c:forEach>
				</div>
				<div class="button">
					<button class="insert-btn" type="button" onclick="addRow()">アイテム欄の追加</button>
				</div>
				<div class="button">
					<button class="insert-btn">修正</button>
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript" src="jh/js/modal.js"></script>
<script type="text/javascript" src="jh/js/updateCont.js"></script>
<script type="text/javascript" src="jh/js/contract_company.js"></script>
<script type="text/javascript" src="jh/js/employee_search.js"></script>
</html>
