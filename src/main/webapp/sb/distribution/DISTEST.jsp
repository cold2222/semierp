<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>유통부 사원 조회</title>
<style type="text/css">
.search-container {
    text-align: center;
    margin: 20px;
}

.search-input {
    padding: 10px;
    width: 250px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin-right: 5px;
}

.search-select {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin-right: 5px;
}

.search-button {
    padding: 10px 20px;
    background-color: #337ab7;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
</style>
</head>
<body>
<div class="search-container">
    <form action="검색결과페이지 URL" method="GET">
        <select name="Field" id="searchField" class="search-select" onchange="showInput()">
            <option value="all">全体検索</option>
            <option value="title">タイトル</option>
            <option value="content">内容</option>
        </select>
        <input type="text" placeholder="検索するキーワードを入力してください" name="Word" id="searchWord" class="search-input" style="display: none;">
        <button type="submit" class="search-button">検索</button>
    </form>
</div>
  <div class="container">
    <h1>유통부 사원 조회</h1>
    <table class="employee-table">
      <thead>
        <tr>
          <th>이름</th>
          <th>직급</th>
          <th>전화번호</th>
          <th>이메일</th>
          <th>입사일</th>
          <th>수정</th>
          <th>삭제</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>John Doe</td>
          <td>Manager</td>
          <td>123-456-7890</td>
          <td>johndoe@example.com</td>
          <td>2023-01-15</td>
          <td><button class="edit-btn">수정</button></td>
          <td><button class="delete-btn">삭제</button></td>
        </tr>
        <tr>
          <td>Jane Smith</td>
          <td>Sales Representative</td>
          <td>987-654-3210</td>
          <td>janesmith@example.com</td>
          <td>2023-03-20</td>
          <td><button class="edit-btn">수정</button></td>
          <td><button class="delete-btn">삭제</button></td>
        </tr>
        <tr>
          <td>Alex Johnson</td>
          <td>Analyst</td>
          <td>456-789-0123</td>
          <td>alexjohnson@example.com</td>
          <td>2023-05-10</td>
          <td><button class="edit-btn">수정</button></td>
          <td><button class="delete-btn">삭제</button></td>
        </tr>
      </tbody>
    </table>
    <button class="register-btn">등록하기</button>
  </div>
  <script type="text/javascript">
  function showInput() {
	    var selectedValue = document.getElementById("searchField").value;
	    var searchInput = document.getElementById("searchWord");

	    // 선택된 값이 'all'이면 입력창 숨김
	    if (selectedValue === 'all') {
	        searchInput.style.display = "none";
	    } else {
	        searchInput.style.display = "inline-block";
	    }
	}
  </script>
</body>
</html>