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
									<h2><spring:message code="creditCardActivityUC"/> </h2>
									<ul class="breadcrumb pull-left">
										<li><i class="icon ion-home"></i><a href="${index}"><spring:message code="home"/></a></li>
										<li><a href="#"><spring:message code="creditCardActivity"/></a></li>
									</ul>
								</div>
																
								<div class="widget">
									<div class="widget-header clearfix">
										<h3><i class="icon ion-clipboard"></i> <span><spring:message code="activitySummaryUC"/> | <c:out value="${actualCreditCardNumber}"></c:out></span></h3>
									</div>
									<div class="widget-content">
										<table class="table table-bordered  colored-header">
											<thead>
												<tr><th><spring:message code="date"/></th><th><spring:message code="description"/></th><th><spring:message code="charge"/></th></tr>
											</thead>
											<tbody>
												<tr>
													<td>14-04-2017</td>
													<td>Amazon Inc.</td>
													<td>120.30 EUR</td>		
												</tr>
												<tr>
													<td>13-04-2017</td>
													<td>Vodafone</td>
													<td>20.10 EUR</td>		
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
