<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="sb/distribution_css/receipt/receiptclear.css">
<script type="text/javascript" src="sb/distribution_js/deliverysaleclear.js"></script>
<script type="text/javascript" src="sb/distribution_js/search.js"></script>
</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">配送完了登録 (納品)</div>
		</div>
		<div class="content-body">
		<div class="search-container">
				<form action="DistributionDeliverySaleClearC" method="GET">
					<select name="field" id="searchField" class="search-select"
						onchange="showInput()">
						<option value="all">全体検索</option>
						<option value="d.c_name">取引先</option>
						<option value="c.e_name">配送担当者</option>
					</select> <input type="text" placeholder="検索するキーワードを入力してください" name="word"
						id="searchWord" class="search-input" style="display: none;" autocomplete="off">
					<button type="submit" class="search-button">検索</button>
				</form>
			</div>
			<div class="bbs-content">
				<div class="bbs-content1 bbs-content">
					<div class="bbs-content-main">
						<div class="bbs-content-body">
							<div class="bbs-content-bbs">
								<div class="bbs-main">
									<div class="bbs-main-text1 bbs-main-title">契約書番号</div>
									<div class="bbs-main-text1 bbs-main-title">取引先名</div>
									<div class="bbs-main-text3 bbs-main-title">入港日</div>
									<div class="bbs-main-text3 bbs-main-title">配送担当者</div>
									<div class="bbs-main-text3 bbs-main-title">配送日</div>
									<div class="bbs-main-text4 bbs-main-title">詳細確認</div>
									<div class="bbs-main-text4 bbs-main-title">配送完了</div>
								</div>
								<c:forEach var="d" items="${deliverySaleList }" varStatus="loop">
								<div class="bbs-main" style="background-color: ${loop.index % 2 == 0 ? 'white' : '#f0f0f0'};">
									<div class="bbs-main-text1 bbs-main-text">${d.c_contract_no }</div>
									<div class="bbs-main-text1 bbs-main-text">${d.c_name }</div>
									<div class="bbs-main-text3 bbs-main-text">${d.c_due_date }</div>
									<div class="bbs-main-text3 bbs-main-text">${d.e_name}</div>
									<div class="bbs-main-text3 bbs-main-text">${d.c_delivery_date }</div>
									<div class="bbs-main-text3 bbs-main-text"><button class="select-btn" onclick="location.href='DistributionDeliverySaleViewC?c_contract_no=${d.c_contract_no }&page=clearList'">Move</button></div>
									<div class="bbs-main-text4 bbs-main-text"><button class="clear-btn" onclick="DistributionDeliverySaleClear('${d.c_contract_no}')">完了</button></div>
								</div>
								</c:forEach>
							</div>
							<div class="paging">
									<c:choose>
										<c:when test="${pageNum != 1}">
											<button
												onclick="location.href='DistributionReceiptClearPageC?pageNum=${pageNum - 1}&field=${param.field }&word=${param.word }'">prev</button>
										</c:when>
									</c:choose>

									<c:forEach var="i" begin="${pageNum - 3 > 0 ? pageNum - 3 : 1}"
										end="${pageNum + 3 <= totalPage ? pageNum + 3 : totalPage}"
										step="1">
										<c:choose>
											<c:when test="${i eq pageNum}">
												<a
													href="DistributionReceiptClearPageC?pageNum=${i}&field=${param.field }&word=${param.word }"
													style="color: black; font-weight: bold;">${i}</a>
											</c:when>
											<c:otherwise>
												<a
													href="DistributionReceiptClearPageC?pageNum=${i}&field=${param.field }&word=${param.word }">${i}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>

									<c:choose>
										<c:when test="${pageNum != totalPage && totalPage != 0}">
											<button
												onclick="location.href='DistributionReceiptClearPageC?pageNum=${pageNum + 1}&field=${param.field }&word=${param.word }'">next</button>
										</c:when>
									</c:choose>
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