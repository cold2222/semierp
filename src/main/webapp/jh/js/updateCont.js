function deleteContract(c_contract_no){
	if(confirm("本当に削除しますか？")){
		location.href='DeleteContractC?c_contract_no='+c_contract_no;
	}else{
		return;
	}
}
function deleteContractItem(ci_no,c_contract_no){
	if(confirm("本当に削除しますか？")){
		location.href='DeleteContractItemsC?ci_no='+ci_no+"&c_contract_no="+c_contract_no;
	}else{
		return;
	}
}