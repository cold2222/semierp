<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="UpdateTypeC?t=${t.type }" method="post">
<table>
		<tr>
			<td>타입<input name="type" value="${t.type }"></td>
		</tr>
</table>
<button>수정 확인</button>
</form>
</body>
</html>