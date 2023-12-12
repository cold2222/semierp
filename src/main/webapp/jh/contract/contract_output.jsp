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
		<c:forEach var="st" items="${sts }">
<table>
		<tr>
			<td>${st.recordall_buy_num }</td>
			<td> ${st.supply_num }</td>
			<td> 구매할 날짜${st.purchase_date } </td>
			<td> 입고 예정일${st.transaction_date}</td>
			<td> 입고 날짜${st.in_warehouse_date}</td>
			<td> ${st.status } </td>
		</tr>
</table>
<button onclick="location.href='MakeContentsC?num=${st.recordall_buy_num}'">계약서 내용 작성</button>
		</c:forEach>
</body>
</html>