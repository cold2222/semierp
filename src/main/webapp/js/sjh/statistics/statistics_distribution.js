document.addEventListener('DOMContentLoaded', function() {
	let contractCanvas = document.getElementById('distributionYear');

	let dataToSend = {
		setDate: document.getElementById('dateInput').value,
	};

	$.ajax({
		url: 'DistributionChartOfYearAPI',
		type: 'GET',
		data: dataToSend,
		dataType: 'json',
		success: function(result) {
			console.log(result)
			result

			new Chart(contractCanvas, {
				type: 'bar',
				data: result[0],
				
			});
			

		},
		error: function(xhr, status, error) {
			console.error('에러 발생');
			console.log('xhr : ', xhr);
			console.log('status : ', status);
			console.log('error : ', error);
			failureCallback(error); // 실패 시 에러 콜백
		}
	});



});
