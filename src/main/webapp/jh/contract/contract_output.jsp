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
					<span>契約書番号</span> <span>取引先の会社番号</span> <span>本社員番号</span> <span>作成日</span>
					<span>納期日</span> <span>取引状態</span> 
				</div>
				<div  class="span-wrap">
					<span>${contract.c_contract_no}</span> <span>${contract.c_c_no}</span>
					<span>${contract.c_e_id}</span> <span>${contract.c_created_date}</span>
					<span>${contract.c_due_date}</span> <span> <c:if
							test="${contract.c_type eq 1 && contract.c_status eq 1}">配送未指定</c:if> <c:if
							test="${contract.c_type eq 1 && contract.c_status eq 2}">配送日確定</c:if> <c:if
							test="${contract.c_type eq 1 && contract.c_status eq 3}">配送完了</c:if> <c:if
							test="${contract.c_type eq 1 && contract.c_status eq 4}">倉庫摘載完了</c:if> <c:if
							test="${contract.c_type eq 2 && contract.c_status eq 1}">配送未指定</c:if> <c:if
							test="${contract.c_type eq 2 && contract.c_status eq 2}">配送日確定</c:if> <c:if
							test="${contract.c_type eq 2 && contract.c_status eq 3}">配送準備中</c:if> <c:if
							test="${contract.c_type eq 2 && contract.c_status eq 4}">配送完了</c:if></span>
				</div>
			</div>
			<hr>
			<div class="item-details2">
				<div class="span-wrap">
					<span>アイテム番号</span> <span>アイテム名</span> <span>買い求めた品の数</span> <span>一つ当たりの価格</span>
				</div>
				<c:forEach var="item" items="${contract.items}">
					<div class="span-wrap">
						<span>${item.ci_p_id}</span> <span>${item.p_name}</span> <span>${item.ci_count}</span>
						<span>${item.ci_unit_price}</span>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>