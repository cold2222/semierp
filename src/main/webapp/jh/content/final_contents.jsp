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
<c:forEach var="co" items="${contents }">
<table>
		<tr>
			<td> ${co.record_buy_num } </td>
			<td> ${co.recordall_buy_num } </td>
			<td> 품목ID${co.p_id } </td>
			<td> ${co.record_count }개 구매</td>
			<td> ${co.record_price }원 구매</td>
		</tr>
</table>
</c:forEach>
</body>
</html>