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
	<form action="CompanyRegC" method="post">
		<table>
			<tr>
				
				<td><input type="number" name="c_e_id" placeholder="사원이이디 담당자"></td>
				<td><input name="c_name" placeholder="회사 이름"></td>
				<td><input name="c_keeper" placeholder="업체쪽 담당자"></td>
				<td><input name="c_phone" placeholder="업체쪽 연락처"></td>
				<td><input name="c_addr" placeholder="회사 주소"></td>
				<td><input name="c_text" placeholder="비고"></td>
				<td><button>등록</button></td>
			</tr>
		</table>

	</form>
</body>
</html>