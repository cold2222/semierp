<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
input[type="number"]::-webkit-inner-spin-button, input[type="number"]::-webkit-outer-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}
</style>
</head>
<body>
	<input type="hidden" id="type" value="${p.p_type }">
	<input type="hidden" id="si" value="${p.p_si }">
	<form action="" method="post" id="myFrom">
		<table>
			<tr>
				<td>단위<select name="p_si" id="siSelect">
						<c:forEach var="u" items="${us}">
							<option value="${u.unit}">${u.unit}</option>
						</c:forEach>
				</select></td>
				<td>타입<select name="p_type" id="typeSelect">
						<c:forEach var="t" items="${ts}">
							<option value="${t.type}">${t.type}</option>
						</c:forEach>
				</select></td>
						<td><input name="p_quantity" placeholder="단위량" type="text"
							value="${p.p_quantity}"></td>
				<td><input name="p_name" placeholder="상품명" required="required"
					value="${p.p_name}"></td>
				<td><input name="p_unitCost" placeholder="가격" type="number"
					required="required" value="${p.p_unitCost}"></td>
				<td><input name="p_minStock" placeholder="최소 스톡량" type="number"
					required="required" value="${p.p_minStock}"></td>
				<td><input name="p_maxStock" placeholder="최대 보유량" type="number"
					required="required" value="${p.p_maxStock}"></td>
				<td><input name="p_manufacturer" placeholder="제조사"
					value="${p.p_manufacturer}"></td>
			</tr>
		</table>
		<button onclick="updateProduct()">수정 확인</button>
	</form>
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

		    for (const key in data) {
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
		    const type = document.getElementById('typeSelect').value;
		    const si = document.getElementById('siSelect').value;
		    const quantity = document.getElementsByName('p_quantity')[0].value;
		    const name = document.getElementsByName('p_name')[0].value;
		    const unitCost = document.getElementsByName('p_unitCost')[0].value;
		    const minStock = document.getElementsByName('p_minStock')[0].value;
		    const maxStock = document.getElementsByName('p_maxStock')[0].value;
		    const manufacturer = document.getElementsByName('p_manufacturer')[0].value;

		    const formData = {
		        p_type: type,
		        p_si: si,
		        p_quantity: quantity,
		        p_name: name,
		        p_unitCost: unitCost,
		        p_minStock: minStock,
		        p_maxStock: maxStock,
		        p_manufacturer: manufacturer
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