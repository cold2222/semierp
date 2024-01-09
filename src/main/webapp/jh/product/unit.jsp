<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="jh/css/unit.css">
</head>
<body>
	<div class="contents">
    <div class="content-head">
        <div class="content-head-text">単位とタイプ</div>
    </div>
    <div class="content-body">
        <div style="display: flex;">
            <div style="flex: 1; height: 80px; border: 1px solid black;" class="registration-form">
                <form action="RegUnitC" method="post">
                    <div style="display: flex; align-items: center;">
                        <input name="unit" style="flex: 1;" autocomplete="off">
                        <button style="margin-left: 10px;">単位登録</button>
                    </div>
                </form>
            </div>
            <div style="flex: 1; height: 80px; border: 1px solid black; margin-left: 10px;" class="registration-form">
                <form action="RegTypeC" method="post">
                    <div style="display: flex; align-items: center;">
                        <input name="type" style="flex: 1;" autocomplete="off">
                        <button style="margin-left: 10px;">タイプ登録</button>
                    </div>
                </form>
            </div>
        </div>
        <div style="display: flex;">
            <div style="flex: 1; border: 1px solid black; height: 800px; margin-top: 10px;" class="data-display">
                <div style="border-bottom: 1px solid black;">
                    <div style="font-weight: bold; font-size: 20pt;">Unit</div>
                </div>
                <c:forEach var="u" items="${us}">
                    <div style="display: flex; align-items: center; border-bottom: 1px solid black;">
                        <div style="flex: 1;">${u.unit}</div>
                        <button onclick="location.href='UpdateUnitC?u=${u.unit}'">修正</button>
                        <button onclick="location.href='DeleteUnitC?u=${u.unit}'">削除</button>
                    </div>
                </c:forEach>
            </div>
            <div style="flex: 1; border: 1px solid black; height: 800px; margin-top: 10px; margin-left: 10px;" class="data-display">
                <div style="border-bottom: 1px solid black;">
                    <div style="font-weight: bold; font-size: 20pt;">Type</div>
                </div>
                <c:forEach var="t" items="${ts}">
                    <div style="display: flex; align-items: center; border-bottom: 1px solid black;">
                        <div style="flex: 1;">${t.type}</div>
                        <button onclick="location.href='UpdateTypeC?t=${t.type}'">修正</button>
                        <button onclick="location.href='DeleteTypeC?t=${t.type}'">削除</button>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>