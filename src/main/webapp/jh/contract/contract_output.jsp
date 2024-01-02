<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="jh/css/contract_output.css">
</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">契約書詳細</div>
		</div>
		<div class="content-body">
			<div class="item-details1">
				<div class="span-wrap">
					<div class="item">契約情報</div>
				</div>
				<div class="span-wrap1">
					<span class="company">取引先名</span> <span class="date">取引担当社員</span>
					<span class="date">作成日</span>
					<c:if test="${contract.c_type eq 1}">
						<span class="date">入港日</span>
					</c:if>
					<c:if test="${contract.c_type eq 2}">
						<span class="date">納期日</span>
					</c:if>
					<span class="date">取引状態</span>
				</div>
				<div class="span-wrap">
					<span class="company">${contract.c_name}</span> <span class="date">${contract.e_name}</span>
					<span class="date">${contract.c_created_date}</span> <span
						class="date">${contract.c_due_date}</span> <span class="date">
						<c:if test="${contract.c_type eq 1 && contract.c_status eq 1}">配送未指定</c:if>
						<c:if test="${contract.c_type eq 1 && contract.c_status eq 2}">配送日確定</c:if>
						<c:if test="${contract.c_type eq 1 && contract.c_status eq 3}">配送完了</c:if>
						<c:if test="${contract.c_type eq 1 && contract.c_status eq 4}">倉庫摘載完了</c:if>
						<c:if test="${contract.c_type eq 2 && contract.c_status eq 1}">配送未指定</c:if>
						<c:if test="${contract.c_type eq 2 && contract.c_status eq 2}">配送日確定</c:if>
						<c:if test="${contract.c_type eq 2 && contract.c_status eq 3}">配送準備中</c:if>
						<c:if test="${contract.c_type eq 2 && contract.c_status eq 4}">配送完了</c:if>
					</span>
				</div>
			</div>
			<hr style="margin-top: 50px">
			<div class="item-details2">
				<div class="span-wrap">
					<div class="item">契約アイテム一覧</div>
				</div>
				<div class="span-wrap1">

					<span class="number">Num</span> <span class="item-name">タイプ</span><span
						class="item-name">アイテム名</span> <span class="item-count">単位量</span>
					<span class="item-count">単位</span> <span class="item-count">品の個数</span>
					<span class="item-cost">単価</span>
				</div>
				<c:forEach var="item" items="${contract.items}" varStatus="loop">
					<div class="span-wrap">

						<span class="number">${loop.index + 1}</span> <span
							class="item-name">${item.p_type} </span> <span class="item-name">${item.p_name}</span>
						<span class="item-count">${item.p_quantity}</span> <span
							class="item-count">${item.p_si}</span> <span class="item-count">${item.ci_count}</span>
						<span class="item-cost">${item.ci_unit_price}</span>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>