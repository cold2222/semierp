<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="MakeContentsC" method="post">
<table>
		<tr>
			<td> <input readonly="readonly" name="ci_c_contract_no" value="${ct.c_contract_no }"></td>
			<td> 품목ID<input name="ci_p_id"> </td>
			<td> 몇개 구매할건지<input name="ci_count"></td>
			<td> 얼마에 구매할건지<input name="ci_unit_price"></td>
		</tr>
</table>
<button>계약서 확인</button>
</form>
</body>
</html>