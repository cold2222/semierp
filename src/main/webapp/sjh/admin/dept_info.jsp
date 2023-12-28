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
			<div class="col-conte contract">${importDept.c_deptno }</div>
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
	</div>
	
	
	<br>
	<div>
		부서번호: ${distributionDept.d_deptno } <br> 부서이름:
		${distributionDept.d_dept } <br> 인원수: ${distributionDept.d_count }
		<br> 현월: ${currentYearMonth } <br> 배차 대기:
		${distributionDept.d_waiting } <br> 배차 완료 :
		${distributionDept.d_allocated }<br> 현월 배차 완료 :
		${distributionDept.d_allocatedThisMonth } <br> 현월 배송 완료 :
		${distributionDept.d_completedThisMonth }<br> 만기된 운송 :
		${distributionDept.d_expired } <br> 만기일 운송 :
		${distributionDept.d_dueDate } <br> 당일 운송 :
		${distributionDept.d_todayDelivery } <br> 당일 운송 완료 :
		${distributionDept.d_todayCompleted } <br>
	</div>
</body>
</html>