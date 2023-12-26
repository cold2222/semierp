<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
부서번호: ${importDept.c_deptno } <br>
부서이름: ${importDept.c_dept } <br>
인원수: ${importDept.c_count } <br>
현월: ${currentYearMonth } <br>
현월 계약수: ${importDept.c_contract_count } <br>
현월 물품건수 : ${importDept.c_contract_items }<br>
현월 계약 액수 : ${importDept.c_total_price } <br>
현월 계약 완료수 : ${importDept.c_contract_completed }<br>
입고 대기 건수 : ${importDept. c_awaiting_stock } <br>
</div>
<br>
<div>
부서번호: ${salesDept.c_deptno } <br>
부서이름: ${salesDept.c_dept } <br>
인원수: ${salesDept.c_count } <br>
현월: ${currentYearMonth } <br>
현월 계약수: ${salesDept.c_contract_count } <br>
현월 물품건수 : ${salesDept.c_contract_items }<br>
현월 계약 액수 : ${salesDept.c_total_price } <br>
현월 계약 완료수 : ${salesDept.c_contract_completed }<br>
입고 대기 건수 : ${salesDept. c_awaiting_stock } <br>
</div>
</body>
</html>