<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="sb/distribution_css/noticeview.css">
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
									<div class="bbs-main-title">${bbs.n_title }</div>
								</div>
								<div class="bbs-main-img">
									<div class="bbs-main-img">${bbs.n_img }</div>
								</div>
								<div class="bbs-main-date">
									<div class="bbs-main-date">${bbs.n_date }</div>
								</div>
								<div class="bbs-main-content">
									<div class="bbs-main-text">${bbs.n_content }</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>