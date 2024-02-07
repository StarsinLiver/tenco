<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- haeder.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<!-- 외부 스타일 시트 가져오기 -->
<link rel="stylesheet" href="/css/signin.css">
<div class="col-sm-8">
	<h2>회원가입</h2>
	<h5>어서오세요 환영합니다.</h5>
	multipart/form-data 반드시 정의
	<form action="/user/sign-up" method="post"
		enctype="multipart/form-data">
		<div class="form-group">
			<label for="username">Username:</label> <input type="text"
				class="form-control" name="username" placeholder="Enter email"
				id="username">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input type="password"
				class="form-control" name="password" placeholder="Enter password"
				id="password">
		</div>
		<div class="form-group">
			<label for="fullname">Fullname:</label> <input type="text"
				class="form-control" name="fullname" placeholder="Enter password"
				id="fullname">
		</div>

		<div class="custom-file">
			<input type="file" class="custom-file-input" id="customFile"
				name="customFile"> <label class="custom-file-label"
				for="customFile">Choose file</label>
		</div>

		<button type="submit" class="btn btn-primary">회원가입</button>
	</form>
</div>


<script>
	// Add the following code if you want the name of the file appear on select
	$(".custom-file-input").on(
			"change",
			function() {
				var fileName = $(this).val().split("\\").pop();
				$(this).siblings(".custom-file-label").addClass("selected")
						.html(fileName);
			});
</script>
<!-- footer.jsp -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>