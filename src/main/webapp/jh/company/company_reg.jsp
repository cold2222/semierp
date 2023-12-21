<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
/* 배경 뒷부분 스타일 */
.modal-background {
	display: none;
	position: fixed;
	z-index: 999;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.4);
	justify-content: center;
	align-items: center;
}

/* 모달 스타일 */
.modal-content {
	display: none;
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
	padding: 20px;
	width: 800px;
	height: 500px;
	text-align: center;
	position: relative;
	justify-content: center;
}

.modal p {
	margin-bottom: 20px;
}

.modal button {
	padding: 8px 16px;
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.modal button:hover {
	background-color: #0056b3;
}

.modal-foot {
	position: absolute;
	bottom: 5px;
}
.div-row{
	display: flex;
}
</style>
</head>
<body>
	<form action="CompanyRegC" method="post">
		<table>
			<tr>
				<td><input name="c_name" placeholder="회사 이름" required="required"></td>
				<td><input name="c_keeper" placeholder="업체쪽 담당자" required="required"></td>
				<td><input name="c_phone" placeholder="업체쪽 연락처" required="required"></td>
				<td><input name="c_addr" placeholder="회사 주소" required="required"></td>
				<td><input name="c_text" placeholder="비고"></td>
				<td><button>등록</button></td>
			</tr>
		</table>
	</form>
</body>
</html>