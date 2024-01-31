<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- haeder.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div class="col-sm-8">
	<h2>출금 페이지</h2>
	<h5>어서오세요 환영합니다.</h5>
	<form action="/account/withdraw" method="post">
		<div class="form-group">
			<label for="amount">출금 금액:</label> <input type="text"
				class="form-control" name="amount" placeholder="Enter amount"
				id="amount" value="5555">
		</div>
		<div class="form-group">
			<label for="wAccountNumber">출금 계좌 번호:</label> <input type="text"
				class="form-control" name="wAccountNumber" placeholder="Enter wAccountNumber"
				id="wAccountNumber" value="5555">
		</div>
		<div class="form-group">
			<label for="wAccountPassword">계좌 비밀번호:</label> <input type="password"
				class="form-control" name="wAccountPassword" placeholder="Enter wAccountPassword"
				id="wAccountPassword" value="1234">
		</div>

		<button type="submit" class="btn btn-primary">출금</button>
	</form>
</div>

<!-- footer.jsp -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>