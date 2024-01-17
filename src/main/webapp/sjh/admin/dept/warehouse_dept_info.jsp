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
				onchange="redirectToServletWithTowPaging('WarehouseDeptC','${param.pageNo1 }', '${param.pageNo2 }')">
		</div>
	</div>
	<hr>
	<div class="warehouse-container">
		<div class="col-titles">
			<div class="col-title warehouse">コード</div>
			<div class="col-title warehouse">部 署</div>
			<div class="col-title warehouse">員 数</div>
			<div class="col-title warehouse">商品種類</div>
			<div class="col-title warehouse">総合価値</div>
			<div class="col-title warehouse">在庫未満</div>
			<div class="col-title warehouse">在庫超過</div>
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
			<div class="col-content warehouse">${warehouseDept.w_stockInToday }</div>
			<div class="col-content warehouse">${warehouseDept.w_stockInCompletedToday }</div>
			<div class="col-content warehouse">${warehouseDept.w_watingStockOutThisMonth }</div>
			<div class="col-content warehouse">${warehouseDept.w_stockOutCompletedThisMonth }</div>
			<div class="col-content warehouse">${warehouseDept.w_stockOutToday }</div>
			<div class="col-content warehouse">${warehouseDept.w_stockOutCompletedToday }</div>
		</div>
	</div>
	<hr>
	<div class="tableName"> - 社員 - </div>
	<div class="staff_container">
		<div class="staffInfoArea">
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
		</div>
		<div class="btn-area">
			<div></div>
			<div class="btn-area-mid">
				<c:if test="${currentPage1 != 1 }">
					<button class="idx-btn colorGold"
						onclick="location.href='WarehouseDeptC?pageNo1=${currentPage1 - 1}&setDate=${param.setDate}&pageNo2=${currentPage2}'">
						prev</button>
				</c:if>
				<c:forEach var="index" items="${indexList1}">
					<c:if test="${index ne 0}">
						<button class="idx-btn ${currentPage1 == index ? 'colorGold' : ''}"
							onclick="location.href='WarehouseDeptC?pageNo1=${index}&setDate=${param.setDate}&pageNo2=${currentPage2}'">${index}</button>
					</c:if>
				</c:forEach>
				<c:if test="${currentPage1 != lastPage1 }">
					<button class="idx-btn colorGold"
						onclick="location.href='WarehouseDeptC?pageNo1=${currentPage1 + 1}&setDate=${param.setDate}&pageNo2=${currentPage2}'">
						next</button>
				</c:if>
			</div>
			<div></div>
		</div>
	</div>
	<hr>
	<div class="tableName">- 商品 -</div>
	<div class="product_container">
		<div class="productInfoArea">
			<div class="col-titles">
				<div class="col-title-item col-short">番号</div>
				<div class="col-title-item">商品名</div>
				<div class="col-title-item col-long">情報</div>
				<div class="col-title-item">ストック【Min, Max】</div>
				<div class="col-title-item">コスト【総合】</div>
				<div class="col-title-item">メーカー</div>
			</div>
			<c:forEach var="productInfo" items="${productsInfo }">
				<div class="col-contents">
					<div class="col-content-item col-short">${productInfo.p_id }</div>
					<div class="col-content-item">${productInfo.p_name}</div>
					<div class="col-content-item col-long">${productInfo.p_type } ${productInfo.p_si } ${productInfo.p_quantity }</div>
					<div class="col-content-item">${productInfo.p_stock }【${productInfo.p_minStock }, ${productInfo.p_maxStock }】</div>
					<div class="col-content-item">${productInfo.p_unitCost}【${productInfo.p_unitCost * productInfo.p_stock }】</div>
					<div class="col-content-item">${productInfo.p_manufacturer}</div>
				</div>
			</c:forEach>
		</div>
		<div class="btn-area">
			<div></div>
			<div class="btn-area-mid">
				<c:if test="${currentPage2 != 1 }">
					<button class="idx-btn colorGold"
						onclick="location.href='WarehouseDeptC?pageNo1=${currentPage1}&setDate=${param.setDate}&pageNo2=${currentPage2 - 1}'">
						prev</button>
				</c:if>
				<c:forEach var="index" items="${indexList2}">
					<c:if test="${index ne 0}">
						<button class="idx-btn ${currentPage2 == index ? 'colorGold' : ''}"
							onclick="location.href='WarehouseDeptC?pageNo1=${currentPage1}&setDate=${param.setDate}&pageNo2=${index}'">${index}</button>
					</c:if>
				</c:forEach>
				<c:if test="${currentPage2 != lastPage2 }">
					<button class="idx-btn colorGold"
						onclick="location.href='WarehouseDeptC?pageNo1=${currentPage1}&setDate=${param.setDate}&pageNo2=${currentPage2 + 1}'">
						next</button>
				</c:if>
			</div>
			<div></div>
		</div>
	</div>

</body>
</html>