<%@ include file="/WEB-INF/jsp/template/tags.jsp"%>

<div class="footer">
	<div class="row">
		<div class="col-lg-12 text-center">
			<p>
				<spring:url var="help" value="/help"></spring:url>
				<a class="btn btn-link" href="${help}" rel="nofollow">Help</a>				
			</p>
		</div>
	</div>
</div>
