<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		
		<table border="1" style="width: 1800px; height: 800px;">
			<tr>
				<td> 
				<jsp:include page="${mainPage}"></jsp:include>
				</td>
			</tr>
		</table>
		
				<button onclick="location.href='TestRegC'">등록하기</button>
				

</body>
</html>