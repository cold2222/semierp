<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="sj/warehouse/warehouse_js/search.js"></script>
<link rel="stylesheet" href="sj/warehouse_css/in_warehouse.css">
</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">입고 등록 확인 컨펌</div>
		</div>
		<div class="content-body">
			<div class="search-container">
				<form action="InWarehouseC" method="GET">
					<select name="field" id="searchField" class="search-select"
						onchange="showInput()">
						<option value="all">全体検索</option>
						<option value="b.c_name">取引先名</option>
						<option value="c.e_name">取引担当者</option>
					</select> <input type="text" placeholder="検索するキーワードを入力してください" name="word"
						id="searchWord" class="search-input" style="display: none;">
					<button type="submit" class="search-button">検索</button>
				</form>
			</div>
			<div class="bbs-content"></div>
			<div class="bbs-content1 bbs-content">
				<div class="bbs-content-main">
					<div class="bbs-content-body">
						<div class="bbs-content-bbs">
							<div class="bbs-main">
								<div class="bbs-main-text1 bbs-main-title">契約ID</div>
								<div class="bbs-main-text1 bbs-main-title">契約日</div>
								<div class="bbs-main-text1 bbs-main-title">取引先会社名</div>
								<div class="bbs-main-text1 bbs-main-title">担当者</div>
								<div class="bbs-main-text1 bbs-main-title">詳細ページ</div>


							</div>

							<c:forEach var="t" items="${inWarehouse}">
								<div class="bbs-main">
									<div class="bbs-main-text1 bbs-main-text" >
										<input name="c_contract_no_${t.c_contract_no}"
											readonly="readonly" value="${t.c_contract_no}" style = "text-align:center;">
									</div>
									<div class="bbs-main-text1 bbs-main-text">
										<input name="c_contract_no_${t.c_created_date}"
											readonly="readonly" value="${t.c_created_date}" style = "text-align:center;" >
									</div>
									<div class="bbs-main-text1 bbs-main-text">
										<input name="c_contract_no_${t.c_name}" value="${t.c_name}"
											readonly="readonly">
									</div>
									<div class="bbs-main-text1 bbs-main-text">
										<input name="c_contract_no_${t.e_name}" value="${t.e_name}"
											readonly="readonly">
									</div>
									<div class="bbs-main-text1 bbs-main-text">
										<a href="InWarehouseDetailC?c_contract_no=${t.c_contract_no}"><button>상세페이지</button></a>
									</div>
								</div>
							</c:forEach>
							<a href="InExBoardC">입고출고 내역 확인하러 가기</a>
							<div class="paging">
								<c:choose>
									<c:when test="${pageNum != 1}">
										<button
											onclick="location.href='InWarehousePageC?pageNum=${pageNum - 1}&field=${param.field }&word=${param.word }&operationType=${operationType }'">prev</button>
									</c:when>
								</c:choose>
								<c:forEach var="i" begin="${pageNum - 3 > 0 ? pageNum - 3 : 1}"
									end="${pageNum + 3 <= totalPage ? pageNum + 3 : totalPage}"
									step="1">
									<c:choose>
										<c:when test="${i eq pageNum}">
											<a
												href="InWarehousePageC?pageNum=${i}&field=${param.field }&word=${param.word }&operationType=${operationType }"
												style="color: black; font-weight: bold;">${i}</a>
										</c:when>
										<c:otherwise>
											<a
												href="InWarehousePageC?pageNum=${i}&field=${param.field }&word=${param.word }&operationType=${operationType }">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${pageNum != totalPage && totalPage != 0}">
										<button
											onclick="location.href='InWarehousePageC?pageNum=${pageNum + 1}&field=${param.field }&word=${param.word }&operationType=${operationType }'">next</button>
									</c:when>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>