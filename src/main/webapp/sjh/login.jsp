<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/login.css">
<title>Insert title here</title>
</head>
<body>
<div class="loginTop">
	<div class="topArea">
		<div class="topArea-img">
			<img src="files/sjh/muy.jpg">
		</div>
		<div class="topArea-login">
			<img src="files/sjh/SolLogistics-crop.jpg">
			<form class="loginForm" action="Login" method="POST">
			<span style="display:flex; justify-content: space-between; font-size: 18pt; font-weight: bolder;"><span class="inputText">&nbsp;ID&nbsp;:&nbsp;</span><input style="width:240px;" name="id"></span><br>
			<span style="display:flex; justify-content: space-between; font-size: 18pt; font-weight: bolder;"><span class="inputText">PW&nbsp;:&nbsp;</span><input style="width:240px;" name="pw"></span><br>
			<button class="loginBtn">로그인</button> 
			${error }
			</form>
		</div>
	</div>
</div>

</body>
</html>