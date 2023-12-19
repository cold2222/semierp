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
<table>
		<tr>
			<td>사원 id : ${ct.c_e_id }</td>
			<td>계약서 작성일 : ${ct.c_created_date } </td>
			<td>납기일 : ${ct.c_due_date}</td>
			<td>구매/판매 : ${ct.c_type } </td>
		</tr>
</table>
<table>
		<tr>
			<td>품목 id : ${cti.ci_p_id }</td>
			<td>구입한 갯수 : ${cti.ci_count } </td>
			<td>구입할 때 쓴 개당 금액 : ${cti.ci_unit_price } </td>
		</tr>
</table>
</body>
</html>