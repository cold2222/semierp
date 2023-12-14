function updateCalendar() {
		let year = document.getElementById('year');
		let month = document.getElementById('month');

		if (year.value > 1990 && month.value > 0 && month.value < 13
				&& year.value % 1 === 0 && month.value % 1 === 0) {
			location.href = 'DistributionShiftC?year=' + year.value + '&month='
					+ month.value;
		} else {
			alert("날짜는 정확히 입력해주세요");
			return false
		}
}

$(function() {
	$('.p-input').click(function() {
	let pValue = $(this).val();
	let pArr = pValue.split('/');
	let status = pArr[2];
       
		if(status === '1'){
			$(this).css("color","red");
			$(this).css("background-color","red");
			$(this).val(pArr[0]+'/'+pArr[1]+'/2');
		}
		if(status === '2'){
			$(this).css("color","green");
			$(this).css("background-color","green");
			$(this).val(pArr[0]+'/'+pArr[1]+'/3');
		}
		if(status === '3'){
			$(this).css("color","white");
			$(this).css("background-color","white");
			$(this).val(pArr[0]+'/'+pArr[1]+'/1');
		}
	if(status === '1'){
		status = '2';
	}else if(status === '2'){
		status = '3';
	}else{
		status = '1';
	}
	
		
	$.ajax({
    url: 'ShiftAPI', 
    type: 'get', 
    data: {
		e_id : pArr[0],
		e_date : pArr[1],
		e_status : status
	},
    success: function(response) {
    },
    error: function(xhr, status, error) {
		console.log("에러 발생");
		console.log('xhr : ',xhr);
		console.log('status : ',status);
		console.log('error : ',error);       

		alert('err');
    }
});
		
		
		
	})
})