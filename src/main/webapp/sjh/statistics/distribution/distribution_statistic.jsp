<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet" href="css/sjh/statistics/distribution.css">
<link rel="stylesheet" href="sb/distribution_css/mainbbs/main_bbs.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="js/sjh/admin/utils/input_date_init.js"></script>
<script src="js/sjh/statistics/statistics_distribution.js"></script>

</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">판매 상품 정보</div>
		</div>
		<div class="content-body">
			<div class="bbs-content1 bbs-content">
				<div class="date">
					日付 : <input type="date" id="dateInput" name="setDate" required
						onchange="redirectToServlet('StatisticDistributionC','${param.pageNo }')">
				</div>
				<div class="long-board">
					<div class="board-title">운송 지표</div>
					<div class="post">
						<canvas id="distributionYear"></canvas>
					</div>
				</div>

				<div class="long-board">
					<div class="board-title">계약 운송 정보</div>
					<div class="row-titles">
						<div class="col-title distribution">타입</div>
						<div class="col-title distribution">01月</div>
						<div class="col-title distribution">02月</div>
						<div class="col-title distribution">03月</div>
						<div class="col-title distribution">04月</div>
						<div class="col-title distribution">05月</div>
						<div class="col-title distribution">06月</div>
						<div class="col-title distribution">07月</div>
						<div class="col-title distribution">08月</div>
						<div class="col-title distribution">09月</div>
						<div class="col-title distribution">10月</div>
						<div class="col-title distribution">11月</div>
						<div class="col-title distribution">12月</div>
						<div class="col-title distribution">총 합</div>
					</div>

					<div class="row-contents">
						<div class="row-type distribution">정상</div>
						<c:forEach var="iter" items="${distributionStatistic}">
							<div class="col-content distribution">${iter.type1Completed + iter.type2Completed }</div>
						</c:forEach>
						<div class="col-content distribution">${sums[0] }</div>
					</div>

					<div class="row-contents">
						<div class="row-type distribution">지연</div>
						<c:forEach var="iter" items="${distributionStatistic}">
							<div class="col-content distribution">${iter.type1Delayed + iter.type2Delayed }</div>
						</c:forEach>
						<div class="col-content distribution">${sums[1] }</div>
					</div>

					<div class="row-contents">
						<div class="row-type distribution">실패 비율</div>
						<c:forEach var="iter" items="${distributionStatistic}">
							<c:if
								test="${iter.type1Completed + iter.type2Completed + iter.type1Delayed + iter.type2Delayed == 0}">
								<div class="col-content distribution">-</div>
							</c:if>
							<c:if
								test="${iter.type1Completed + iter.type2Completed + iter.type1Delayed + iter.type2Delayed != 0}">
								<div class="col-content distribution">
									<fmt:formatNumber
										value="${ (iter.type1Delayed + iter.type2Delayed) / (iter.type1Completed + iter.type2Completed + iter.type1Delayed + iter.type2Delayed) }"
										pattern="0.00"></fmt:formatNumber>
								</div>
							</c:if>
						</c:forEach>
						<div class="col-content distribution">
							<c:if test="${sums[0] + sums[1] == 0 }"> - </c:if>
							<c:if test="${sums[0] + sums[1] != 0 }">
								<fmt:formatNumber value="${sums[1]/(sums[0] + sums[1]) }"
									pattern="0.00"></fmt:formatNumber>
							</c:if>
						</div>
					</div>
				</div>

				<div class="long-board">
					<div class="board-title">수입 운송 정보</div>
					<div class="row-titles">
						<div class="col-title distribution">타입</div>
						<div class="col-title distribution">01月</div>
						<div class="col-title distribution">02月</div>
						<div class="col-title distribution">03月</div>
						<div class="col-title distribution">04月</div>
						<div class="col-title distribution">05月</div>
						<div class="col-title distribution">06月</div>
						<div class="col-title distribution">07月</div>
						<div class="col-title distribution">08月</div>
						<div class="col-title distribution">09月</div>
						<div class="col-title distribution">10月</div>
						<div class="col-title distribution">11月</div>
						<div class="col-title distribution">12月</div>
						<div class="col-title distribution">총 합</div>
					</div>

					<div class="row-contents">
						<div class="row-type distribution">정상</div>
						<c:forEach var="iter" items="${distributionStatistic}">
							<div class="col-content distribution">${iter.type1Completed }</div>
						</c:forEach>
						<div class="col-content distribution">${sums[2] }</div>
					</div>

					<div class="row-contents">
						<div class="row-type distribution">지연</div>
						<c:forEach var="iter" items="${distributionStatistic}">
							<div class="col-content distribution">${iter.type1Delayed }</div>
						</c:forEach>
						<div class="col-content distribution">${sums[3] }</div>
					</div>

					<div class="row-contents">
						<div class="row-type distribution">실패 비율</div>
						<c:forEach var="iter" items="${distributionStatistic}">
							<c:if test="${iter.type1Completed + iter.type1Delayed == 0}">
								<div class="col-content distribution">-</div>
							</c:if>
							<c:if test="${iter.type1Completed + iter.type1Delayed != 0}">
								<div class="col-content distribution">
									<fmt:formatNumber
										value="${ (iter.type1Delayed) / (iter.type1Completed + iter.type1Delayed) }"
										pattern="0.00"></fmt:formatNumber>
								</div>
							</c:if>
						</c:forEach>
						<div class="col-content distribution">
							<c:if test="${sums[2] + sums[3] == 0 }"> - </c:if>
							<c:if test="${sums[0] + sums[1] != 0 }">
								<fmt:formatNumber value="${sums[3]/(sums[2] + sums[3]) }"
									pattern="0.00"></fmt:formatNumber>
							</c:if>
						</div>
					</div>
				</div>

				<div class="long-board">
					<div class="board-title">판매 운송 정보</div>
					<div class="row-titles">
						<div class="col-title distribution">타입</div>
						<div class="col-title distribution">01月</div>
						<div class="col-title distribution">02月</div>
						<div class="col-title distribution">03月</div>
						<div class="col-title distribution">04月</div>
						<div class="col-title distribution">05月</div>
						<div class="col-title distribution">06月</div>
						<div class="col-title distribution">07月</div>
						<div class="col-title distribution">08月</div>
						<div class="col-title distribution">09月</div>
						<div class="col-title distribution">10月</div>
						<div class="col-title distribution">11月</div>
						<div class="col-title distribution">12月</div>
						<div class="col-title distribution">총 합</div>
					</div>

					<div class="row-contents">
						<div class="row-type distribution">정상</div>
						<c:forEach var="iter" items="${distributionStatistic}">
							<div class="col-content distribution">${iter.type2Completed }</div>
						</c:forEach>
						<div class="col-content distribution">${sums[4] }</div>
					</div>

					<div class="row-contents">
						<div class="row-type distribution">지연</div>
						<c:forEach var="iter" items="${distributionStatistic}">
							<div class="col-content distribution">${iter.type2Delayed }</div>
						</c:forEach>
						<div class="col-content distribution">${sums[5] }</div>
					</div>

					<div class="row-contents">
						<div class="row-type distribution">실패 비율</div>
						<c:forEach var="iter" items="${distributionStatistic}">
							<c:if test="${iter.type2Completed + iter.type2Delayed == 0}">
								<div class="col-content distribution">-</div>
							</c:if>
							<c:if test="${iter.type2Completed + iter.type2Delayed != 0}">
								<div class="col-content distribution">
									<fmt:formatNumber
										value="${ (iter.type2Delayed) / (iter.type2Completed + iter.type2Delayed) }"
										pattern="0.00"></fmt:formatNumber>
								</div>
							</c:if>
						</c:forEach>
						<div class="col-content distribution">
							<c:if test="${sums[4] + sums[5] == 0 }"> - </c:if>
							<c:if test="${sums[0] + sums[1] != 0 }">
								<fmt:formatNumber value="${sums[5]/(sums[4] + sums[5]) }"
									pattern="0.00"></fmt:formatNumber>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>