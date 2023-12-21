<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
/* 배경 뒷부분 스타일 */
.modal-background {
	display: none;
	position: fixed;
	z-index: 999;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.4);
	justify-content: center;
	align-items: center;
}

/* 모달 스타일 */
.modal-content {
	display: none;
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
	padding: 20px;
	width: 800px;
	height: 500px;
	text-align: center;
	position: relative;
	justify-content: center;
}

.modal p {
	margin-bottom: 20px;
}

.modal button {
	padding: 8px 16px;
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.modal button:hover {
	background-color: #0056b3;
}

.modal-foot {
	position: absolute;
	bottom: 5px;
}
.div-row{
	display: flex;
}
</style>
</head>
<body>
	<div id="modalBackground" class="modal-background">
		<!-- 모달 내용 -->
		<div id="myModal" class="modal-content">
			<div>
				<div>
				<input placeholder="회사 검색" id="search-btn">
				</div>
				<div class="list"></div>
			</div>
			<div class="modal-foot">
				<button id="closeModalBtn">닫기</button>
			</div>
		</div>
	</div>

<!-- 상품 검색용 모달 -->
<div id="productModalBackground" class="modal-background">
  <div id="productModal" class="modal-content">
    <div>
      <div>
        <input placeholder="상품 검색" id="productSearchInput">
      </div>
      <div class="listProduct"></div>
    </div>
    <div class="modal-foot">
      <button id="closeProductModalBtn">닫기</button>
    </div>
  </div>
</div>
<button type="button" onclick="addRow()">행 추가</button>
	<form action="MakeContractC" method="post" >
		<table>
			<tr>
				<td><input type="hidden" name="s_c_no" readonly="readonly" id="selectedValue" placeholder="회사 번호"></td>
				<td><input readonly="readonly" id="displayName" placeholder="회사 이름"></td>
				<td><input name="c_e_id" placeholder="사원 id" required="required"></td>
				<td>계약서 작성일<input type="date" readonly="readonly" name="c_created_date" id="c_created_date">
				</td>
				<td>납기일<input type="date" name="c_due_date" required="required"></td>
				<td><input type="hidden" name="c_status" placeholder="거래 상태"
					value="1"></td>
				<td>구매/판매 <select id="transactionType"
					 name="c_type">
						<option value="1" selected="selected">구매</option>
						<option value="2">판매</option>
				</select>
				</td>
			</tr>
		</table>
		<table id="contractTable">
			<tr style="display: none;">
				<td><input name="ci_c_contract_no" type="hidden"></td>
				<td><input type="hidden" name="ci_p_id" class="selectedValueP"></td>
				<td><input readonly="readonly" class="displayNameP" placeholder="상품명"></td>
				<td><input name="ci_count" placeholder="몇 개 살건지"></td>
				<td><input name="ci_unit_price" placeholder="얼마로 살건지"></td>
			</tr>
		</table>
		<button>계약서 내용 작성</button>
	</form>
</body>
	<script>
  //회사 모달 열기
  function openModal() {
    var modalBg = document.getElementById('modalBackground');
    var modal = document.getElementById('myModal');
    modalBg.style.display = 'flex';
    modal.style.display = 'block';
  }

  //회사 모달 닫기
  function closeModal() {
    var modalBg = document.getElementById('modalBackground');
    var modal = document.getElementById('myModal');
    modalBg.style.display = 'none';
    modal.style.display = 'none';
  }

  // 인풋창 클릭 시 모달 열기
  document.getElementById('displayName').addEventListener('click', openModal);

  // 닫기 버튼 클릭 시 모달 닫기
  document.getElementById('closeModalBtn').addEventListener('click', closeModal);
  
//상품 검색 모달 열기
  function openProductModal() {
    var productModalBg = document.getElementById('productModalBackground');
    var productModal = document.getElementById('productModal');
    productModalBg.style.display = 'flex';
    productModal.style.display = 'block';
  }

  // 상품 검색 모달 닫기
  function closeProductModal() {
    var productModalBg = document.getElementById('productModalBackground');
    var productModal = document.getElementById('productModal');
    document.querySelector("#productSearchInput").value = "";
    let list = document.querySelector('.listProduct').innerHTML = "";
    productModalBg.style.display = 'none';
    productModal.style.display = 'none';
  }
  
 
  
  document.addEventListener('DOMContentLoaded', function(event) {
	    // 오늘 날짜 가져오기 console.log
	    const today = new Date().toISOString().split('T')[0];

	    // input 필드에 오늘 날짜 설정
	    document.getElementById('c_created_date').value = today;
	   addRow();
	  });
  
  // 행 추가 기능
  function addRow() {
	    var table = document.getElementById("contractTable");
	    var row = table.getElementsByTagName('tr')[0].cloneNode(true); // 첫 번째 행을 복제
		row.style.display = 'block';
	    table.appendChild(row); // 테이블에 새로운 행 추가
	  }
  
  
  
</script>
<script type="text/javascript" src="jh/js/contract_company.js"></script>
</html> 