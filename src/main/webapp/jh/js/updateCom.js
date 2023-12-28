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

