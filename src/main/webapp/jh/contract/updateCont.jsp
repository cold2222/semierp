<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function isSuccess() {
    let params = location.search.substr(location.search.indexOf("?") + 1);
    params = params.split("&");

    for (let i = 0; i < params.length; i++) {
        let temp = params[i].split("=");

        if (temp[0] === "isSuccess") {
            alert(decodeURI(temp[1]).replace("+", " "));
        }
    }
};

document.addEventListener("DOMContentLoaded", isSuccess);




</script>
</head>
<body>
<form action="UpdateContC?num=${st.recordall_buy_num }" method="post">
<table>
		<tr>
			<td> 회사 번호<input readonly="readonly" name="supply_num" value="${st.supply_num }"></td>
			<td> 구매 날짜<input name="purchase_date" value="${st.purchase_date }"> </td>
			<td> 입고 예정일<input name="transaction_date" value="${st.transaction_date }"></td>
			<td> 입고 날짜<input name="in_warehouse_date" value="${st.in_warehouse_date }"></td>
			<td> 거래 상태<input name="status" value="${st.status }"></td>
		</tr>
</table>
<button>수정 확인</button>
</form>
</body>
</html>