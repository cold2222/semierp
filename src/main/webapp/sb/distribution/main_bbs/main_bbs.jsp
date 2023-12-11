<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="sb/distribution_css/mainbbs/main_bbs.css">
</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">통합 알림 게시판</div>
		</div>
		<div class="content-body">
			<div class="bbs-content1 bbs-content">
				<div class="board">
					<div class="board-title">공지</div>
					<c:forEach var="n" items="${noticeList }">
						<div class="post">
							<div class="post-title">
							<a href="DistributionNoticeViewC?n_num=${n.n_num }&pageNum=1">${n.n_title }</a>
							</div>
							<div class="post-info">${n.n_date }</div>
						</div>
					</c:forEach>
				</div>

				<div class="board">
					<div class="board-title">주의사항/특이사항</div>
					<c:forEach var="s" items="${specialNoteList }">
						<div class="post">
							<div class="post-title">
							<a href="DistributionSpecialNoteViewC?s_num=${s.s_num }&pageNum=1">${s.s_title }</a>
							</div>
							<div class="post-info">${s.s_date }</div>
						</div>
					</c:forEach>
				</div>

				<div class="board">
					<div class="board-title">납품예정확인</div>
					<div class="post">
						<div class="post-title">제목 2-1</div>
						<div class="post-info">날짜 2-1</div>
					</div>
					<div class="post">
						<div class="post-title">제목 2-2</div>
						<div class="post-info">날짜 2-2</div>
					</div>
				</div>

				<div class="board">
					<div class="board-title">수령예정확인</div>
					<div class="post">
						<div class="post-title">제목 2-1</div>
						<div class="post-info">날짜 2-1</div>
					</div>
					<div class="post">
						<div class="post-title">제목 2-2</div>
						<div class="post-info">날짜 2-2</div>
					</div>
				</div>

				<div class="board">
					<div class="board-title">배송미정목록</div>
					<div class="post">
						<div class="post-title">제목 2-1</div>
						<div class="post-info">날짜 2-1</div>
					</div>
					<div class="post">
						<div class="post-title">제목 2-2</div>
						<div class="post-info">날짜 2-2</div>
					</div>
				</div>

				<div class="board">
					<div class="board-title">차량검사일정</div>
					<div class="post">
						<div class="post-title">제목 2-1</div>
						<div class="post-info">날짜 2-1</div>
					</div>
					<div class="post">
						<div class="post-title">제목 2-2</div>
						<div class="post-info">날짜 2-2</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>