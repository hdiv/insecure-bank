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
									<h2><spring:message code="userDetailUC"/></h2>
									<ul class="breadcrumb pull-left">
										<li><i class="icon ion-home"></i><a href="${index}"><spring:message code="home"/></a></li>
										<li><a href="#"><spring:message code="userDetail"/></a></li>
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
											<dt><spring:message code="currentDate"/>:</dt>
											<dd>${now}</dd>										
											<dt><spring:message code="registerDate"/>:</dt>
											<dd>20-09-2014</dd>
											<dt><spring:message code="username"/>:</dt>
											<dd>${account.username}</dd>
											<dt><spring:message code="name"/>:</dt>
											<dd><c:out value="${account.name}"/></dd>
											<dt><spring:message code="surname"/>:</dt>
											<dd><c:out value="${account.surname}"/></dd>																				
											<dt><spring:message code="accountType"/>:</dt>
											<dd>Personal</dd>
											<dt><spring:message code="accountStatus"/>:</dt>
											<dd><span class="label label-success"><spring:message code="activeUC"/></span></dd>
											<dt style="padding: 10px 0px 10px 0px;"><spring:message code="digitalCertificate"/>:</dt>
											<dd style="padding: 10px 0px 10px 0px;">
												<form:form method="POST" class="form-horizontal" role="form" commandName="account" action="userDetail/certificate">
													<form:input type="hidden" path="username" /> 
													<button type="submit"><spring:message code="download"/></button>
												</form:form>
											</dd>
											<dt style="padding: 10px 0px 10px 0px;"><spring:message code="maliciousCertificate"/>:</dt>
											<dd style="padding: 10px 0px 10px 0px;">
												<form:form method="POST" class="form-horizontal" role="form" commandName="accountMalicious" action="userDetail/maliciouscertificate">
													<form:input type="hidden" path="username" /> 
													<button type="submit"><spring:message code="download"/></button>
												</form:form>
											</dd>											
												<dt style="padding: 10px 0px 10px 0px;"><spring:message code="uploadNewCertificate"/>:</dt>
												<dd style="padding: 10px 0px 10px 0px;">

													<form:form action="userDetail/newcertificate" method="POST"
														enctype="multipart/form-data">
														<input id="file" type="file" name="file" />

														<button type="submit" style="margin-top: 5px;"><spring:message code="upload"/></button>
													</form:form>
												</dd>

												<dt><spring:message code="creditCards"/>:</dt>
											<br>
											<dd>
												<div class="table-responsive">
												<table class="table table-condensed">
													<thead>
														<tr>
															<th><spring:message code="cashAccount"/></th>
															<th><spring:message code="description"/></th>										
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${creditAccounts}" var="credit">
															<tr>
																<td>
																<c:if test="${fn:contains(credit.description, 'Visa')}">
																   <i class="fa fa-cc-visa"></i>
																</c:if>
																<c:if test="${fn:contains(credit.description, 'AmEx')}">
																   <i class="fa fa-cc-amex"></i>
																</c:if>
																<c:if test="${fn:contains(credit.description, 'Master')}">
																	<i class="fa fa-cc-mastercard"></i>
																</c:if>																														
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
				</div>
				<!-- END MAIN CONTENT -->
			</div>
		</div>
	</div>
	<div class="left-content-bg col-md-2"></div>
	<!-- END WRAPPER -->

	<%@ include file="/WEB-INF/jsp/template/javascript.jsp"%>
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

<%@ include file="/WEB-INF/jsp/template/footer.jsp"%>