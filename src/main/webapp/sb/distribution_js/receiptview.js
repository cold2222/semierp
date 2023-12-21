  document.getElementById('openModal').addEventListener('click', function() {
    document.getElementById('overlay').style.display = 'block';
    document.getElementById('modal').style.display = 'block';
  });

  function closeModal() {
    document.getElementById('overlay').style.display = 'none';
    document.getElementById('modal').style.display = 'none';
  }


let c_due_date = document.getElementById('c_due_date');
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('myForm').addEventListener('submit', function(event) {
        event.preventDefault(); 
	
	let c_delivery_date = document.getElementById('c_delivery_date').value;
	let due_date = new Date(c_due_date.textContent);
	let delivery_date = new Date(c_delivery_date);
	let inputRadio = document.querySelectorAll('.inputRadio');
	let selectedValue = null;
	for(let i=0; i<inputRadio.length; i++){
		if(inputRadio[i].checked){
			selectedValue = inputRadio[i].value;
			break;
		}
	}
	if(selectedValue === null){
		alert('配送人を設定してください。');
		return;
	}
	
	if(!(delivery_date < due_date)){
		event.target.submit();
	}else{
		alert('配送日を品物到着日以前に設定できません。');
		return;
	}
    
    });
});