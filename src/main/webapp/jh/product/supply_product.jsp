<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<form action="RegProductC" method="post">
		<table border="1" style="width: 83%; height: 100px;">
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
				<td><input name="p_quantity" placeholder="단위량"></td>
				<td><button onclick="openPopup()">상품명</button></td>
				<td><input name="p_name" placeholder="상품명"></td>
				<td><input name="p_unitCost" placeholder="가격"></td>
				<td><input name="p_minStock" placeholder="최소 스톡량"></td>
				<td><input name="p_maxStock" placeholder="최대 보유량"></td>
				<td><input name="p_manufacturer" placeholder="제조사"></td>
				<td>
					<button>등록</button>
				</td>
			</tr>
		</table>
	</form>
	<table border="1" style="width: 83%; height: 800px;">
		<c:forEach var="p" items="${ps }">
			<tr>
				<td>${p.p_id }</td>
				<td>${p.p_si }</td>
				<td>${p.p_type }</td>
				<td>${p.p_quantity }</td>
				<td>${p.p_name }</td>
				<td>${p.p_unitCost }</td>
				<td>${p.p_minStock }</td>
				<td>${p.p_maxStock }</td>
				<td>${p.p_manufacturer }</td>
				<td>
					<button onclick="location.href='UpdateProductC?id=${p.p_id }'">수정</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
<script>
// function openPopup() {
//     // 팝업 창 열기
//     var popup = window.open("jh/product/popup.jsp", "popup", "width=400,height=300");
//     // 팝업이 차단되었을 경우에 대한 처리
//     if (!popup || popup.closed || typeof popup.closed == 'undefined') {
//         alert('팝업이 차단되었습니다. 팝업 차단을 해제해주세요.');
//     }
// }
// function updateValue(data) {
//     // 전달받은 데이터 처리 (예: 어떤 동작을 수행하거나 입력 필드에 값 반영 등)
//     document.getElementById("resultField").value = data;
// }
</script>
</html>