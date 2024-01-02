document.addEventListener('DOMContentLoaded', function() {
    // 현재 날짜를 가져오는 함수
    function getCurrentDate() {
        const now = new Date();
        const year = now.getFullYear();
        const month = String(now.getMonth() + 1).padStart(2, '0');
        const day = String(now.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    }

    // URL에서 'setDate' 파라미터 가져오기
    function getURLParameter(name) {
        const urlSearchParams = new URLSearchParams(window.location.search);
        return urlSearchParams.get(name);
    }

    // 'setDate' 파라미터 가져오기
    const setDateParam = getURLParameter('setDate');

    // 'setDate' 파라미터가 존재하면 해당 값으로, 아니면 현재 날짜로 초기화
    const initialDate = setDateParam ? setDateParam : getCurrentDate();

    // 오늘 날짜 또는 파라미터 값으로 초기화
    document.getElementById('dateInput').value = initialDate;
});

function redirectToServlet(servlet, pageNo) {
            // 날짜 입력 필드의 값 가져오기
            var selectedDate = document.getElementById('dateInput').value;
            
            if(pageNo != null & pageNo != ""){
				pageNo = "&pageNo="+pageNo;
			} else {
				pageNo="";
			}

            // 서블릿으로 이동
            location.href = servlet+'?setDate=' + encodeURIComponent(selectedDate)+pageNo;
        }
function redirectToServletWithTowPaging(servlet, pageNo1, pageNo2) {
            // 날짜 입력 필드의 값 가져오기
            var selectedDate = document.getElementById('dateInput').value;
            
            if(pageNo1 != null & pageNo1 != ""){
				pageNo1 = "&pageNo1="+pageNo1;
			} else {
				pageNo1="";
			}
			if(pageNo2 != null & pageNo2 != ""){
				pageNo2 = "&pageNo2="+pageNo2;
			} else {
				pageNo2="";
			}

            // 서블릿으로 이동
            location.href = servlet+'?setDate=' + encodeURIComponent(selectedDate)+ pageNo1 + pageNo2;
        }