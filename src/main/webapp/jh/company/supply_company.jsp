<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="DetailComC">
			<input name="search" placeholder="검색">
			<button>검색</button>
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
		<c:if test="${curPageNo != 1 }">
			<a href="CompanyPageC?p=${curPageNo - 1 }"><button>이전</button></a>
		</c:if>
		<c:if test="${curPageNo != pageCount }">
			<a href="CompanyPageC?p=${curPageNo + 1 }"><button>다음</button></a>
		</c:if>
	</div>


</body>
</html>