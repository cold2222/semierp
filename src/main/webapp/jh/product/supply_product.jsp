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
	<div>
		<form action="DetailProductC">
			<input name="search" placeholder="검색">
			<button>검색</button>
		</form>
	</div>
	<div>
		<form action="UnitC">
			<button>단위와 타입</button>
		</form>
	</div>
	<button onclick="addRow()">행 추가</button>
	<form action="RegProductC" method="post">
		<table id="myTable" border="1" style="width: 83%; height: 100px;">
			<tr>
				<td>단위<select name="p_si">
						<c:forEach var="u" items="${us}">
							<option value="${u.unit}">${u.unit}</option>
						</c:forEach>
				</select></td>
				<td>타입<select name="p_type">
						<c:forEach var="t" items="${ts}">
							<option value="${t.type}">${t.type}</option>
						</c:forEach>
				</select></td>
				<td><input name="p_quantity" placeholder="단위량" type="number"></td>
				<td><input name="p_name" placeholder="상품명" required="required"></td>
				<td><input name="p_unitCost" placeholder="가격" type="number"
					required="required"></td>
				<td><input name="p_minStock" placeholder="최소 스톡량" type="number"
					required="required"></td>
				<td><input name="p_maxStock" placeholder="최대 보유량" type="number"
					required="required"></td>
				<td><input name="p_manufacturer" placeholder="제조사"></td>
			</tr>
		</table>
		<button>등록</button>
	</form>
	<table border="1" style="width: 83%; height: 800px;">
		<tr>
			<td>아이템 코드</td>
			<td>아이템 단위</td>
			<td>아이템 타입</td>
			<td>아이템 단위량</td>
			<td>아이템 이름</td>
			<td>아이템 가격</td>
			<td>최소보유량</td>
			<td>최대보유량</td>
			<td>제조사</td>
		</tr>
		<c:forEach var="p" items="${ps }">
			<tr>
				<td>${p.p_id }</td>
				<td>${p.p_si }</td>
				<td>${p.p_type }</td>
				<c:choose>
					<c:when test="${p.p_quantity == 0}">
						<td>단위 없음</td>
					</c:when>
					<c:otherwise>
						<td>${p.p_quantity }</td>
					</c:otherwise>
				</c:choose>
				<td>${p.p_name }</td>
				<td>${p.p_unitCost }</td>
				<td>${p.p_minStock }</td>
				<td>${p.p_maxStock }</td>
				<c:choose>
					<c:when test="${p.p_manufacturer == null }">
						<td>제조사 없음</td>
					</c:when>
					<c:otherwise>
						<td>${p.p_manufacturer }</td>
					</c:otherwise>
				</c:choose>
				<td>
					<button onclick="location.href='UpdateProductC?id=${p.p_id }'">수정</button>
				</td>
				<td>
					<button onclick="deleteProduct('${p.p_id}')">삭제</button>
				</td>
			</tr>
		</c:forEach>
	</table>
<script type="text/javascript">
function deleteProduct(p_id) {
	if(confirm('정말 삭제하시겠습니까?')){
		location.href='DeleteProductC?p_id='+p_id;
	}else{
		return;
	}
}
</script>		
</body>
</html>