document.addEventListener('DOMContentLoaded', function() {
	// 현재 날짜를 가져오는 함수
	function getCurrentDate() {
		const now = new Date();
		const year = now.getFullYear();
		const month = String(now.getMonth() + 1).padStart(2, '0');
		const day = String(now.getDate()).padStart(2, '0');
		return `${year}-${month}-${day}`;
	}

	// 오늘 날짜로 초기화
	document.getElementById('dateInput').value = getCurrentDate();
});
