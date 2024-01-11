<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous">
	
</script>
<link rel="stylesheet" href="css/sjh/statistics/common.css">
<link rel="stylesheet" href="css/sjh/statistics/product.css">
<link rel="stylesheet" href="sb/distribution_css/mainbbs/main_bbs.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="js/sjh/admin/utils/input_date_init.js"></script>
<script src="js/sjh/statistics/statistics_import_product.js"></script>

</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">輸入商品情報</div>
		</div>
		<div class="content-body">
			<div class="bbs-content1 bbs-content">
				<div class="date">
					日付 : <input type="date" id="dateInput" name="setDate" required
						onchange="redirectToServlet('ImportProductStatisticByMonthC','${param.pageNo }')">
				</div>
				<div class="board">
					<div class="board-title">商品輸入量(Top 7)</div>
					<div class="post">
						<canvas id="contractOfYear"></canvas>
					</div>
				</div>

				<div class="board">
					<div class="board-title">商品輸入金額(Top 7)</div>
					<div class="post">
						<canvas id="contractCostOfYear"></canvas>
					</div>
				</div>
				<div class="long-board">
					<div class="board-title">商品輸入情報</div>
					<div class="row-titles">
						<div class="col-title product">商品名</div>
						<div class="col-title product">商品情報</div>
						<div class="col-title product">価格(基準)</div>						
						<div class="col-title product">在庫[Min, Max]</div>
						<div class="col-title product">メーカー</div>
						<div class="col-title product">輸入量</div>
						<div class="col-title product">輸入金額</div>
						<div class="col-title product">基準価値</div>
						<div class="col-title product">金額差</div>
					</div>
					<c:forEach var="product" items="${products }">
						<div class="row-contents">
							<div class="col-content product">${product.p_name }</div>
							<div class="col-content product">${product.p_type }${product.p_si }
								${product.p_quantity }</div>
							<div class="col-content product">${product.p_unitcost }</div>
							<div class="col-content product">[${product.p_minStock },
								${product.p_maxStock }]</div>
							<div class="col-content product">${product.p_manufacturer }</div>
							<div class="col-content product">${product.p_countAll }</div>
							<div class="col-content product">${product.p_sumByRealCost }</div>
							<div class="col-content product">${product.p_sumByUnitCost }</div>
							<div class="col-content product">${product.p_sumByUnitCost - product.p_sumByRealCost}</div>
						</div>
					</c:forEach>
					<div class="btn-area">
						<div></div>
						<div class="btn-area-mid">
							<c:if test="${currentPage != 1 }">
								<button class="idx-btn colorGold"
									onclick="location.href='ImportProductStatisticByMonthC?pageNo=${currentPage - 1}&setDate=${param.setDate}'">
									prev</button>
							</c:if>
							<c:forEach var="index" items="${indexList}">
								<c:if test="${index ne 0}">
									<button
										class="idx-btn ${currentPage == index ? 'colorGold' : ''}"
										onclick="location.href='ImportProductStatisticByMonthC?pageNo=${index}&setDate=${param.setDate}'">${index}</button>
								</c:if>
							</c:forEach>
							<c:if test="${currentPage != lastPage }">
								<button class="idx-btn colorGold"
									onclick="location.href='ImportProductStatisticByMonthC?pageNo=${currentPage + 1}&setDate=${param.setDate}'">
									next</button>
							</c:if>
						</div>
						<div></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>