<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="sb/distribution_css/employee/insertemployee.css">
</head>
<body>
  <div class="container">
    <h1>運送部　社員登録ページ</h1>
    <form action="DistributionInsertEmployeeC" method="post">
      <div class="form-group">
        <label for="name">名前:</label>
        <input type="text" id="name" name="name" required>
      </div>
      <div class="form-group">
        <label for="position">職級:</label>
        <input type="text" id="position" name="position" required>
      </div>
      <div class="form-group">
        <label for="phone">電話番号:</label>
        <input type="tel" id="phone" name="phone" required>
      </div>
      <div class="form-group">
        <label for="email">E-Mail:</label>
        <input type="email" id="email" name="email" required>
      </div>
      <div class="form-group">
        <label for="hireDate">入社日:</label>
        <input type="date" id="hireDate" name="hireDate" required>
      </div>
      <div class="form-group">
        <button>入力完了</button>
      </div>
    </form>
  </div>
</body>
</html>