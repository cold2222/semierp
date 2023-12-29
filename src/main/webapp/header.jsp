<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			<div class="header1-logo" onclick="goToMainPage()">
				<img class="header1-logo-img" alt="" 
				src="logoFolder/SOLlogisticsHeaderLogoNew.png">
			</div>
			<div id="header1-empty"></div>
			<div class="header1-atag">마이페이지</div>
			<div class="header1-atag">개인메모</div>
			<div class="header1-select1">환영합니다</div>
			<div class="header1-select1">
				<select id="mySelect">
					<option>${sessionScope.empInfo.e_name }님</option>
					<option>dropdown1</option>
					<option>dropdown2</option>
					<option value="AdminPage">AdminPage</option>
					<option>Logout</option>
				</select>
				<!-- 셀렉트 자바스크립트 -->
				<script type="text/javascript" src="js/indexSelect.js"></script>
			</div>
		</div>
		<div class="header2">
			<div class="header2-item" ${selectedHeader == 'main' ? 'id="selectedHeader"' : ''} onclick="location.href='HC'">メイン</div>
            <div class="header2-item" ${selectedHeader == 'stock' ? 'id="selectedHeader"' : ''} onclick="location.href='WarehouseBoardC'">在庫管理</div>
            <div class="header2-item" ${selectedHeader == 'contract' ? 'id="selectedHeader"' : ''} onclick="location.href='CompanyC'">輸入/販売</div>
            <div class="header2-item" ${selectedHeader == 'distribution' ? 'id="selectedHeader"' : '' } onclick="location.href='DistributionMainBBSC'">運送</div>
            <div class="header2-item" ${selectedHeader == 'statistic' ? 'id="selectedHeader"' : ''} onclick="location.href=''">決算</div>
			
		</div>
	</div>
</body>
</html>
