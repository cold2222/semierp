<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/sjh/admin/staff_modify.css">
</head>
<body>
	<div class="content_title">社員修正</div>
	<hr>

	<div class="content_area">
		<form action="StaffAdminModifyC?e_no=${staffInfo.e_no }" method="post">
			<div class="row">
				<div class="row-title">コード</div>
				<div class="row-content">
					${staffInfo.e_deptno } <select name="d_deptno" id="deptSelect">
						<c:forEach var="dept" items="${deptsInfo}">
							<option value="${dept.d_deptno}"
								<c:if test="${staffInfo.e_deptno eq dept.d_deptno}">selected</c:if>>
								(${dept.d_deptno}) ${dept.d_dept}</option>
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
				<div class="row-content">
					<input name="e_rank" value="${staffInfo.e_rank }">
				</div>
			</div>
			<div class="row">
				<div class="row-title">E-Mail</div>
				<div class="row-content">
					<input name="e_email" value="${staffInfo.e_email }">
				</div>
			</div>
			<div class="row">
				<div class="row-title">Tel</div>
				<div class="row-content">
					<input name="e_tel" value="${staffInfo.e_tel }">
				</div>
			</div>
			<div class="row">
				<div class="row-title">入社日</div>
				<div class="row-content">${staffInfo.e_joined_company }</div>
			</div>
			<div class="row-btn">
				<div class="row-btn-left">
					<button class="pw-btn btn" type="button" onclick="location.href='ResetStaffPWC?e_no=${staffInfo.e_no }'" >Reset PW</button>
					&nbsp;
					<button class="modify-btn btn">修 正</button>
				</div>
				<button class="back-btn btn" type="button" onclick="location.href='StaffC'" >戻 る</button>
			</div>
		</form>

	</div>
</body>
</html>