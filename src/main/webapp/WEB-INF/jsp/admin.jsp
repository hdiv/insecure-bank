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
							<i class="fa fa-university fa-logo"></i> <span class="logo-text">Insecure
								Bank </span>
						</div>
					</div>
					<div class="col-md-10 col-sm-8 col-xs-5">
						<div class="row">
							<div class="col-md-4 col-sm-4"></div>
							<div class="col-md-8 col-sm-8">
								<div class="top-bar-right pull-right">
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
												<li><a href="#"> <i
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
								<spring:url value="/dashboard" var="index" />
								<spring:url value="/activity" var="activity" />

								<li><a href="${index}"><i
										class="icon ion-ios7-speedometer"></i><span class="text">Dashboard</span></a></li>
								<li><a href="${activity}"><i class="icon ion-clipboard"></i><span
										class="text">Accounts activity</span></a></li>
								<li class="has-submenu"><a href="#" class="submenu-toggle"><i
										class="icon ion-android-note"></i><span class="text">Transfers</span></a>
									<ul class="list-unstyled sub-menu collapse">
										<spring:url var="transfer" value="/transfer"></spring:url>
										<li><a href="${transfer}"><span class="text">Make
													a transfer</span></a></li>
									</ul></li>
								<spring:url var="admin" value="/admin"></spring:url>
								<li class="active"><a href="${admin}"><i
										class="icon ion-android-contact"></i><span class="text">Admin</span></a></li>
							</ul>
						</nav>
					</div>
				</div>
				<div class="col-lg-10 col-right ">
					<div class="right-content">
						<div class="main-content">
							<div class="primary-content">
								<div class="heading clearfix">
									<h2>ADMIN PANEL</h2>
									<ul class="breadcrumb pull-left">
										<li><i class="icon ion-home"></i><a href="${index}">Home</a></li>
										<li><a href="#">Admin</a></li>
									</ul>
								</div>
								<!-- RESPONSIVE TABLE -->
								<div class="widget">
									<div class="widget-header clearfix">
										<h3>
											<i class="fa fa-list-alt"></i> <span>Bank Users</span>
										</h3>
									</div>
									<div class="widget-content">
										<div class="table-responsive">
											<table class="table table-condensed">
												<thead>
													<tr>
														<th>Username</th>
														<th>Name</th>
														<th>Surname</th>
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

table th, table td {
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

.top-bar .logged-user {
	padding-top: 3px;
}
</style>
<%@ include file="/WEB-INF/jsp/template/footer.jsp"%>
