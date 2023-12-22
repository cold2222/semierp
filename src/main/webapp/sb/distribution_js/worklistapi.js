$(function() {
	$('#c_delivery_date').change(function() {	
	let c_delivery_date = $('#c_delivery_date').val();	
	console.log(c_delivery_date);
	
	$.ajax({
    url: 'DistributionWorkListAPIC', 
    type: 'get', 
    data: {
		delivery_date : c_delivery_date
	},
	dataType: 'json',
    success: function(response) {
		 // empList 데이터 가져오기
            let empList = response.empList;

            // 기존 데이터를 지우고 새로운 데이터로 HTML 업데이트
            let radioDiv = $('#radioDiv'); // 변경할 부분을 감싸는 div 요소
            radioDiv.empty(); // 기존 데이터 제거

            // 가져온 데이터로 HTML 업데이트
            response.forEach(function(employee) {
                let radioInput = `<input class="inputRadio" type="radio" id="e_no_${employee.e_no}" name="e_no" value="${employee.e_no}">
                                  <label for="e_no_${employee.e_no}">${employee.e_name}: ${employee.e_rank}</label><br>`;
                radioDiv.append(radioInput); // HTML 업데이트
            });
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