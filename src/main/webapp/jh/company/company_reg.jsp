<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="jh/css/company_reg.css">
</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">取引先登録</div>
		</div>
		<div class="contents">
			<div class="content-body">
				<div class="bbs-content1">
					<form action="CompanyRegC" method="post">
						<div class="input-container">
							<label for="c_name" class="input-label">取引先名:</label> <input
								name="c_name" id="c_name" required="required" type="text">
						</div>
						<div class="input-container">
							<label for="c_keeper" class="input-label">取引先の担当者:</label> <input
								name="c_keeper" id="c_keeper" required="required" type="text">
						</div>
						<div class="input-container">
							<label for="c_phone" class="input-label">取引先の連絡先:</label> <input
								name="c_phone" id="c_phone" required="required" type="text">
						</div>
						<div class="input-container">
							<label for="c_addr" class="input-label">取引先の住所:</label> <input
								name="c_addr" id="c_addr" required="required" type="text">
						</div>
						<div class="input-container">
							<label for="c_text" class="input-label">備考:</label> <textarea
								name="c_text" id="c_text" rows="4"></textarea>
						</div>
						<div class="button">
							<button class="insert-btn">登録</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>