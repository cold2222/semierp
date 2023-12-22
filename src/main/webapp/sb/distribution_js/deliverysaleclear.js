function DistributionReceiptClear(num){
	if(confirm("配送を完了しますか？")){
		location.href="DistributionDeliverySaleCompleteC?c_contract_no="+num;
	}
	
}