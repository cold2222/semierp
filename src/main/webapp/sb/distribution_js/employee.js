function DistributionEmployeeDel(e_id){
	if(confirm("削除しますか?")){
		location.href="DistributionEmployeeDelC?e_id="+e_id;
	}
	
}