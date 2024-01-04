<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jh/js/updateCom.js"></script>
<link rel="stylesheet" href="jh/css/updateCom.css">
<style type="text/css">
input[type="number"]::-webkit-inner-spin-button, input[type="number"]::-webkit-outer-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}
</style>
</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">アイテム修正</div>
		</div>

		<div class="content-body">
			<div class="bbs-content1">
				<input type="hidden" id="id" name="id" value="${p.p_id }"> <input
					type="hidden" id="type" value="${p.p_type }"> <input
					type="hidden" id="si" value="${p.p_si }">
				<div class="input-container">
					<label for="p_si" class="input-label-select">単位</label> <select
						name="p_si" id="siSelect" class="select">
						<c:forEach var="u" items="${us}">
							<option value="${u.unit}">${u.unit}</option>
						</c:forEach>
					</select>
				</div>
				<div class="input-container">
					<label for="p_type" class="input-label-select">タイプ</label> <select
						name="p_type" id="typeSelect" class="select">
						<c:forEach var="t" items="${ts}">
							<option value="${t.type}">${t.type}</option>
						</c:forEach>
					</select>
				</div>
				<div class="input-container">
					<label for="p_quantity" class="input-label">単位量</label> <input
						name="p_quantity" id="p_quantity" type="text"
						value="${p.p_quantity}">
				</div>
				<div class="input-container">
					<label for="p_name" class="input-label">アイテム名</label> <input
						name="p_name" id="p_name" type="text" value="${p.p_name}">
				</div>
				<div class="input-container">
					<label for="p_unitCost" class="input-label">価格</label> <input
						name="p_unitCost" id="p_unitCost" type="number"
						value="${p.p_unitCost}">
				</div>
				<div class="input-container">
					<label for="p_minStock" class="input-label">最小保有量</label> <input
						name="p_minStock" id="p_minStock" type="number"
						value="${p.p_minStock}">
				</div>
				<div class="input-container">
					<label for="p_maxStock" class="input-label">最大保有量</label> <input
						name="p_maxStock" id="p_maxStock" type="number"
						value="${p.p_maxStock}">
				</div>
				<div class="input-container">
					<label for="p_manufacturer" class="input-label">製造社</label> <input
						name="p_manufacturer" id="p_manufacturer" type="text"
						value="${p.p_manufacturer}">
				</div>
				<div class="button" id="saveButton">
					<button class="insert-btn3" onclick="updateProduct()">修正</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="jh/js/supply_company.js"></script>
	<script type="text/javascript">
		// 데이터베이스에서 가져온 값
		let typeVal = document.getElementById("type").value;
		let siVal = document.getElementById("si").value;
		console.log(typeVal);
		console.log(siVal);

		// 선택 요소 참조
		let typeBox = document.getElementById("typeSelect");
		let siBox = document.getElementById("siSelect");

		// 데이터베이스에서 가져온 값과 옵션 값을 비교하여 일치하는 항목을 찾아 선택
		for (let i = 0; i < typeBox.options.length; i++) {
			if (typeBox.options[i].value === typeVal) {
				typeBox.selectedIndex = i;
				break;
			}
		}
		for (let i = 0; i < siBox.options.length; i++) {
			if (siBox.options[i].value === siVal) {
				siBox.selectedIndex = i;
				break;
			}
		}

		function postFormData(url, data) {
			const form = document.createElement('form');
			form.method = 'POST';
			form.action = url;

			for ( const key in data) {
				if (data.hasOwnProperty(key)) {
					const input = document.createElement('input');
					input.type = 'hidden';
					input.name = key;
					input.value = data[key];
					form.appendChild(input);
				}
			}

			document.body.appendChild(form);
			form.submit();
		}

		function updateProduct() {
			const id = document.getElementById('id').value;
			const type = document.getElementById('typeSelect').value;
			const si = document.getElementById('siSelect').value;
			const quantity = document.getElementsByName('p_quantity')[0].value;
			const name = document.getElementsByName('p_name')[0].value;
			const unitCost = document.getElementsByName('p_unitCost')[0].value;
			const minStock = document.getElementsByName('p_minStock')[0].value;
			const maxStock = document.getElementsByName('p_maxStock')[0].value;
			const manufacturer = document.getElementsByName('p_manufacturer')[0].value;
			if (id === '' || type === '' || si === '' || quantity === ''
					|| name === '' || unitCost === '' || minStock === ''
					|| maxStock === '' || manufacturer === '') {
				alert('컬럼 값에 빈 칸이 있습니다. 업데이트를 실행할 수 없습니다.');
				return;
			}
			const formData = {
				id : id,
				p_type : type,
				p_si : si,
				p_quantity : quantity,
				p_name : name,
				p_unitCost : unitCost,
				p_minStock : minStock,
				p_maxStock : maxStock,
				p_manufacturer : manufacturer
			};

			if (confirm('업데이트 하시겠습니까?')) {
				postFormData('UpdateProductC', formData);
				alert('업데이트 완료');
			} else {
				return;
			}
		}
		
	</script>
</body>
</html>