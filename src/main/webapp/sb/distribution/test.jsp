<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
<link rel="stylesheet" href="test.css">
<script type="text/javascript">
	function updateCalendar() {
		let year = document.getElementById('year');
		let month = document.getElementById('month');
		
		if( year.value > 1990 && month.value > 0 && month.value < 13 && year.value%1 === 0 && month.value%1 === 0){
				location.href='TestC?year='+year.value+'&month='+month.value;
		}else{
			alert("날짜는 정확히 입력해주세요");
			return false
		}
	}
</script>
</head>
<body>
	<div class="selectym">
	<label for="year">년도:</label>
    <input type="number" id="year" value="2023" pattern="^[0-9]+$">
    <label for="month">월:</label>
    <input type="number" id="month" value="12" pattern="^[0-9]+$">
    <button onclick="updateCalendar()">일정 갱신</button>
	</div>
	<h1>${year }년 ${month }월</h1>
	<div class="content">
		<div class="content-top">
		일,요일 들어갈곳
		<div>1</div>
		<div>2</div>
		<div>3</div>
		</div>
		<div class="content-body">
			<div class="content-people">포문돌릴곳</div>
		</div>
	</div>
</body>
</html>