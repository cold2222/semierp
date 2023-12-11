<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
								<div class="dis-container">
        <div class="dis-post-container">
            <c:forEach var="b" items="${bbsList }">
            <div class="dis-post">
                <div class="dis-title">${b.n_title }</div>
                <div class="dis-date">作成日:${b.n_date }</div>
                <a href="DistributionNoticeViewC?n_num=${b.n_num}&pageNum=${pageNum }" class="dis-btn">詳しく見る</a>
            </div>
            </c:forEach>
        </div>
    </div>
							<div class="paging">
								<c:choose>
									<c:when test="${pageNum != 1}">
										<button
											onclick="location.href='DistributionNoticePageC?pageNum=${pageNum - 1}'">prev</button>
									</c:when>
								</c:choose>

								<c:forEach var="i" begin="${pageNum - 3 > 0 ? pageNum - 3 : 1}"
									end="${pageNum + 3 <= totalPage ? pageNum + 3 : totalPage}"
									step="1">
									<c:choose>
										<c:when test="${i eq pageNum}">
											<a href="DistributionNoticePageC?pageNum=${i}"
												style="color: black; font-weight: bold;">${i}</a>
										</c:when>
										<c:otherwise>
											<a href="DistributionNoticePageC?pageNum=${i}">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:choose>
									<c:when test="${pageNum != totalPage}">
										<button
											onclick="location.href='DistributionNoticePageC?pageNum=${pageNum + 1}'">next</button>
									</c:when>
								</c:choose>
							</div>
							</div>
							<!-- 권한설정 해야됨 -->
							<div class="insert-btn">
								<button onclick="location.href='DistributionNoticeInsertC'">掲示物作成</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>