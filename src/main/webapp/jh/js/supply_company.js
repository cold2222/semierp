function deleteCompany(c_no){
	if(confirm('削除しますか？')){
		location.href='DeleteCompnayC?c_no='+c_no;
	}else{
		return;
	} 
	
}