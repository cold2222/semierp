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
		<c:forEach var="ic" items="${ics }">
			<tr>
				<td>${ic.ic_no }</td>
				<td>${ic.ic_e_id }</td>
				<td>${ic.ic_name }</td>
				<td>${ic.ic_keeper }</td>
				<td>${ic.ic_phone }</td>
				<td>${ic.ic_addr }</td>
				<td>${ic.ic_text }</td>
				<td>
					<button onclick="location.href='UpdateComC?num=${ic.ic_no }'">수정</button>
				</td>
				<td>
					<button
						onclick="location.href='MakeContractC?num=${ic.ic_no }'">계약서
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