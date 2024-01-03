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


const checkBtn = document.querySelector('.check-btn');
checkBtn.addEventListener('click', function() {
	const e_no = document.querySelector('#e_no');

});




$(function() {
	$('.check-btn').click(function() {

		$.ajax({
			url: 'StaffRegNumCheckAPIC',
			type: 'get',
			success: function(response) {
				employeeRegNumCheck(response);

			},
			error: function(xhr, status, error) {
				console.log("에러 발생");
				console.log('xhr : ', xhr);
				console.log('status : ', status);
				console.log('error : ', error);

				alert('err');
			}
		});
	})
})

function employeeRegNumCheck(response) {
	const e_no = document.querySelector('#e_no');
	const regBtn = document.querySelector('#reg-btn');
	const checkBtn = document.querySelector('#check-btn');

	let check = 0;
	for (let i = 0; i < response.length; i++) {
		if (response[i].e_no == e_no.value) {
			alert('既に存在する社員番号です。');
			check = 1;
			break;
		}
	}
	if (check == 0) {
		regBtn.style.display = 'block';
		checkBtn.style.display = 'none';
		e_no.readOnly = true;
	}

}