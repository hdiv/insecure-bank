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
							</ul>
						</nav>
					</div>
				</div>
				<div class="col-lg-10 col-right ">
					<div class="right-content">
						<div class="main-content">
							<div class="primary-content">
								<div class="heading clearfix">
									<h2>USER DETAIL</h2>
									<ul class="breadcrumb pull-left">
										<li><i class="icon ion-home"></i><a href="${index}">Home</a></li>
										<li><a href="#">User detail</a></li>
									</ul>
								</div>
							<div class="row">
								<div class="col-sm-9">
									<div class="col-sm-2">
										<spring:url value="/dashboard/userDetail/avatar" var="userAvatar" htmlEscape="true" >
							 				<spring:param name="image" value="${account.username}.png"></spring:param>
							 			</spring:url>
										<img id="userAvatarImage" src="${userAvatar}" class="img-thumbnail" alt="avatar" style="border:solid 1px grey;cursor:pointer;" />
										<form:form id="userAvatarForm" action="userDetail/avatar/update" method="POST" style="display:none" enctype="multipart/form-data">
											<input id="userAvatarInput" type="file" name="imageFile"/>
										</form:form>
									</div>
									<div class="col-sm-10">
										<dl class="dl-horizontal">
											<c:set var="now" value="<%=new java.util.Date()%>" />
											<dt>Current Date:</dt>
											<dd>${now}</dd>										
											<dt>Register Date:</dt>
											<dd>20-09-2014</dd>
											<dt>Username:</dt>
											<dd>${account.username}</dd>
											<dt>Name:</dt>
											<dd>${account.name}</dd>
											<dt>Surname:</dt>
											<dd>${account.surname}</dd>																				
											<dt>Account Type:</dt>
											<dd>Personal</dd>
											<dt>Account Status:</dt>
											<dd><span class="label label-success">ACTIVE</span></dd>
											<dt style="padding: 10px 0px 10px 0px;">Digital Certificate:</dt>
											<dd style="padding: 10px 0px 10px 0px;">
												<form:form method="POST" class="form-horizontal" role="form" commandName="account" action="userDetail/certificate">
													<form:input type="hidden" path="username" /> 
													<button type="submit">Download</button>
												</form:form>
											</dd>
											<dt style="padding: 10px 0px 10px 0px;">Malicious Certificate:</dt>
											<dd style="padding: 10px 0px 10px 0px;">
												<form:form method="POST" class="form-horizontal" role="form" commandName="accountMalicious" action="userDetail/maliciouscertificate">
													<form:input type="hidden" path="username" /> 
													<button type="submit">Download</button>
												</form:form>
											</dd>											
												<dt style="padding: 10px 0px 10px 0px;">Upload 
													new certificate:</dt>
												<dd style="padding: 10px 0px 10px 0px;">

													<form:form action="userDetail/newcertificate" method="POST"
														enctype="multipart/form-data">
														<input id="file" type="file" name="file" />

														<button type="submit" style="margin-top: 5px;">Upload</button>
													</form:form>
												</dd>

												<dt>Credit cards:</dt>
											<br>
											<dd>
												<div class="table-responsive">
												<table class="table table-condensed">
													<thead>
														<tr>
															<th>Cash Account</th>
															<th>Description</th>										
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${creditAccounts}" var="credit">
															<tr>
																<td>
                                                                  <c:if test="${fn:contains(credit.description, 'Visa')}">
                                                                    <spring:url value="/dashboard/userDetail/creditCardImage" var="cardIcon" htmlEscape="true" >
                                                                   		<spring:param name="url" value="classpath:creditCards/visa.png"></spring:param>
                                                             		</spring:url>
                                                             	  </c:if>
                                                             	  <c:if test="${fn:contains(credit.description, 'AmEx')}">
                                                             	    <spring:url value="/dashboard/userDetail/creditCardImage" var="cardIcon" htmlEscape="true" >
                                                                      <spring:param name="url" value="classpath:creditCards/amex.png"></spring:param>
                                                                    </spring:url>
                                                             	  </c:if>
                                                             	  <c:if test="${fn:contains(credit.description, 'Master')}">
                                                                    <spring:url value="/dashboard/userDetail/creditCardImage" var="cardIcon" htmlEscape="true" >
                                                                      <spring:param name="url" value="classpath:creditCards/mastercard.png"></spring:param>
                                                                    </spring:url>
                                                                  </c:if>
										                           <img id="card-ico" src="${cardIcon}" class="img-thumbnail" alt="card" style="border:solid 1px grey;cursor:pointer;" />
																<c:out value="${credit.number}"/></td>
																<td><c:out value="${credit.description}"/></td>																		
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
											</dd>
										</dl>
									</div>
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
		
												
	<script>
		$(function(){
			$('#userAvatarImage').click(function(){
				$('#userAvatarInput').click();
			});
			$('#userAvatarInput').change(function(){
				$('#userAvatarForm').submit();
			});
		});
	</script>
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

