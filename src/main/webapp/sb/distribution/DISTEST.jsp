<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>출근 예정표</title>
    <style>
        /* CSS 스타일링은 여기에 */
    </style>
</head>
<body>
    <h1>출근 예정표</h1>

    <label for="year">년도:</label>
    <input type="number" id="year" value="2023">
    <label for="month">월:</label>
    <input type="number" id="month" value="12">
    <button onclick="updateCalendar()">일정 갱신</button>

    <table id="calendar">
        <tr>
            <td></td> <!-- 빈 셀 -->
            <c:forEach begin="1" end="${daysInMonth}" var="day">
                <td>${day}</td>
            </c:forEach>
        </tr>
        <c:forEach items="${people}" var="person">
            <tr>
                <td>${person}</td>
                <c:forEach begin="1" end="${daysInMonth}" var="day">
                    <td>
                        <!-- 여기에 일정 입력을 위한 input 태그 추가 -->
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>

    <script>
        // 자바스크립트 기능은 여기에 구현
    </script>
</body>
</html>