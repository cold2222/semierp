<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/sjh/admin/dept_info.css">
<link rel="stylesheet" href="css/sjh/admin/distribution_dept_info.css">
<script type="text/javascript"
	src="js/sjh/admin/utils/input_date_init.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="content_title">
		<div>運送</div>
		<div></div>
		<div class="date">
			日付 : <input type="date" id="dateInput" name="setDate" required
				onchange="redirectToServlet('DistributionDeptC','${param.pageNo }')">
		</div>
	</div>
	<hr>
	<div class="distribution-container">
		<div class="col-titles">
			<div class="col-title distribution">コード</div>
			<div class="col-title distribution">部 署</div>
			<div class="col-title distribution">員 数</div>
			<div class="col-title distribution">배차대기</div>
			<div class="col-title distribution">배차완료</div>
		</div>
		<div class="col-contents">
			<div class="col-content distribution">${distributionDept.d_deptno }</div>
			<div class="col-content distribution">${distributionDept.d_dept }</div>
			<div class="col-content distribution">${distributionDept.d_count }</div>
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