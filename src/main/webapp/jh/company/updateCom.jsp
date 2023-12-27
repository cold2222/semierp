<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="jh/css/updateCom.css">
</head>
<body>
		<div class="contents">
		<div class="content-head">
			<div class="content-head-text">取引先の詳細内容</div>
		</div>
	<c:if test="${param.isSuccess != null }">
		<div>${param.isSuccess }</div>
	</c:if>
	

	<form action="UpdateComC" method="post">
		<input name="c_no" type="hidden" value="${c.c_no }">
		<table>
			<tr>
				<td>회사 이름<input required="required" name="c_name"
					value="${c.c_name }"></td>
				<td>업체쪽 담당자<input required="required" name="c_keeper"
					value="${c.c_keeper }">
				</td>
				<td>업체쪽 담당자 연락처<input required="required" name="c_phone"
					value="${c.c_phone }"></td>
				<td>업체쪽 회사 주소<input required="required" name="c_addr"
					value="${c.c_addr }"></td>
				<td>비고<input name="c_text" value="${c.c_text }"></td>
			</tr>
		</table>
		<button>수정 확인</button>
	</form>
	<div class="company-main-text4 company-main-text">
		<button onclick="deleteCompany('${c.c_no }')">削除</button>
	</div>
	</div>
</body>
</html>