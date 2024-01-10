<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function handleGoBack() {

		event.preventDefault(); // 폼으로 제출 안되게 함
		history.back();
	}
</script>
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
								<div class="bbs-main contract-bbs">
									<div class="bbs-main-text3 bbs-main-title-a">契約ID :
										${contract.c_contract_no}</div>
									<div class="bbs-main-text4 bbs-main-title-b">契約日 :
										${contract.c_created_date}</div>
								</div>
								<div class="bbs-main contract-bbs bbs-main-between">
									<div class="bbs-main-text3 bbs-main-title-a">取引先名 :
										${contract.c_name}</div>
									<div class="bbs-main-text4 bbs-main-title-b">取引担当社員 :
										${contract.e_name}</div>
								</div>
								<form action="InWarehouseDetailC" method="post"
									id="warehouseForm">
									<div class="bbs-main">
										<div class="bbs-main-text2 bbs-main-title-s">商品 ID</div>
										<div class="bbs-main-text2 bbs-main-title-l">タイプ</div>
										<div class="bbs-main-text2 bbs-main-title-l">商品名</div>
										<div class="bbs-main-text2 bbs-main-title-s">単位量</div>
										<div class="bbs-main-text2 bbs-main-title-s">単位</div>
										<div class="bbs-main-text2 bbs-main-title-s">数量</div>
										<div class="bbs-main-text2 bbs-main-title-s">倉庫</div>
									</div>
									<c:forEach var="t" items="${inWarehouse}" varStatus="loop">
										<input type="hidden" name="c_contract_no"
											value="${param.c_contract_no }">
										<div class="bbs-main"
											style="background-color: ${loop.index % 2 == 0 ? 'white' : '#f0f0f0'};">
											<div class="bbs-main-text2 bbs-main-text-s">
												<input name="ci_p_id" value="${t.ci_p_id}" class="input-s"
													readonly="readonly"
													style="background-color: ${loop.index % 2 == 0 ? 'white' : '#f0f0f0'};">
											</div>
											<div class="bbs-main-text2 bbs-main-text-l">
												<input class="input-l" name="p_type" value="${t.p_type}"
													readonly="readonly"
													style="background-color: ${loop.index % 2 == 0 ? 'white' : '#f0f0f0'};">
											</div>
											<div class="bbs-main-text2 bbs-main-text-l">
												<input class="input-l" name="p_name" value="${t.p_name}"
													readonly="readonly"
													style="background-color: ${loop.index % 2 == 0 ? 'white' : '#f0f0f0'};">
											</div>
											<div class="bbs-main-text2 bbs-main-text-s">
												<input class="input-s" name="p_quantity"
													value="${t.p_quantity}" readonly="readonly"
													style="background-color: ${loop.index % 2 == 0 ? 'white' : '#f0f0f0'};">
											</div>
											<div class="bbs-main-text2 bbs-main-text-s">
												<input class="input-s" name="p_si" value="${t.p_si}"
													readonly="readonly"
													style="background-color: ${loop.index % 2 == 0 ? 'white' : '#f0f0f0'};">

											</div>
											<div class="bbs-main-text2 bbs-main-text-s">
												<input class="input-s" name="ci_count" value="${t.ci_count}"
													readonly="readonly"
													style="background-color: ${loop.index % 2 == 0 ? 'white' : '#f0f0f0'};">
											</div>
											<div class="bbs-main-text2 bbs-main-text-s">
												<select name="warehouse_id">
													<option value="1">一般資材倉庫</option>
													<option value="2">油類倉庫</option>
													<option value="3">臨時倉庫</option>
												</select>
											</div>
										</div>
									</c:forEach>
									<div class="button-container">
										<button onclick="handleGoBack()">戻る</button>
										<button id="fromBtn">入庫確認</button>
									</div>
								</form>
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