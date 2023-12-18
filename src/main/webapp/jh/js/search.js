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