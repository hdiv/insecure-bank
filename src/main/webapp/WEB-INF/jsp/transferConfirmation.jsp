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
									<h2><spring:message code="makeATransferUC"/></h2>
									<ul class="breadcrumb pull-left">
										<li><i class="icon ion-home"></i><a href="${index}"><spring:message code="home"/></a></li>
										<spring:url value="/transfer" var="transfer" />
										<li><a href="${transferurl}"><spring:message code="transfers"/></a></li>
										<li><a href="${transferurl}"><spring:message code="makeATransfer"/></a></li>
										<li><spring:message code="transferConfirmation"/></li>										
									</ul>
								</div>
								<!-- RESPONSIVE TABLE -->
								
								<div class="widget">
									<div class="widget-header clearfix">
										<h3><i class="icon ion-compose"></i> <span><spring:message code="transferConfirmationUC"/></span></h3>
									</div>
									<div class="widget-content">
									<div class="alert alert-success fade in">
											<button class="close" data-dismiss="alert">×</button>
											<i class="icon ion-checkmark-circled"></i>
											<strong><spring:message code="success"/>!</strong> <spring:message code="successfullyMadeTransfer"/>.
										</div>
										<table id="user" class="table table-bordered table-striped">
											<tbody>
												<tr>
													<td><label class="col-md-2 control-label"><spring:message code="from"/></label></td>
													<td><c:out value="${account.name}" /> <c:out value="${account.surname}"/></td>
												</tr>
												<tr>
													<td><label class="col-md-4 control-label"><spring:message code="accountType"/></label></td>
													<td><c:out value="${accountType}" /> </td>
												</tr>
												<tr>
													<td><label class="col-md-4 control-label"><spring:message code="fromAccount"/></label></td>
													<td><c:out value="${transferbean.fromAccount}" /> </td>
												</tr>
												<tr>
													<td><label class="col-md-4 control-label"><spring:message code="sendTo"/></label></td>
													<td><c:out value="${transferbean.toAccount}" /> </td>
												</tr>
												<tr>
													<td><label class="col-md-4 control-label"><spring:message code="description"/></label></td>
													<td><c:out value="${transferbean.description}" /> </td>
												</tr>
												<tr>
													<td><label class="col-md-4 control-label"><spring:message code="amount"/></label></td>
													<td><c:out value="${transferbean.amount}" /> &#8364;</td>
												</tr>
												<tr>
													<td><label class="col-md-4 control-label"><spring:message code="fee"/></label></td>
													<td><c:out value="${transferbean.fee}" /> &#8364;</td>
												</tr>
												<tr>
													<td><label class="col-md-4 control-label"><spring:message code="date"/></label></td>
													<td><c:out value="${transferbean.date}" /> </td>
												</tr>									
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
