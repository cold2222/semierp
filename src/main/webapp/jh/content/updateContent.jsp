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
<form action="UpdateContentC?num=${cti.ci_no }" method="post">
<table>
		<tr>
			<td> 계약서 번호<input readonly="readonly" name="ci_c_contract_no" value="${cti.ci_c_contract_no }"></td>
			<td> 상품 번호<input name="ci_p_id" value="${cti.ci_p_id }"> </td>
			<td> 몇 개 구매할건지<input name="ci_count" value="${cti.ci_count }"></td>
			<td> 얼마로 구매할건지<input name="ci_unit_price" value="${cti.ci_unit_price }"></td>
		</tr>
</table>
<button>수정 확인</button>
</form>
</body>
</html>