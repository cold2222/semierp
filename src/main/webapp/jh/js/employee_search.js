//사원 모달 열기
function openModal() {
  var modalBg = document.getElementById('modalBackground');
  var modal = document.getElementById('myModal');
  modalBg.style.display = 'flex';
  modal.style.display = 'block';
}

//사원 모달 닫기
function closeModal() {
  var modalBg = document.getElementById('modalBackground');
  var modal = document.getElementById('myModal');
  modalBg.style.display = 'none';
  modal.style.display = 'none';
}

// 회사 검색 기능
let url = 'EmployeeSearchC?search=';
let searchBtn = document.querySelector("#search-btn");
searchBtn.addEventListener("input", function(e) {
	let search = e.target.value;
	
	if (search) {
		fetch(url + search).then((response) => response.json())
			.then((data) => showEmployee(data));

	}
});

function showEmployee(data) {
	let list = document.querySelector('.list');
	let coms = '';
	data.forEach(employee => {
		let div = `<div class='div-row' onclick="setValue('${employee.e_no}','${employee.e_name}')">
        <div>${employee.e_name}</div>
      </div>`;
		coms += div;
	});
	list.innerHTML = coms;
}

function setValue(e_no, e_name) {
	var e_noInput = document.getElementById('e_no');
	var employeeSearchInput = document.getElementById('employeeSearch');
	e_noInput.value = e_no;
	employeeSearchInput.placeholder = e_name;
	closeModal();
}

  // 인풋창 클릭 시 모달 열기
  document.getElementById('employeeSearch').addEventListener('click', openModal);

  // 닫기 버튼 클릭 시 모달 닫기
  document.getElementById('closeModalBtn').addEventListener('click', closeModal);