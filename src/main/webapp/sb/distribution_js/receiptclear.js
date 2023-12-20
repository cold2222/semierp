function DistributionReceiptClear(num){
	if(confirm("配送を完了しますか？")){
		location.href="DistributionReceiptCompleteC?c_contract_no="+num;
	}
	
}