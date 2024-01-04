<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jh/js/search.js"></script>
<link rel="stylesheet" href="jh/css/supply_product.css">
<style type="text/css">
input[type="number"]::-webkit-inner-spin-button, input[type="number"]::-webkit-outer-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}
</style>
</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">アイテム一覧</div>
		</div>
		<div class="content-body">
			<div class="search-container">
				<form action="ProductC" method="GET">
					<select name="field" id="searchField" class="search-select"
						onchange="showInput()">
						<option value="all">全体検索</option>
						<option value="p_type">タイプ</option>
						<option value="p_name">アイテム名</option>
					</select> <input type="text" placeholder="検索するキーワードを入力してください" name="word"
						id="searchWord" class="search-input" style="display: none;">
					<button type="submit" class="search-button">検索</button>
				</form>
			</div>
			
			
			<div class="bbs-content">
				<div class="bbs-content-main">
					<div class="bbs-content-body">
						<div class="bbs-content-bbs">
							<div class="bbs-main">
								<div class="bbs-main-title-s">単位</div>
								<div class="bbs-main-title-s">タイプ</div>
								<div class="bbs-main-title-s">単位量</div>
								<div class="bbs-main-title-l">アイテム名</div>
								<div class="bbs-main-title-s">価格</div>
								<div class="bbs-main-title-s">最小/最大<br>保有量</div>
								<div class="bbs-main-title-s">製造社</div>
							</div>
							<!-- 전체 데이터 표시 -->
							<c:forEach var="p" items="${productItems}">
								<div class="bbs-main">
									<div class="bbs-main-text-s">${p.p_si}</div>
									<div class="bbs-main-text-s">${p.p_type}</div>
									<div class="bbs-main-text-s">
										<c:choose>
											<c:when test="${p.p_quantity eq null}">
            단위 없음
        </c:when>
											<c:otherwise>
            ${p.p_quantity}
        </c:otherwise>
										</c:choose>
									</div>
									<div class="bbs-main-text-l"><span>${p.p_name}</span></div>
									<div class="bbs-main-text-s">${p.p_unitCost}</div>
									<div class="bbs-main-text-s">${p.p_minStock}/${p.p_maxStock}</div>
									<div class="bbs-main-text-s">
										<c:choose>
											<c:when test="${p.p_manufacturer == null}">
            제조사 없음
        </c:when>
											<c:otherwise>
            ${p.p_manufacturer}
        </c:otherwise>
										</c:choose>
									</div>
									<div class="button">
										<button class="insert-btn" onclick="location.href='UpdateProductC?id=${p.p_id}'">修正</button>
										<button class="insert-btn2" onclick="deleteProduct('${p.p_id}')">削除</button>
									</div> 
								</div>
							</c:forEach>
						</div>
						<div class="paging">
							<c:choose>
								<c:when test="${pageNum != 1}">
									<button
										onclick="location.href='ProductPageC?pageNum=${pageNum - 1}&field=${param.field }&word=${param.word }'">prev</button>
								</c:when>
							</c:choose>

							<c:forEach var="i" begin="${pageNum - 3 > 0 ? pageNum - 3 : 1}"
								end="${pageNum + 3 <= totalPage ? pageNum + 3 : totalPage}"
								step="1">
								<c:choose>
									<c:when test="${i eq pageNum}">
										<a
											href="ProductPageC?pageNum=${i}&field=${param.field }&word=${param.word }"
											style="color: black; font-weight: bold;">${i}</a>
									</c:when>
									<c:otherwise>
										<a
											href="ProductPageC?pageNum=${i}&field=${param.field }&word=${param.word }">${i}</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<c:choose>
								<c:when test="${pageNum != totalPage && totalPage != 0}">
									<button
										onclick="location.href='ProductPageC?pageNum=${pageNum + 1}&field=${param.field }&word=${param.word }'">next</button>
								</c:when>
							</c:choose>
						</div>
						<script type="text/javascript">
							function deleteProduct(p_id) {
								if (confirm('정말 삭제하시겠습니까?')) {
									location.href = 'DeleteProductC?p_id='
											+ p_id;
								} else {
									return;
								}
							}
						</script>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>