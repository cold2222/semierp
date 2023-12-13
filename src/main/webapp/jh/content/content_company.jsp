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
<form action="DetailContC">
	<input name="search" placeholder="검색"> <button >검색</button> 
	</form> 
	</div>
	<table border="1" style="width: 83%; height: 800px;">
		<c:forEach var="c" items="${contents }">
			<tr>
				<td>${c.record_buy_num }</td>
				<td>${c.recordall_buy_num }</td>
				<td>${c.p_id }</td>
				<td>${c.record_count }</td>
				<td>${c.record_price }</td>
				<td> <button onclick="location.href='UpdateContentC?num=${c.record_buy_num }'">수정</button> </td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>