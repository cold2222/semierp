<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>semi ERP</title>
<link rel="stylesheet" href="css/index.css">

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="index-contents">
		<div class="index-content">
			<div class="content-body">
				<div class="contents ">
					<div class="bbs-content1">
						<div class="board">
							<div class="board-title">社員情報</div>
							<div class="myself">
								<div class="myself-info">
									<div class="myself-line1">部署&nbsp;&nbsp;</div>
									<div class="myself-line2">&nbsp;&nbsp;${empInfo.e_dept}/${empInfo.e_rank}</div>
								</div>
								<div class="myself-info">
									<div class="myself-line1">名前&nbsp;&nbsp;</div>
									<div class="myself-line2">&nbsp;&nbsp;${empInfo.e_name}</div>
								</div>
								<div class="myself-info">
									<div class="myself-line1">職員番号&nbsp;&nbsp;</div>
									<div class="myself-line2">&nbsp;&nbsp;${empInfo.e_no}</div>
								</div>
								<div class="myself-info">
									<div class="myself-line1">電話番号&nbsp;&nbsp;</div>
									<div class="myself-line2">&nbsp;&nbsp;${empInfo.e_tel}</div>
								</div>
								<div class="board-button">
									<button>マイページ</button>
									<button onclick="location.href ='LogoutC'">ログアウト</button>
								</div>
							</div>
						</div>
						<div class="board">

							<div class="board-title">お知らせ</div>
							<c:forEach var="notice" items="${notices }">
							<div class="post">
								<div class="post-info"><a href="NoticeRedirectC?ccn_e_no=${notice.ccn_e_no }&ccn_cn_no=${notice.ccn_cn_no }">${notice.cn_content }</a></div>
							</div>
							</c:forEach>
							
							
						</div>
					</div>


					
						<jsp:include page="${contentPage }"></jsp:include>
				
				</div>
			</div>
		</div>
	</div>









	<div class="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>