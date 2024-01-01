<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/sjh/admin/dept_info.css">
<link rel="stylesheet" href="css/sjh/admin/warehouse_dept_info.css">
<script type="text/javascript"
	src="js/sjh/admin/utils/input_date_init.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="content_title">
		<div>倉庫</div>
		<div></div>
		<div class="date">
			日付 : <input type="date" id="dateInput" name="setDate" required
				onchange="redirectToServlet('DistributionDeptC','${param.pageNo }')">
		</div>
	</div>
	<hr>
	<div class="warehouse-container">
		<div class="col-titles">
			<div class="col-title warehouse">コード</div>
			<div class="col-title warehouse">部 署</div>
			<div class="col-title warehouse">員 数</div>
			<div class="col-title warehouse">상품종류</div>
			<div class="col-title warehouse">총가치</div>
			<div class="col-title warehouse">재고부족상품</div>
			<div class="col-title warehouse">재고초과상품</div>
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

		<div class="col-titles">
			<div class="col-title warehouse">입고완료(月)</div>
			<div class="col-title warehouse">입고대기(月)</div>
			<div class="col-title warehouse">입고(日)</div>
			<div class="col-title warehouse">입고완료(日)</div>
			<div class="col-title warehouse">출고완료(月)</div>
			<div class="col-title warehouse">출고대기(月)</div>
			<div class="col-title warehouse">출고(日)</div>
			<div class="col-title warehouse">출고완료(日)</div>
		</div>
		<div class="col-contents">
			<div class="col-content warehouse">${warehouseDept.w_stockInCompletedThisMonth }</div>
			<div class="col-content warehouse">${warehouseDept.w_watingStockInThisMonth }</div>
			<div class="col-content warehouse">${warehouseDept.w_stockInToday }</div>
			<div class="col-content warehouse">${warehouseDept.w_stockInCompletedToday }</div>
			<div class="col-content warehouse">${warehouseDept.w_stockOutCompletedThisMonth }</div>
			<div class="col-content warehouse">${warehouseDept.w_watingStockOutThisMonth }</div>
			<div class="col-content warehouse">${warehouseDept.w_stockOutToday }</div>
			<div class="col-content warehouse">${warehouseDept.w_stockOutCompletedToday }</div>
		</div>
	</div>
	<hr>
	<div class="Staff_container">
		<div class="col-titles">
			<div class="col-title-item">社員番号</div>
			<div class="col-title-item">お名前</div>
			<div class="col-title-item">職 級</div>
			<div class="col-title-item">Tel</div>
			<div class="col-title-item col-long">E-Mail</div>
		</div>
		<c:forEach var="warehouseStaffInfo" items="${warehouseStaffsInfo}">
			<div class="col-contents">
				<div class="col-content-item">${warehouseStaffInfo.ws_no }</div>
				<div class="col-content-item">${warehouseStaffInfo.ws_name}</div>
				<div class="col-content-item">${warehouseStaffInfo.ws_rank }</div>
				<div class="col-content-item">${warehouseStaffInfo.ws_tel}</div>
				<div class="col-content-item col-long">${warehouseStaffInfo.ws_email }</div>
			</div>
		</c:forEach>
		<div class="btn-area">
			<div></div>
			<div class="btn-area-mid">
				<c:if test="${currentPage != 1 }">
					<button class="idx-btn"
						onclick="location.href='WarehouseDeptC?pageNo=${currentPage - 1}&setDate=${param.setDate}'">
						prev</button>
				</c:if>
				<c:forEach var="index" items="${indexList}">
					<c:if test="${index ne 0}">
						<button class="idx-btn"
							onclick="location.href='WarehouseDeptC?pageNo=${index}&setDate=${param.setDate}'">${index}</button>
					</c:if>
				</c:forEach>
				<c:if test="${currentPage != lastPage }">
					<button class="idx-btn"
						onclick="location.href='WarehouseDeptC?pageNo=${currentPage + 1}&setDate=${param.setDate}'">
						next</button>
				</c:if>
			</div>
			<div></div>
		</div>
	</div>

</body>
</html>