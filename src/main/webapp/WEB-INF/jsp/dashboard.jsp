<%@ include file="/WEB-INF/jsp/template/tags.jsp"%>
<%@ include file="/WEB-INF/jsp/template/head.jsp"%>

<body class="static-tables">

	<!-- WRAPPER -->
	<div class="wrapper">
		<div class="container-fluid">
			<%@ include file="/WEB-INF/jsp/template/header.jsp"%>
			<div class="row">
				<%@ include file="/WEB-INF/jsp/template/sidebar.jsp"%>
				<div class="col-lg-10 col-right ">
					<div class="right-content">
						<!-- MAIN CONTENT -->
						<div class="main-content">
							<div class="primary-content">
								<div class="heading clearfix">
									<h2><spring:message code="dashboardUC"/></h2>
									<ul class="breadcrumb pull-left">
										<li><i class="icon ion-home"></i><a href="${index}">Home</a></li>
										<li><a href="#"><spring:message code="dashboard"/></a></li>
									</ul>
								</div>
								<!-- RESPONSIVE TABLE -->
								<div class="widget">
									<div class="widget-header clearfix">
										<h3><i class="fa fa-list-alt"></i> <span><spring:message code="cashAccountsUC"/></span></h3>
										<spring:url value="/dashboard/cashAccount/export" var="cashAccountExport"/>
										<i class="fa fa-file-excel-o fa-align-right" style="position: absolute;right: 25px;margin-top: 10px;" onclick='javascript:window.open("${cashAccountExport}");'></i>
									</div>
									<div class="widget-content">
										<div class="table-responsive">
											<table class="table table-condensed">
												<thead>
													<tr>
														<th><spring:message code="cashAccount"/></th>
														<th><spring:message code="description"/></th>										
														<th><spring:message code="availableBalance"/></th>
														<th style="width: 140px;"></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${cashAccounts}" var="cash">
														<tr>
															<spring:url value="/activity/${cash.number}/detail" var="url">
															</spring:url>
															<td><c:out value="${cash.number}"/></td>
															<td><c:out value="${cash.description}"/></td>																		
															<td><c:if test="${cash.availableBalance > 0}">+</c:if><c:out value="${cash.availableBalance}"/> EUR</td>	
															<td><a href="${url}" class="btn btn-primary btn-xs"><i class="fa fa-search"></i> <spring:message code="viewActivity"/></a></td>		
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<!-- END RESPONSIVE TABLE -->
								<!-- RESPONSIVE TABLE -->
								<div class="widget">
									<div class="widget-header clearfix">
										<h3><i class="fa fa-credit-card"></i> <span><spring:message code="creditAccountsUC"/></span></h3>
										<spring:url value="/dashboard/creditAccount/export" var="creditAccountExport"/>
										<i class="fa fa-file-excel-o fa-align-right" style="position: absolute;right: 25px;margin-top: 10px;" onclick='javascript:window.open("${creditAccountExport}");'></i>
									</div>
									<div class="widget-content">
										<div class="table-responsive">
											<table class="table table-condensed">
												<thead>
													<tr>
														<th><spring:message code="cashAccount"/></th>
														<th><spring:message code="description"/></th>										
														<th><spring:message code="availableBalance"/></th>
														<th style="width: 140px;"></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${creditAccounts}" var="credit">
														<tr>
															<td>
															<c:if test="${fn:contains(credit.description, 'Visa')}">
															   <i class="fa fa-cc-visa"></i>
															</c:if>
															<c:if test="${fn:contains(credit.description, 'AmEx')}">
															   <i class="fa fa-cc-amex"></i>
															</c:if>
															<c:if test="${fn:contains(credit.description, 'Master')}">
																<i class="fa fa-cc-mastercard"></i>
															</c:if>																														
															<c:out value="${credit.number}"/></td>
															<td><c:out value="${credit.description}"/></td>																		
															<td><c:if test="${credit.availableBalance > 0}">+</c:if><c:out value="${credit.availableBalance}"/> EUR</td>	
															<spring:url value="/activity/credit.html?number=${credit.number}" var="url">
															</spring:url>
															<td><a href="${url}" class="btn btn-primary btn-xs"><i class="fa fa-search"></i> <spring:message code="viewActivity"/></a></td>									
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<!-- END RESPONSIVE TABLE -->								
							</div>
						</div>
					</div>
				</div>
				<!-- END MAIN CONTENT -->
			</div>
		</div>				
	</div>
	<div class="left-content-bg col-md-2"></div>
	<!-- END WRAPPER -->

	<%@ include file="/WEB-INF/jsp/template/javascript.jsp"%>
</body>

<%@ include file="/WEB-INF/jsp/template/footer.jsp"%>
