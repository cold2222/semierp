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
<table>
	<c:forEach var="t" items="${tests }">
		<tr>
			<td> ${t.record_num } </td>
			<td> ${t.company_sell} </td>
			<td> ${t.delivery_date} </td>
			<td> ${t.sell_date} </td>
		</tr>
		</c:forEach>
</table>
</body>
</html>