<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
<tr>
	<td> <button onclick="location.href='CompanyRegC'">회사 등록하기</button> </td>
	<td> <button onclick="location.href='CompanyC'">회사 조회하기</button> </td>
	<td> <button onclick="location.href='ContractC'">거례서 조회하기</button> </td>
	<td> <button onclick="location.href='ContentC'">거례서 내용 조회하기</button> </td>
	<td> <button onclick="location.href='ProductC'">상품 조회</button> </td>
</tr>
</table>


<table border="1" style="width: 1800px; height: 800px;">
			<tr>
				<td>
				<jsp:include page="${page}"></jsp:include>
				</td>
			</tr>
		</table>
</body>
</html>