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

		<c:forEach var="c" items="${cs }">
	<table>
			<tr>
				<td>${c.c_no}</td>
				<td>${c.c_e_id}</td>
				<td>${c.c_name}</td>
				<td>${c.c_keeper}</td>
				<td>${c.c_phone}</td>
				<td>${c.c_addr}</td>
				<td>${c.c_text}</td>
			</tr>
			
	</table>
		<button onclick="location.href='MakeContractC?num=${c.c_no}'">계약서 작성</button>	
		</c:forEach>
</body>

</html>