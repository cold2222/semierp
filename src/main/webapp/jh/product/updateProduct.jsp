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
	<form action="UpdateProductC?id=${p.p_id }" method="post">
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
				<c:choose>
					<c:when test="${p.p_quantity == 0}">
						<td><input name="p_quantity" placeholder="단위량" type="number"
							value=""></td>
					</c:when>
					<c:otherwise>
						<td><input name="p_quantity" placeholder="단위량" type="number"
							value="${p.p_quantity}"></td>
					</c:otherwise>
				</c:choose>
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
		<button>수정 확인</button>
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
	</script>
</body>
</html>