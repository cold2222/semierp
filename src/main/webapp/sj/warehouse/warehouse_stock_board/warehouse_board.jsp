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
					var input = document.querySelector('.searchInput');
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
				<form action="WarehouseBoardC" method="get">
					<label for="operationType">倉庫 :</label> <select
						name="operationType" id="operationType">
						<option value="all">全体</option>
						<option value="one">1倉庫</option>
						<option value="two">2倉庫</option>
						<option value="three">3倉庫</option>
					</select> 検索 : <select id="searchOption" name="searchOption">
						<option value="x">検索条件</option>
						<option value="p_name">商品名</option>
						<option value="p_type">タイプ</option>
						<option value="p_manufacturer">メーカー</option>
					</select> <input type="text" name="word" class="searchInput"
						style="display: none;">
					<button type="submit">確認</button>
					<div class="right-align">
						在庫価格 :
						<c:choose>
							<c:when test="${operationType eq 'all' or operationType eq null}">
								<c:set var="totalValue" value="0" />
								<c:forEach var="ts" items="${totalStockList}">
									<c:set var="totalValue" value="${totalValue + ts.total_stock}" />
								</c:forEach>
								<fmt:formatNumber value="${totalValue}" pattern="#,###" />
							</c:when>
							<c:otherwise>
								<c:forEach var="ts" items="${totalStockList}">
									<c:if test="${ts.warehouse_name eq operationType}">
                	 ${operationType}: ${ts.total_stock}
            </c:if>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
				</form>
			</div>
			<div class="bbs-content">
				<div class="bbs-content1 bbs-content">
					<div class="bbs-content-main">
						<div class="bbs-content-body">
							<div class="bbs-content-bbs">
								<div class="bbs-main">
									<div class="bbs-main-text1 bbs-main-title">商品名</div>
									<div class="bbs-main-text1 bbs-main-title">タイプ</div>
									<div class="bbs-main-text1 bbs-main-title">単位量</div>
									<div class="bbs-main-text1 bbs-main-title">メーカー</div>
									<div class="bbs-main-text1 bbs-main-title">単価</div>
									<div class="bbs-main-text1 bbs-main-title">在庫数量</div>
									<div class="bbs-main-text1 bbs-main-title">現在庫価格</div>
									<div class="bbs-main-text1 bbs-main-title">担当者</div>
								</div>
								<!-- 1줄씩 나타내줄 것들 -->
								<!-- 입고 데이터 표시 -->
								<c:forEach var="wb" items="${warehouseBoard}">
									<div class="bbs-main">
										<div class="bbs-main-text1 bbs-main-title">${wb.p_name}</div>
										<div class="bbs-main-text1 bbs-main-title">${wb.p_type}</div>
										<div class="bbs-main-text1 bbs-main-title">${wb.p_quantity}</div>
										<div class="bbs-main-text1 bbs-main-title">${wb.p_si}</div>
										<div class="bbs-main-text1 bbs-main-title">${wb.manufacture_name}</div>
										<div class="bbs-main-text1 bbs-main-title">
											<fmt:formatNumber value="${wb.p_unicost}" pattern="#,###" />
										</div>
										<div class="bbs-main-text1 bbs-main-title">${wb.stock}</div>
										<div class="bbs-main-text1 bbs-main-title">
											<fmt:formatNumber value="${wb.stock * wb.p_unicost}"
												pattern="#,###" />
										</div>
										<div class="bbs-main-text1 bbs-main-title">${wb.warehouse_name}</div>
										<div class="bbs-main-text1 bbs-main-title">${wb.e_name}</div>
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