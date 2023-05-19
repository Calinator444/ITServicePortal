<%@ taglib prefix="c" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: calin
  Date: 16/05/2023
  Time: 3:22 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
  <h1>Login</h1>
  <c:form action="LoginAction.action">
    <c:textfield name="username" label="Username"></c:textfield>

    <c:password name="password" label="Password" showPassword="false"></c:password>
    <c:submit/>
  </c:form>
</body>
</html>
