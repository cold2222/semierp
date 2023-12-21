<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.10/index.global.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous">
</script>	
<script type="text/javascript" src="sb/distribution_js/shipping.js"></script>
<style  type="text/css">
.content{
	width: 91%;
	background-color: white;
	margin: 30px 30px;
	padding: 15px;
}
.fc-event-title {
    white-space: normal; /* 줄 바꿈을 허용하여 넘치는 텍스트를 다음 줄로 이동시킴 */
    overflow: visible; /* 텍스트가 요소 너비를 넘어갈 경우 텍스트를 자르지 않고 나타내도록 설정 */
    max-width: none; /* 최대 너비 설정 해제 */
}
</style>
</head>
<body>
	<div class="content">
		<div id='calendar'></div>
	</div>
</body>
</html>