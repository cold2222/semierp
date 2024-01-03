<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jh/js/updateCom.js"></script>
<link rel="stylesheet" href="jh/css/updateCom.css">
</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">取引先の詳細内容</div>
		</div>

		<div class="content-body">
			<div class="bbs-content1">
				<form action="UpdateComC" method="post">
					<input name="c_no" type="hidden" value="${c.c_no }">
					<div class="input-container">
						<label for="c_name" class="input-label">取引先名:</label> <input
							name="c_name" id="c_name" required="required" type="text"
							value="${c.c_name }" disabled="disabled">
					</div>
					<div class="input-container">
						<label for="c_keeper" class="input-label">取引先の担当者:</label> <input
							name="c_keeper" id="c_keeper" required="required" type="text"
							value="${c.c_keeper }" disabled="disabled">
					</div>
					<div class="input-container">
						<label for="c_phone" class="input-label">取引先の連絡先:</label> <input
							name="c_phone" id="c_phone" required="required" type="text"
							value="${c.c_phone }" disabled="disabled">
					</div>
					<div class="input-container">
						<label for="c_addr" class="input-label">取引先の住所:</label> <input
							name="c_addr" id="c_addr" required="required" type="text"
							value="${c.c_addr }" disabled="disabled">
					</div>
					<div class="input-container">
						<label for="c_text" class="input-label">備考:</label>
						<textarea name="c_text" id="c_text" rows="4" disabled="disabled">${c.c_text }</textarea>
					</div>

					<div class="button" id="saveButton" style="display: none;">
						<button class="insert-btn3">保存</button>
					</div>
				</form>
				<div class="button" id="editButton">
					<button class="insert-btn" onclick="toggleEdit()">修正する</button>
					<button class="insert-btn2" onclick="deleteCompany('${c.c_no }')">削除</button>
				</div>
					<c:if test="${param.isSuccess != null }">
						<div class="insert-btn1">修正完了</div>
					</c:if>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="jh/js/supply_company.js"></script>
</body>
</html>