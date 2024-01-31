<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- haeder.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div class="col-sm-8">
	<h2>계좌 생성 페이지(인증)</h2>
	<h5>어서오세요 환영합니다.</h5>
	<form action="/account/save" method="post">
		<div class="form-group">
			<label for="number">계좌 번호:</label> <input type="text"
				class="form-control" name="number" placeholder="Enter number"
				id="number" value="5555">
		</div>
		<div class="form-group">
			<label for="password">비밀번호:</label> <input type="password"
				class="form-control" name="password" placeholder="Enter password"
				id="password" value="1234">
		</div>
		<div class="form-group">
			<label for="balance">입금액:</label> <input type="text"
				class="form-control" name="balance" placeholder="Enter balance"
				id="balance" value="2000">
		</div>

		<button type="submit" class="btn btn-primary">계좌 생성</button>
	</form>
</div>

<!-- footer.jsp -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>