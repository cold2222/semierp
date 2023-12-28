<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="jh/css/contract_company.css">
<script type="text/javascript" src="jh/js/contract_search.js"></script>
</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">契約書一覧</div>
		</div>
		<div class="content-body">
			<div class="search-container">
				<form action="ContractC" method="GET">
					<select name="field" id="searchField" class="search-select"
						onchange="showInput()">
						<option value="all">全体検索</option>
						<option value="b.c_name">取引先名</option>
						<option value="a.c_status">契約状態</option>
						<option value="a.c_type">契約書の種類</option>
					</select> <input type="text" placeholder="検索するキーワードを入力してください"
						name="inputWord" id="searchWord" class="search-input"
						style="display: none;"> <select id="statusSelect"
						name="statusWord" style="display: none;">
						<option value="1,1">輸入 : 配送未指定</option>
						<option value="1,2">輸入 : 配送日確定</option>
						<option value="1,3">輸入 : 配送完了</option>
						<option value="1,4">輸入 : 倉庫摘載済み</option>
						<option value="2,1">販売 : 配送未指定</option>
						<option value="2,2">販売 : 配送日確定</option>
						<option value="2,3">販売 : 配送準備中</option>
						<option value="2,4">販売 : 配送完了</option>
					</select> <select id="typeSelect" name="typeWord" style="display: none;">
						<option value="1">輸入</option>
						<option value="2">販売</option>
					</select>
					<button type="submit" class="search-button">検索</button>
				</form>
			</div>
			<div class="bbs-content">
					<div class="bbs-content-main">
						<div class="bbs-content-body">
							<div class="bbs-content-bbs">
								<div class="bbs-main">
									<div class="bbs-main-title">取引先名</div>
									<div class="bbs-main-title">作成日</div>
									<div class="bbs-main-title">納期日</div>
									<div class="bbs-main-title">取引状態</div>
									<div class="bbs-main-title">契約書の種類</div>
									<div class="bbs-main-title">契約商品の詳細</div>
									<div class="bbs-main-title">修正</div>
								</div>
								<c:forEach var="ct" items="${cts }">
									<div class="bbs-main">
										<div class="bbs-main-text">
											<a href='CompanyC?field=c_name&word=${ct.c_name }'>${ct.c_name }	</a>
										</div>
										<div class="bbs-main-text3 bbs-main-text">${ct.c_created_date }</div>
										<div class="bbs-main-text3 bbs-main-text">${ct.c_due_date }</div>
										<div class="bbs-main-text3 bbs-main-text">
											<c:if test="${ct.c_type eq 1 && ct.c_status eq 1}">配送未指定</c:if>
											<c:if test="${ct.c_type eq 1 && ct.c_status eq 2}">配送日確定</c:if>
											<c:if test="${ct.c_type eq 1 && ct.c_status eq 3}">配送完了</c:if>
											<c:if test="${ct.c_type eq 1 && ct.c_status eq 4}">倉庫摘載完了</c:if>
											<c:if test="${ct.c_type eq 2 && ct.c_status eq 1}">配送未指定</c:if>
											<c:if test="${ct.c_type eq 2 && ct.c_status eq 2}">配送日確定</c:if>
											<c:if test="${ct.c_type eq 2 && ct.c_status eq 3}">配送準備中</c:if>
											<c:if test="${ct.c_type eq 2 && ct.c_status eq 4}">配送完了</c:if>
										</div>
										<div class="bbs-main-text3 bbs-main-text">
											<c:if test="${ct.c_type eq 1 }">輸入</c:if>
											<c:if test="${ct.c_type eq 2 }">販売</c:if>
										</div>
										<div class="bbs-main-text4 bbs-main-text">
											<button
												onclick="location.href='ContractDetailC?no=${ct.c_contract_no }'">詳細</button>
										</div>
										<div class="bbs-main-text4 bbs-main-text">
											<button
												onclick="location.href='UpdateContC?no=${ct.c_contract_no }'">修正</button>
										</div>
									</div>
								</c:forEach>
							</div>


							<div class="paging">
								<c:choose>
									<c:when test="${pageNum != 1}">
										<button
											onclick="location.href='ContractPageC?pageNum=${pageNum - 1}&field=${param.field }&inputWord=${param.inputWord }&statusWord=${param.statusWord }&typeWord=${param.typeWord }'">prev</button>
									</c:when>
								</c:choose>

								<c:forEach var="i" begin="${pageNum - 3 > 0 ? pageNum - 3 : 1}"
									end="${pageNum + 3 <= totalPage ? pageNum + 3 : totalPage}"
									step="1">
									<c:choose>
										<c:when test="${i eq pageNum}">
											<a
												href="ContractPageC?pageNum=${i}&field=${param.field }
						&inputWord=${param.inputWord }&statusWord=${param.statusWord }&typeWord=${param.typeWord }"
												style="color: black; font-weight: bold;">${i}</a>
										</c:when>
										<c:otherwise>
											<a
												href="ContractPageC?pageNum=${i}&field=${param.field }
						&inputWord=${param.inputWord }&statusWord=${param.statusWord }&typeWord=${param.typeWord }">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:choose>
									<c:when test="${pageNum != totalPage && totalPage != 0}">
										<button
											onclick="location.href='ContractPageC?pageNum=${pageNum + 1}&field=${param.field }&inputWord=${param.inputWord }&statusWord=${param.statusWord }&typeWord=${param.typeWord }'">next</button>
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