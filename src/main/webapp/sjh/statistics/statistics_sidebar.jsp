<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="sb/distribution_css/distribution_sidebar.css">
</head>
<body>
	<div class="sidebar">
		<div class="sidebar-1">
			<div class="sidebar-index">契約決算</div>
			<ul>
				<li onclick="location.href='StatisticsMainC'">&nbsp; - 契約情報(年)　</li>
				<li onclick="location.href='StatisticsImportC'">&nbsp; - 輸入情報(年)</li>
				<li onclick="location.href='StatisticsSalesC'">&nbsp; - 販売情報(年)</li>
			</ul>
			
		</div>
		<div class="sidebar-1">
			<div class="sidebar-index">商品決算</div>
			<ul>
				<li onclick="location.href='ImportProductStatisticByMonthC?pageNo=1'">&nbsp; - 輸入商品情報(月)　</li>
				<li onclick="location.href='SalesProductStatisticByMonthC?pageNo=1'">&nbsp; - 販売商品情報(月)</li>
			</ul>
			
		</div>
		<div class="sidebar-1">
			<div class="sidebar-index">運送決算</div>
			<ul>
				<li onclick="location.href='StatisticDistributionC'">&nbsp; - 運送指標(年)　</li>
			</ul>
			
		</div>
		
		
	</div>
</body>
</html>