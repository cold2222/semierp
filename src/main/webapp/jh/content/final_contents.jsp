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
<c:forEach var="cti" items="${ctis }">
<table>
		<tr>
			<td> ${cti.ci_no } </td>
			<td> ${cti.ci_c_contract_no } </td>
			<td> 품목ID${cti.ci_p_id } </td>
			<td> ${cti.ci_count }개 구매</td>
			<td> ${cti.ci_unit_price }원 구매</td>
		</tr>
</table>
</c:forEach>
</body>
</html>