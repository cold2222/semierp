<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="sj/warehouse_css/in_warehouse_detail.css">
</head>
<body>

	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">入庫受領確認</div>
		</div>
		<div class="content-body">
			<div class="bbs-content">
				<div class="bbs-content1 bbs-content">
					<div class="bbs-content-main">
						<div class="bbs-content-body">
							<div class="bbs-content-bbs">
								<div class="bbs-main">
									<div class="bbs-main-text1 bbs-main-title">契約ID</div>
									<div class="bbs-main-text1 bbs-main-title">契約日</div>
									<div class="bbs-main-text1 bbs-main-title">取引先会社名</div>
									<div class="bbs-main-text1 bbs-main-title">担当者</div>
								</div>
								<div class="bbs-main">
									<div class="bbs-main-text1 bbs-main-text">
										<input value="${contract.c_contract_no}" readonly="readonly">
									</div>
									<div class="bbs-main-text1 bbs-main-text">
										<input value="${contract.c_created_date}" readonly="readonly">
									</div>
									<div class="bbs-main-text1 bbs-main-text">
										<input value="${contract.c_name}" readonly="readonly">
									</div>
									<div class="bbs-main-text1 bbs-main-text">
										<input value="${contract.e_name}" readonly="readonly">
									</div>
								</div>
								<form action="InWarehouseDetailC" method="post"
									id="warehouseForm">
									<div class="bbs-main">
										<div class="bbs-main-text2 bbs-main-title">품목 ID</div>
										<div class="bbs-main-text2 bbs-main-title">타입</div>
										<div class="bbs-main-text2 bbs-main-title">제품 이름</div>
										<div class="bbs-main-text2 bbs-main-title">p_quantity</div>
										<div class="bbs-main-text2 bbs-main-title">단위</div>
										<div class="bbs-main-text2 bbs-main-title">수량</div>
										<div class="bbs-main-text2 bbs-main-title">창고</div>
									</div>

									<c:forEach var="t" items="${inWarehouse}">
										<div class="bbs-main">
											<div class="bbs-main-text2 bbs-main-text">
												<input type="hidden" name="c_contract_no"
													value="${param.c_contract_no }">
											</div>
											<div class="bbs-main-text2 bbs-main-text">
												<input name="ci_p_id" value="${t.ci_p_id}"
													readonly="readonly">
											</div>
											<div class="bbs-main-text2 bbs-main-text">
												<input name="p_type" value="${t.p_type}" readonly="readonly">
											</div>
											<div class="bbs-main-text2 bbs-main-text">
												<input name="p_name" value="${t.p_name}" readonly="readonly">
											</div>
											<div class="bbs-main-text2 bbs-main-text">
												<input name="p_quantity" value="${t.p_quantity}"
													readonly="readonly">
											</div>
											<div class="bbs-main-text2 bbs-main-text">
												<input name="p_si" value="${t.p_si}" readonly="readonly">
											</div>
											<div class="bbs-main-text2 bbs-main-text">
												<input name="ci_count" value="${t.ci_count}"
													readonly="readonly">
											</div>
											<div class="bbs-main-text2 bbs-main-text">
												<select name="warehouse_id">
													<option value="1">1창고</option>
													<option value="2">2창고</option>
													<option value="3">3창고</option>
												</select>
											</div>
										</div>
									</c:forEach>
									<button id="fromBtn">수령확인</button>
								</form>
								<button onclick="history.back()">돌아가기</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="sj/warehouse/warehouse_js/warehouse_detail.js"></script>
</html>