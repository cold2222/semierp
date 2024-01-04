document.addEventListener('DOMContentLoaded', function(event) {
	// 오늘 날짜 가져오기 console.log
	const today = new Date().toISOString().split('T')[0];

	// input 필드에 오늘 날짜 설정
	document.getElementById('c_created_date').value = today;
	addRow();
});
