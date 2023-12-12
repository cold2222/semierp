function DistributionNoticeDel(num){
	if(confirm("削除しますか?")){
		location.href="DistributionNoticeDelC?n_num="+num;
	}
	
}