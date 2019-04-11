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
										<li><a href="${transfer}"><spring:message code="transfers"/></a></li>
										<li><a href="${transfer}"><spring:message code="makeATransfer"/></a></li>
									</ul>
								</div>
								<div class="widget-content">
									<h4><spring:message code="detailsConfirmationUC"/></h4>
									<table id="user" class="table table-bordered table-striped">
										<tbody>
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
												<td>${transferbean.description}</td>
											</tr>
											<tr>
												<td><label class="col-md-4 control-label"><spring:message code="amount"/></label></td>
												<td><c:out value="${transferbean.amount}" /> &#8364;</td>
											</tr>
											<tr>
												<td><label class="col-md-4 control-label"><spring:message code="fee"/></label></td>
												<td><c:out value="${transferbean.fee}" /> &#8364;</td>
											</tr>									
										</tbody>
									</table>
								</div>
								
								<!-- RESPONSIVE TABLE -->
								<div class="widget">
									<div class="widget-header clearfix">
										<h3>
											<i class="fa fa-list-alt"></i> <span>Find the number <c:out value="${confirmationValue}"/>  and enter the corresponding code:</span>
										</h3>
									</div>
									<div class="widget-content">										
    									<form:form method="POST" class="form-horizontal" role="form" commandName="operationConfirm" action="transfer/confirm">								
											<spring:bind path="code">											
											<div class="form-group">
												<label class="col-md-2 control-label"><spring:message code="code"/></label>
												<div class="col-md-1">
													<form:input path="code" type="text" class="form-control" maxlength="4"/>
												</div>
												<div class="col-md-6">
													<form:button type="submit" class="btn btn-primary" name="action" value="confirm"><spring:message code="confirm"/></form:button>												
													<form:button type="submit" class="btn btn-primary" name="action" value="cancel"><spring:message code="cancel"/></form:button>								
												</div>
											</div>
											</spring:bind>
											<input type="hidden" name="confirmationValue" value='<c:out value="${confirmationValue}"/>'/>
										 </form:form>																											
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
