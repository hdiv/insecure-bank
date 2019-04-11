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
								<!-- RESPONSIVE TABLE -->
								<div class="widget">
									<div class="widget-header clearfix">
										<h3>
											<i class="fa fa-list-alt"></i> <span><spring:message code="newTransferUC"/></span>
										</h3>
									</div>
									<div class="widget-content">										
    									<form:form method="POST" class="form-horizontal" role="form" commandName="transfer" action="transfer">								
											<spring:bind path="fromAccount">											
											<div class="form-group">
												<label class="col-md-2 control-label"><spring:message code="fromAccount"/></label>
												<div class="col-md-10">
													<form:select path="fromAccount" class="form-control">
														<c:forEach items="${cashAccounts}" var="cash">												
															<form:option value="${cash.number}" label="${cash.number} (${cash.description}) " />
														</c:forEach>
													</form:select>
												</div>
											</div>
											</spring:bind>
											
											<spring:bind path="toAccount">										
											<div class="form-group">
												<label class="col-md-2 control-label"><spring:message code="sendTo"/></label>
												<div class="col-md-10">
													<form:input path="toAccount"  type="text" class="form-control" required="required"/>
												</div>
											</div>
											</spring:bind>

											<spring:bind path="description">										
											<div class="form-group">
												<label class="col-md-2 control-label"><spring:message code="description"/></label>
												<div class="col-md-10">
													<form:input path="description" type="text" class="form-control" required="required"/>
													<form:errors path="description" cssClass="text-danger" />
												</div>
											</div>
											</spring:bind>
											
											<spring:bind path="amount">										
											<div class="form-group ${error eq true  ? 'has-error has-feedback' : ''}">
												<label class="col-sm-2 control-label"><spring:message code="amount"/></label>																					
												<div class="col-sm-10">
												<div class="input-group">
													<form:input path="amount" type="text" class="form-control" />
													<span class="input-group-addon">&#8364;</span>
												</div>
												<form:errors path="amount" cssClass="text-danger" />																								
												</div>
											</div>
											</spring:bind>
																														
											<div class="form-group">
												<div class="col-sm-12">
													 <div class="pull-right"><button type="submit" class="btn btn-primary"><spring:message code="send"/></button></div>
												</div>
											</div>
												
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
