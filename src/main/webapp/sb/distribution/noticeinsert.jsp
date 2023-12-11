<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="sb/distribution_css/noticeinsert.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous">
</script>
</head>
<body>
	<form action="DistributionNoticeInsertC" method="post"
		enctype="multipart/form-data">
		<div class="contents">
			<div class="content-head">
				<div class="content-head-text">공지 작성 페이지</div>
			</div>
			<div class="content-body">
				<div class="bbs-content">
					<div class="bbs-content1 bbs-content">
						<div class="bbs-content-main">
							<div class="bbs-content-body">
								<div class="bbs-content-bbs">
									<div class="bbs-main">
										<input class="input-title" name="n_title"
											required="required" placeholder="タイトルを入力してください。">
										<textarea name="n_content" class="input-content" required="required" placeholder="内容を入力してください。"></textarea>
										<div class="filebox">
											<label for="file">イメージ登録</label> <input type="file" name="n_img" id="file">
											<input class="upload-name" readonly="readonly" value="登録されたイメージ" placeholder="登録されたイメージ">
										</div>
									</div>
								</div>
								<div class="insert-btn">
									<button>登録</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
	$("#file").on('change',function(){
		  var fileName = $("#file").val();
		  $(".upload-name").val(fileName);
		});
	</script>
</body>
</html>