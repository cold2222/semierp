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
	<form action="RegUnitC" method="post">
		<table border="1" style="width: 83%; height: 80px;">
			<tr>
				<td><input name="unit"></td>
				<td>
					<button>단위 등록</button>
				</td>
			</tr>
		</table>
	</form>
	<form action="RegTypeC" method="post">
		<table border="1" style="width: 83%; height: 80px;">
			<tr>
				<td><input name="type"></td>
				<td>
					<button>타입 등록</button>
				</td>
			</tr>
		</table>
	</form>

	<table border="1" style="width: 83%; height: 800px;">
		<tr>
			<td colspan="3">unit</td>
		</tr>
		<c:forEach var="u" items="${us }">
			<tr>
				<td>${u.unit }</td>
				<td> <button onclick="location.href='UpdateUnitC?u=${u.unit}'">수정</button> </td>
				<td> <button onclick="location.href='DeleteUnitC?u=${u.unit}'">삭제</button> </td>
			</tr>
		</c:forEach>
	</table>
	<table border="1" style="width: 83%; height: 800px;">
		<tr>
			<td colspan="3">type</td>
		</tr>
		<c:forEach var="t" items="${ts }">
			<tr>
				<td>${t.type }</td>
				<td> <button onclick="location.href='UpdateTypeC?t=${t.type}'">수정</button> </td>
				<td> <button onclick="location.href='DeleteTypeC?t=${t.type}'">삭제</button> </td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>