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
<link rel="stylesheet" href="sb/distribution_css/mainbbs/main_bbs.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="js/sjh/admin/utils/input_date_init.js"></script>
<script src="js/sjh/statistics/statistics_sales.js"></script>

</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">수입 정보</div>
		</div>
		<div class="content-body">
			<div class="bbs-content1 bbs-content">
				<div class="date">
					日付 : <input type="date" id="dateInput" name="setDate" required
						onchange="redirectToServlet('StatisticsImportC','${param.pageNo }')">
				</div>
				<div class="board">
					<div class="board-title">계약건수</div>
					<div class="post">
						<canvas id="contractOfYear"></canvas>
					</div>
				</div>

				<div class="board">
					<div class="board-title">계약Cost</div>
					<div class="post">
						<canvas id="contractCostOfYear"></canvas>
					</div>
				</div>
				<div class="long-board">
					<div class="board-title">수출</div>
					<div class="row-titles">
						<div class="col-title">년도</div>
						<div class="col-title">01月</div>
						<div class="col-title">02月</div>
						<div class="col-title">03月</div>
						<div class="col-title">04月</div>
						<div class="col-title">05月</div>
						<div class="col-title">06月</div>
						<div class="col-title">07月</div>
						<div class="col-title">08月</div>
						<div class="col-title">09月</div>
						<div class="col-title">10月</div>
						<div class="col-title">11月</div>
						<div class="col-title">12月</div>
						<div class="col-title">총 합</div>
					</div>
					<div class="row-contents">
						<div class="row-type"> ${selectedYear } </div>
						<c:forEach var="ia" items="${contractCountArr1 }">
							<div class="col-content">${ia }</div>
						</c:forEach>
							<div class="col-content">${count1 }</div>
					</div>
					<div class="row-contents">
						<div class="row-type"> ${selectedYear-1 } </div>
						<c:forEach var="ia" items="${contractCountArr2 }">
							<div class="col-content">${ia }</div>
						</c:forEach>
							<div class="col-content">${count2 }</div>
					</div>
					<div class="row-contents">
						<div class="row-type"> ${selectedYear-2 } </div>
						<c:forEach var="ia" items="${contractCountArr3 }">
							<div class="col-content">${ia }</div>
						</c:forEach>
							<div class="col-content">${count3 }</div>
					</div>
				</div>
				<div class="long-board">
					<div class="board-title">수입 금액</div>
					<div class="row-titles">
						<div class="col-title">년도</div>
						<div class="col-title">01月</div>
						<div class="col-title">02月</div>
						<div class="col-title">03月</div>
						<div class="col-title">04月</div>
						<div class="col-title">05月</div>
						<div class="col-title">06月</div>
						<div class="col-title">07月</div>
						<div class="col-title">08月</div>
						<div class="col-title">09月</div>
						<div class="col-title">10月</div>
						<div class="col-title">11月</div>
						<div class="col-title">12月</div>
						<div class="col-title">총 합</div>
					</div>
					<div class="row-contents">
						<div class="row-type"> ${selectedYear } </div>
						<c:forEach var="ia" items="${contractCostArr1 }">
							<div class="col-content">${ia / 1000 }K</div>
						</c:forEach>
							<div class="col-content">${cost1/ 1000 }K</div>
					</div>
					<div class="row-contents margin-top">
						<div class="row-type"> ${selectedYear-1 } </div>
						<c:forEach var="ia" items="${contractCostArr2 }">
							<div class="col-content">${ia/ 1000 }K</div>
						</c:forEach>
							<div class="col-content">${cost2/ 1000 }K</div>
					</div>
					<div class="row-contents">
						<div class="row-type"> 차 </div>
						<c:forEach var="ia" items="${diffCostArr1 }">
							<div class="col-content">${ia / 1000 }K</div>
						</c:forEach>
							<div class="col-content">${difCos1/ 1000 }K</div>
					</div>
					<div class="row-contents margin-top">
						<div class="row-type"> ${selectedYear-2 } </div>
						<c:forEach var="ia" items="${contractCostArr3 }">
							<div class="col-content">${ia / 1000 }K</div>
						</c:forEach>
							<div class="col-content">${cost3 / 1000 }K</div>
					</div>
					<div class="row-contents">
						<div class="row-type"> 차 </div>
						<c:forEach var="ia" items="${diffCostArr2 }">
							<div class="col-content">${ia / 1000 }K</div>
						</c:forEach>
							<div class="col-content">${difCos2 /1000 }</div>
					</div>
					
				</div>

			</div>
		</div>
	</div>
</body>
</html>