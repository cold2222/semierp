<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function addRow() {
	var table = document.getElementById('myTable'); // 테이블 가져오기
	var newRow = table.insertRow(-1); // 새로운 행 추가 (-1은 맨 마지막에 추가)

	// 각 열에 셀(열) 추가
	for (var i = 0; i < 8; i++) {
		var newCell = newRow.insertCell(i); // 셀(열) 추가

		if (i === 0 || i === 1) {
		    var select = document.createElement('select'); // select 요소 생성
		    select.name = 'p_si' + i; // 이름 설정 (테스트용)

		    // 첫 번째 select: 서버에서 가져온 ${u.unit}으로 옵션 추가
		    if (i === 0) {
		        <c:forEach var="u" items="${us}">
		            var option = document.createElement('option'); // option 요소 생성
		            option.value = '${u.unit}'; // 옵션 값 설정 (${u.unit} 값으로 동적 설정)
		            option.text = '${u.unit}'; // 옵션 텍스트 설정 (${u.unit} 값으로 동적 설정)
		            select.appendChild(option); // select에 option 요소 추가
		        </c:forEach>
		    }

		    // 두 번째 select: 서버에서 가져온 ${t.type}으로 옵션 추가
		    if (i === 1) {
		        <c:forEach var="t" items="${ts}">
		            var option2 = document.createElement('option'); // option 요소 생성
		            option2.value = '${t.type}'; // 옵션 값 설정 (${t.type} 값으로 동적 설정)
		            option2.text = '${t.type}'; // 옵션 텍스트 설정 (${t.type} 값으로 동적 설정)
		            select.appendChild(option2); // select에 option 요소 추가
		        </c:forEach>
		    }

		    newCell.appendChild(select); // 셀에 select 요소 추가
		} else { // 나머지 열에는 input 요소 추가
			var input = document.createElement('input'); // input 요소 생성
			input.type = 'text'; // input 타입 설정 (텍스트 입력)
			newCell.appendChild(input); // 셀에 input 요소 추가
		}
	

}
	// 마지막 열에 버튼 추가
	var lastCell = newRow.insertCell(-1);
	var button = document.createElement('button');
	button.textContent = '삭제';
	button.onclick = function() { // 삭제 버튼 클릭 시 해당 행 삭제
		table.deleteRow(this.parentNode.parentNode.rowIndex);
	};
	lastCell.appendChild(button);

}
function Reg(){
    var form = document.createElement('form'); // form 요소 생성
    form.action = 'RegProductC'; // form action 설정
    form.method = 'post'; // form method 설정

    // 각 열의 값들을 form에 추가
    for (var i = 0; i < 8; i++) {
        if (i === 0 || i === 1) {
            // select 요소의 값을 form에 추가
            var select = newRow.cells[i].querySelector('select');
            var selectedValue = select.options[select.selectedIndex].value;
            var input = document.createElement('input');
            input.type = 'hidden';
            input.name = select.name;
            input.value = selectedValue;
            form.appendChild(input);
        } else {
            // input 요소의 값을 form에 추가
            var input = newRow.cells[i].querySelector('input');
            var inputValue = input.value;
            var inputHidden = document.createElement('input');
            inputHidden.type = 'hidden';
            inputHidden.name = input.name;
            inputHidden.value = inputValue;
            form.appendChild(inputHidden);
        }
    }

    document.body.appendChild(form); // form을 body에 추가
    form.submit(); // form 제출
}
</script>
</head>
<body>
	<div>
		<form action="DetailProductC">
			<input name="search" placeholder="검색">
			<button>검색</button>
		</form>
	</div>
	<div>
		<form action="UnitC">
			<button>단위와 타입</button>
		</form>
	</div>
	<button onclick="addRow()">행 추가</button>
	<form action="RegProductC" method="post" onchange="Reg()">
		<table id="myTable" border="1" style="width: 83%; height: 100px;">
			<tr>
				<td>단위<select name="p_si">
						<c:forEach var="u" items="${us}">
							<option value="${u.unit}">${u.unit}</option>
						</c:forEach>
				</select></td>
				<td>타입<select name="p_type">
						<c:forEach var="t" items="${ts}">
							<option value="${t.type}">${t.type}</option>
						</c:forEach>
				</select></td>
				<td><input name="p_quantity" placeholder="단위량"></td>
				<td><input name="p_name" placeholder="상품명"></td>
				<td><input name="p_unitCost" placeholder="가격"></td>
				<td><input name="p_minStock" placeholder="최소 스톡량"></td>
				<td><input name="p_maxStock" placeholder="최대 보유량"></td>
				<td><input name="p_manufacturer" placeholder="제조사"></td>
			</tr>
		</table>
	<button>등록</button>
	</form>
	<table border="1" style="width: 83%; height: 800px;">
		<c:forEach var="p" items="${ps }">
			<tr>
				<td>${p.p_id }</td>
				<td>${p.p_si }</td>
				<td>${p.p_type }</td>
				<td>${p.p_quantity }</td>
				<td>${p.p_name }</td>
				<td>${p.p_unitCost }</td>
				<td>${p.p_minStock }</td>
				<td>${p.p_maxStock }</td>
				<td>${p.p_manufacturer }</td>
				<td>
					<button onclick="location.href='UpdateProductC?id=${p.p_id }'">수정</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>