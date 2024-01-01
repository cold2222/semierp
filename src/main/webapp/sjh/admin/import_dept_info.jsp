<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/sjh/admin/dept_info.css">
<link rel="stylesheet" href="css/sjh/admin/contract_dept_info.css">
<script type="text/javascript"
	src="js/sjh/admin/utils/input_date_init.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="content_title">
		<div>輸入</div>
		<div></div>
		<div class="date">
			日付 : <input type="date" id="dateInput" name="setDate" required
				onchange="redirectToServlet('ImportDeptC','${param.pageNo }')">
		</div>
	</div>
	<hr>
	<div class="contract-container">
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
	</div>
	<div class="Staff_container">
		<div class="col-titles">
			<div class="col-title-item">社員番号</div>
			<div class="col-title-item">お名前</div>
			<div class="col-title-item">職 級</div>
			<div class="col-title-item">Tel</div>
			<div class="col-title-item col-long">E-Mail</div>
			<div class="col-title-item">契約件数(月)</div>
			<div class="col-title-item">契約商品数(月)</div>
			<div class="col-title-item">契約金額(月)</div>
			<div class="col-title-item button">修 正</div>
		</div>
		<c:forEach var="contractStaffInfo" items="${contractStaffsInfo}">
			<div class="col-contents">
				<div class="col-content-item">${contractStaffInfo.cs_no }</div>
				<div class="col-content-item">${contractStaffInfo.cs_name}</div>
				<div class="col-content-item">${contractStaffInfo.cs_rank }</div>
				<div class="col-content-item">${contractStaffInfo.cs_tel}</div>
				<div class="col-content-item col-long">${contractStaffInfo.cs_email }</div>
				<div class="col-content-item">${contractStaffInfo.cs_thisMonthContract}</div>
				<div class="col-content-item">${contractStaffInfo.cs_thisMonthProduct}</div>
				<div class="col-content-item">${contractStaffInfo.cs_thisMonthTotalPrice}</div>
				<div class="col-content-item button">
					<button onclick="location.href='StaffAdminModifyC?e_no='">Detail</button>
				</div>
			</div>
		</c:forEach>
		<div class="btn-area">
			<div></div>
			<div class="btn-area-mid">
				<c:if test="${currentPage != 1 }">
					<button class="idx-btn"
						onclick="location.href='ImportDeptC?pageNo=${currentPage - 1}&setDate=${param.setDate}'">
						prev</button>
				</c:if>
				<c:forEach var="index" items="${indexList}">
					<c:if test="${index ne 0}">
						<button class="idx-btn"
							onclick="location.href='ImportDeptC?pageNo=${index}&setDate=${param.setDate}'">${index}</button>
					</c:if>
				</c:forEach>
				<c:if test="${currentPage != lastPage }">
					<button class="idx-btn"
						onclick="location.href='ImportDeptC?pageNo=${currentPage + 1}&setDate=${param.setDate}'">
						next</button>
				</c:if>
			</div>
			<div></div>
		</div>
	</div>
</body>
</html>