<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Modal Window on Button Click</title>
  <style>
    /* 초기에는 숨겨진 상태 */
    .overlay {
      display: none;
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0, 0, 0, 0.5); /* 검은 반투명 배경 */
      z-index: 1;
    }

    /* 모달 창 스타일 */
    .modal {
      display: none;
      position: fixed;
      width : 500px;
      height : 500px;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      background-color: white;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
      z-index: 2;
    }

    /* 버튼 스타일 */
    .modal-btn {
      padding: 10px;
      background-color: #333;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }
  </style>
</head>
<body>

<!-- 버튼을 클릭하여 모달 열기 -->
<button id="openModal" class="modal-btn">모달 창 열기</button>

<!-- 오버레이 및 모달 창 -->
<div class="overlay" id="overlay"></div>
<div class="modal" id="modal">
  <!-- 모달 창 내용 -->
  <h2>출근사원 리스트</h2>
  <p>모달 창 내용을 이곳에 추가하세요.</p>
  <button onclick="closeModal()" class="modal-btn">모달 닫기</button>
</div>

<script>
  document.getElementById('openModal').addEventListener('click', function() {
    document.getElementById('overlay').style.display = 'block';
    document.getElementById('modal').style.display = 'block';
  });

  function closeModal() {
    document.getElementById('overlay').style.display = 'none';
    document.getElementById('modal').style.display = 'none';
  }
</script>

</body>
</html>