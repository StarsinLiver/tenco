<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- header.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<!-- MAIN -->
<div class="main">
	<!-- MAIN CONTENT -->
	<div class="main-content">
		<div class="container-fluid">
			<h3 class="page-title">Tables</h3>
			<div class="row">
				<div class="col-md-12">
					<div class="bg-light mb-4">
						<div class="card rounded">
							<div class="card-body d-flex flex-column">
								<div>
									<h4>${principal.username}님의계좌</h4>
									<h6>
										<span class="badge bg-secondary"
											style="color: white; width: 5em; margin-inline-end: 10px;">계좌번호</span>${account.number}
									</h6>
									<h6>
										<span class="badge bg-secondary"
											style="color: white; width: 5em; margin-inline-end: 10px;">잔액</span>${account.formatBalance()}
									</h6>
								</div>
							</div>
						</div>
					</div>

					<div class="mt-auto d-flex justify-content-end">
						<div class="btn-group me-3" role="group">
							<a href="/account/detail/${account.id}">
								<button type="button" class="btn btn-outline-secondary mr-2">전체조회</button>
							</a> <a href="/account/detail/${account.id}?type=deposit">
								<button type="button" class="btn btn-outline-secondary mr-2">입금조회</button>
							</a> <a href="/account/detail/${account.id}?type=withdraw">
								<button type="button" class="btn btn-outline-secondary">출금조회</button>
							</a>
						</div>
					</div>
					<!-- BASIC TABLE -->
					<div class="panel">
						<div class="panel-heading">
							<h3 class="panel-title">나의 계좌 목록</h3>
						</div>
						<div class="panel-body">
							<table class="table">
								<c:choose>
									<c:when test="${historyList != null}">
										<thead>
											<tr>
												<th>날짜</th>
												<th>보낸이</th>
												<th>받은이</th>
												<th>입출금 금액</th>
												<th>계좌 잔액</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="history" items="${historyList}">
												<tr>
													<td>${history.formatCreatedAt()}</td>
													<td>${history.sender}</td>
													<td>${history.receiver}</td>
													<td>${history.formatAmount()}</td>
													<td>${history.formatBalance()}</td>
												</tr>
											</c:forEach>
										</tbody>
									</c:when>
									<c:otherwise>
										<p>계좌가 존재하지 않습니다.</p>
									</c:otherwise>
								</c:choose>
							</table>
						</div>
					</div>
					<!-- END BASIC TABLE -->
				</div>
			</div>
		</div>
		<!-- END MAIN CONTENT -->
	</div>
	<!-- END MAIN -->

<!-- footer.jsp -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>