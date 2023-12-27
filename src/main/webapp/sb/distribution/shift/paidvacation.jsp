<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="sb/distribution_css/shift/shift.css">
</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">有給休暇取得確認</div>
		</div>
	</div>
	<div class="content">
		<table class="content-table">
			<thead>
				<tr>
					<th>お名前</th>
					<th>今年</th>
					<th>去年</th>
					<th>有給休暇更新日</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="e" items="${emps }">
					<tr>
						<td>${e.e_name }</td>
						<c:forEach var="list" items="${paidVacation[e.e_no] }" varStatus="i">
							<c:forEach var="p" items="${list }">
								<td>${list }</td>
							</c:forEach>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>