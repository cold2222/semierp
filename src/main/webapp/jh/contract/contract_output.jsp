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
		<c:forEach var="ct" items="${cts }">
<table>
		<tr>
			<td>${ct.c_contract_no }</td>
			<td>${ct.s_c_no }</td>
			<td>${ct.c_e_id }</td>
			<td>계약서 작성일${ct.c_created_date } </td>
			<td>납기일${ct.c_due_date}</td>
			<td>배송예정일${ct.c_delivery_date}</td>
			<td>입고/출고 일자${ct.c_completed_date}</td>
			<td>거래 상태${ct.c_status } </td>
			<td>구매1/판매2${ct.c_type } </td>
		</tr>
</table>
<button onclick="location.href='MakeContentsC?num=${ct.c_contract_no}'">계약서 내용 작성</button>
		</c:forEach>
</body>
</html>