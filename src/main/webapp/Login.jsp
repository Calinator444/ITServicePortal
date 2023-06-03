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
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="style.css">
</head>
<body class="loginPage">
  <h1>Login</h1>
  <div class = "loginFormWrapper">
      <h2>Login to your account</h2>
      <c:form action="LoginAction.action">
          <c:textfield name="username" label="Username" class="loginInput"></c:textfield>

          <c:password name="password" label="Password" showPassword="false"></c:password>
          <c:submit class="loginSubmit"/>
      </c:form>
  </div>
</body>
</html>
