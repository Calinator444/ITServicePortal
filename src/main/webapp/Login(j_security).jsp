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
  <!--removes login error-->

</head>
<body>
  <h1>Login</h1>
  <!--maybe use struts tags instead-->
  <form action="j_security_check" method="POST">
    <input type="text" name="j_username"/>
    <input type="password" name="j_password"/>
    <input type="submit" value="Login"/>
  </form>
</body>
</html>
