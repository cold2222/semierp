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
	
	<div class="staff_container">
		<div>
			<div class="col-titles">
				<div class="col-title-item">社員番号</div>
				<div class="col-title-item">お名前</div>
				<div class="col-title-item">職 級</div>
				<div class="col-title-item">Tel</div>
				<div class="col-title-item col-long">E-Mail</div>
				<div class="col-title-item">총운송(月)</div>
				<div class="col-title-item">운송완료(月)</div>
				<div class="col-title-item">총운송(日)</div>
				<div class="col-title-item">운송완료(日)</div>
				<div class="col-title-item button">Detail</div>
			</div>
			<c:forEach var="distributionStaffInfo" items="${distributionStaffsInfo}">
				<div class="col-contents">
					<div class="col-content-item">${distributionStaffInfo.ds_no }</div>
					<div class="col-content-item">${distributionStaffInfo.ds_name}</div>
					<div class="col-content-item">${distributionStaffInfo.ds_rank }</div>
					<div class="col-content-item">${distributionStaffInfo.ds_tel}</div>
					<div class="col-content-item col-long">${distributionStaffInfo.ds_email }</div>
					<div class="col-content-item">${distributionStaffInfo.ds_shippingThisMonth}</div>
					<div class="col-content-item">${distributionStaffInfo.ds_completedThisMonth}</div>
					<div class="col-content-item">${distributionStaffInfo.ds_shippingToday}</div>
					<div class="col-content-item">${distributionStaffInfo.ds_completedToday}</div>
					<div class="col-content-item button">
						<button onclick="location.href='StaffAdminModifyC?e_no='">Detail</button>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="btn-area">
			<div></div>
			<div class="btn-area-mid">
				<c:if test="${currentPage != 1 }">
					<button class="idx-btn colorGold"
						onclick="location.href='ImportDeptC?pageNo=${currentPage - 1}&setDate=${param.setDate}'">
						prev</button>
				</c:if>
				<c:forEach var="index" items="${indexList}">
					<c:if test="${index ne 0}">
						<button class="idx-btn ${currentPage == index ? 'colorGold' : ''}"
							onclick="location.href='ImportDeptC?pageNo=${index}&setDate=${param.setDate}'">${index}</button>
					</c:if>
				</c:forEach>
				<c:if test="${currentPage != lastPage }">
					<button class="idx-btn colorGold"
						onclick="location.href='ImportDeptC?pageNo=${currentPage + 1}&setDate=${param.setDate}'">
						next</button>
				</c:if>
			</div>
			<div></div>
		</div>
	</div>
</body>
</html>