<%@ include file="/WEB-INF/jsp/template/tags.jsp"%>
<%@ include file="/WEB-INF/jsp/template/header.jsp"%>

<body class="static-tables">

	<!-- WRAPPER -->
	<div class="wrapper">
		<div class="container-fluid">
			<!-- TOP BAR -->
			<nav class="top-bar navbar-fixed-top" role="navigation">
				<div class="row">
					<div class="col-md-2 col-sm-4 col-xs-7">
						<a class="btn btn-link btn-off-canvas pull-left"><i
							class="icon ion-navicon"></i></a>
						<div class="logo pull-left">
							<i class="fa fa-university fa-logo"></i> <span class="logo-text">Insecure Bank </span>
						</div>
					</div>
					<div class="col-md-10 col-sm-8 col-xs-5">
						<div class="row">
							<div class="col-md-4 col-sm-4"></div>
							<div class="col-md-8 col-sm-8">
								<div class="top-bar-right pull-right">

									<!-- logged user and the menu -->
									<div class="logged-user">
										<div class="btn-group">
											<a href="#" class="btn btn-link dropdown-toggle"
												data-toggle="dropdown"> <i
												class="icon ion-android-social-user"></i><span class="name"><c:out
														value="${account.name}" /> <c:out
														value="${account.surname}" /> <i
													class="icon ion-ios7-arrow-down"></i></span>
											</a>
											<ul class="dropdown-menu" role="menu">
												<li><spring:url value="/dashboard/userDetail" var="userDetail" htmlEscape="true" >
									 				<spring:param name="username" value="${account.username}"></spring:param>
									 			</spring:url>												
													<a href="${userDetail}"> <i
														class="icon ion-android-social-user"></i> <span
														class="text">Profile</span>
												</a></li>
												<li><a href="#"> <i
														class="icon ion-android-settings"></i> <span class="text">Settings</span>
												</a></li>
												<li><spring:url value="/j_spring_security_logout"
														var="logout" /> <a href="${logout}"> <i
														class="icon ion-power"></i> <span class="text">Logout</span>
												</a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</nav>
			<!-- END TOP BAR -->
			<div class="row">
				<div class="col-lg-2 col-left">
					<!-- LEFT CONTENT -->
					<div class="left-content">
						<nav id="main-nav" class="main-nav">

							<h3>MAIN</h3>
							<ul class="main-menu">
							<spring:url value="/activity" var="activity" />
							<spring:url value="/dashboard" var="index" />
						
								<li><a href="${index}"><i class="icon ion-ios7-speedometer"></i><span class="text">Dashboard</span></a></li>
								<li><a href="${activity}"><i class="icon ion-clipboard"></i><span class="text">Accounts activity</span></a></li>	
								<li class="has-submenu"><a href="#" class="submenu-toggle"><i
										class="icon ion-android-note"></i><span class="text">Transfers</span></a>
									<ul class="list-unstyled sub-menu">
										<spring:url var="transfer" value="/transfer"></spring:url>
										<li class="active"><a href="${transfer}"><span
												class="text">Make a transfer</span></a></li>
									</ul></li>
							</ul>
						</nav>
					</div>
				</div>
				<div class="col-lg-10 col-right ">
					<div class="right-content">
						<!-- MAIN CONTENT -->
						<div class="main-content">
							<div class="primary-content">
								<div class="heading clearfix">
									<h2>MAKE A TRANSFER</h2>
									<ul class="breadcrumb pull-left">
										<li><i class="icon ion-home"></i><a href="${index}">Home</a></li>
										<spring:url value="/transfer" var="transfer" />

										<li><a href="${transfer}">Transfers</a></li>
										<li><a href="${transfer}">Make a transfer</a></li>
									</ul>
								</div>
								<!-- RESPONSIVE TABLE -->
								<div class="widget">
									<div class="widget-header clearfix">
										<h3>
											<i class="fa fa-list-alt"></i> <span>NEW TRANSFER</span>
										</h3>
									</div>
									<div class="widget-content">										
    									<form:form method="POST" class="form-horizontal" role="form" commandName="transfer" action="transfer">								
											<spring:bind path="fromAccount">											
											<div class="form-group">
												<label class="col-md-2 control-label">From Account</label>
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
												<label class="col-md-2 control-label">Send To</label>
												<div class="col-md-10">
													<form:input path="toAccount"  type="text" class="form-control" required="required"/>
												</div>
											</div>
											</spring:bind>

											<spring:bind path="description">										
											<div class="form-group">
												<label class="col-md-2 control-label">Description</label>
												<div class="col-md-10">
													<form:input path="description" type="text" class="form-control" required="required"/>
													<form:errors path="description" cssClass="text-danger" />
												</div>
											</div>
											</spring:bind>
											
											<spring:bind path="amount">										
											<div class="form-group ${error eq true  ? 'has-error has-feedback' : ''}">
												<label class="col-sm-2 control-label">Amount</label>																					
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
													 <div class="pull-right"><button type="submit" class="btn btn-primary">Send</button></div>
												</div>
											</div>
												
										 </form:form>																											
										</div>		
									</div>
								</div>
							</div>
						</div>
						<%@ include file="/WEB-INF/jsp/template/bottom.jsp"%>
					</div>
					<!-- END MAIN CONTENT -->
				</div>
			</div>

		</div>
		<div class="left-content-bg col-md-2"></div>
		<!-- END WRAPPER -->

		<!-- Javascript -->
		<script
			src="${pageContext.request.contextPath}/resources/js/jquery/jquery-2.1.0.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/bootstrap/bootstrap.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/plugins/stat/jquery-easypiechart/jquery.easypiechart.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/plugins/bootstrap-multiselect/bootstrap-multiselect.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/queen-common.js"></script>
</body>
<style>
table {
	table-layout: fixed;
}

btn-align-center {
	text-align: right;
}
textarea {
    resize:none;
}

table th,table td {
	overflow: hidden;
}

.fa-logo {
	color: #fff;
	font-size: 18px;
}

.logo-text {
	color: #fff;
	font-family: 'Oswald';
	font-size: 16px;	
}
</style>

<%@ include file="/WEB-INF/jsp/template/footer.jsp"%>
