<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>semi ERP</title>
<link rel="stylesheet" href="css/index2.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="index-contents">
		<div class="index-side-menu">
			<jsp:include page="${sidebar }"></jsp:include>
		</div>
		<div class="index-content">
			<jsp:include page="${contentPage }"></jsp:include>
		</div>
	</div>
	<div class="footer">  
	</div>
</body>
</html>