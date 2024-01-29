<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- haeder.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div class="col-sm-8">
	<h2>회원가입</h2>
	<h5>어서오세요 환영합니다.</h5>
	<form action="/user/sign-up" method="post">
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

		<button type="submit" class="btn btn-primary">회원가입</button>
	</form>
</div>

<!-- footer.jsp -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>