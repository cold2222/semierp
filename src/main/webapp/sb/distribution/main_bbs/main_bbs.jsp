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
					<c:forEach var="r" items="${receiptList }">
						<div class="post">
							<div class="post-title">
							<a href="DistributionReceiptViewC?c_contract_no=${r.c_contract_no }&page=List">取引先名 :${r.c_name }</a>
							</div>
							<div class="post-info">入港日 :${r.c_due_date }</div>
						</div>
					</c:forEach>
				</div>
				<div class="board">
					<div class="board-title">配送完了リスト</div>
					<c:forEach var="c" items="${clearList }">
						<div class="post">
							<div class="post-title">
							<c:if test="${c.c_type eq 1 }">
							<a href="DistributionReceiptClearC?field=e_name&word=${c.e_name }">取引先名 :${c.c_name }</a>
							</c:if>
							<c:if test="${c.c_type eq 2 }">
							<a href="DistributionDeliverySaleClearC?field=e_name&word=${c.e_name }">取引先名 :${c.c_name }</a>
							</c:if>
							</div>
							<div class="post-info">担当者 :${c.e_name }</div>
							<div class="post-info">配送日 :${c.c_delivery_date }</div>
						</div>
					</c:forEach>
				</div>
				<div class="board">
				<div class="emp-board">
					<div class="emp-board-title">本日出勤リスト</div>
					<c:forEach var="e" items="${empList }">
						<div class="emp-post">
							<div class="emp-post-title">
							${e.e_name }
							</div>
						</div>
					</c:forEach>
				</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>