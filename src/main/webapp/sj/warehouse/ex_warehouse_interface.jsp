<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.input-container {
	display: flex;
	margin-bottom: 10px;
}

.input-box {
	border: 1px solid black;
}

.in_name {
	border: 1px solid black;
	width: 73px;
}

.input-container div {
	/* margin-right: 10px; */
	
}

.input-container input, .input-container select {
	width: 69px;
}
</style>


 <script>
 // 
	function submitForm() {
		var form = document.getElementById("warehouseForm");
		var checkboxes = document
				.querySelectorAll('input[name="selectedItems"]:checked');

		var selectedIds = Array.from(checkboxes).map(function(checkbox) {
			return checkbox.value;
		});

		var selectedRecordSalesCounts = Array.from(checkboxes).map(
				function(checkbox) {
					return checkbox.getAttribute("data-record-sales-count");
				});

		var selectedSellDates = Array.from(checkboxes).map(
				function(checkbox) {
					return checkbox.getAttribute("data-sell-date");
				});

/* 		var selectedWarehouseIds = Array.from(checkboxes).map(
				function(checkbox) {
					return checkbox.getAttribute("data-warehouse-id");
				});
 */	
 		console.log(selectedIds)
		console.log(selectedRecordSalesCounts)
		console.log(selectedSellDates)
//		console.log(selectedWarehouseIds)
		alert('콘솔창 확인용  ')

		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "selectedIds");
		hiddenField.setAttribute("value", selectedIds.join(','));		
		form.appendChild(hiddenField);

		var hiddenRecordSalesCountsField = document.createElement("input");
		hiddenRecordSalesCountsField.setAttribute("type", "hidden");
		hiddenRecordSalesCountsField.setAttribute("name", "selectedRecordSalesCounts");
		hiddenRecordSalesCountsField.setAttribute("value", selectedRecordSalesCounts.join(','));
	    form.appendChild(hiddenRecordSalesCountsField);

	    var hiddenSellDatesField = document.createElement("input");
	    hiddenSellDatesField.setAttribute("type", "hidden");
	    hiddenSellDatesField.setAttribute("name", "selectedSellDates");
	    hiddenSellDatesField.setAttribute("value", selectedSellDates.join(','));
	    form.appendChild(hiddenSellDatesField);
		
		
//		form.submit();

		return true;
	}
	
</script>
<script>
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('searchOption').addEventListener(
				'change',
				function() {
					var input = document.querySelector('.searchInput');
					input.style.display = this.value === 'x' ? 'none'
							: 'inline-block';
				});

	});
</script>


</head>
<body>
<h1 style="font-size: 30pt;">출고 등록 컨펌 페이지 </h1>
	<form action="ExWarehouseTestC" method="get">
		<div class="input-container">
	 검색 : <select id="searchOption" name="searchOption">
				<option value="x">검색조건선택</option>
				<option value="p_name">제품명</option>
				<option value="p_type">타입</option>
			</select> <input type="text" name="word" class="searchInput"
				style="display: none;">
			<button type="submit">확인</button>
		</div>
	</form>


	<form action="ExWarehouseTestC" method="post"
		onsubmit="return submitForm()" id="warehouseForm">

		<div class="input-box input-container">
			<div class="in_name">품목 ID</div>
			<div class="in_name">타입</div>
			<div class="in_name">제품 이름</div>
			<div class="in_name">p_quantity</div>
			<div class="in_name">단위</div>
			<div class="in_name">수량</div>
			<div class="in_name">날짜</div>
			<div class="in_name">창고</div>
			<div class="in_name">체크박스</div>
		</div>

		<c:forEach var="t" items="${testExWarehouse}">
			<div class="input-box input-container">
				<div>
					<input name="p_id" value="${t.p_id}" readonly="readonly">
				</div>
				<div>
					<input name="p_type" value="${t.p_type}" readonly="readonly">
				</div>
				<div>
					<input name="p_name" value="${t.p_name}" readonly="readonly">
				</div>
				<div>
					<input name="p_quantity" value="${t.p_quantity}" readonly="readonly">
				</div>
				<div>
					<input name="p_si" value="${t.p_si}" readonly="readonly">
				</div>
				<div>
					<input name="record_sales_count" value="${t.ci_count}"
						readonly="readonly" data-record-sales-count="${t.ci_count}">
				</div>
				<div>
					<input name="sell_date" value="${t.c_completed_date}"
						readonly="readonly"
						data-sell-date="${t.c_completed_date}">
				</div>
				<div>
				<!-- warehouse_id 뒤에 t.p_id 를 툼으로써 창고번호 가져갈 수 있게함  -->
					<select name="warehouse_id_${t.p_id}" id="warehouse_id_${t.p_id}">
						<option value="1">1창고</option>
						<option value="2">2창고</option>
						<option value="3">3창고</option>
					</select>
				</div>
				<div>
					<input type="checkbox" name="selectedItems" value="${t.p_id}"
						 data-record-sales-count="${t.ci_count}"
						data-sell-date="${t.c_completed_date}">
				</div>
			</div>
		</c:forEach>
		<button>수령확인</button>
	</form>
</body>
</html>