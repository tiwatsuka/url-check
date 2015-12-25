<div id="wrapper">
    <h1>Hello world!</h1>
    <p>The time on the server is ${serverTime}.</p>
    <form:form action="${pageContext.request.contextPath}/login" method="get">
    	<input type="hidden" name="redirectTo" value="/demo" />
    	<input type="hidden" name="sign" value="3934d13019daf233ff6f954c136cd1face6e8a8f0da5d95a7c5f0520db228491" />
    	<p><input type="submit" value="go to demo page" /></p>
    	
    </form:form>
</div>
