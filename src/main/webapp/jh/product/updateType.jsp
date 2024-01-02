<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="jh/css/update_unit.css">
</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">タイプ修正</div>
		</div>
		 <div class="content-body">
		<div class="update-form">
			<form action="UpdateTypeC?t=${t.type}" method="post">
				<div class="table-container">
					<div class="table-row">
						<div class="table-cell">
							<label for="typeInput">タイプ</label> <input id="typeInput"
								name="type" value="${t.type}" class="input-field">
						</div>
					</div>
				</div>
				<button class="submit-button">修正確認</button>
			</form>
		</div>
	</div>
	</div>
</body>
</html>