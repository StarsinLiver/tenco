<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- haeder.jsp -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<!-- MAIN -->
<div class="main">
	<!-- MAIN CONTENT -->
	<div class="main-content">
		<div class="container-fluid">
			<h3 class="page-title">Tables</h3>
			<div class="row">
				<div class="col-md-12">
					<!-- BASIC TABLE -->
					<div class="panel">
						<div class="panel-heading">
							<h3 class="panel-title">나의 계좌 목록</h3>
						</div>
						<div class="panel-body">
							<table class="table">
								<c:choose>
									<c:when test="${accountList != null}">
										<thead>
											<tr>
												<th>계좌 번호</th>
												<th>잔액</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="account" items="${accountList}">
												<tr>
													<td><a href="/account/detail/${account.id}">${account.number}</a></td>
													<td>${account.formatBalance()}</td>
												</tr>
											</c:forEach>
										</tbody>
									</c:when>
									<c:otherwise>
										<p>아직 생성된 계좌가 없습니다.</p>
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