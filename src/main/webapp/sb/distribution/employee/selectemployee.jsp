<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="sb/distribution_css/employee/selectemployee.css">
<script type="text/javascript" src="sb/distribution_js/employee.js"></script>
</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">流通部社員管理</div>
		</div>
	</div>
	<div class="container">
		<table class="employee-table">
			<thead>
				<tr>
					<th>名前</th>
					<th>職級</th>
					<th>電話番号</th>
					<th>E-Mail</th>
					<th>入社日</th>
					<th>修正</th>
					<th>削除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="e" items="${empList }">
					<tr>
						<td>${e.e_name }</td>
						<td>${e.e_rank }</td>
						<td>${e.e_tel }</td>
						<td>${e.e_email }</td>
						<td>${e.e_joined_company }</td>
						<td><button onclick="location.href='DistributionEmployeeUpdateC?e_no=${e.e_no}'" class="edit-btn">修正</button></td>
						<td><button onclick="DistributionEmployeeDel('${e.e_no}')" class="delete-btn">削除</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="paging">
			<c:choose>
				<c:when test="${pageNum != 1}">
					<button
						onclick="location.href='DistributionEmployeePageC?pageNum=${pageNum - 1}'">prev</button>
				</c:when>
			</c:choose>

			<c:forEach var="i" begin="${pageNum - 3 > 0 ? pageNum - 3 : 1}"
				end="${pageNum + 3 <= totalPage ? pageNum + 3 : totalPage}" step="1">
				<c:choose>
					<c:when test="${i eq pageNum}">
						<a href="DistributionEmployeePageC?pageNum=${i}"
							style="color: black; font-weight: bold;">${i}</a>
					</c:when>
					<c:otherwise>
						<a href="DistributionEmployeePageC?pageNum=${i}">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:choose>
				<c:when test="${pageNum != totalPage}">
					<button
						onclick="location.href='DistributionEmployeePageC?pageNum=${pageNum + 1}'">next</button>
				</c:when>
			</c:choose>
		</div>
		<button onclick="location.href='DistributionInsertEmployeeC'" class="register-btn">社員登録</button>
	</div>
</body>
</html>