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
			<div class="sidebar-index">運送部メイン掲示板</div>
			<ul>
				<li onclick="location.href='DistributionMainBBSC'">&nbsp;- メイン掲示板</li>
			</ul>
			<div class="sidebar-index">配車登録</div>
			<ul>
				<li onclick="location.href='DistributionDeliverySaleC'">&nbsp;- 配車登録 (販売)　</li>
				<li onclick="location.href='DistributionReceiptC'">&nbsp;- 配車登録 (受領)</li>
			</ul>
			<div class="sidebar-index">配車管理</div>
			<ul>
				<li onclick="location.href='DistributionDeliveryDataC'">&nbsp;- 配車情報 確認/修正</li>
			</ul>
			<div class="sidebar-index">配送完了登録</div>
			<ul>
				<li onclick="location.href='DistributionDeliverySaleClearC'">&nbsp;- 配送完了登録 (販売)</li>
				<li onclick="location.href='DistributionReceiptClearC'">&nbsp;- 配送完了登録 (受領)</li>
			</ul>
			<div class="sidebar-index">運送部　掲示板</div>
			<ul>
				<li onclick="location.href='DistributionNoticeC'">&nbsp;- 運送部お知らせ</li>
				<li onclick="location.href='DistributionSpecialNoteC'">&nbsp;- 周知事項</li>
			</ul>
			<div class="sidebar-index">運送予定キャレンダー</div>
			<ul>
				<li onclick="location.href='DistributionShippingC'">&nbsp;- 運送予定キャレンダー</li>
			</ul>
			<div class="sidebar-index">運送部　出勤登録/管理</div>
			<ul>
				<li onclick="location.href='DistributionSelectEmployeeC'">&nbsp;- 運送部 社員リスト</li>
				<li onclick="location.href='DistributionShiftC'">&nbsp;- 運送部出勤表登録</li>
				<li onclick="location.href='DistributionPaidVacationC'">&nbsp;- 有給休暇習得確認</li>
			</ul>
		</div>
	</div>
</body>
</html>