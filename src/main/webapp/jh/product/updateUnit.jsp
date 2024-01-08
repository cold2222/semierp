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
			<div class="content-head-text">単位修正</div>
		</div>
		 <div class="content-body">
<div class="update-form">
    <form action="UpdateUnitC?u=${u.unit}" method="post">
        <div class="table-container">
            <div class="table-row">
                <div class="table-cell">
                    <label for="unitInput">単位</label>
                    <input id="unitInput" name="unit" value="${u.unit}" class="input-field" autocomplete="off">
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