document.addEventListener('DOMContentLoaded', function() {
    let calendarEl = document.getElementById('calendar');
    let calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
		eventOverlap: false,
        events: function(info, successCallback, failureCallback) {
            // 현재 보이는 뷰의 시작 날짜 정보 가져오기
            let end = info.endStr;
            // 현재 보이는 뷰의 시작 날짜를 기반으로 년도와 월 추출
            let year = new Date(end).getFullYear();
            let month = new Date(end).getMonth() + 1;
			month = month - 1;
			if(month == 0){
				month = 12;
				year = year - 1;
			}

            // 서블릿에 보낼 데이터 준비
            let dataToSend = {
				temp : end,
                selectedYear: year,
                selectedMonth: month
            };

            // AJAX 요청 보내기
            $.ajax({
                url: 'ShippingAPIC',
                type: 'GET',
                data: dataToSend,
                dataType: 'json',
                success: function(response) {
                    let events = [];

                    // 가져온 JSON 데이터를 FullCalendar의 이벤트 형식에 맞게 가공
                    response.forEach(function(eventData) {
                        let event = {
                            title: eventData.title,
                            start: eventData.start,
                            backgroundColor: eventData.backgroundColor
                            // 필요한 경우 추가적인 속성들을 설정할 수 있음
                        };
                        events.push(event);
                    });
					
					successCallback(response); // 성공 시 이벤트 데이터 콜백
                },
                error: function(xhr, status, error) {
                    console.error('에러 발생');
                    console.log('xhr : ', xhr);
                    console.log('status : ', status);
                    console.log('error : ', error);
                    failureCallback(error); // 실패 시 에러 콜백
                }
            });
        }
    });

    calendar.render(); // 캘린더 렌더링
});