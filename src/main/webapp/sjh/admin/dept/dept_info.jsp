<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/sjh/admin/dept_info.css">
<script type="text/javascript"
	src="js/sjh/admin/utils/input_date_init.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="content_title">
		<div>部署</div>
		<div></div>
		<div class="date">
			日付 : <input type="date" id="dateInput" name="setDate" required
				onchange="redirectToServlet('DeptC')">
		</div>
	</div>
	<hr>
	<div class="contract-container">
		<h2>輸入/販売</h2>
		<div class="col-titles">
			<div class="col-title contract">コード</div>
			<div class="col-title contract">部 署</div>
			<div class="col-title contract">員 数</div>
			<div class="col-title contract">契約(月)</div>
			<div class="col-title contract">契約物品(月)</div>
			<div class="col-title contract">仕入れ金額(月)</div>
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
		<div class="col-titles margin-top">
			<div class="col-title contract">コード</div>
			<div class="col-title contract">部 署</div>
			<div class="col-title contract">員 数</div>
			<div class="col-title contract">契約(月)</div>
			<div class="col-title contract">契約物品(月)</div>
			<div class="col-title contract">売上(月)</div>
			<div class="col-title contract">契約完了(月)</div>
			<div class="col-title contract">契約進行</div>
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
			<div class="col-title distribution">コード</div>
			<div class="col-title distribution">部 署</div>
			<div class="col-title distribution">員 数</div>
			<div class="col-title distribution">配車待機</div>
			<div class="col-title distribution">配車完了</div>
		</div>
		<div class="col-contents">
			<div class="col-content distribution">${distributionDept.d_deptno }</div>
			<div class="col-content distribution">${distributionDept.d_dept }</div>
			<div class="col-content distribution">${distributionDept.d_count }</div>
			<div class="col-content distribution">${distributionDept.d_waiting }</div>
			<div class="col-content distribution">${distributionDept.d_allocated }</div>
		</div>
		<div class="col-titles margin-top">
			<div class="col-title distribution">配車完了(月)</div>
			<div class="col-title distribution">運送完了(月)</div>
			<div class="col-title distribution">運送遅延</div>
			<div class="col-title distribution">満期日</div>
			<div class="col-title distribution">運送件(日)</div>
			<div class="col-title distribution">運送完了(日)</div>
		</div>
		<div class="col-contents">
			<div class="col-content distribution">${distributionDept.d_allocatedThisMonth }</div>
			<div class="col-content distribution">${distributionDept.d_completedThisMonth }</div>
			<div class="col-content distribution">${distributionDept.d_expired }</div>
			<div class="col-content distribution">${distributionDept.d_dueDate }</div>
			<div class="col-content distribution">${distributionDept.d_todayDelivery }</div>
			<div class="col-content distribution">${distributionDept.d_todayCompleted }</div>
		</div>
	</div>
	<div class="warehouse-container">
		<h2>在庫</h2>
		<div class="col-titles">
			<div class="col-title warehouse">コード</div>
			<div class="col-title warehouse">部 署</div>
			<div class="col-title warehouse">員 数</div>
			<div class="col-title warehouse">商品種類</div>
			<div class="col-title warehouse">総合価値</div>
			<div class="col-title warehouse">在庫未満商品数</div>
			<div class="col-title warehouse">在庫超過商品数</div>
		</div>
		<div class="col-contents">
			<div class="col-content warehouse">${warehouseDept.w_deptno }</div>
			<div class="col-content warehouse">${warehouseDept.w_dept }</div>
			<div class="col-content warehouse">${warehouseDept.w_count }</div>
			<div class="col-content warehouse">${warehouseDept.w_products }</div>
			<div class="col-content warehouse">${warehouseDept.w_value }</div>
			<div class="col-content warehouse">${warehouseDept.w_underMinStock }</div>
			<div class="col-content warehouse">${warehouseDept.w_overMaxStock }</div>
		</div>

		<div class="col-titles margin-top">
			<div class="col-title warehouse">入庫予定(月)</div>
			<div class="col-title warehouse">入庫完了(月)</div>
			<div class="col-title warehouse">入庫(日)</div>
			<div class="col-title warehouse">入庫完了(日)</div>
			<div class="col-title warehouse">出庫予定(月)</div>
			<div class="col-title warehouse">出庫完了(月)</div>
			<div class="col-title warehouse">出庫(日)</div>
			<div class="col-title warehouse">出庫完了(日)</div>
		</div>
		<div class="col-contents">
			<div class="col-content warehouse">${warehouseDept.w_watingStockInThisMonth }</div>
			<div class="col-content warehouse">${warehouseDept.w_stockInCompletedThisMonth }</div>
			<div class="col-content warehouse">${warehouseDept.w_stockInToday  }</div>
			<div class="col-content warehouse">${warehouseDept.w_stockInCompletedToday }</div>
			<div class="col-content warehouse">${warehouseDept.w_watingStockOutThisMonth }</div>
			<div class="col-content warehouse">${warehouseDept.w_stockOutCompletedThisMonth }</div>
			<div class="col-content warehouse">${warehouseDept.w_stockOutToday }</div>
			<div class="col-content warehouse">${warehouseDept.w_stockOutCompletedToday }</div>
		</div>
	</div>
</body>
</html>