<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="jh/css/contract_reg_modal.css">
<link rel="stylesheet" href="jh/css/updateContract.css">
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
			<div class="content-head-text">契約書修正</div>
		</div>
		<div class="content-body">
			<div class="bbs-content">

				<form action="UpdateContC" method="post">
					<div class="bbs-content1">
						<div class="content1-main">
							<div class="input-container">
								<div class="button">
									<button type="button" class="insert-btn"
										onclick="deleteContract('${contract.c_contract_no}')">契約書削除</button>
								</div>
								作成日: <input type="date" readonly="readonly"
									name="c_created_date" value="${contract.c_created_date }">
							</div>
						</div>
						<div class="table-container">
							<div class="row">
								<div>
									契約書番号<input name="c_contract_no"
										value="${contract.c_contract_no}">
								</div>
								<div>
									<input type="hidden" name="c_c_no" id="selectedValue"
										value="${contract.c_c_no}">
								</div>
								<div>
									<input readonly="readonly" id="displayName"
										value="${contract.c_name}">
								</div>
								<div>
									<input type="hidden" name="c_e_id" id="e_id"
										value="${contract.c_e_id}" required="required">
								</div>
								<c:if test="${contract.e_deptno eq 101}">
									<div>
										<input name="e_name" id="employeeSearch" readonly="readonly"
											required="required"
											value="輸入事業部 :${contract.e_rank } ${contract.e_name}">
									</div>
								</c:if>
								<c:if test="${contract.e_deptno eq 102}">
									<div>
										<input name="e_name" id="employeeSearch" readonly="readonly"
											required="required"
											value="販売営業部 :${contract.e_rank } ${contract.e_name}">
									</div>
								</c:if>
								<div>
									納期日<input type="date" name="c_due_date" required="required"
										value="${contract.c_due_date}">
								</div>
								<div>
									配送予定日<input type="date" name="c_delivery_date"
										readonly="readonly" value="${contract.c_delivery_date}">
								</div>
								<div>
									配送完了日<input type="date" name="c_completed_date"
										readonly="readonly" value="${contract.c_completed_date}">
								</div>
								<div>
									取引状態 : <input type="hidden" name="c_status"
										value="${contract.c_status}">
									<c:if test="${contract.c_type eq 1 && contract.c_status eq 1}">配送未指定</c:if>
									<c:if test="${contract.c_type eq 1 && contract.c_status eq 2}">配送日確定</c:if>
									<c:if test="${contract.c_type eq 1 && contract.c_status eq 3}">配送完了</c:if>
									<c:if test="${contract.c_type eq 1 && contract.c_status eq 4}">倉庫摘載済み</c:if>
									<c:if test="${contract.c_type eq 2 && contract.c_status eq 1}">配送未指定</c:if>
									<c:if test="${contract.c_type eq 2 && contract.c_status eq 2}">配送日確定</c:if>
									<c:if test="${contract.c_type eq 2 && contract.c_status eq 3}">配送準備中</c:if>
									<c:if test="${contract.c_type eq 2 && contract.c_status eq 4}">配送完了</c:if>
								</div>
								<div>
									契約書の種類
									<c:if test="${contract.c_type == 1}">
										<select id="transactionType" name="c_type">
											<option value="1" selected="selected">輸入</option>
											<option value="2">販売</option>
										</select>
									</c:if>
									<c:if test="${contract.c_type == 2}">
										<select id="transactionType" name="c_type">
											<option value="1">輸入</option>
											<option value="2" selected="selected">販売</option>
										</select>
									</c:if>
								</div>
							</div>
							<div>
							<div class="contract-reg-main">
								<div class="company-item-title">アイテム名</div>
								<div class="company-main-title">品の個数</div>
								<div class="company-main-title">単価</div>
							</div>
							<div class="hidden-row" style="display: none;">
								<div>
									<input name="ci_no" type="hidden">
								</div>
								<div>
									<input type="hidden" name="ci_p_id" class="selectedValueP">
								</div>
								<div>
									<input readonly="readonly" class="displayNameP"
										placeholder="アイテム名">
								</div>
								<div>
									<input name="ci_count" placeholder="品の個数">
								</div>
								<div>
									<input name="ci_unit_price" placeholder="単価">
								</div>
							</div>
							<c:forEach var="items" items="${contract.items}">
								<div class="item-row" style="display: block;">
									<input name="ci_no" type="hidden" value="${items.ci_no}">
									<input type="hidden" name="ci_p_id" value="${items.ci_p_id}"
										class="selectedValueP"> <input readonly="readonly"
										class="displayNameP"
										value="${items.p_type}: ${items.p_name} ${items.p_quantity}${items.p_si}"
										placeholder="アイテム名"> <input name="ci_count"
										placeholder="品の個数" value="${items.ci_count}"> <input
										name="ci_unit_price" placeholder="単価"
										value="${items.ci_unit_price}">
									<button type="button"
										onclick="deleteContractItem('${items.ci_no}','${contract.c_contract_no}')">アイテム削除</button>
								</div>
							</c:forEach>
							</div>
						</div>
					</div>
					<button type="button" onclick="addRow()">アイテム欄の追加</button>
					<button>修正</button>
				</form>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript" src="jh/js/modal_update.js"></script>
<script type="text/javascript" src="jh/js/updateCont.js"></script>
<script type="text/javascript" src="jh/js/contract_company.js"></script>
<script type="text/javascript" src="jh/js/employee_search.js"></script>
</html>
