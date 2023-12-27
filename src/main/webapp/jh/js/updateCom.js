function toggleEdit() {
   var inputs = document.querySelectorAll('input[type="text"]');
    var textarea = document.querySelector('textarea');

    for (var i = 0; i < inputs.length; i++) {
        inputs[i].readOnly = !inputs[i].readOnly;
    }

    textarea.readOnly = !textarea.readOnly;

    var editButton = document.getElementById('editButton');
    editButton.style.display = (editButton.style.display === 'none') ? 'block' : 'none';

    var saveButton = document.getElementById('saveButton');
    saveButton.style.display = (saveButton.style.display === 'none') ? 'block' : 'none';
}

function saveChanges() {
    // 수정 사항을 서버로 전송하고 필요한 처리를 수행하는 로직을 추가합니다.
    // 이 함수는 수정 버튼을 누를 때 호출됩니다.
}