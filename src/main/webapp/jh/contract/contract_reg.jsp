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

<form action="MakeContractC" method="post">
<table>
		<tr>
			<td> <input readonly="readonly" name="s_c_no" value="${c.c_no }"></td>
			<td> <input readonly="readonly" name="c_e_id" value="${c.c_e_id }"></td>
			<td> 계약서 작성일<input type="date" name="c_created_date"> </td>
			<td> 납기일<input type="date" name="c_due_date"></td>
			<td> 배송예정일<input type="date" name="c_delivery_date"></td>
			<td> 입고/출고 일자<input type="date" name="c_completed_date"></td>
			<td> 거래 상태<input readonly="readonly" name="c_status" placeholder="거래 상태" value="1"> </td>
			<td> 구매/판매<input name="c_type" placeholder="구매1 or 판매2"> </td>
		</tr>
</table>
<button>계약서 내용 작성</button>
</form>



</body>
</html>