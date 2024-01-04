<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/sjh/admin/company_inform_reg.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="content_title">
		<div>お知らせ登録</div>
		<div></div>
		<div class="date"></div>
	</div>
	<hr>
	<div class="companyInformInput-content">
		<form action="CompanyInformRegC" method="post">

			<div class="input-row">
				<div class="input-title" required>題目</div>
				<input class="textInput" name="ci_title" size=60 maxlength=30>
			</div>
			<div class="input-row">
				<div class="input-title" required>部署</div>
				<div class="deptCheckbox-area">
				<label class="deptCheckbox-label"> <input
					class="deptCheckbox" type="checkbox" name="ci_depts" value="1">経営
				</label> &nbsp;&nbsp; <label class="deptCheckbox-label"> <input
					class="deptCheckbox" type="checkbox" name="ci_depts" value="10">輸入
				</label> &nbsp;&nbsp; <label class="deptCheckbox-label"> <input
					class="deptCheckbox" type="checkbox" name="ci_depts" value="100">販売
				</label> &nbsp;&nbsp; <label class="deptCheckbox-label"> <input
					class="deptCheckbox" type="checkbox" name="ci_depts" value="1000">運送
				</label> &nbsp;&nbsp; <label class="deptCheckbox-label"> <input
					class="deptCheckbox" type="checkbox" name="ci_depts" value="10000">販売
				</label>
				</div>
			</div>
			<div class="input-row">
				<div class="input-title">内容</div>
				<textarea class="inform-textArea" name="ci_content" ></textarea>
			</div>
			<div class="input-row btn-area">
			<div></div>
			<div>
			 <button type="button" class="bottom-btn back-btn" onclick="location.href='CompanyInformC'">戻る</button>
			 <button class="bottom-btn submit-btn" >提出</button>
			</div>
			</div>
			


		</form>
	</div>


</body>
</html>