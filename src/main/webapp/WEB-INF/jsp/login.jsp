<%@ include file="/WEB-INF/jsp/template/tags.jsp"%>
<%@ include file="/WEB-INF/jsp/template/head.jsp"%>

<body class="middle-content page-login">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-5 col-sm-offset-1 col-lg-4 col-lg-offset-2">
				<div class="content-box-bordered login-box box-with-help">
					<h1><spring:message code="logInToYourAccount"/> </h1>
					<c:if test="${param.authenticationFailure == true}">
						<div class="alert alert-warning fade in">
							<i class="icon ion-alert-circled"></i>
							<strong><spring:message code="invalidPass"/></strong> 
						</div>
					</c:if>
					<c:if test="${param.logout == true}">
						<div class="alert alert-warning fade in">
							<i class="icon ion-alert-circled"></i>
							<strong><spring:message code="successfullyLoggedOut"/>.</strong> 
						</div>
					</c:if>					
					<form action="" method="POST" accept-charset="utf-8" class="form-horizontal" role="form">
						<div class="form-group">
							<label for="inputEmail3b" class="control-label sr-only"><spring:message code="email"/></label>
							<div class="col-sm-12">
								<div class="input-group">
									<span class="input-group-addon"><i class="icon ion-person"></i></span>
									<input name="username" class="form-control" id="inputUsername" placeholder="Username" autofocus="autofocus">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3b" class="control-label sr-only"><spring:message code="password"/></label>
							<div class="col-sm-12">
								<div class="input-group">
									<span class="input-group-addon"><i class="icon ion-locked"></i></span>
									<input name="password" type="password" class="form-control" id="inputPassword" placeholder="Password">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-7">
								<button type="submit" class="btn btn-primary btn-block"><spring:message code="signIn"/></button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="col-sm-5 col-lg-4">
				<div class="login-copytext">
					<h2>Welcome to <i class="fa fa-university fa-logo"></i><span class="logo-text">Insecure Bank </span></h2>
					<p> Log in to access your checking, savings, credit cards, and mortgage accounts with Insecure Bank.</p>
					<h2>Online Payments</h2>
					<p>Using your Insecure Bank account, you can pay your bills like electricity, telephone, satellite television, mobile phone etc. You can also pay your insurance premium, credit card bills etc.</p>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/jsp/template/javascript.jsp"%>
</body>
<style>
.fa-logo {
	font-size: 23px !important;
}

.logo-text {
	font-family: 'Oswald';
	font-size: 19px !important;	
}
</style>	
<%@ include file="/WEB-INF/jsp/template/footer.jsp"%>
