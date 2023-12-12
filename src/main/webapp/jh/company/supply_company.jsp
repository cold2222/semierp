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
	<form action="DetailComC">
	<input name="search" placeholder="검색"> <button >검색</button> 
	</form> 
	</div>
	<table border="1" style="width: 1800px; height: 800px;">
		<c:forEach var="sc" items="${scs }">
			<tr>
				<td>${sc.supply_num }</td>
				<td>${sc.supply_company }</td>
				<td>${sc.supply_name }</td>
				<td>${sc.supplied_name }</td>
				<td>${sc.supply_addr }</td>
				<td>${sc.purchase_text }</td>
				<td> <button onclick="location.href='UpdateComC?num=${sc.supply_num }'">수정</button> </td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>