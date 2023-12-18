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
			<div class="sidebar-index">유통부 메인 게시판</div>
			<ul>
				<li onclick="location.href='DistributionMainBBSC'">&nbsp;- 메인 알림 게시판</li>
			</ul>
			<div class="sidebar-index">주문 처리 밑 관리</div>
			<ul>
				<li onclick="location.href='DistributionDeliveryC'">&nbsp;- 납품배차등록</li>
				<li onclick="location.href='DistributionReceiptC'">&nbsp;- 수령배차등록</li>
			</ul>
			<div class="sidebar-index">배송관리</div>
			<ul>
				<li onclick="location.href=''">&nbsp;- 납품확인등록</li>
				<li onclick="location.href=''">&nbsp;- 수령확인등록</li>
			</ul>
			<div class="sidebar-index">유통부 공통 게시판</div>
			<ul>
				<li onclick="location.href='DistributionNoticeC'">&nbsp;- 공지 게시판</li>
				<li onclick="location.href='DistributionSpecialNoteC'">&nbsp;- 주의사항/특이사항 게시판</li>
			</ul>
			<div class="sidebar-index">운송일정 대시보드</div>
			<ul>
				<li onclick="">&nbsp;- 운송일정 달력</li>
			</ul>
			<div class="sidebar-index">유통부 출근표 등록/관리</div>
			<ul>
				<li onclick="location.href='DistributionSelectEmployeeC'">&nbsp;- 운송부 사원관리</li>
				<li onclick="location.href='DistributionShiftC'">&nbsp;- 출근표</li>
				<li onclick="location.href='DistributionPaidVacationC'">&nbsp;- 유급휴가 습득확인</li>
			</ul>
			<div class="sidebar-index">차량 관리</div>
			<ul>
				<li onclick="">&nbsp;- 차량 정보</li>
			</ul>
		</div>
	</div>
</body>
</html>