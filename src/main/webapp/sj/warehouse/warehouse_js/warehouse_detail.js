let myfrom = document.querySelector('#warehouseForm');
let fromBtn = document.querySelector('#fromBtn');

myfrom.addEventListener('submit', function(event) {
	 event.preventDefault();
	if(confirm("確定しますか？")){
		  event.target.submit();
	}else{
		alert("取り消しました");
	}
});