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
<link rel="stylesheet" href="sb/distribution_css/shift/shift.css">
<script type="text/javascript" src="sb/distribution_js/shift.js"></script>
</head>
<body>
	<div class="contents">
		<div class="content-head">
			<div class="content-head-text">運送部　出勤登録</div>
		</div>
	</div>
	<div class="content">
		<h1>${year }年${month }月出勤簿</h1>
		<div class="menu-top">
			<div class="workstatus">
				<div style="background-color: red; width: 40px; margin-right: 5px;"></div>
				定休日 &nbsp;&nbsp;&nbsp;
				<div
					style="background-color: green; width: 40px; margin-right: 5px;"></div>
				有給休暇
			</div>
			<div class="selectym">
				<span>年&nbsp;</span><input type="text" name="st_department" list="depList2" id="year" pattern="^[0-9]+$" value="${year }"><br>
				<datalist id="depList2">
					<c:forEach begin="2000" end="2027" var="i">
					<option value="${i}">${i }</option>
					</c:forEach>
				</datalist>
				<span>&nbsp;月&nbsp;</span><input type="text" name="st_department" list="depList" id="month"  pattern="^[0-9]+$" placeholder="${month }"><br>
				<datalist id="depList">
					<c:forEach begin="1" end="12" var="i">
					<option value="${i}">${i }</option>
					</c:forEach>
				</datalist>
				<button class="dis-btn" onclick="updateCalendar()">更新</button>
			</div>
		</div>
		<table class="content-table">
			<thead>
				<tr>
					<th class="name-line">曜日</th>
					<c:forEach var="dow" items="${dayOfWeek }">
						<c:choose>
							<c:when test="${dow == '土' }">
								<th style="color: blue">${dow }</th>
							</c:when>
							<c:when test="${dow == '日' }">
								<th style="color: red">${dow }</th>
							</c:when>
							<c:otherwise>
								<th>${dow }</th>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
				<tr>
					<th>お名前</th>
					<c:forEach var="i" begin="1" end="${lastDay }">
						<th>${i }</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="e" items="${emps }">
					<tr class="p-input-tr">
						<td>${e.e_name }</td>
						<c:forEach var="list" items="${calendarMap[e.e_no] }"
							varStatus="i">
							<c:choose>
								<c:when test="${list == 1}">
									<td><input class="p-input" readonly="readonly" type="text"
										value="${e.e_no }/${year }-${month}-${i.count}/${list }"
										style="color: white"></td>
								</c:when>
								<c:when test="${list == 2}">
									<td><input class="p-input" readonly="readonly" type="text"
										value="${e.e_no }/${year }-${month}-${i.count}/${list }"
										style="color: red; background-color: red;"></td>
								</c:when>
								<c:otherwise>
									<td><input class="p-input" readonly="readonly" type="text"
										value="${e.e_no }/${year }-${month}-${i.count}/${list }"
										style="color: green; background-color: green;"></td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>