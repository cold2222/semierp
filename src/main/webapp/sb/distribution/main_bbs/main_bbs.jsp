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
			<div class="content-head-text">運送部メイン掲示板</div>
		</div>
		<div class="content-body">
			<div class="bbs-content1 bbs-content">
				<div class="board">
					<div class="board-title">お知らせ</div>
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
					<div class="board-title">周知事項</div>
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
					<div class="board-title">配車登録(販売)</div>
					<c:forEach var="d" items="${deliverySaleList }">
						<div class="post">
							<div class="post-title">
							<a href="DistributionDeliverySaleViewC?c_contract_no=${d.c_contract_no }&page=List">取引先名 :${d.c_name }</a>
							</div>
							<div class="post-info">納期日 :${d.c_due_date }</div>
						</div>
					</c:forEach>
				</div>
				<div class="board">
					<div class="board-title">配車登録(受領)</div>
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
					<div class="board-title">配送完了リスト</div>
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
					<div class="board-title">本日出勤リスト</div>
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