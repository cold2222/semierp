<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.input-container {
	display: flex;
	margin-bottom: 10px;
}

.input-box {
	border: 1px solid #ccc;
}

.in_name {
	border: 1px solid #ccc;
	width: 73px;
}

.input-container div {
	/* margin-right: 10px; */
	
}

.input-container input, .input-container select {
	width: 70px;
}

.paging {
	margin-top: 10px;
}

.paging a, .paging span {
	margin-right: 5px;
	text-decoration: none;
	padding: 5px 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	color: #333;
}

.paging a:hover {
	background-color: #f0f0f0;
}
</style>
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


</head>
<body>


	<h1 style="font-size: 30pt;">입고 출고 테이블</h1>




	<form action="InExBoardC" method="get">
		<div class="input-container">
			<label for="operationType">구분 :</label> <select name="operationType"
				id="operationType">
				<option value="all">전체</option>
				<option value="inWarehouse">입고</option>
				<option value="exWarehouse">출고</option>
			</select> 검색 : <select id="searchOption" name="searchOption">
				<option value="x">검색조건선택</option>
				<option value="p_name">제품명</option>
				<option value="p_type">타입</option>
			</select> <input type="text" name="word" class="searchInput"
				style="display: none;">
			<button type="submit">확인</button>
		</div>
	</form>


	<div class="input-box input-container">

		<div class="in_name">구분</div>
		<div class="in_name">제품 이름</div>
		<div class="in_name">타입</div>
		<div class="in_name">p_quantity</div>
		<div class="in_name">단위</div>
		<div class="in_name">수량</div>
		<div class="in_name">날짜</div>
		<div class="in_name">창고</div>
	</div>
	<!-- 1줄씩 나타내줄 것들 -->
	<!-- 전체 데이터 표시 -->
	<c:forEach var="al" items="${allInExWarehouse}">
		<div class="input-box input-container">
			<div class="in_name">${al.warehouse_type}</div>
			<div class="in_name">${al.p_name}</div>
			<div class="in_name">${al.p_type}</div>
			<div class="in_name">${al.p_quantity}</div>
			<div class="in_name">${al.p_si}</div>
			<div class="in_name">${al.quantity}</div>
			<div class="in_name">${al.warehouse_date}</div>
			<div class="in_name">${al.warehouse_name}</div>
		</div>
	</c:forEach>





	<div class="paging">
    <c:choose>
        <c:when test="${pageNum != 1}">
            <a href="InExBoardC?pageNum=${pageNum - 1}&operationType=${param.operationType}&searchOption=${param.searchOption}&word=${param.word}">이전</a>
        </c:when>
    </c:choose>

    <c:forEach var="i" begin="${pageNum - 3 > 0 ? pageNum - 3 : 1}" end="${pageNum + 3 <= totalPage ? pageNum + 3 : totalPage}" step="1">
        <c:choose>
            <c:when test="${i eq pageNum}">
                <span>${i}</span>
            </c:when>
            <c:otherwise>
                <a href="InExBoardC?pageNum=${i}&operationType=${param.operationType}&searchOption=${param.searchOption}&word=${param.word}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:choose>
        <c:when test="${pageNum != totalPage}">
            <a href="InExBoardC?pageNum=${pageNum + 1}&operationType=${param.operationType}&searchOption=${param.searchOption}&word=${param.word}">다음</a>
        </c:when>
    </c:choose>
</div>






</body>
</html>