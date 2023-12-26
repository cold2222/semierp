<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/header.css">
<script type="text/javascript" src="js/header.js"></script>
</head>
<body>
	<div class="header">
		<div class="header1">
			<div class="header1-logo">
				<img class="header1-logo-img" alt="" 
				src="logoFolder/SollogisticsHeaderLogo.png">
			</div>
			<div id="header1-empty"></div>
			<div class="header1-atag">마이페이지</div>
			<div class="header1-atag">개인메모</div>
			<div class="header1-select1">환영합니다</div>
			<div class="header1-select1">
				<select>
					<option>temp님</option>
					<option>dropdown1</option>
					<option>dropdown2</option>
					<option>Logout</option>
				</select>
			</div>
		</div>
		<div class="header2">
			<div class="header2-item" onclick="changeColor(this, 'HC')">メイン</div>
			<div class="header2-item" onclick="changeColor(this, 'WarehousebbsC')">在庫管理</div>
			<div class="header2-item" onclick="changeColor(this, 'CompanyC')">輸入/販売</div>
			<div class="header2-item" onclick="changeColor(this, 'DistributionMainBBSC')">運送</div>
			<div class="header2-item" onclick="changeColor(this, '')">決算</div>
		</div>
	</div>
</body>
</html>