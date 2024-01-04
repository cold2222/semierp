<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="jh/css/sidebar.css">
</head>
<body>
	<div class="sidebar">
		<div class="sidebar-1">
			<div class="sidebar-index">取引先管理</div>
			<ul>
				<li onclick="location.href='CompanyC'">&nbsp;- 取引先一覧</li>
				<li onclick="location.href='CompanyRegC'">&nbsp;- 取引先登録</li>
			</ul>
			<div class="sidebar-index">輸入／販売　契約書</div>
			<ul>
				<li onclick="location.href='ContractC'">&nbsp;- 契約書一覧</li>
				<li onclick="location.href='MakeContractC'">&nbsp;- 契約書作成</li>
			</ul>
			<div class="sidebar-index">アイテム管理</div>
			<ul>
				<li onclick="location.href='ProductC'">&nbsp;- アイテム一覧</li>
				<li onclick="location.href='ProductRegC'">&nbsp;- アイテム登録</li>
				<li onclick="location.href='UnitC'">&nbsp;- 単位・タイプ登録／一覧</li>
			</ul>
		</div>
	</div>
</body>
</html>