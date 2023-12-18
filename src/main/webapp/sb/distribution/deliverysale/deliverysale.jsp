<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="sb/distribution_css/receipt/receipt.css">
</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">납품예정확인</div>
		</div>
		<div class="content-body">
			<div class="bbs-content">
				<div class="bbs-content1 bbs-content">
					<div class="bbs-content-main">
						<div class="bbs-content-body">
							<div class="bbs-content-bbs">
								<div class="bbs-main">
									<div class="bbs-main-text1 bbs-main-title">판매이력번호</div>
									<div class="bbs-main-text1 bbs-main-title">거래처명</div>
									<div class="bbs-main-text3 bbs-main-title">판매날짜</div>
									<div class="bbs-main-text3 bbs-main-title">담당자</div>
									<div class="bbs-main-text3 bbs-main-title">이력작성날짜</div>
									<div class="bbs-main-text4 bbs-main-title">상세페이지 이동</div>
								</div>
								<c:forEach var="r" items="${deliveryList }">
								<div class="bbs-main">
									<div class="bbs-main-text1 bbs-main-text">${r.c_contract_no }</div>
									<div class="bbs-main-text1 bbs-main-text">${r.c_name }</div>
									<div class="bbs-main-text3 bbs-main-text">${r.c_due_date }</div>
									<div class="bbs-main-text3 bbs-main-text">${r.e_name}</div>
									<div class="bbs-main-text3 bbs-main-text">${r.c_created_date }</div>
									<div class="bbs-main-text4 bbs-main-text"><button onclick="location.href='DistributionReceiptViewC?c_contract_no=${r.c_contract_no }'">Move</button></div>
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