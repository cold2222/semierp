<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="UpdateUnitC?u=${u.unit }" method="post">
<table>
		<tr>
			<td>단위<input name="unit" value="${u.unit }"></td>
		</tr>
</table>
<button>수정 확인</button>
</form>
</body>
</html>