// 행 추가 기능
function addRow() {
	var table = document.getElementById("contractTable");
	var row = table.getElementsByTagName('div')[0].cloneNode(true); // 첫 번째 행을 복제
	row.style.display = 'block';

	table.appendChild(row); // 테이블에 새로운 행 추가


	if (table.children.length > 2) { // 테이블에 자식이 두 개 이상인 경우(첫 번째 행 제외)
		var deleteButton = document.createElement('button');
		deleteButton.textContent = '削除';
		deleteButton.classList.add('insert-btn');
		deleteButton.onclick = function() {
			this.parentNode.remove(); // 해당 행 삭제
		};
		row.appendChild(deleteButton); // 행에 삭제 버튼 추가
	}
}
function valid() {
	let inputField = document.querySelectorAll('input[type="date"]');
	let displayName = document.querySelector('#displayName');
	let employeeSearch = document.querySelector('#employeeSearch');
	let displayNameP = document.querySelectorAll('.displayNameP');
	let ci_count = document.querySelectorAll('input[name="ci_count"]');
	let ci_unit_price = document.querySelectorAll('input[name="ci_unit_price"]');



	console.log(inputField[1]);
	if (!inputField[1].value) {
		alert('入港日を入力してください。');
		return false;
	}
	if (!displayName.value) {
		alert('取引先を入力してください。');
		return false;
	}
	if (!employeeSearch.value) {
		alert('担当者を入力してください。');
		return false;
	}

	for (let i = 1; i < displayNameP.length; i++) {
		let field = displayNameP[i];
		if (!field.value.trim()) {
			alert('アイテム名を入力してください。');
			return false;
		}
	}
	for (let i = 1; i < ci_count.length; i++) {
		let field = ci_count[i];
		if (!field.value.trim()) {
			alert('数量を入力してください。');
			return false;
		}
	}
	for (let i = 1; i < ci_unit_price.length; i++) {
		let field = ci_unit_price[i];
		if (!field.value.trim()) {
			alert('単価を入力してください。');
			return false;
		}
	}


}