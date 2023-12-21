function showInput() {
	let selectedValue = document.getElementById("searchField").value;
	let searchInput = document.getElementById("searchWord");
	let typeSelect = document.getElementById("typeSelect");
	let statusSelect = document.getElementById("statusSelect");
	// 선택된 값이 'all'이면 입력창 숨김
	if (selectedValue === 'all') {
		searchInput.style.display = "none";
		statusSelect.style.display = "none";
		typeSelect.style.display = "none";
	} else if (selectedValue === 'b.c_name') {
		searchInput.style.display = "inline-block";
		statusSelect.style.display = "none";
		typeSelect.style.display = "none";
	} else if (selectedValue === 'a.c_type') {
		searchInput.style.display = "none";
		statusSelect.style.display = "none";
		typeSelect.style.display = "inline-block";
	} else if (selectedValue === 'a.c_status') {
		searchInput.style.display = "none";
		statusSelect.style.display = "inline-block";
		typeSelect.style.display = "none";
	}
}




