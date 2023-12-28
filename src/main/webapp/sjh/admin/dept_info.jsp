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
	<div class="content_title">部署</div>
	<hr>
	<div class="contract-container">
		<div class="col-titles">
			<div class="col-title contract">コード</div>
			<div class="col-title contract">部 署</div>
			<div class="col-title contract">員 数</div>
			<div class="col-title contract">日 付</div>
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
			<div class="col-content contract">${currentYearMonth }</div>
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
			<div class="col-content contract">${currentYearMonth }</div>
			<div class="col-content contract">${salesDept.c_contract_count }</div>
			<div class="col-content contract">${salesDept.c_contract_items }</div>
			<div class="col-content contract">${salesDept.c_total_price }</div>
			<div class="col-content contract">${salesDept.c_contract_completed }</div>
			<div class="col-content contract">${salesDept. c_awaiting_stock }</div>
		</div>
	</div>
	<div class="distribution-container">
		<div class="col-titles">
			<div class="col-title distribution">コード</div>
			<div class="col-title distribution">部 署</div>
			<div class="col-title distribution">員 数</div>
			<div class="col-title distribution">日 付</div>
			<div class="col-title distribution">배차대기</div>
			<div class="col-title distribution">배차완료</div>
		</div>
		<div class="col-contents">
			<div class="col-content distribution">${distributionDept.d_deptno }</div>
			<div class="col-content distribution">${distributionDept.d_dept }</div>
			<div class="col-content distribution">${distributionDept.d_count }</div>
			<div class="col-content distribution">${currentYearMonth }</div>
			<div class="col-content distribution">${distributionDept.d_waiting }</div>
			<div class="col-content distribution">${distributionDept.d_allocated }</div>
		</div>
		<div class="col-titles">
			<div class="col-title distribution">배차 완료(月)</div>
			<div class="col-title distribution">배송 완료(月)</div>
			<div class="col-title distribution">만기된 운송</div>
			<div class="col-title distribution">만기일</div>
			<div class="col-title distribution">운송(日)</div>
			<div class="col-title distribution">운송 완료(日)</div>
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
</body>
</html>