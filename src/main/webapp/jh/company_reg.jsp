<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<td> <input name="supply_company" placeholder="구매처 회사 이름"> </td>
			<td> <input name="supply_name" placeholder="구매 담당자"> </td>
			<td> <input name="supplied_name" placeholder="구매업체쪽 담당자"> </td>
			<td> <input name="supply_addr" placeholder="연락처"> </td>
			<td> <input name="purchase_text" placeholder="비고"> </td>
		</tr>
</table>
<button>등록</button>
<button>수정</button>
<button onclick="DeleteComC?name=">삭제</button>

</form>
</body>
</html>