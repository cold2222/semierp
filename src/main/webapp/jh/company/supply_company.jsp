<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="jh/css/supply_company.css">
<script type="text/javascript" src="jh/js/search.js"></script>
</head>
<body>

	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">取引先一覧</div>
		</div>
		<div class="content-body">
			<div class="search-container">
				<form action="CompanyC" method="GET">
					<select name="field" id="searchField" class="search-select"
						onchange="showInput()">
						<option value="all">全体検索</option>
						<option value="c_name">会社名</option>
						<option value="c_keeper">取引先の担当者</option>
						<option value="c_addr">住所</option>
					</select> <input type="text" autocomplete="off" placeholder="検索するキーワードを入力してください" name="word"
						id="searchWord" class="search-input" style="display: none;">
					<button type="submit" class="search-button">検索</button>
				</form>
			</div>
			<div class="company-content">
					<div class="company-content-main">
						<div class="company-content-body">
							<div class="company-content-company">
								<div class="company-main">
									<div class="company-main-with">取引先名</div>
									<div class="company-main-title">取引先の担当者</div>
									<div class="company-main-title">取引先の連絡先</div>
									<div class="company-main-title">詳細／修正</div>
								</div>
								<c:forEach var="c" items="${cs }"  varStatus="loop">
									<div class="company-main"  style="background-color: ${loop.index % 2 == 0 ? 'white' : '#f0f0f0'};">
										<div class="company-main-text1">${c.c_name }</div>
										<div class="company-main-text">${c.c_keeper }</div>
										<div class="company-main-text">${c.c_phone}</div>
										<div class="company-main-text">
											<button class="insert-btn" onclick="location.href='UpdateComC?c_no=${c.c_no }'">Move</button>
										</div>
									</div>
								</c:forEach>
							</div>

							<div class="paging">
								<c:choose>
									<c:when test="${pageNum != 1}">
										<button
											onclick="location.href='CompanyPageC?p=${pageNum - 1}&field=${param.field }&word=${param.word }'">prev</button>
									</c:when>
								</c:choose>

								<c:forEach var="i" begin="${pageNum - 3 > 0 ? pageNum - 3 : 1}"
									end="${pageNum + 3 <= pageCount ? pageNum + 3 : pageCount}"
									step="1">
									<c:choose>
										<c:when test="${i eq pageNum}">
											<a
												href="CompanyPageC?p=${i}&field=${param.field }&word=${param.word }"
												style="color: black; font-weight: bold;">${i}</a>
										</c:when>
										<c:otherwise>
											<a
												href="CompanyPageC?p=${i}&field=${param.field }&word=${param.word }">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:choose>
									<c:when test="${pageNum != pageCount}">
										<button
											onclick="location.href='CompanyPageC?p=${pageNum + 1}&field=${param.field }&word=${param.word }'">next</button>
									</c:when>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


	
</body>
</html>