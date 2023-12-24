<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<div class="row-content">${staffInfo.e_deptno }</div>
		</div>
		<div class="row">
			<div class="row-title">部 署</div>
			<div class="row-content">${staffInfo.e_dept }</div>
		</div>
		<div class="row">
			<div class="row-title">コード</div>
			<div class="row-content">${staffInfo.e_deptno }</div>
		</div>
			<div class="col-title-item col-deptno">コード</div>
			<div class="col-title-item">部 署</div>
			<div class="col-title-item">社員番号</div>
			<div class="col-title-item">お名前</div>
			<div class="col-title-item">職 級</div>
			<div class="col-title-item col-long">E-Mail</div>
			<div class="col-title-item">Tel</div>
			<div class="col-title-item">入社日</div>
			<div class="col-title-item button">修 正</div>
		<div class="col-content-item col-deptno">${staffInfo.e_deptno }</div>
		<div class="col-content-item">${staffInfo.e_dept }</div>
		<div class="col-content-item">${staffInfo.e_no }</div>
		<div class="col-content-item">${staffInfo.e_name}</div>
		<div class="col-content-item">${staffInfo.e_rank }</div>
		<div class="col-content-item col-long">${staffInfo.e_email }</div>
		<div class="col-content-item">${staffInfo.e_tel}</div>
		<div class="col-content-item">${staffInfo.e_joined_company}</div>
		<div class="col-content-item button">
			<button onclick="location.href='StaffAdminModifyC?e_no=${staffInfo.e_no }'">
			修正
			</button>
		</div>
	</div>
</body>
</html>