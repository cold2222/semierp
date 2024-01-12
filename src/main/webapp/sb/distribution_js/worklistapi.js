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
            let empList = response.empList;

            let radioDiv = $('#radioDiv');
            radioDiv.empty();

            response.forEach(function(employee) {
                let radioInput = `<input class="inputRadio" type="radio" id="e_no_${employee.e_no}" name="e_no" value="${employee.e_no}">
                                  <label for="e_no_${employee.e_no}">${employee.e_name}: ${employee.e_rank}</label><br>`;
                radioDiv.append(radioInput);
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