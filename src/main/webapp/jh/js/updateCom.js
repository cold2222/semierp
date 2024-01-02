function toggleEdit() {
	var inputs = document.querySelectorAll('input[type="text"]');
	var textarea = document.querySelector('textarea');

	for (var i = 0; i < inputs.length; i++) {
		inputs[i].disabled = !inputs[i].disabled;
	}

	textarea.disabled = !textarea.disabled;

	var editButton = document.getElementById('editButton');
	editButton.style.display = (editButton.style.display === 'none') ? 'block' : 'none';

	var saveButton = document.getElementById('saveButton');
	saveButton.style.display = (saveButton.style.display === 'none') ? 'block' : 'none';
	var successMessage = document.querySelector('.insert-btn1');

	// 해당 요소가 존재하고 표시되어 있다면
	if (successMessage && window.getComputedStyle(successMessage).display !== 'none') {
		// 요소의 투명도를 조정합니다
		successMessage.style.opacity = '0'; // 예를 들어, 0은 완전 투명을 나타냅니다.
	}
}

