<%@page import="java.math.BigInteger"%>
<%@page import="java.security.SecureRandom"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- haeder.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div class="col-sm-8">
	<h2>로그인</h2>
	<h5>어서오세요 환영합니다.</h5>
	<form action="/user/sign-in" method="post">
		<div class="form-group">
			<label for="username">Username:</label> <input type="text"
				class="form-control" name="username" placeholder="Enter email"
				id="username" value="길동">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input type="password"
				class="form-control" name="password" placeholder="Enter password"
				id="password" value="1234">
		</div>

		<button type="submit" class="btn btn-primary">로그인</button>
		<a
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
		<a href="<%=apiURL%>"><img height="50"
			src="http://static.nid.naver.com/oauth/small_g_in.PNG" /></a>
	</form>
</div>

<!-- footer.jsp -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>