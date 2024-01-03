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
			<div class="content-head-text">アイテム登録</div>
		</div>
		<div class="content-body">
			<div>
				<form action="UnitC">
					<button class="insert-btn2">単位とタイプ</button>
				</form>
			</div>
			<div class="bbs-content1">
				<form action="ProductRegC" method="post">
					<div class="input-container">
						<label for="p_si" class="input-label-select">単位</label> <select
							name="p_si" id="p_si" class="select">
							<c:forEach var="u" items="${us}">
								<option value="${u.unit}">${u.unit}</option>
							</c:forEach>
						</select>
					</div>
					<div class="input-container">
						<label for="p_type" class="input-label-select">タイプ</label> <select
							name="p_type" id="p_type" class="select">
							<c:forEach var="t" items="${ts}">
								<option value="${t.type}">${t.type}</option>
							</c:forEach>
						</select>
					</div>
					<div class="input-container">
						<label for="p_quantity" class="input-label">単位量</label> <input
							name="p_quantity" id="p_quantity" required="required" type="text">
					</div>
					<div class="input-container">
						<label for="p_name" class="input-label">アイテム名</label> <input
							name="p_name" id="p_name" required="required" type="text">
					</div>
					<div class="input-container">
						<label for="p_unitCost" class="input-label">価格</label> <input
							name="p_unitCost" id="p_unitCost" required="required"
							type="number">
					</div>
					<div class="input-container">
						<label for="p_minStock" class="input-label">最小保有量</label> <input
							name="p_minStock" id="p_minStock" required="required"
							type="number">
					</div>
					<div class="input-container">
						<label for="p_maxStock" class="input-label">最大保有量</label> <input
							name="p_maxStock" id="p_maxStock" required="required"
							type="number">
					</div>
					<div class="input-container">
						<label for="p_manufacturer" class="input-label">製造社</label> <input
							name="p_manufacturer" id="p_manufacturer" required="required"
							type="text">
					</div>

					<div class="button">
						<button class="insert-btn">登録</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>