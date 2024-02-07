<%@page import="java.math.BigInteger"%>
<%@page import="java.security.SecureRandom"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en" class="fullscreen-bg">

<head>
<title>Login | Klorofil - Free Bootstrap Dashboard Template</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet" href="/assets/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/assets/vendor/linearicons/style.css">
<!-- MAIN CSS -->
<link rel="stylesheet" href="/assets/css/main.css">
<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
<link rel="stylesheet" href="/assets/css/demo.css">
<!-- GOOGLE FONTS -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
	rel="stylesheet">
<!-- ICONS -->
<link rel="apple-touch-icon" sizes="76x76"
	href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="assets/img/favicon.png">
</head>

<body>
	<!-- WRAPPER -->
	<div id="wrapper ">
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box ">
					<div class="left">
						<div class="content">
							<div class="header">
								<div class="navbar-btn">
									<a href="/">
										<button type="button" class="btn-toggle-fullwidth">
											<i class="lnr lnr-arrow-left-circle"></i>
										</button>
									</a>
								</div>
								<p class="lead">회원가입</p>
							</div>
							<form action="/user/sign-up" method="post" class="text-center"
								enctype="multipart/form-data">
								<div class="form-group">
									<label for="username">Username:</label> <input type="text"
										class="form-control" name="username" placeholder="Enter email"
										id="username">
								</div>
								<div class="form-group">
									<label for="password">Password:</label> <input type="password"
										class="form-control" name="password"
										placeholder="Enter password" id="password">
								</div>
								<div class="form-group">
									<label for="fullname">Fullname:</label> <input type="text"
										class="form-control" name="fullname"
										placeholder="Enter password" id="fullname">
								</div>

								<div class="custom-file">
								<label class="custom-file-label"
										for="customFile">회원 프로필 사진: </label>
									<input type="file" class="custom-file-input" id="customFile"
										name="customFile"> 
								</div>

								<button type="submit" class="btn btn-primary">회원가입</button>
							</form>
						</div>
					</div>
					<div class="right">
						<div class="overlay"></div>
						<div class="content text">
							<h1 class="heading">이곳은 회원가입 페이지입니다.</h1>
							<p>by The Develovers</p>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- END WRAPPER -->
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
</body>
</html>