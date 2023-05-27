<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: calin
  Date: 17/05/2023
  Time: 11:43 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <nav>
        <ul>
            <li><a href="./Home.jsp">Home</a></li>
            <li><a href="#Kowledge Base">Kowledge Base</a></li>
            <li><a href="./SubmitIssue.jsp">Submit Issue</a></li>
            <li><s:form action="search"><s:textfield/><s:submit value="Search"/></s:form></li>
            <li><s:form action="toLogin"><s:submit value="Login"/></s:form></li>
            <li><s:form action="./Logout.jsp"><s:submit value="Logout"/></s:form></li>
        </ul>
    </nav>
</head>
<body>
<h1>Welcome to IT Services portal!</h1>
</body>
</html>
