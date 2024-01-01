<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/sjh/admin/staff_info.css">
<title>Insert title here</title>
</head>
<body>
	<div class="content_title">社員</div>
	<hr>
	<div class="dept_container">
		<div class="row-titles">
			<div class="row-title-item">部 署</div>
			<div class="row-title-item">コード</div>
			<div class="row-title-item">員 数</div>
		</div>
		<c:forEach var="dept" items="${deptsInfo}">
			<div class="row-contents">
				<div class="row-content-item">${dept.d_dept}</div>
				<div class="row-content-item">${dept.d_deptno}</div>
				<div class="row-content-item">${dept.d_count}</div>
			</div>
		</c:forEach>
		<div class="row-contents">
			<div class="row-content-item">社員</div>
			<div class="row-content-item">-</div>
			<div class="row-content-item">${staffSum}</div>
		</div>
	</div>
	<div class="Staff_container">
		<div class="col-titles">
			<div class="col-title-item col-deptno">コード</div>
			<div class="col-title-item">部 署</div>
			<div class="col-title-item">社員番号</div>
			<div class="col-title-item">お名前</div>
			<div class="col-title-item">職 級</div>
			<div class="col-title-item col-long">E-Mail</div>
			<div class="col-title-item">Tel</div>
			<div class="col-title-item">入社日</div>
			<div class="col-title-item button">修 正</div>
		</div>
		<c:forEach var="staff" items="${staffsInfo}">
			<div class="col-contents">
				<div class="col-content-item col-deptno">${staff.e_deptno }</div>
				<div class="col-content-item">${staff.e_dept }</div>
				<div class="col-content-item">${staff.e_no }</div>
				<div class="col-content-item">${staff.e_name}</div>
				<div class="col-content-item">${staff.e_rank }</div>
				<div class="col-content-item col-long">${staff.e_email }</div>
				<div class="col-content-item">${staff.e_tel}</div>
				<div class="col-content-item">${staff.e_joined_company}</div>
				<div class="col-content-item button">
					<button
						onclick="location.href='StaffAdminModifyC?e_no=${staff.e_no }'">修正</button>
				</div>
			</div>
		</c:forEach>
		<div class="btn-area">
			<div></div>
			<div class="btn-area-mid">
				<c:if test="${currentPage != 1 }">
					<button class="idx-btn"
						onclick="location.href='StaffC?pageNo=${currentPage - 1}'">
						prev
					</button>
				</c:if>
				<c:forEach var="index" items="${indexList}">
					<c:if test="${index ne 0}">
						<button class="idx-btn"
							onclick="location.href='StaffC?pageNo=${index}'">${index}</button>
					</c:if>
				</c:forEach>
				<c:if test="${currentPage != lastPage }">
					<button class="idx-btn"
						onclick="location.href='StaffC?pageNo=${currentPage + 1}'">
						next
					</button>
				</c:if>
			</div>
			<button class="reg-btn" onclick="location.href='StaffRegC'">社員登録</button>
		</div>
	</div>

</body>
</html>