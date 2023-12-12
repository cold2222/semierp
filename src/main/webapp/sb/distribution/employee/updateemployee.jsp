<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="sb/distribution_css/employee/updateemployee.css">
</head>
<body>
  <div class="container">
    <h1>運送部　社員登録ページ</h1>
    <form action="DistributionEmployeeUpdateC" method="post">
      <input type="hidden" name="id" value="${emp.e_id }">
      <div class="form-group">
        <label for="name">名前:</label>
        <input type="text" id="name" name="name" required value="${emp.e_name }">
      </div>
      <div class="form-group">
        <label for="position">職級:</label>
        <input type="text" id="position" name="position" required value="${emp.e_position }">
      </div>
      <div class="form-group">
        <label for="phone">電話番号:</label>
        <input type="tel" id="phone" name="phone" required value="${emp.e_phone_number }">
      </div>
      <div class="form-group">
        <label for="email">E-Mail:</label>
        <input type="email" id="email" name="email" required value="${emp.e_email }">
      </div>
      <div class="form-group">
        <label for="hireDate">入社日:</label>
        <input type="date" id="hireDate" name="hireDate" required value="${emp.e_hire_date }">
      </div>
      <div class="form-group">
        <button>入力完了</button>
      </div>
    </form>
  </div>
</body>
</html>