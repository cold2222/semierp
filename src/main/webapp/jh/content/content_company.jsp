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
<div>
<form action="DetailContentC">
	<input name="search" placeholder="검색"> <button >검색</button> 
	</form> 
	</div>
	<table border="1" style="width: 83%; height: 800px;">
		<c:forEach var="cti" items="${ctis }">
			<tr>
				<td>${cti.ci_no }</td>
				<td>${cti.ci_c_contract_no}</td>
				<td>${cti.ci_p_id }</td>
				<td>${cti.ci_count }</td>
				<td>${cti.ci_unit_price }</td>
				<td> <button onclick="location.href='UpdateContentC?num=${cti.ci_no }'">수정</button> </td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>