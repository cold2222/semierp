<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div> 
	<form action="DetailProductC">
	<input name="search" placeholder="검색"> <button >검색</button> 
	</form> 
	</div>
	<table border="1" style="width: 1800px; height: 800px;">
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
				<td> <button onclick="location.href='UpdateProductC?num=${p.p_id }'">수정</button> </td>
				<td> <button onclick="location.href='RegProductC'">등록</button> </td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>