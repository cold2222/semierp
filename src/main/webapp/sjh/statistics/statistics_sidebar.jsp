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
			<div class="sidebar-index">계약 결산</div>
			<ul>
				<li onclick="location.href='StatisticsMainC'">&nbsp; - 계약 정보(年)　</li>
				<li onclick="location.href='StatisticsImportC'">&nbsp; - 수입 정보(年)</li>
				<li onclick="location.href='StatisticsSalesC'">&nbsp; - 수출 정보(年)</li>
			</ul>
			
		</div>
		<div class="sidebar-1">
			<div class="sidebar-index">상품 결산</div>
			<ul>
				<li onclick="location.href='ImportProductStatisticByMonthC?pageNo=1'">&nbsp; - 상품 수입(月)　</li>
				<li onclick="location.href='SalesProductStatisticByMonthC?pageNo=1'">&nbsp; - 상품 판매(月)</li>
			</ul>
			
		</div>
		<div class="sidebar-1">
			<div class="sidebar-index">운송 지표</div>
			<ul>
				<li onclick="location.href='StatisticDistributionC'">&nbsp; - 운송 현황(年)　</li>
			</ul>
			
		</div>
		
		
	</div>
</body>
</html>