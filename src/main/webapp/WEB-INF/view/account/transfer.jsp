<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- haeder.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<!-- MAIN -->
<div class="main">
	<!-- MAIN CONTENT -->
	<div class="main-content">
		<div class="container-fluid">
			<h3 class="page-title">이체 페이지(인증)</h3>
			<h5>어서오세요 환영합니다.</h5>
			<div class="panel panel-headline">
				<div class="panel-body">
					<form action="/account/transfer" method="post">
						<div class="form-group">
							<label for="amount">이체 금액:</label> <input type="text"
								name="amount" class="form-control" placeholder="Enter amount"
								id="amount" value="1000">
						</div>
						<div class="form-group">
							<label for="wAccountNumber">출금 계좌번호:</label> <input type="text"
								name="wAccountNumber" class="form-control"
								placeholder="출금하실 계좌번호를 입력하세요" id="wAccountNumber" value="1111">
						</div>
						<div class="form-group">
							<label for="dAccountNumber">이체 계좌번호:</label> <input type="text"
								name="dAccountNumber" class="form-control"
								placeholder="이체하실 계좌번호를 입력하세요" id="dAccountNumber" value="5555">
						</div>
						<div class="form-group">
							<label for="password">출금 비밀번호:</label> <input type="password"
								name="password" class="form-control"
								placeholder="출금하실 계좌번호를 입력하세요" id="password" value="1234">
						</div>

						<button type="submit" class="btn btn-primary">이체하기</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</div>
<!-- END MAIN -->
<!-- footer.jsp -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>