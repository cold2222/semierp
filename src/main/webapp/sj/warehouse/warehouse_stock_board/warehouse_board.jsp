<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('searchOption').addEventListener(
				'change',
				function() {
					var input = document.querySelector('.search-input');
					input.style.display = this.value === 'x' ? 'none'
							: 'inline-block';
				});

	});
</script>
<link rel="stylesheet" href="sj/warehouse_css/warehouse_board.css">
</head>
<body>




	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">在庫確認</div>
		</div>
		<div class="content-body">
			<div class="search-container">
				<div class="search-line">
				<c:if test="${param.operationType != 'all' && param.operationType != null }">
					<div><span class="value-box">倉庫 :</span><span class="value"> ${warehouseBoard[0].warehouse_name }</span></div>
					<div> <span class="value-box">倉庫担当者 : </span> <span class="value">${warehouseBoard[0].e_name }</span></div>
				</c:if>
					<span class="value-box">在庫価格 : </span> <span class="value">
						<c:choose>
							<c:when test="${operationType eq 'all' or operationType eq null}">
								<c:set var="totalValue" value="0" />
								<c:forEach var="ts" items="${totalStockList}">
									<c:set var="totalValue" value="${totalValue + ts.total_stock}" />
								</c:forEach>
								<fmt:formatNumber value="${totalValue}" pattern="￥#,###" />
							</c:when>
							<c:otherwise>
								<c:forEach var="ts" items="${totalStockList}">
									<c:if test="${ts.warehouse_name eq operationType}">
                	 ${operationType}: ${ts.total_stock}
            </c:if>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</span>
				</div>
				<div class="search-line">
					<form action="WarehouseBoardC" method="get">
						<label for="operationType"> <span class="value-box">
								倉庫 : </span>
						</label> <select name="operationType" id="operationType"
							class="search-select">
							<option value="all">全体</option>
							<option value="one">1倉庫</option>
							<option value="two">2倉庫</option>
							<option value="three">3倉庫</option>
						</select> <span class="value-box"> 検索 : </span> <select id="searchOption"
							name="searchOption" class="search-select">
							<option value="x">検索条件</option>
							<option value="p_name">商品名</option>
							<option value="p_type">タイプ</option>
							<option value="p_manufacturer">メーカー</option>
						</select> <input type="text" placeholder="検索するキーワードを入力してください" name="word"
							class="search-input" style="display: none;">
						<button type="submit" class="search-button">確認</button>
					</form>
				</div>
			</div>




			<div class="bbs-content">
				<div class="bbs-content1 bbs-content">
					<div class="bbs-content-main">
						<div class="bbs-content-body">
							<div class="bbs-content-bbs">
								<div class="bbs-main">
									<div class="bbs-main-text1 bbs-main-title-l">商品名</div>
									<div class="bbs-main-text1 bbs-main-title-l">タイプ</div>
									<div class="bbs-main-text1 bbs-main-title-s">単位</div>
									<div class="bbs-main-text1 bbs-main-title-s">単位量</div>
									<div class="bbs-main-text1 bbs-main-title-l">メーカー</div>
									<div class="bbs-main-text1 bbs-main-title-s">単価</div>
									<div class="bbs-main-text1 bbs-main-title-s">在庫数量</div>
									<div class="bbs-main-text1 bbs-main-title">現在庫価格</div>
								</div>
								<!-- for 문으로 데이터 돌림  -->
								<c:forEach var="wb" items="${warehouseBoard}" varStatus="loop">
									<div class="bbs-main" style="background-color: ${loop.index % 2 == 0 ? 'white' : '#f0f0f0'};">
										<div class="bbs-main-text1 bbs-main-text-l">${wb.p_name}</div>
										<div class="bbs-main-text1 bbs-main-text-l">${wb.p_type}</div>
										<div class="bbs-main-text1 bbs-main-text-s">${wb.p_si}</div>
										<div class="bbs-main-text1 bbs-main-text-s">${wb.p_quantity}</div>
										<div class="bbs-main-text1 bbs-main-text-l">${wb.manufacture_name}</div>
										<div class="bbs-main-text1 bbs-main-text-s">
											<fmt:formatNumber value="${wb.p_unicost}" pattern="￥#,###"  />
										</div>
										<div class="bbs-main-text1 bbs-main-text-s">${wb.stock}</div>
										<div class="bbs-main-text1 bbs-main-text">
											<fmt:formatNumber value="${wb.stock * wb.p_unicost}"
												pattern="￥#,###" />
										</div>
									</div>
								</c:forEach>
							</div>
							<div class="paging">
								<c:choose>
									<c:when test="${pageNum != 1}">
										<button
											onclick="location.href='WarehouseBoardPageC?pageNum=${pageNum - 1}&field=${param.field }&word=${param.word }'">prev</button>
									</c:when>
								</c:choose>
								<c:forEach var="i" begin="${pageNum - 3 > 0 ? pageNum - 3 : 1}"
									end="${pageNum + 3 <= totalPage ? pageNum + 3 : totalPage}"
									step="1">
									<c:choose>
										<c:when test="${i eq pageNum}">
											<a
												href="WarehouseBoardPageC?pageNum=${i}&field=${param.field }&word=${param.word }"
												style="color: black; font-weight: bold;">${i}</a>
										</c:when>
										<c:otherwise>
											<a
												href="WarehouseBoardPageC?pageNum=${i}&field=${param.field }&word=${param.word }">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:choose>
									<c:when test="${pageNum != totalPage && totalPage != 0}">
										<button
											onclick="location.href='WarehouseBoardPageC?pageNum=${pageNum + 1}&field=${param.field }&word=${param.word }'">next</button>
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