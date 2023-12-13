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
<form action="UpdateProductC?id=${p.p_id }" method="post">
<table>
		<tr>
			<td>단위<input name="p_si" value="${p.p_si }"></td>
			<td>타입<input name="p_type" value="${p.p_type }"> </td>
			<td>단위량<input name="p_quantity" value="${p.p_quantity}"></td>
			<td>상품명<input name="p_name" value="${p.p_name}"></td>
			<td>가격<input name="p_unitCost" value="${p.p_unitCost}"></td>
			<td>최소 스톡량<input name="p_unitCost" value="${p.p_unitCost}"></td>
			<td>최대 보유량<input name="p_unitCost" value="${p.p_unitCost}"></td>
			<td>제조사<input name="p_manufacturer" value="${p.p_manufacturer}"></td>
		</tr>
</table>
<button>수정 확인</button>
</form>
</body>
</html>