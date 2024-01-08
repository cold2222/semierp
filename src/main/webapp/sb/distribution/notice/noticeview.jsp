<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="sb/distribution_css/notice/noticeview.css">
<script type="text/javascript" src="sb/distribution_js/noticeview.js"></script>
</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">告知</div>
		</div>
		<div class="content-body">
			<div class="bbs-content">
				<div class="bbs-content1 bbs-content">
					<div class="bbs-content-main">
						<div class="bbs-content-body">
							<div class="bbs-content-bbs">
								<div class="dis-container">
									<div class="dis-post-detail">
										<div class="dis-title">${bbs.n_title }</div>
										<div class="dis-date">作成日: ${bbs.n_date }</div>
										<c:if test="${bbs.n_img != null }">
											<img src="sb/distribution/imgfile/${bbs.n_img }" alt=""
												class="dis-image">
										</c:if>
										<div class="dis-content">${bbs.n_content }</div>
										<a onclick="history.back()" style="cursor: pointer;"
											class="dis-back-btn">戻る</a> <a
											href="DistributionNoticeUpdateC?n_num=${bbs.n_num}"
											class="dis-back-btn">修正</a> <a href="#"
											onClick="DistributionNoticeDel('${bbs.n_num}')"
											class="dis-back-btn">削除</a>
									</div>
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