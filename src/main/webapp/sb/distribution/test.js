document.addEventListener('DOMContentLoaded', function () {
		  const calendarDiv = document.querySelector('.calendar');

		  // 현재 날짜 가져오기
		  const currentDate = new Date();
		  let currentMonth = currentDate.getMonth() + 1;
		  let currentYear = currentDate.getFullYear();

		  // '월 선택' input의 변경 감지
		  const monthInput = document.getElementById('month');
		  monthInput.addEventListener('change', function () {
		    const selectedDate = new Date(this.value + '-01');
		    currentMonth = selectedDate.getMonth() + 1;
		    currentYear = selectedDate.getFullYear();
		    createCalendar(currentMonth, currentYear);
		  });

		  // 달력 생성 함수
		  function createCalendar(month, year) {
		    calendarDiv.innerHTML = ''; // 기존 달력 내용 초기화

		    const daysInMonth = new Date(year, month, 0).getDate();

		    for (let i = 1; i <= daysInMonth; i++) {
		      const date = `${year}-${('0' + month).slice(-2)}-${('0' + i).slice(-2)}`;

		      const calendarDay = document.createElement('div');
		      calendarDay.classList.add('calendar-day');
		      calendarDay.textContent = i;

		      // 각 날짜를 클릭하여 출근, 휴일, 유급 휴가 선택 기능 구현
		      calendarDay.addEventListener('click', function () {
		        const isHoliday = confirm(`날짜: ${date}\n출근, 휴일, 유급 휴가 중 어떤 것으로 선택하시겠습니까?`);
		        if (isHoliday) {
		          this.classList.add('holiday');
		          this.setAttribute('title', '휴일');
		        } else {
		          this.classList.remove('holiday');
		          this.removeAttribute('title');
		        }
		      });

		      calendarDiv.appendChild(calendarDay);
		    }
		  }

		  // 초기에 현재 월의 달력 생성
		  createCalendar(currentMonth, currentYear);
		});