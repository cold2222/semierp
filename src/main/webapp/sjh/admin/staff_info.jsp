<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/sjh/staff_info.css">
<title>Insert title here</title>
</head>
<body>
	<div class="content_title">社員</div>
	<hr>
	<div class="dept_container">
		<div class="row-title">
			<div class="row-title-item">部 署</div>
			<div class="row-title-item">コード</div>
			<div class="row-title-item">員 数</div>
		</div>
		<c:forEach var="dept" items="${deptInfo}">
			<div class="row-content">
				<div class="row-content-item">${dept.d_dept}</div>
				<div class="row-content-item">${dept.d_deptNo}</div>
				<div class="row-content-item">${dept.d_count}</div>
			</div>
		</c:forEach>
	</div>
	<div class=""></div>
</body>
</html>