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
	function submitForm() {
		var form = document.getElementById("warehouseForm");
		var checkboxes = document.querySelectorAll('input[name="selectedItems"]:checked');

		var selectedIds = Array.from(checkboxes).map(function(checkbox) {
			return checkbox.value;
		});
		
		console.log(selectedIds)

		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "selectedIds");
		hiddenField.setAttribute("value", selectedIds.join(','));

		form.appendChild(hiddenField);
		
		alert('콘솔창 확인 ')
	
		for(let i = 0; i < selectedIds.length;i++){
			
		}
		console.log(hiddenField)
		console.log(form)

//		form.submit();
		return true
	}
</script>


<!-- <script>
	function submitForm() {
		var form = document.getElementById("warehouseForm");
		var checkboxes = document
				.querySelectorAll('input[name="selectedItems"]:checked');

		var selectedIds = Array.from(checkboxes).map(function(checkbox) {
			return checkbox.value;
		});

		var selectedRecordCounts = Array.from(checkboxes).map(
				function(checkbox) {
					return checkbox.getAttribute("data-record-count");
				});

		var selectedInWarehouseDates = Array.from(checkboxes).map(
				function(checkbox) {
					return checkbox.getAttribute("data-in-warehouse-date");
				});

/* 		var selectedWarehouseIds = Array.from(checkboxes).map(
				function(checkbox) {
					return checkbox.getAttribute("data-warehouse-id");
				});
 */	
 		console.log(selectedIds)
		console.log(selectedRecordCounts)
		console.log(selectedInWarehouseDates)
//		console.log(selectedWarehouseIds)
		alert('콘솔창 확인 ')
		
		document.getElementById("selectedIds").value = selectedIds.join(',');
		document.getElementById("selectedRecordCounts").value = selectedRecordCounts.join(',');
		document.getElementById("selectedInWarehouseDates").value = selectedInWarehouseDates.join(',');
//		document.getElementById("selectedWarehouseIds").value = selectedWarehouseIds.join(',');

//		form.submit();

		return true;
	}
	
/* 	function updateSelectedWarehouseId(selectElement) {
	    var selectedWarehouseId = selectElement.value;

	    var form = selectElement.closest('form');

	    var selectedWarehouseIdsInput = form.querySelector('input[name="selectedWarehouseIds"]');

	    var checkboxValue = selectElement.parentElement.parentElement.querySelector('input[name="selectedItems"]').value;

	    var selectedWarehouseIds = selectedWarehouseIdsInput.value.split(',');

	    var index = selectedWarehouseIds.indexOf(checkboxValue);

	    if (index !== -1) {
	        selectedWarehouseIds[index] = selectedWarehouseId;
	    }
	    selectedWarehouseIdsInput.value = selectedWarehouseIds.join(',');
	}
	 */
</script>

 -->

</head>
<body>

	<form action="TestwarehouseC" method="post"
		onsubmit="return submitForm()" id="warehouseForm">

		<div class="input-box input-container">
			<div class="in_name">품목 ID</div>
			<div class="in_name">타입</div>
			<div class="in_name">제품 이름</div>
			<div class="in_name">수량</div>
			<div class="in_name">단위</div>
			<div class="in_name">날짜</div>
			<div class="in_name">창고</div>
			<div class="in_name">체크박스</div>
		</div>

		<c:forEach var="t" items="${testWarehouse}">
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
					<input name="record_count" value="${t.record_count}"
						readonly="readonly" data-record-count="${t.record_count}">
				</div>
				<div>
					<input name="p_si" value="${t.p_si}" readonly="readonly">
				</div>
				<div>
					<input name="in_warehouse_date" value="${t.in_warehouse_date}"
						readonly="readonly"
						data-in-warehouse-date="${t.in_warehouse_date}">
				</div>
				<div>
					<select name="warehouse_id">
						<option value="inchon_one_warehouse">인천1창고</option>
						<option value="inchon_two_warehouse">인천2창고</option>
						<option value="inchon_three_warehouse">인천3창고</option>
					</select>
				</div>
				<div>
					<input type="checkbox" name="selectedItems" value="${t.p_id}"
						<%-- data-record-count="${t.record_count}"
						data-in-warehouse-date="${t.in_warehouse_date}" --%>>
				</div>
			</div>
		</c:forEach>
		<button>수령확인</button>
	</form>
</body>
</html>