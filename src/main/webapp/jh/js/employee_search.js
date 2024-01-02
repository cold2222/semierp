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
	let list = document.querySelector('.empList');
	let coms = '';
	data.forEach(employee => {
		let div = `<div class='div-row' onclick="empSetValue('${employee.e_no}','${employee.e_name}','${employee.e_rank}',${employee.e_deptno})">
        <div>取引担当社員番号：${employee.e_no}</div>
        <div>　名前：${employee.e_name}</div>
        <div>　階級：${employee.e_rank}</div>
        <div>　部門番号：${employee.e_deptno}</div>
      </div>`;
		coms += div;
	});
	list.innerHTML = coms;
}

function empSetValue(e_no, e_name,e_rank,e_deptno) {
	let deptName = "輸入事業部";
		if(e_deptno == 102){
			deptName = "販売営業部"
		}
	
	let e_noInput = document.getElementById('e_id');
	let employeeSearchInput = document.getElementById('employeeSearch');
	e_noInput.value = e_no;
	employeeSearchInput.value =deptName+" :"+e_rank+" "+e_name;
	empCloseModal();
}

  // 인풋창 클릭 시 모달 열기
  document.getElementById('employeeSearch').addEventListener('click', empOpenModal);

  // 닫기 버튼 클릭 시 모달 닫기
  document.getElementById('empCloseModalBtn').addEventListener('click', empCloseModal);