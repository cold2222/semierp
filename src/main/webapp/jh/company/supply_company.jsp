<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jh/js/search.js"></script>
</head>
<body>
	<div>
		<form action="CompanyC" method="GET">
					<select name="field" id="searchField" onchange="showInput()">
						<option value="all">全体検索</option>
						<option value="c_name">会社名</option>
						<option value="c_keeper">取引先の担当者</option>
						<option value="c_addr">住所</option>
					</select> <input type="text" placeholder="検索するキーワードを入力してください" name="word"
						id="searchWord" class="search-input" style="display: none;">
					<button type="submit" class="search-button">検索</button>
				</form>
	</div>
	<table border="1" style="width: 83%; height: 800px;">
		<c:forEach var="c" items="${cs }">
			<tr>
				<td>${c.c_no }</td>
				<td>${c.c_e_id }</td>
				<td>${c.c_name }</td>
				<td>${c.c_keeper }</td>
				<td>${c.c_phone }</td>
				<td>${c.c_addr }</td>
				<td>${c.c_text }</td>
				<td>
					<button onclick="location.href='UpdateComC?num=${c.c_no }'">수정</button>
				</td>
				<td>
					<button
						onclick="location.href='MakeContractC?num=${c.c_no }'">계약서
						작성</button>
				</td>
			</tr>
		</c:forEach>
	</table>

	<div>
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


</body>
</html>