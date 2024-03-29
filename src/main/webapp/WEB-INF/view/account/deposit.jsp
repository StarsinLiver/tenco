<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- haeder.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<!-- MAIN -->
<div class="main">
	<!-- MAIN CONTENT -->
	<div class="main-content">
		<div class="container-fluid">
			<h3 class="page-title">입금 페이지(인증 / ATM)</h3>
			<h5>어서오세요 환영합니다.</h5>
			<div class="panel panel-headline">

				<div class="panel-body">

					<form action="/account/deposit" method="post">
						<div class="form-group">
							<label for="amount">입금금액:</label> <input type="text"
								name="amount" class="form-control" placeholder="Enter amount"
								id="amount" value="1000">
						</div>
						<div class="form-group">
							<label for="dAccountNumber">입금 계좌번호:</label> <input type="text"
								name="dAccountNumber" class="form-control"
								placeholder="입금 계좌번호 입력" id="dAccountNumber" value="1111">
						</div>

						<button type="submit" class="btn btn-primary">입금</button>
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