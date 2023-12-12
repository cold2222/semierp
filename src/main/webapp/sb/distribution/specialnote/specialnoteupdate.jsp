<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="sb/distribution_css/specialnote/specialnoteupdate.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous">
</script>
</head>
<body>
	<form action="DistributionSpecialNoteUpdateC" method="post"
		enctype="multipart/form-data">
		<div class="contents">
			<div class="content-head">
				<div class="content-head-text">注意/特異事項修正　　　ページ</div>
			</div>
			<div class="content-body">
				<div class="bbs-content">
					<div class="bbs-content1 bbs-content">
						<div class="bbs-content-main">
							<div class="bbs-content-body">
								<div class="bbs-content-bbs">
									<div class="bbs-main">
										<input type="hidden" name="s_num" value="${bbs.s_num }">
										<input class="input-title" name="s_title"
											placeholder="タイトルを入力してください。" required="required" value="${bbs.s_title }">
										<textarea name="s_content" class="input-content" placeholder="内容を入力してください。" required="required">${bbs.s_content }</textarea>
										<div class="filebox">
											<input type="hidden" name="old_img" value="${bbs.s_img }">
											<label for="file">イメージ登録</label> <input type="file" name="s_img" id="file">
											<input class="upload-name" readonly="readonly" value="${bbs.s_img }" placeholder="${bbs.s_img }">
										</div>
									</div>
								</div>
								<div class="insert-btn">
									<button>修正確認</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<div class="back-btn">
		<button>戻る</button>
	</div>
	<script type="text/javascript">
	$("#file").on('change',function(){
		  var fileName = $("#file").val();
		  $(".upload-name").val(fileName);
		});
	</script>
</body>
</html>