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

		<c:forEach var="s" items="${scs }">
	<table>
			<tr>
				<td>${s.supply_num}</td>
				<td>${s.supply_company}</td>
				<td>${s.supply_name}</td>
				<td>${s.supplied_name}</td>
				<td>${s.supply_addr}</td>
				<td>${s.purchase_text}</td>
			</tr>
			
	</table>
		<button onclick="location.href='MakeContractC?num=${s.supply_num}'">계약서 작성</button>	
		</c:forEach>
</body>

</html>