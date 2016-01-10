<div id="wrapper">
    <h1>Hello world!</h1>
    <p>The time on the server is ${serverTime}.</p>
    <form:form action="${pageContext.request.contextPath}/login" method="get">
    	<input type="hidden" name="redirectTo" value="demo" />
    	<input type="hidden" name="sign" value="c5f2def24386f06542af8fdd01e75e58695e061be27947d30cbb60be1e3d5331" />
    	<p><input type="submit" value="go to demo page" /></p>
    	
    </form:form>
</div>
