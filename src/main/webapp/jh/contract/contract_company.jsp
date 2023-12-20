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
	<tr>
		<td>1</td>
		<td>2</td>
		<td>3</td>
		<td>4</td>
		<td>7</td>
		<td>8</td>
		<td>계약상품</td>
	</tr>
	<c:forEach var="ct" items="${cts }">
			<tr>
				<td>${ct.c_contract_no }</td>
				<td><a href='CompanyC?field=c_name&word=${ct.c_name }'">${ct.c_name }(${ct.s_c_no })</a></td>
				<td>${ct.c_created_date }</td>
				<td>${ct.c_due_date }</td>
				<td>${ct.c_status }</td>
				<td>
				 <c:if test="${ct.c_type eq 1 }">구매</c:if>
				 <c:if test="${ct.c_type eq 2 }">판매</c:if>
				  </td>
				<td> <button onclick="location.href='contract-detail?no=${ct.c_contract_no }'">상세조회</button> </td>
			</tr>
</c:forEach>
	</table>

</body>

</html>