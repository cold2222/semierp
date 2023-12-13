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
<form action="UpdateContentC?num=${c.record_buy_num }" method="post">
<table>
		<tr>
			<td> 계약서 번호<input readonly="readonly" name="recordall_buy_num" value="${c.recordall_buy_num }"></td>
			<td> 상품 번호<input name="p_id" value="${c.p_id }"> </td>
			<td> 몇 개 구매할건지<input name="record_count" value="${c.record_count }"></td>
			<td> 얼마로 구매할건지<input name="record_price" value="${c.record_price }"></td>
		</tr>
</table>
<button>수정 확인</button>
</form>
</body>
</html>