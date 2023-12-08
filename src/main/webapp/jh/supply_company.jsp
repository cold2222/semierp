<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<jsp:include page="${page}"></jsp:include>
				</td>
			</tr>
		</table>
		
				<button onclick="location.href='CompanyRegC'">등록하기</button>

</body>
</html>