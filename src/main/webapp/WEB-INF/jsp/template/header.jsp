<%@ include file="/WEB-INF/jsp/template/tags.jsp"%>
<!-- TOP BAR -->
<nav class="top-bar navbar-fixed-top" role="navigation">
	<div class="row">
		<div class="col-md-2 col-sm-4 col-xs-7">
			<a class="btn btn-link btn-off-canvas pull-left"><i class="icon ion-navicon"></i></a>
			<div class="logo pull-left">
				<i class="fa fa-university fa-logo"></i> <span class="logo-text"><spring:message code="insecureBank"/> </span>
			</div>
		</div>
		<div class="col-md-10 col-sm-8 col-xs-5">
			<div class="row">
				<div class="col-md-4 col-sm-4">
				</div>
				<div class="col-md-8 col-sm-8">
					<div class="top-bar-right pull-right">
						
						<!-- logged user and the menu -->
						<div class="logged-user">
							<div class="btn-group">
								<a href="#" class="btn btn-link dropdown-toggle" data-toggle="dropdown">
									<i class="icon ion-android-social-user"></i><span class="name"><c:out value="${account.name}"/> <c:out value="${account.surname}"/> <i class="icon ion-ios7-arrow-down"></i></span>
								</a>
								<ul class="dropdown-menu" role="menu">
									<li>
									<spring:url value="/dashboard/userDetail" var="userDetail" htmlEscape="true" >
						 				<spring:param name="username" value="${account.username}"></spring:param>
						 			</spring:url>												
										<a href="${userDetail}">
											<i class="icon ion-android-social-user"></i>
											<span class="text"><spring:message code="profile"/></span>
										</a>
									</li>
									<li>
										<a href="#">
											<i class="icon ion-android-settings"></i>
											<span class="text"><spring:message code="settings"/></span>
										</a>
									</li>
									<li><spring:url value="/j_spring_security_logout" var="logout" />											
										<a href="${logout}">
											<i class="icon ion-power"></i>
											<span class="text"><spring:message code="logout"/></span>
										</a>
									</li>
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