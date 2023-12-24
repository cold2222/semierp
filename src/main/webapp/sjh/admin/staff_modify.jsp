<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/sjh/staff_modify.css">
</head>
<body>
	<div class="content_title">社員修正</div>
	<hr>

	<div class="content">
		<div class="row">
			<div class="row-title">コード</div>
			<div class="row-content">${staffInfo.e_deptno }
				<select id="deptSelect">
					<c:forEach var="dept" items="${deptsInfo}">
						<option value="${dept.d_dept}">(${dept.d_deptNo})${dept.d_dept}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row">
			<div class="row-title">部 署</div>
			<div class="row-content">${staffInfo.e_dept }</div>
		</div>
		<div class="row">
			<div class="row-title">社員番号</div>
			<div class="row-content">${staffInfo.e_no }</div>
		</div>
		<div class="row">
			<div class="row-title">お名前</div>
			<div class="row-content">${staffInfo.e_name }</div>
		</div>
		<div class="row">
			<div class="row-title">職 級</div>
			<div class="row-content"> <input value="${staffInfo.e_rank }"> </div>
		</div>
		<div class="row">
			<div class="row-title">E-Mail</div>
			<div class="row-content">${staffInfo.e_email }</div>
		</div>
		<div class="row">
			<div class="row-title">Tel</div>
			<div class="row-content">${staffInfo.e_tel }</div>
		</div>
		<div class="row">
			<div class="row-title">入社日</div>
			<div class="row-content">${staffInfo.e_joined_company }</div>
		</div>

	</div>
</body>
</html>