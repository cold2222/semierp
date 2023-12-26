<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/sjh/admin/adminMain.css">
<title>AdminPage</title>
</head>
<body>
	<div class="container">
		<div class="side-menu">
			<img class="company_logo" src="files/sjh/adminpage/adminPageLogo.jpg">
			<hr style="margin: 50px 30px 0px 30px;">
			<div class="side-menu-items">
				<div class="side-menu-item" onclick="location.href=''">
					<img class="side-menu-icon" src="files/sjh/adminpage/notice_white.png">&nbsp;&nbsp;お知らせ
				</div>
				<div class="side-menu-item" ${selectedPage == 'staff' ? 'id="selected"' : ''} onclick="location.href='StaffC'">
					<img class="side-menu-icon" src="files/sjh/adminpage/staff_white.png">&nbsp;&nbsp;社員一覽
				</div>
				<div class="side-menu-item" ${selectedPage == 'dept' ? 'id="selected"' : ''} onclick="location.href='DeptC'">
					<img class="side-menu-icon" src="files/sjh/adminpage/depart_white.png">&nbsp;&nbsp;部署一覽
				</div>
			</div>
		</div>
		<div class="left-area">
		
			<div class="top-menu shadow-def">
				<span class="top-title">- 管理 -</span>
				<div class="top-title-left">
				<span class="top-admin-info shadow-def">${sessionScope.empInfo.e_name } ${sessionScope.empInfo.e_rank }</span>
				<button class="back-HC-btn shadow-def" onclick="location.href='HC'" >サイトに戻る</button>
				</div>
			</div>
			
			<div class="content shadow-def">
				<jsp:include page="/sjh/admin/${contentPage }"></jsp:include>
			</div>
		</div>
	</div>
</body>
</html>