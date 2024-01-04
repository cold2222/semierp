//사원 모달 열기
function empOpenModal() {
  let modalBg = document.getElementById('empSearchModal');
  let modal = document.getElementById('empModal');
  modalBg.style.display = 'flex';
  modal.style.display = 'block';
}

//사원 모달 닫기
function empCloseModal() {
  let modalBg = document.getElementById('empSearchModal');
  let modal = document.getElementById('empModal');
  modalBg.style.display = 'none';
  modal.style.display = 'none';
}

// 회사 검색 기능
let empUrl = 'EmployeeSearchC?search=';
let empSearchBtn = document.querySelector("#empSearchBtn");
empSearchBtn.addEventListener("input", function(e) {
	let search = e.target.value;
	
	if (search) {
		fetch(empUrl + search).then((response) => response.json())
			.then((data) => showEmployee(data));

	}
});

function showEmployee(data) {
	console.log(data);
	let list = document.querySelector('.empList');
	list.innerHTML = '';
	let div = `<div class='modal'>
  <div class='table'>
    <div class='table-header'>
      <div class='table-cell'>社員番号</div>
      <div class='table-cell'>名前</div>
      <div class='table-cell'>役職</div>
      <div class='table-cell'>部署番号</div>
    </div>`;
	data.forEach(employee => {
		div += `<div class='table-row' onclick="empSetValue('${employee.e_no}','${employee.e_name}','${employee.e_rank}','${employee.d_dept}')">
        <div class='table-cell'>${employee.e_no}</div>
        <div class='table-cell'>${employee.e_name}</div>
        <div class='table-cell'>${employee.e_rank}</div>
        <div class='table-cell'>

${employee.d_dept}

		</div>
      </div>`;
	});
		div += `</div>`;
	list.innerHTML = div;
}

function empSetValue(e_no, e_name,e_rank,d_dept) {
	let e_noInput = document.getElementById('e_id');
	let employeeSearchInput = document.getElementById('employeeSearch');
	e_noInput.value = e_no;
	employeeSearchInput.value =d_dept+" :"+e_rank+" "+e_name;
	empCloseModal();
}

  // 인풋창 클릭 시 모달 열기
  document.getElementById('employeeSearch').addEventListener('click', empOpenModal);

  // 닫기 버튼 클릭 시 모달 닫기
  document.getElementById('empCloseModalBtn').addEventListener('click', empCloseModal);