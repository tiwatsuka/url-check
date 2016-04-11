<div id="wrapper">
    <h1>Hello world!</h1>
    <p>The time on the server is ${serverTime}.</p>
    <form:form action="${pageContext.request.contextPath}/login" method="get">
        <mysec:redirector targetUrl="demo"/>
    	<p><input type="submit" value="go to demo page" /></p>
    </form:form>
    <form:form action="${pageContext.request.contextPath}/login"  method="get">
    	<p><input type="submit" value="go to demo2 page" /></p>
    </form:form>
    <form:form action="${pageContext.request.contextPath}/login"  method="get">
    	<mysec:redirector targetUrl="demo3"/>
    	<p><input type="submit" value="go to demo3 page" /></p>
    </form:form>
</div>
