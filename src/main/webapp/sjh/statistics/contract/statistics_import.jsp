<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous">
	
</script>
<link rel="stylesheet" href="css/sjh/statistics/common.css">
<link rel="stylesheet" href="css/sjh/statistics/contract.css">
<link rel="stylesheet" href="sb/distribution_css/mainbbs/main_bbs.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="js/sjh/admin/utils/input_date_init.js"></script>
<script src="js/sjh/statistics/statistics_import.js"></script>

</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">輸入情報</div>
		</div>
		<div class="content-body">
			<div class="bbs-content1 bbs-content">
				<div class="date">
					日付 : <input type="date" id="dateInput" name="setDate" required
						onchange="redirectToServlet('StatisticsImportC','${param.pageNo }')">
				</div>
				<div class="board">
					<div class="board-title">契約件数(3年)</div>
					<div class="post">
						<canvas id="contractOfYear"></canvas>
					</div>
				</div>

				<div class="board">
					<div class="board-title">契約金額(3年)</div>
					<div class="post">
						<canvas id="contractCostOfYear"></canvas>
					</div>
				</div>
				<div class="long-board">
					<div class="board-title">輸入契約</div>
					<div class="row-titles">
						<div class="col-title contract">年度</div>
						<div class="col-title contract">01月</div>
						<div class="col-title contract">02月</div>
						<div class="col-title contract">03月</div>
						<div class="col-title contract">04月</div>
						<div class="col-title contract">05月</div>
						<div class="col-title contract">06月</div>
						<div class="col-title contract">07月</div>
						<div class="col-title contract">08月</div>
						<div class="col-title contract">09月</div>
						<div class="col-title contract">10月</div>
						<div class="col-title contract">11月</div>
						<div class="col-title contract">12月</div>
						<div class="col-title contract">総合</div>
					</div>
					<div class="row-contents">
						<div class="row-type contract"> ${selectedYear } </div>
						<c:forEach var="ia" items="${contractCountArr1 }">
							<div class="col-content contract">${ia }</div>
						</c:forEach>
							<div class="col-content contract">${count1 }</div>
					</div>
					<div class="row-contents">
						<div class="row-type contract"> ${selectedYear-1 } </div>
						<c:forEach var="ia" items="${contractCountArr2 }">
							<div class="col-content contract">${ia }</div>
						</c:forEach>
							<div class="col-content contract">${count2 }</div>
					</div>
					<div class="row-contents">
						<div class="row-type contract"> ${selectedYear-2 } </div>
						<c:forEach var="ia" items="${contractCountArr3 }">
							<div class="col-content contract">${ia }</div>
						</c:forEach>
							<div class="col-content contract">${count3 }</div>
					</div>
				</div>
				<div class="long-board">
					<div class="board-title">輸入金額</div>
					<div class="row-titles">
						<div class="col-title contract">年度</div>
						<div class="col-title contract">01月</div>
						<div class="col-title contract">02月</div>
						<div class="col-title contract">03月</div>
						<div class="col-title contract">04月</div>
						<div class="col-title contract">05月</div>
						<div class="col-title contract">06月</div>
						<div class="col-title contract">07月</div>
						<div class="col-title contract">08月</div>
						<div class="col-title contract">09月</div>
						<div class="col-title contract">10月</div>
						<div class="col-title contract">11月</div>
						<div class="col-title contract">12月</div>
						<div class="col-title contract">総合</div>
					</div>
					<div class="row-contents">
						<div class="row-type contract"> ${selectedYear } </div>
						<c:forEach var="ia" items="${contractCostArr1 }">
							<div class="col-content contract-cost">${ia/1000}K</div>
						</c:forEach>
							<div class="col-content contract-cost">${cost1 /1000 }K</div>
					</div>
					<div class="row-contents margin-top">
						<div class="row-type contract"> ${selectedYear-1 } </div>
						<c:forEach var="ia" items="${contractCostArr2 }">
							<div class="col-content contract-cost">${ia / 1000 }K</div>
						</c:forEach>
							<div class="col-content contract-cost">${cost2/ 1000 }K</div>
					</div>
					<div class="row-contents">
						<div class="row-type contract"> 金額差 </div>
						<c:forEach var="ia" items="${diffCostArr1 }">
							<div class="col-content contract-cost">${ia/ 1000 }K</div>
						</c:forEach>
							<div class="col-content contract-cost">${difCos1 / 1000 }K</div>
					</div>
					<div class="row-contents margin-top">
						<div class="row-type contract"> ${selectedYear-2 } </div>
						<c:forEach var="ia" items="${contractCostArr3 }">
							<div class="col-content contract-cost">${ia / 1000 }K</div>
						</c:forEach>
							<div class="col-content contract-cost">${cost3 / 1000 }K</div>
					</div>
					<div class="row-contents">
						<div class="row-type contract"> 金額差 </div>
						<c:forEach var="ia" items="${diffCostArr2 }">
							<div class="col-content contract-cost">${ia / 1000 }K</div>
						</c:forEach>
							<div class="col-content contract-cost">${difCos2/ 1000 }K</div>
					</div>
					
				</div>

			</div>
		</div>
	</div>
</body>
</html>