<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

부서번호: ${importDept.i_deptno } <br>
부서이름: ${importDept.i_dept } <br>
인원수: ${importDept.i_count } <br>
현 월: ${currentYearMonth } <br>
현월 계약수: ${importDept.i_contract_count } <br>
현월 물품건수 : ${importDept.i_contract_items }<br>
현월 계약 액수 : ${importDept.i_total_price } <br>
현월 계약 완료수 : ${importDept.i_contract_completed }<br>
입고 대기 건수 : ${importDept. i_awaiting_stock }
</body>
</html>