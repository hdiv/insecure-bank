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
						<div class="main-content">
							<div class="primary-content">
								<div class="heading clearfix">
									<h2><spring:message code="adminPanelUC"/></h2>
									<ul class="breadcrumb pull-left">
										<li><i class="icon ion-home"></i><a href="${index}"><spring:message code="home"/></a></li>
										<li><a href="#"><spring:message code="admin"/></a></li>
									</ul>
								</div>
								<!-- RESPONSIVE TABLE -->
								<div class="widget">
									<div class="widget-header clearfix">
										<h3>
											<i class="fa fa-list-alt"></i> <span><spring:message code="bankUsers"/></span>
										</h3>
									</div>
									<div class="widget-content">
										<div class="table-responsive">
											<table class="table table-condensed">
												<thead>
													<tr>
														<th><spring:message code="username"/></th>
														<th><spring:message code="name"/></th>
														<th><spring:message code="surname"/></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${accounts}" var="account">
														<tr>
															<td><c:out value="${account.username}" /></td>
															<td><c:out value="${account.name}" /></td>
															<td><c:out value="${account.surname}" /></td>
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
