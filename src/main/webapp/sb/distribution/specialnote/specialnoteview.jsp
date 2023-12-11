<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="sb/distribution_css/specialnote/specialnoteview.css">
<script type="text/javascript" src="sb/distribution_js/specialnoteview.js"></script>
</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">注意/特異事項</div>
		</div>
		<div class="content-body">
			<div class="bbs-content">
				<div class="bbs-content1 bbs-content">
					<div class="bbs-content-main">
						<div class="bbs-content-body">
							<div class="bbs-content-bbs">
								<div class="dis-container">
									<div class="dis-post-detail">
										<div class="dis-title">${bbs.s_title }</div>
										<div class="dis-date">作成日: ${bbs.s_date }</div>
										<c:if test="${bbs.s_img != null }">
											<img src="sb/distribution/imgfile/${bbs.s_img }" alt=""
												class="dis-image">
										</c:if>
										<div class="dis-content">${bbs.s_content }</div>
										<a onclick="history.back()" style="cursor: pointer;"
											class="dis-back-btn">戻る</a> <a
											href="DistributionSpecialNoteUpdateC?s_num=${bbs.s_num}"
											class="dis-back-btn">修正</a> <a href="#"
											onClick="DistributionSpecialNoteDel('${bbs.s_num}')"
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