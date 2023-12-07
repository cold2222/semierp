<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="sb/distribution_css/notice.css">
</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">공지</div>
		</div>
		<div class="content-body">
			<div class="bbs-content">
				<div class="bbs-content1 bbs-content">
					<div class="bbs-content-main">
						<div class="bbs-content-body">
							<div class="bbs-content-bbs">
								<div class="bbs-main">
									<div class="bbs-main-title1 bbs-main-title">Title</div>
									<div class="bbs-main-title2 bbs-main-title">Date</div>
								</div>
								<c:forEach var="b" items="${bbsList }">
								<div class="bbs-main">
									<div onclick="location.href='DistributionNoticeViewC?n_num=${b.n_num}'" class="bbs-main-text1 bbs-main-text">${b.n_title }</div>
									<div class="bbs-main-text2 bbs-main-text">${b.n_date }</div>
								</div>
								</c:forEach>
							</div>
							<div class="paging">페이징처리할부분</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>