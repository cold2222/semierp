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
				<td>${ct.c_contract_no }</td>
				<td>${ct.c_name }(${ct.s_c_no })</td>
				<td>${ct.c_created_date }</td>
				<td>${ct.c_due_date }</td>
				<td>${ct.c_delivery_date }</td>
				<td>${ct.c_completed_date }</td>
				<td>${ct.c_status }</td>
				<td>${ct.c_type }</td>
			</tr>
<!-- 			<tr> -->
<%-- 				<td>${cti.ci_no }</td> --%>
<%-- 				<td>${cti.ci_c_contract_no }</td> --%>
<%-- 				<td>${cti.ci_p_id }</td> --%>
<%-- 				<td>${cti.ci_count}</td> --%>
<%-- 				<td>${cti.ci_unit_price}</td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<%-- 				<td>${p.p_id }</td> --%>
<%-- 				<td>${p.p_si }</td> --%>
<%-- 				<td>${p.p_type }</td> --%>
<%-- 				<td>${p.p_quantity }</td> --%>
<%-- 				<td>${p.p_name }</td> --%>
<%-- 				<td>${p.p_unitCost }</td> --%>
<%-- 				<td>${p.p_minStock }</td> --%>
<%-- 				<td>${p.p_maxStock }</td> --%>
<%-- 				<td>${p.p_manufacturer }</td> --%>
<!-- 			</tr> -->
	</table>

</body>

</html>