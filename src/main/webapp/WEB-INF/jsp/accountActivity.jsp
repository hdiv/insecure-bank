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
									<h2><spring:message code="accountsActivityUC"/></h2>
									<ul class="breadcrumb pull-left">
										<li><i class="icon ion-home"></i><a href="${index}"><spring:message code="home"/></a></li>
										<li><a href="#"><spring:message code="accountsActivity"/></a></li>
									</ul>
								</div>
								
								<div class="form-group">
								   <spring:url value="/activity/detail" var="url"></spring:url>
								   <form:form method="POST" class="form-horizontal" role="form" commandName="cashAccount" action="${url}">																												
										<spring:bind path="number">																			
										<label class="col-md-2 control-label" style=" padding-top: 7px; text-align: center;"><spring:message code="account"/></label>
											<div class="input-group">
												<form:select path="number" class="form-control">
													<c:forEach items="${cashAccounts}" var="cash">													
														<c:if test = "${cash.number == actualCashAccountNumber}">	
															<form:option selected="selected" value="${cash.number}" label="${cash.number} (${cash.description}) " />																									
														</c:if>														
														<c:if test = "${cash.number ne actualCashAccountNumber}">	
															<form:option value="${cash.number}" label="${cash.number} (${cash.description}) " />																									
														</c:if>										
													</c:forEach>
												</form:select>
											<span class="input-group-btn"><button type="submit" class="btn btn-primary"><spring:message code="go"/></button></span>
											</div>	
										</spring:bind>																																							
									</form:form>																																											
								</div>
																
								<div class="widget">
											<div class="widget-header clearfix">
												<h3><i class="icon ion-clipboard"></i> <span><spring:message code="activitySummaryUC"/> | <c:out value="${actualCashAccountNumber}"></c:out></span></h3>
											</div>
											<div class="widget-content">
												<table class="table table-bordered  colored-header">
													<thead>
														<tr>
															<th><spring:message code="date"/></th>
															<th><spring:message code="description"/></th>
															<th><spring:message code="deposits"/></th>
															<th><spring:message code="withdrawals"/></th>
															<th><spring:message code="endingBalance"/></th>
														</tr>
													</thead>
													<tbody>
													<c:forEach items="${firstCashAccountTransfers}" var="firstCashAccountTransfer">
														<tr>
															<td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${firstCashAccountTransfer.date}" /></td>
															<td>${firstCashAccountTransfer.description}</td>
															
															<c:if test="${firstCashAccountTransfer.amount > 0}">															
																<td>+<c:out value="${firstCashAccountTransfer.amount}"/> EUR</td>																		
																<td></td>																
															</c:if>
															
															<c:if test="${firstCashAccountTransfer.amount < 0}">															
																<td></td>																		
																<td><c:out value="${firstCashAccountTransfer.amount}"/> EUR</td>																
															</c:if>																	
																	
															<td><c:if test="${firstCashAccountTransfer.availablebalance > 0}">+</c:if><c:out value="${firstCashAccountTransfer.availablebalance}"/> EUR</td>									
														</tr>
													</c:forEach>
													</tbody>
												</table>
											</div>
										</div>							
					

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
