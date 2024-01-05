<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<div class="header1-border">
				<div class="header1-select1"> <a href="HC">ホーム</a></div>
				<div class="header1-select1"> <a href="https://www.trade.gov/">ITA</a></div>
				<div class="header1-select1"> <a href="https://www.jetro.go.jp/">JETRO</a></div>
				<div class="header1-select1"> <a href="https://www.kotra.or.kr/index.do">KOTRA</a></div>
				<div class="header1-select1">
					<select id="mySelect">
						<option>${sessionScope.empInfo.e_name }様</option>
						<option value="AdminPage">AdminPage</option>
						<option value="Logout">ログアウト</option>
					</select>
					<!-- 셀렉트 자바스크립트 -->
					<script type="text/javascript" src="js/indexSelect.js"></script>
				</div>
			</div>
		</div>
		<div class="header2">
			<div class="header2-border">
				<div class="header2-item"
					${selectedHeader == 'main' ? 'id="selectedHeader"' : ''}
					onclick="location.href='HC'">
					<div class="header1-logo">
						<img class="header1-logo-img" alt=""
							src="logoFolder/SOLlogisticsHeaderLogoNewRemoveBackground.png">
					</div>
				</div>
				<div class="header2-item"
					${selectedHeader == 'stock' ? 'id="selectedHeader"' : ''}
					onclick="location.href='WarehouseBoardC'">在庫管理</div>
				<div class="header2-item"
					${selectedHeader == 'contract' ? 'id="selectedHeader"' : ''}
					onclick="location.href='CompanyC'">輸入/販売</div>
				<div class="header2-item"
					${selectedHeader == 'distribution' ? 'id="selectedHeader"' : '' }
					onclick="location.href='DistributionMainBBSC'">運送</div>
				<div class="header2-item"
					${selectedHeader == 'statistic' ? 'id="selectedHeader"' : ''}
					onclick="location.href=''">決算</div>
			</div>

		</div>
	</div>
</body>
</html>
