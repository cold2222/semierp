
// 회사 검색 기능
let url = 'CompanySearchC?search=';
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
        <div>会社名 : ${company.c_name}</div>
        <div>　住所 : ${company.c_addr}</div>
        <div>　取引先の担当者 : ${company.c_keeper}</div>
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
// 회사검색기능 끝

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
      <div class='div-row' onclick="setValue1('${product.p_id}','${product.p_name}','${product.p_type}','${product.p_si}','${product.p_quantity}')">
        <div>タイプ：${product.p_type}</div>
        <div>　アイテム名：${product.p_name}</div>
        <div>　単位：${product.p_si}</div>
        <div>　単位量：${product.p_quantity}</div>
      </div>`;
		coms += div;
	});
	list.innerHTML = coms;
}

function setValue1(value, displayName, type, si, quantity) {
	let productData = type +": "+displayName+" "+quantity+si;
	clickedInput.value = productData;
	console.log(value, productData);
	console.log(clickedInput.parentNode.previousElementSibling.children);
	clickedInput.parentNode.previousElementSibling.value = value;
	closeProductModal();
}