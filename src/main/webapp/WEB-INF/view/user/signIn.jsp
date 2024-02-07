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
	<div id="wrapper">
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
								<p class="lead">로그인</p>
							</div>
							<form action="/user/sign-in" method="post">
								<div class="form-group">
									<label for="username">Username:</label> <input type="text"
										class="form-control" name="username" placeholder="Enter email"
										id="username" value="길동">
								</div>
								<div class="form-group">
									<label for="password">Password:</label> <input type="password"
										class="form-control" name="password"
										placeholder="Enter password" id="password" value="password">
								</div>

								<button type="submit" class="btn btn-primary">로그인</button>
								<a href="/user/sign-up" class="btn">회원가입</a> <a
									href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=e91a42ac029ee16e51f12b687a122c1f&redirect_uri=http://localhost:80/user/kakao-callback">
									<img alt="" src="/images/kakao_login_small.png" width="75"
									height="38" />
								</a>
								<!-- 네이버 로그인 -->
								<%
								String clientId = "CHN2CjQQxDAV3oJAjakG";//애플리케이션 클라이언트 아이디값";
								String redirectURI = URLEncoder.encode("http://localhost:80/user/naver-callback", "UTF-8");
								SecureRandom random = new SecureRandom();
								String state = new BigInteger(130, random).toString();
								String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
								apiURL += "&client_id=" + clientId;
								apiURL += "&redirect_uri=" + redirectURI;
								apiURL += "&state=" + state;
								session.setAttribute("state", state);
								%>
								<a href="<%=apiURL%>"><img height="37"
									src="http://static.nid.naver.com/oauth/small_g_in.PNG" /></a>
							</form>


						</div>
					</div>
					<div class="right">
						<div class="overlay"></div>
						<div class="content text">
							<h1 class="heading">이곳은 로그인 페이지 입니다.</h1>
							<p>by The Develovers</p>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- END WRAPPER -->
</body>
</html>