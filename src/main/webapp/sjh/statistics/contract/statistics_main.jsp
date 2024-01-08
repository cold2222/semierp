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
<script src="js/sjh/statistics/statistics_main.js"></script>

</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">계약 현황</div>
		</div>
		<div class="content-body">
			<div class="bbs-content1 bbs-content">
				<div class="date">
					日付 : <input type="date" id="dateInput" name="setDate" required
						onchange="redirectToServlet('StatisticsMainC','${param.pageNo }')">
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
					<div class="board-title">수입, 판매 정보</div>
					<div class="row-titles">
						<div class="col-title contract">타입</div>
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
						<div class="col-title contract">총 합</div>
					</div>
					<div class="row-contents">
						<div class="row-type contract">수입</div>
						<c:forEach var="ia" items="${contractsInfo.importArr }">
							<div class="col-content contract-cost">${ia }</div>
						</c:forEach>
							<div class="col-content contract-cost">${contractsInfo.importCount }</div>
					</div>
					<div class="row-contents">
						<div class="row-type contract">판매</div>
						<c:forEach var="ia" items="${contractsInfo.salesArr }">
							<div class="col-content contract-cost">${ia }</div>
						</c:forEach>
							<div class="col-content contract-cost">${contractsInfo.salesCount }</div>
					</div>
				</div>
				<div class="long-board">
					<div class="board-title">수입, 판매 정보</div>
					<div class="row-titles">
						<div class="col-title contract">타입</div>
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
						<div class="col-title contract">총 합</div>
					</div>
					<div class="row-contents">
						<div class="row-type contract">수입</div>
						<c:forEach var="ia" items="${contractsInfo.importCostArr }">
							<div class="col-content contract-cost">${ia/1000 }K</div>
						</c:forEach>
							<div class="col-content contract-cost">${contractsInfo.importCostSum/1000 }K</div>
					</div>
					<div class="row-contents">
						<div class="row-type contract">판매</div>
						<c:forEach var="ia" items="${contractsInfo.salesCostArr }">
							<div class="col-content contract-cost">${ia/1000 }K</div>
						</c:forEach>
							<div class="col-content contract-cost">${contractsInfo.salesCostSum/1000 }K</div>
					</div>
					<div class="row-contents">
						<div class="row-type contract">차</div>
						<c:forEach var="ia" items="${contractsInfo.diffCostArr }">
							<div class="col-content contract-cost">${ia/1000 }K</div>
						</c:forEach>
						<div class="col-content contract-cost">${sumDif}K</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>