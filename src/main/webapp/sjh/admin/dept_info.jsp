<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/sjh/admin/dept_info.css">
<title>Insert title here</title>
</head>
<body>
	<div class="content_title"><div>部署</div><div></div><div>日付 : ${currentDate }</div></div>
	<hr>
	<div class="contract-container">
		<h2>輸入/販売</h2>
		<div class="col-titles">
			<div class="col-title contract">コード</div>
			<div class="col-title contract">部 署</div>
			<div class="col-title contract">員 数</div>
			<div class="col-title contract">契約(月)</div>
			<div class="col-title contract">契約物品(月)</div>
			<div class="col-title contract">金額(月)</div>
			<div class="col-title contract">契約完了(月)</div>
			<div class="col-title contract">契約進行</div>
		</div>
		<div class="col-contents">
			<div class="col-content contract">${importDept.c_deptno }</div>
			<div class="col-content contract">${importDept.c_dept }</div>
			<div class="col-content contract">${importDept.c_count }</div>
			<div class="col-content contract">${importDept.c_contract_count }</div>
			<div class="col-content contract">${importDept.c_contract_items }</div>
			<div class="col-content contract">${importDept.c_total_price }</div>
			<div class="col-content contract">${importDept.c_contract_completed }</div>
			<div class="col-content contract">${importDept. c_awaiting_stock }</div>
		</div>
		<div class="col-contents">
			<div class="col-content contract">${salesDept.c_deptno }</div>
			<div class="col-content contract">${salesDept.c_dept }</div>
			<div class="col-content contract">${salesDept.c_count }</div>
			<div class="col-content contract">${salesDept.c_contract_count }</div>
			<div class="col-content contract">${salesDept.c_contract_items }</div>
			<div class="col-content contract">${salesDept.c_total_price }</div>
			<div class="col-content contract">${salesDept.c_contract_completed }</div>
			<div class="col-content contract">${salesDept. c_awaiting_stock }</div>
		</div>
	</div>
	<div class="distribution-container">
	<h2>運送</h2>
		<div class="col-titles">
			<div class="col-title distribution1">コード</div>
			<div class="col-title distribution1">部 署</div>
			<div class="col-title distribution1">員 数</div>
			<div class="col-title distribution1">배차대기</div>
			<div class="col-title distribution1">배차완료</div>
		</div>
		<div class="col-contents">
			<div class="col-content distribution1">${distributionDept.d_deptno }</div>
			<div class="col-content distribution1">${distributionDept.d_dept }</div>
			<div class="col-content distribution1">${distributionDept.d_count }</div>
			<div class="col-content distribution1">${distributionDept.d_waiting }</div>
			<div class="col-content distribution1">${distributionDept.d_allocated }</div>
		</div>
		<div class="col-titles margin-top">
			<div class="col-title distribution2">배차 완료(月)</div>
			<div class="col-title distribution2">배송 완료(月)</div>
			<div class="col-title distribution2">만기된 운송</div>
			<div class="col-title distribution2">만기일</div>
			<div class="col-title distribution2">운송(日)</div>
			<div class="col-title distribution2">운송 완료(日)</div>
		</div>
		<div class="col-contents">
			<div class="col-content distribution2">${distributionDept.d_allocatedThisMonth }</div>
			<div class="col-content distribution2">${distributionDept.d_completedThisMonth }</div>
			<div class="col-content distribution2">${distributionDept.d_expired }</div>
			<div class="col-content distribution2">${distributionDept.d_dueDate }</div>
			<div class="col-content distribution2">${distributionDept.d_todayDelivery }</div>
			<div class="col-content distribution2">${distributionDept.d_todayCompleted }</div>
		</div>
	</div>
	<div class="warehouse-container">
	<h2>在庫</h2>
		<div class="col-titles">
			<div class="col-title warehouse1">コード</div>
			<div class="col-title warehouse1">部 署</div>
			<div class="col-title warehouse1">員 数</div>
			<div class="col-title warehouse1">상품종류</div>
			<div class="col-title warehouse1">총가치</div>
			<div class="col-title warehouse1">재고부족상품</div>
			<div class="col-title warehouse1">재고초과상품</div>
		</div>
		<div class="col-contents">
			<div class="col-content warehouse1">${warehouseDept.w_deptno }</div>
			<div class="col-content warehouse1">${warehouseDept.w_dept }</div>
			<div class="col-content warehouse1">${warehouseDept.w_count }</div>
			<div class="col-content warehouse1">${warehouseDept.w_products }</div>
			<div class="col-content warehouse1">${warehouseDept.w_value }</div>
			<div class="col-content warehouse1">${warehouseDept.w_underMinStock }</div>
			<div class="col-content warehouse1">${warehouseDept.w_overMaxStock }</div>
		</div>
		
		<div class="col-titles margin-top">
			<div class="col-title warehouse2">입고완료(月)</div>
			<div class="col-title warehouse2">입고대기(月)</div>
			<div class="col-title warehouse2">입고(日)</div>
			<div class="col-title warehouse2">입고완료(日)</div>
			<div class="col-title warehouse2">출고완료(月)</div>
			<div class="col-title warehouse2">출고대기(月)</div>
			<div class="col-title warehouse2">출고(日)</div>
			<div class="col-title warehouse2">출고완료(日)</div>
		</div>
		<div class="col-contents">
			<div class="col-content warehouse2">${warehouseDept.w_stockInCompletedThisMonth }</div>
			<div class="col-content warehouse2">${warehouseDept.w_watingStockInThisMonth }</div>
			<div class="col-content warehouse2">${warehouseDept.w_stockInToday }</div>
			<div class="col-content warehouse2">${warehouseDept.w_stockInCompletedToday }</div>
			<div class="col-content warehouse2">${warehouseDept.w_stockOutCompletedThisMonth }</div>
			<div class="col-content warehouse2">${warehouseDept.w_watingStockOutThisMonth }</div>
			<div class="col-content warehouse2">${warehouseDept.w_stockOutToday }</div>
			<div class="col-content warehouse2">${warehouseDept.w_stockOutCompletedToday }</div>
		</div>
</body>
</html>