document.addEventListener('DOMContentLoaded', function() {
	let contractCanvas = document.getElementById('contractOfYear');
	let costCanvas = document.getElementById('contractCostOfYear');

	let dataToSend = {
		setDate: document.getElementById('dateInput').value,
	};

	$.ajax({
		url: 'ImportChartAPI',
		type: 'GET',
		data: dataToSend,
		dataType: 'json',
		success: function(result) {
			console.log(result)
			result

			new Chart(contractCanvas, {
				type: 'line',
				data: result[0],
				options: {
					scales: {
						y: {
							beginAtZero: true
						}
					}
				}
			});
			
			new Chart(costCanvas, {
				type: 'line',
				data: result[1],
				options: {
					scales: {
						y: {
							beginAtZero: true
						}
					}
				}
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
