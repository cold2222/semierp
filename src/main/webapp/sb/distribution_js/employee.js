function DistributionEmployeeDel(e_no){
	if(confirm("削除しますか?")){
		location.href="DistributionEmployeeDelC?e_no="+e_no;
	}
	
}