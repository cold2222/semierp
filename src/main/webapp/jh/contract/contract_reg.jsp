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
			<td> <input readonly="readonly" name="supply_num" value="${sc.supply_num }"></td>
			<td> 구매할 날짜<input type="date" name="purchase_date"> </td>
			<td> 입고 예정일<input type="date" name="transaction_date"></td>
			<td> 입고 날짜<input type="date" name="in_warehouse_date"></td>
			<td> <input readonly="readonly" name="status" placeholder="거래 상태" value="1"> </td>
		</tr>
</table>
<button>계약서 내용 작성</button>
</form>



</body>
</html>