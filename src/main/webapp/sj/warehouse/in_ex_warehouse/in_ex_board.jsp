<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<link rel="stylesheet" href="sj/warehouse_css/in_ex_board.css">
</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">入庫-出庫内訳</div>
		</div>
		<div class="content-body">
			<div class="search-container">
				<div class="search-line">

					<form action="InExBoardC" method="get">
							<label for="operationType"><span class="value-box">
									種類: </span></label> <select name="operationType" id="operationType"
								class="search-select">
								<option value="all">全体</option>
								<option value="inWarehouse">入庫</option>
								<option value="exWarehouse">出庫</option>
							</select> <span class="value-box"> 検索 : </span> <select id="searchOption"
								name="searchOption" class="search-select">
								<option value="x">検索条件</option>
								<option value="p_name">商品名</option>
								<option value="p_type">タイプ</option>
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
									<div class="bbs-main-text1 bbs-main-title-s">種類</div>
									<div class="bbs-main-text1 bbs-main-title-l">商品名</div>
									<div class="bbs-main-text1 bbs-main-title-l">タイプ</div>
									<div class="bbs-main-text1 bbs-main-title-s">単位</div>
									<div class="bbs-main-text1 bbs-main-title-s">単位量</div>
									<div class="bbs-main-text1 bbs-main-title-s">在庫数量</div>
									<div class="bbs-main-text1 bbs-main-title-s">入-出庫日</div>
									<div class="bbs-main-text1 bbs-main-title-s">倉庫</div>
								</div>
								<!-- 전체 데이터 표시 -->
								<c:forEach var="al" items="${allInExWarehouse}" varStatus="loop">
									<div class="bbs-main"
										style="background-color: ${loop.index % 2 == 0 ? 'white' : '#f0f0f0'};">
										<div class="bbs-main-text1 bbs-main-text-s">${al.warehouse_type}</div>
										<div class="bbs-main-text1 bbs-main-text-l">${al.p_name}</div>
										<div class="bbs-main-text1 bbs-main-text-l">${al.p_type}</div>
										<div class="bbs-main-text1 bbs-main-text-s">${al.p_quantity}</div>
										<div class="bbs-main-text1 bbs-main-text-s">${al.p_si}</div>
										<div class="bbs-main-text1 bbs-main-text-s">${al.quantity}</div>
										<div class="bbs-main-text1 bbs-main-text-s">${al.warehouse_date}</div>
										<div class="bbs-main-text1 bbs-main-text-s">${al.warehouse_name}</div>
									</div>
								</c:forEach>
							</div>

							<div class="paging">
								<c:choose>
									<c:when test="${pageNum != 1}">
										<a
											href="InExBoardC?pageNum=${pageNum - 1}&operationType=${param.operationType}&searchOption=${param.searchOption}&word=${param.word}">前のページ</a>
									</c:when>
								</c:choose>

								<c:forEach var="i" begin="${pageNum - 3 > 0 ? pageNum - 3 : 1}"
									end="${pageNum + 3 <= totalPage ? pageNum + 3 : totalPage}"
									step="1">
									<c:choose>
										<c:when test="${i eq pageNum}">
											<span>${i}</span>
										</c:when>
										<c:otherwise>
											<a
												href="InExBoardC?pageNum=${i}&operationType=${param.operationType}&searchOption=${param.searchOption}&word=${param.word}">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:choose>
									<c:when test="${pageNum != totalPage && totalPage != 0}">
										<a
											href="InExBoardC?pageNum=${pageNum + 1}&operationType=${param.operationType}&searchOption=${param.searchOption}&word=${param.word}">次のページ</a>
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