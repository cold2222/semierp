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
<form action="UpdateContC?num=${ct.c_contract_no }" method="post">
<table>
		<tr>
			<td> 회사 번호<input readonly="readonly" name="s_c_no" value="${ct.s_c_no }"></td>
			<td> 사원 id<input readonly="readonly" name="c_e_id" value="${ct.c_e_id }"></td>
			<td> 계약서 작성일<input name="c_created_date" value="${ct.c_created_date }"> </td>
			<td> 납기일<input name="c_due_date" value="${ct.c_due_date }"></td>
			<td> 배송예정일<input name="c_delivery_date" value="${ct.c_delivery_date }"></td>
			<td> 입고/출고 일자<input name="c_completed_date" value="${ct.c_completed_date }"></td>
			<td> 거래 상태<input name="c_status" value="${ct.c_status }"></td>
			<td> 구매1/판매2<input name="c_type" value="${ct.c_type }"></td>
		</tr>
</table>
<button>수정 확인</button>
</form>
</body>
</html>