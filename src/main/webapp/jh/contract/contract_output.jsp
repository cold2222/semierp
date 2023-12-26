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
	<table border="1">
		<tr>
			<td>${contract.c_contract_no}</td>
			<td>${contract.c_c_no}</td>
			<td>${contract.c_e_id}</td>
			<td>${contract.c_created_date}</td>
			<td>${contract.c_due_date}</td>
			<td>${contract.c_delivery_date}</td>
			<td>${contract.c_completed_date}</td>
			<td>${contract.c_status}</td>
			<td>${contract.c_type}</td>
		</tr>
	</table>
	<hr>
	<table>
		<tr>
			<td>ci_p_id</td>
			<td>상품명</td>
			<td>몇개</td>
			<td>당 가격</td>
		</tr>
		<c:forEach var="item" items="${contract.items}">
			<tr>
				<td>${item.ci_p_id }</td>
				<td>${item.p_name }</td>
				<td>${item.ci_count }</td>
				<td>${item.ci_unit_price }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>