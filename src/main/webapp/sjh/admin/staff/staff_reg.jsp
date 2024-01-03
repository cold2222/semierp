<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="css/sjh/admin/staff_modify.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="content_title">社員登録</div>
	<hr>

	<div class="content_area">
		<form action="StaffRegC" method="post">
			<div class="row">
				<div class="row-title">(コード)部署</div>
				<div class="row-content">
					<select name="d_deptno" id="deptSelect">
						<c:forEach var="dept" items="${deptsInfo}">
							<option value="${dept.d_deptno}">(${dept.d_deptno})
								${dept.d_dept}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="row-title">社員番号</div>
				<div class="row-content">
					<input type="number" name="e_no" id="e_no" required>
				</div>
			</div>
			<div class="row">
				<div class="row-title">お名前</div>
				<div class="row-content">
					<input name="e_name" required>
				</div>
			</div>
			<div class="row">
				<div class="row-title">職 級</div>
				<div class="row-content">
					<input name="e_rank" required>
				</div>
			</div>
			<div class="row">
				<div class="row-title">E-Mail</div>
				<div class="row-content">
					<input name="e_email" required>
				</div>
			</div>
			<div class="row">
				<div class="row-title">Tel</div>
				<div class="row-content">
					<input name="e_tel" required>
				</div>
			</div>
			<div class="row">
				<div class="row-title">入社日</div>
				<div class="row-content">
					<input type="date" id="dateInput" name="e_joined_company" required>
				</div>
			</div>
			<div class="row-btn">
				<div class="row-btn-left">
					<button type="submit" class="reg-btn btn" id="reg-btn" style="display: none;">登録</button>
					<button type="button" class="reg-btn check-btn" id="check-btn" style="display: block;">社員番号重複チェック</button>
				</div>
				<button class="back-btn btn" type="button"
					onclick="location.href='StaffC'">戻る</button>
			</div>
		</form>
	</div>
<script type="text/javascript" src="js/sjh/admin/staff_reg.js"></script>
<script type="text/javascript" src="js/sjh/admin/utils/input_date_init.js"></script>
</body>
</html>