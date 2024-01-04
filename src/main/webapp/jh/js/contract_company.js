
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
	list.innerHTML = '';
	
  let div = `<div class='modal'>
  <div class='table'>
    <div class='table-header'>
      <div class='table-cell'>会社名</div>
      <div class='table-cell'>住所</div>
      <div class='table-cell'>取引先の担当者</div>
    </div>`;
	data.forEach(company => {
		div += `
    <div class='table-row' onclick="setValue('${company.c_no}','${company.c_name}')">
      <div class='table-cell'>${company.c_name}</div>
      <div class='table-cell'>${company.c_addr}</div>
      <div class='table-cell'>${company.c_keeper}</div>
    </div>
`;
	});
	div += `</div>`;
	list.innerHTML = div;
}

function setValue(value, displayName) {
	var selectedValueInput = document.getElementById('selectedValue');
	var displayNameElement = document.getElementById('displayName');
	selectedValueInput.value = value;
	displayNameElement.value = displayName;
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
		list.innerHTML = '';

let div = `<div class='modal'>
  <div class='table'>
    <div class='table-header'>
      <div class='table-cell'>タイプ</div>
      <div class='table-cell'>アイテム名</div>
      <div class='table-cell'>単位</div>
      <div class='table-cell'>単位量</div>
    </div>`;
	data.forEach(product => {
		 div += `
      <div class='table-row' onclick="setValue1('${product.p_id}','${product.p_name}','${product.p_type}','${product.p_si}','${product.p_quantity}')">
        <div class='table-cell'>${product.p_type}</div>
        <div class='table-cell'>${product.p_name}</div>
        <div class='table-cell'>${product.p_si}</div>
        <div class='table-cell'>${product.p_quantity}</div>
      </div>`;
	});
		div += `</div>`;
	list.innerHTML = div;
}
	
function setValue1(value, displayName, type, si, quantity) {
	let productData = type +": "+displayName+" "+quantity+si;
	clickedInput.value = productData;
	console.log(value, productData);
	console.log(clickedInput.parentNode.previousElementSibling.children);
	clickedInput.parentNode.previousElementSibling.value = value;
	closeProductModal();
}