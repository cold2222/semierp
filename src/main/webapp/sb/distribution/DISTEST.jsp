<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>통합 알림 게시판</title>
    <link rel="stylesheet" href="your_css_file.css">
</head>

<style>
	.contents {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

/* 상단 제목 */
.content-head {
    text-align: center;
    margin-bottom: 20px;
}

.content-head-text {
    font-size: 28px;
    font-weight: bold;
    text-transform: uppercase;
    background-color: #f5f5f5; /* 연한 회색 배경 */
    padding: 10px 20px; /* 내부 여백 설정 */
    border: 1px solid #ddd; /* 테두리 설정 */
    border-radius: 5px; /* 테두리의 모서리를 둥글게 설정 */
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); /* 입체적인 박스 효과 */
}

/* 게시판 */
.bbs-content {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
}

.board {
    width: calc(50% - 20px);
    background-color: #fff;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    padding: 20px;
    margin-bottom: 20px;
}

.board-title {
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 10px;
}

.post {
    border-bottom: 1px solid #ddd;
    padding: 10px 0;
}

.post-title {
    font-weight: bold;
    color: #007bff;
    margin-bottom: 5px;
}

.post-info {
    font-style: italic;
    color: #888;
}
</style>
<body>
    <div class="contents">
        <div class="content-head">
            <div class="content-head-text">통합 알림 게시판</div>
        </div>
        <div class="content-body">
            <div class="bbs-content">
                <div class="board">
                    <div class="board-title">게시판 1</div>
                    <div class="post">
                        <div class="post-title">제목 1-1</div>
                        <div class="post-info">날짜 1-1</div>
                    </div>
                    <div class="post">
                        <div class="post-title">제목 1-2</div>
                        <div class="post-info">날짜 1-2</div>
                    </div>
                </div>

                <!-- ... (각 게시판 별로 2개의 글이 있는 형식을 2x3으로 추가) -->

            </div>
        </div>
    </div>
</body>
</html>