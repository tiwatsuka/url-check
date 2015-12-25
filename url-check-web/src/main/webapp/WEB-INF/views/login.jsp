<html>
<head>
<title>Login Form</title>
</head>
<body>
<h1>Login Form</h1>
<c:if test="${param.error}">
    <t:messagesPanel
        messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION"/>
</c:if>
<form:form action="${pageContext.request.contextPath}/authentication" method="post">
	<table>
		<tr>
			<td><label for="username">username</label></td>
    		<td><input type="text" id="username" name="username"></td>
    	</tr>
    	<tr>
    		<td><label for="password">password</label></td>
    		<td><input type="password" id="password" name="password"></td>
    	</tr>
    	<tr>
    		<td><input type="submit" value="Login"><td>
    	</tr>
    </table>
    <input type="hidden" name="redirectTo" value="${f:h(param.redirectTo)}" />
    <input type="hidden" name="sign" value="${f:h(param.sign)}" />
</form:form>
</body>
</html>