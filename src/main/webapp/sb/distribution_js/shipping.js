document.addEventListener('DOMContentLoaded', function() {
	let calendarEl = document.getElementById('calendar');
	let calendar = new FullCalendar.Calendar(calendarEl, {
		initialView: 'dayGridMonth',
		events: function(info, successCallback, failureCallback) {
			// FullCalendar의 getView() 메서드를 사용하여 현재 보이는 뷰(연도 및 월)를 가져옴
			let currentView = calendar.getView();
			let year = currentView.currentStart.getFullYear(); // 현재 뷰의 연도
			let month = currentView.currentStart.getMonth() + 1; // 현재 뷰의 월 (0부터 시작하므로 +1)

			// 서블릿에 보낼 데이터 준비
			let dataToSend = {
				selectedYear: year,
				selectedMonth: month
			};

			$.ajax({
				url: 'ShippingAPIC',
				type: 'get',
				data: dataToSend, // 선택한 연도와 월 데이터를 서블릿에 보냄
				dataType: 'json',
				success: function(response) {
					successCallback(response);
				},
				error: function(xhr, status, error) {
					console.error('에러 발생');
					console.log('xhr : ', xhr);
					console.log('status : ', status);
					console.log('error : ', error);
					alert('err');
				}
			});
		}
	});
	calendar.render();
});