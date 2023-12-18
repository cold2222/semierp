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
		<c:forEach var="ct" items="${cts }">
			<tr>
				<td>${ct.c_contract_no }</td>
				<td>${ct.c_name }(${ct.s_c_no })</td>
				<td>${ct.c_created_date }</td>
				<td>${ct.c_due_date }</td>
				<td>${ct.c_delivery_date }</td>
				<td>${ct.c_completed_date }</td>
				<td>${ct.c_status }</td>
				<td>${ct.c_type }</td>
				<td> <button onclick="location.href='UpdateContC?num=${ct.c_contract_no }'">수정</button> </td>
				<td> <button onclick="location.href='MakeContentsC?num=${ct.c_contract_no }'">계약서 내용 작성</button> </td>
			</tr>
		</c:forEach>
	</table>

</body>

</html>