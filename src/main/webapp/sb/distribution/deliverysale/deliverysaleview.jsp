<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.content{
	width: 95%;
	height: 95%;
	margin: 30px;
	background-color: white;
}
</style>
</head>
<body>
<div class="content">
<table border="1">
	<tr>
		<td>구매이력번호</td>
		<td>계약서작성일</td>
		<td>거래처명</td>
		<td>구매담당자이름</td>
		<td>구매담당자폰번호</td>
		<td>장소</td>
		<td>항구도착일</td>
	</tr>
	<tr>
		<td>${dec.c_contract_no }</td>
		<td>${dec.c_created_date }</td>
		<td>${dec.c_name }</td>
		<td>${dec.c_keeper }</td>
		<td>${dec.c_phone }</td>
		<td>부산항</td>
		<td>${dec.c_due_date }</td>
	</tr>
	<tr>
		<td>품명</td>
		<td>종류</td>
		<td>개수</td>
		<td>단가</td>
	</tr>
	<c:forEach var="i" items="${itemList }">
	<tr>
		<td>${i.p_name }</td>
		<td>${i.p_type }${i.p_quantity }${i.p_si }</td>
		<td>${i.ci_count }</td>
		<td>${i.ci_unit_price }</td>
	</tr>
	</c:forEach>
	
</table>
</div>
</body>
</html>