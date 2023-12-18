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
<form action="UpdateComC?num=${c.c_no }" method="post">
<table>
		<tr>
			<td> 사원id<input name="c_e_id" value="${c.c_e_id }"></td>
			<td> 회사 이름<input name="c_name" value="${c.c_name }"></td>
			<td> 업체쪽 담당자<input name="c_keeper" value="${c.c_keeper }"> </td>
			<td> 업체쪽 담당자 연락처<input name="c_phone" value="${c.c_phone }"></td>
			<td> 업체쪽 회사 주소<input name="c_addr" value="${c.c_addr }"></td>
			<td> 비고<input name="c_text" value="${c.c_text }"></td>
		</tr>
</table>
<button>수정 확인</button>
</form>

</body>
</html>