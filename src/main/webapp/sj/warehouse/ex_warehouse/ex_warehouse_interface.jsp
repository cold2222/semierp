<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="sj/warehouse/warehouse_js/search.js"></script>
<link rel="stylesheet" href="sj/warehouse_css/ex_warehouse.css">
</head>
<body>

	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">出庫登録</div>
		</div>
	<div class="content-body">
		<div class="search-container">
			<form action="ExWarehouseC" method="GET">
				<select name="field" id="searchField" class="search-select"
					onchange="showInput()">
					<option value="all">全体検索</option>
					<option value="b.c_name">取引先名</option>
					<option value="c.e_name">取引担当者</option>
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
								<div class="bbs-main-text1 bbs-main-title">契約ID</div>
								<div class="bbs-main-text1 bbs-main-title">契約日</div>
								<div class="bbs-main-text1 bbs-main-title">取引先会社名</div>
								<div class="bbs-main-text1 bbs-main-title">取引担当社員</div>
								<div class="bbs-main-text1 bbs-main-title">詳細ページ</div>
							</div>

							<c:forEach var="t" items="${exWarehouses}" varStatus="loop">
								<div class="bbs-main"
									style="background-color: ${loop.index % 2 == 0 ? 'white' : '#f0f0f0'};">
									<div class="bbs-main-text1 bbs-main-text">
										<input name="c_contract_no_${t.c_contract_no}"
											readonly="readonly" value="${t.c_contract_no}"
											style="background-color: ${loop.index % 2 == 0 ? 'white' : '#f0f0f0'};">
									</div>
									<div class="bbs-main-text1 bbs-main-text">
										<input name="c_contract_no_${t.c_created_date}"
											readonly="readonly" value="${t.c_created_date}"
											style="background-color: ${loop.index % 2 == 0 ? 'white' : '#f0f0f0'};">
									</div>
									<div class="bbs-main-text1 bbs-main-text">
										<input name="c_contract_no_${t.c_name}" value="${t.c_name}"
											readonly="readonly"
											style="background-color: ${loop.index % 2 == 0 ? 'white' : '#f0f0f0'};">
									</div>
									<div class="bbs-main-text1 bbs-main-text">
										<input name="c_contract_no_${t.e_name}" value="${t.e_name}"
											readonly="readonly"
											style="background-color: ${loop.index % 2 == 0 ? 'white' : '#f0f0f0'};">
									</div>
									<div class="bbs-main-text1 bbs-main-text">
										<a href="ExWarehouseDetailC?c_contract_no=${t.c_contract_no}"><button
												class="detail-button">詳細ページ</button></a>
									</div>
								</div>
							</c:forEach>
							<div class="paging">
								<c:choose>
									<c:when test="${pageNum != 1}">
										<button
											onclick="location.href='ExWarehousePageC?pageNum=${pageNum - 1}&field=${param.field }&word=${param.word }'">prev</button>
									</c:when>
								</c:choose>

								<c:forEach var="i" begin="${pageNum - 3 > 0 ? pageNum - 3 : 1}"
									end="${pageNum + 3 <= totalPage ? pageNum + 3 : totalPage}"
									step="1">
									<c:choose>
										<c:when test="${i eq pageNum}">
											<a
												href="ExWarehousePageC?pageNum=${i}&field=${param.field }&word=${param.word }"
												style="color: black; font-weight: bold;">${i}</a>
										</c:when>
										<c:otherwise>
											<a
												href="ExWarehousePageC?pageNum=${i}&field=${param.field }&word=${param.word }">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:choose>
									<c:when test="${pageNum != totalPage && totalPage != 0}">
										<button
											onclick="location.href='ExWarehousePageC?pageNum=${pageNum + 1}&field=${param.field }&word=${param.word }'">next</button>
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

