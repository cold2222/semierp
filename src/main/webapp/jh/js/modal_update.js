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
document.getElementById('closeProductModalBtn').addEventListener('click', closeProductModal);

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




// 행 추가 기능
function addRow() {
	var table = document.getElementById("contractTable");
	var row = table.getElementsByTagName('div')[0].cloneNode(true); // 첫 번째 행을 복제
	row.style.display = 'block';
	table.appendChild(row); // 테이블에 새로운 행 추가


	if (table.children.length > 2) { // 테이블에 자식이 두 개 이상인 경우(첫 번째 행 제외)
		var deleteButton = document.createElement('button');
		deleteButton.textContent = '削除';
		deleteButton.classList.add('delete-btn');
		deleteButton.onclick = function() {
			this.parentNode.remove(); // 해당 행 삭제
		};
		row.appendChild(deleteButton); // 행에 삭제 버튼 추가
	}
}