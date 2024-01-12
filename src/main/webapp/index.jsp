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
<<<<<<< HEAD
							<div class="board-title">알림창</div>
							<c:forEach var="notice" items="${notices }">
=======
							<div class="board-title">お知らせ</div>
							<!-- forEach로 넣으셔서 내용 채우시면 됩니다. -->
							<%-- <c:forEach var="" items=""> --%>
							<!-- 밑줄 만들어주는 border-bottom 적용class 입니다. -->
>>>>>>> 6d27cdcd7d0cad56c7f7e487c5f8d46692babb31
							<div class="post">
								<div class="post-info">${notice.cn_content }</div>
							</div>
							</c:forEach>
							
							
						</div>
					</div>


					<div class="bbs-content2">

						<div class="board">
							<div class="board-title">周知事項</div>
							<c:forEach var="broadCastInform" items="${broadCastInforms }" >
								<div class="post">
								<div class="post-title">
									<div class="post-title-info"><a href="${broadCastInform.ci_no }">${broadCastInform.ci_title }</a></div>
									<div class="post-title-info">
										<span class="post-title-info-date">${broadCastInform.ci_date }</span>
									</div>
								</div>
								<div class="post-info">${broadCastInform.ci_content }</div>
							</div>
							</c:forEach>	
						</div>

						<div class="board">
							<div class="board-title">部署周知事項</div>
							<c:forEach var="deptInform" items="${deptInforms }" >
								<div class="post">
								<div class="post-title">
									<div class="post-title-info"><a href="${deptInform.ci_no }">${deptInform.ci_title }</a></div>
									<div class="post-title-info">
										<span class="post-title-info-date">${deptInform.ci_date }</span>
									</div>
								</div>
								<div class="post-info">${deptInform.ci_content }</div>
							</div>
							</c:forEach>
						</div>

						<div class="board">
							<div class="board-title">掲示板1</div>
							<!-- forEach로 넣으셔서 내용 채우시면 됩니다. -->
							<%-- <c:forEach var="" items=""> --%>
							<!-- 밑줄 만들어주는 border-bottom 적용class 입니다. -->
							<div class="post">
								<!-- 제목입니다. -->
								<div class="post-title"><a href="">테스트입니다.</a></div>
								<!-- 내용을 넣어주시면됩니다. -->
								<div class="post-info">내용 테스트입니다. ㄴㅇㄹㅇㄹㅇㄹㅇㄹㅇ길게 나와도 괜찮은지보려고
									합ㄴ디ㅏ. ㅎ</div>
							</div>
							<%-- </c:forEach> --%>

							<div class="post">
								<div class="post-title"><a href="">테스트입니다.</a></div>
								<div class="post-info">asdl;fkjri;asldkfjri;alsdkfjir;laksdjfr;liasdjfirl;aksdjf</div>
							</div>

							<div class="post">
								<div class="post-title"><a href="">테스트입니다.</a></div>
								<div class="post-info">asdl;fkjri;asldkfjri;alsdkfjir;laksdjfr;liasdjfirl;aksdjfㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇ</div>
							</div>
							<div class="post">
								<div class="post-title"><a href="">테스트입니다.</a></div>
								<div class="post-info">asdl;fkjri;asldkfjri;alsdkfjir;laksdjfr;liasdjfirl;aksdjfㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇ</div>
							</div>
							<div class="post">
								<div class="post-title"><a href="">테스트입니다.</a></div>
								<div class="post-info">asdl;fkjri;asldkfjri;alsdkfjir;laksdjfr;liasdjfirl;aksdjfㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇ</div>
							</div>
						</div>
						<div class="board">
							<div class="board-title">掲示板2</div>
							<!-- forEach로 넣으셔서 내용 채우시면 됩니다. -->
							<%-- <c:forEach var="" items=""> --%>
							<div class="post">
								<!-- 제목입니다. -->
								<div class="post-title"><a href="">테스트입니다.</a></div>
								<!-- 내용을 넣어주시면됩니다. -->
								<div class="post-info">내용 테스트입니다. ㄴㅇㄹㅇㄹㅇㄹㅇㄹㅇ길게 나와도 괜찮은지보려고
									합ㄴ디ㅏ. ㅎ</div>
							</div>
							<%-- </c:forEach> --%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>









	<div class="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>