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
<div> 
	<form action="DetailContC">
	<input name="search" placeholder="검색"> <button >검색</button> 
	</form> 
	</div>
	<table border="1" style="width: 83%; height: 800px;">
		<c:forEach var="st" items="${sts }">
			<tr>
				<td>${st.recordall_buy_num }</td>
				<td>${st.supply_num }</td>
				<td>${st.purchase_date }</td>
				<td>${st.transaction_date }</td>
				<td>${st.in_warehouse_date }</td>
				<td>${st.status }</td>
				<td> <button onclick="location.href='UpdateContC?num=${st.recordall_buy_num }'">수정</button> </td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>