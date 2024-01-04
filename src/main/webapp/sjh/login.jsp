<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/login.css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="loginTop">
		<div class="backgroundContainer">
			<img src="files/sjh/muy.jpg" class="backgroundImage">
			<div class="topArea-login">
				<div class="loginFormContainer">
					<img src="logoFolder/SOLlogisticsHeaderLogoNewRemoveBackground.png"
						class="loginLogo">
					<form class="loginForm" action="Login" method="POST">
						<h1>ログイン</h1>
						<div class="int-area">
							<input name="id" id="id" type="text" autocomplete="off" required>
							<label for="id">アカウント</label>
						</div>
						<div class="int-area">
							<input name="pw" id="pw" type="password" autocomplete="off"
								required> <label for="pw">パスワード</label>
						</div>
						<div class="btn-area">
							<button class="loginBtn" id="btn">ログイン</button>
						</div>
					</form>
					<div class="caption">
						<span id="loginError">${error}</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		let id = $('#id');
		let pw = $('#pw');
		let btn = $('#btn');
		let loginForm = $('#loginForm');
		let loginError = $('#loginError');

		$(btn).on('click', function() {
			if ($(id).val() == "") {
				$(id).next('label').addClass('warning');
				setTimeout(function() {
					$('label').removeClass('warning');
				}, 1500);

			} else if ($(pw).val() == "") {
				$(pw).next('label').addClass('warning');
				setTimeout(function() {
					$('label').removeClass('warning');
				}, 1500);
			}

		});
	</script>
</body>




</html>