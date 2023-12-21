<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jh/js/search.js"></script>
<style type="text/css">
input[type="number"]::-webkit-inner-spin-button, input[type="number"]::-webkit-outer-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}
</style>
</head>
<body>
	<div class="search-container">
		<form action="ProductC" method="GET">
			<select name="field" id="searchField" class="search-select"
				onchange="showInput()">
				<option value="all">全体検索</option>
				<option value="p_type">타입</option>
				<option value="p_name">이름</option>
			</select> <input type="text" placeholder="検索するキーワードを入力してください" name="word"
				id="searchWord" class="search-input" style="display: none;">
			<button type="submit" class="search-button">検索</button>
		</form>
	</div>
	<div>
		<form action="UnitC">
			<button>단위와 타입</button>
		</form>
	</div>
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
		<c:forEach var="p" items="${productItems }">
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
	<div class="paging">
		<c:choose>
			<c:when test="${pageNum != 1}">
				<button
					onclick="location.href='ProductPageC?pageNum=${pageNum - 1}&field=${param.field }&word=${param.word }'">prev</button>
			</c:when>
		</c:choose>

		<c:forEach var="i" begin="${pageNum - 3 > 0 ? pageNum - 3 : 1}"
			end="${pageNum + 3 <= totalPage ? pageNum + 3 : totalPage}" step="1">
			<c:choose>
				<c:when test="${i eq pageNum}">
					<a
						href="ProductPageC?pageNum=${i}&field=${param.field }&word=${param.word }"
						style="color: black; font-weight: bold;">${i}</a>
				</c:when>
				<c:otherwise>
					<a
						href="ProductPageC?pageNum=${i}&field=${param.field }&word=${param.word }">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:choose>
			<c:when test="${pageNum != totalPage && totalPage != 0}">
				<button
					onclick="location.href='ProductPageC?pageNum=${pageNum + 1}&field=${param.field }&word=${param.word }'">next</button>
			</c:when>
		</c:choose>
	</div>
	<script type="text/javascript">
		function deleteProduct(p_id) {
			if (confirm('정말 삭제하시겠습니까?')) {
				location.href = 'DeleteProductC?p_id=' + p_id;
			} else {
				return;
			}
		}
	</script>
</body>
</html>