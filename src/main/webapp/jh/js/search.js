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



// 모달 회사 검색
// 회사 검색 기능
let url = 'company-search?search=';
let searchBtn = document.querySelector("#search-btn");
searchBtn.addEventListener("input", function(e) {
	let search = e.target.value;
	if (search) {
		fetch(url + search).then((response) => response.json())
			.then((data) => showCompany(data));

	}
});

function showCompany(data) {
	let list = document.querySelector('.list');
	let coms = '';
	data.forEach(company => {
		let div = `<div class='div-row' onclick="setValue('${company.c_no}','${company.c_name}')">
        <div>${company.c_no}</div>
        <div>${company.c_name}</div>
        <div>${company.c_addr}</div>
        <div>${company.c_keeper}</div>
      </div>`;
		coms += div;
	});
	list.innerHTML = coms;
}

function setValue(value, displayName) {
	var selectedValueInput = document.getElementById('selectedValue');
	var displayNameElement = document.getElementById('displayName');
	selectedValueInput.value = value;
	displayNameElement.placeholder = displayName;
	closeModal();
}


// 상품 검색 기능
let clickedInput;
document.addEventListener('click', function(event) {
	if (event.target.classList.contains('displayNameP')) {
		// displayNameP 클래스를 가진 요소가 클릭되었을 때의 동작을 정의합니다.
			clickedInput = event.target;
		if (clickedInput.classList.contains('displayNameP')) {
			openProductModal();
		}
	}
});


let url1 = 'product-search?search=';
let searchBtn1 = document.querySelector("#productSearchInput");
searchBtn1.addEventListener("input", function(e) {
	let search = e.target.value;
	if (search) {
		fetch('product-search?search=' + search)
			.then((response) => response.json())
			.then((data) => showProduct(data));
	}
});

function showProduct(data) {
	let list = document.querySelector('.listProduct');
	let coms = '';
	data.forEach(product => {
		let div = `
      <div class='div-row' onclick="setValue1('${product.p_id}','${product.p_name}')">
        <div>${product.p_id}</div>
        <div>${product.p_name}</div>
        <div>${product.p_si}</div>
        <div>${product.p_type}</div>
      </div>`;
		coms += div;
	});
	list.innerHTML = coms;
}

function setValue1(value, displayName) {
	clickedInput.value = displayName;
	console.log(value, displayName);
	console.log(clickedInput.parentNode.previousElementSibling.children);
	clickedInput.parentNode.previousElementSibling.children[0].value = value;
	closeProductModal();
}



