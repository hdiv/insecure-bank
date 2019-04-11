<%@ include file="/WEB-INF/jsp/template/tags.jsp"%>
<div class="col-lg-2 col-left">
	<!-- LEFT CONTENT -->
	<div class="left-content">
		<nav id="main-nav" class="main-nav">
			<h3><spring:message code="mainUC"/></h3>
			<ul class="main-menu">
				<spring:url value="/dashboard" var="index" />
				<spring:url value="/activity" var="activity" />

				<c:set var="isDashboard" value="${fn:contains(pageContext.request.requestURI, 'dashboard')}"/>
				<c:set var="isActivity" value="${fn:contains(pageContext.request.requestURI, 'ctivity')}"/>
				<c:set var="isTransfer" value="${fn:contains(pageContext.request.requestURI, 'ransfer')}"/>
				<c:set var="isAdmin" value="${fn:contains(pageContext.request.requestURI, 'admin')}"/>

				<li class="${isDashboard? 'active' : ''}">
					<a href="${index}"><i class="icon ion-ios7-speedometer"></i><span class="text"><spring:message code="dashboard"/></span></a>
				</li>
				<li class="${isActivity? 'active' : ''}">
					<a href="${activity}"><i class="icon ion-clipboard"></i><span class="text"><spring:message code="accountsActivity"/></span></a>
				</li>
				<li class="has-submenu">
					<a href="#" class="submenu-toggle"><i class="icon ion-android-note"></i><span class="text"><spring:message code="transfers"/></span></a>
					<ul class="list-unstyled sub-menu ${not isTransfer? 'collapse' : ''}">
						<spring:url var="transfer" value="/transfer"></spring:url>
						<li class="${isTransfer? 'active' : ''}">
							<a href="${transfer}"><span class="text"><spring:message code="makeATransfer"/></span></a>
						</li>
					</ul>
				</li>
				<c:if test="${isAdmin}">
				<spring:url var="admin" value="/admin"></spring:url>
					<li class="${isAdmin? 'active' : ''}">
						<a href="${admin}"><i class="icon ion-android-contact"></i><span class="text">Admin</span></a>
					</li>
				</c:if>
			</ul>
		</nav>
	</div>
</div>