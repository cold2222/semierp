<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/sjh/admin/staff_info.css">
<link rel="stylesheet" href="css/sjh/admin/company_inform.css">
<title>Insert title here</title>
</head>
<body>
	<div class="content_title">
		<div>お知らせ</div>
		<div></div>
		<div class="date">
		</div>
	</div>
	<hr>
	<div class="infrom_container">
		<div class="col-titles">
			<div class="col-title-item no-col">No</div>
			<div class="col-title-item title-col">題目</div>
			<div class="col-title-item content-col">内容</div>
			<div class="col-title-item">登録日</div>
			<div class="col-title-item idx-col">経営部</div>
			<div class="col-title-item idx-col">輸入部</div>
			<div class="col-title-item idx-col">販売部</div>
			<div class="col-title-item idx-col">運送部</div>
			<div class="col-title-item idx-col">倉庫部</div>
			<div class="col-title-item button">修 正</div>
		</div>
		<c:forEach var="companyInform" items="${companyInforms}">
			<div class="col-contents">
				<div class="col-content-item no-col">${companyInform.ci_no }</div>
				<div class="col-content-item title-col">${companyInform.ci_title }</div>
				<div class="col-content-item content-col">${companyInform.ci_content }</div>
				<div class="col-content-item">${companyInform.ci_date}</div>
				<div class="col-content-item idx-col idx-checkbox" ${companyInform.ci_manageIdx == 1 ? 'id="idx-checked"' : '' }></div>
				<div class="col-content-item idx-col idx-checkbox" ${companyInform.ci_importIdx == 1 ? 'id="idx-checked"' : ''}></div>
				<div class="col-content-item idx-col idx-checkbox" ${companyInform.ci_salesIdx == 1 ? 'id="idx-checked"' : ''}></div>
				<div class="col-content-item idx-col idx-checkbox" ${companyInform.ci_distributionIdx == 1 ? 'id="idx-checked"' : ''}></div>
				<div class="col-content-item idx-col idx-checkbox" ${companyInform.ci_warehouseIdx == 1 ? 'id="idx-checked"' : ''}></div>
				<div class="col-content-item button">
					<button
						onclick="location.href='CompanyInformModifyC?e_no=${staff.e_no }'">修正</button>
				</div>
			</div>
		</c:forEach>
		<div class="btn-area">
			<div></div>
			<div class="btn-area-mid">
				<c:if test="${currentPage != 1 }">
					<button class="idx-btn colorGold"
						onclick="location.href='CompanyInformC?pageNo=${currentPage - 1}'">
						prev
					</button>
				</c:if>
				<c:forEach var="index" items="${indexList}">
					<c:if test="${index ne 0}">
						<button class="idx-btn ${currentPage == index ? 'colorGold' : ''}"
							onclick="location.href='CompanyInformC?pageNo=${index}'">${index}</button>
					</c:if>
				</c:forEach>
				<c:if test="${currentPage != lastPage }">
					<button class="idx-btn colorGold"
						onclick="location.href='CompanyInformC?pageNo=${currentPage + 1}'">
						next
					</button>
				</c:if>
			</div>
			<button class="reg-btn" onclick="location.href='CompanyInformRegC'">お知らせ登録</button>
		</div>
	</div>
	
</body>
</html>