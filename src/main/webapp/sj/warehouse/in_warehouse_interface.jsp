<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="TestwarehouseC" method="get">
		<div>
			<div> 등록된것 </div>
			<div>
				<div>
					p_si<input>
				</div>
				<div>
					p_type<input>
				</div>
				<div>
					p_quantity<input>
				</div>
				<div>
					p_name<input>
				</div>
				<div>
					p_unicost<input>
				</div>
				<div>
					p_minstock<input>
				</div>
				<div>
					p_maxstock<input>
				</div>
				<div>
					p_manufacturer<input>
				</div>
			</div>
			
			
			<div>창고로 보내는 것 확인 </div>
			
			
			
			<div>
				<div>
					품목 ID <input name="p_id" value="">
				</div>
				<div>
					제품 이름<input name="p_name">
				</div>
				<div>
					타입<input name="p_type">
				</div>
				<div>
					단위<input name="p_si">
				</div>
				<div>
					제조사<input name="p_manufacturer">
				</div>
				<div>
					수량<input name="p_quantity">
				</div>
				<button>창고 이동 확인</button>
			</div>
		</div>
	</form>

</body>
</html>