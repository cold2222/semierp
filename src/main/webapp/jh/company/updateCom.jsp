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
<form action="UpdateComC?num=${sc.supply_num }" method="post">
<table>
		<tr>
			<td> 회사 이름<input name="supply_company" value="${sc.supply_company }"></td>
			<td> 담당자<input name="supply_name" value="${sc.supply_name }"> </td>
			<td> 업체쪽 담당자<input name="supplied_name" value="${sc.supplied_name }"></td>
			<td> 연락처<input name="supply_addr" value="${sc.supply_addr }"></td>
			<td> 비고<input name="purchase_text" value="${sc.purchase_text }"></td>
		</tr>
</table>
<button>수정 확인</button>
</form>

</body>
</html>