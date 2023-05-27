<%--
  Created by IntelliJ IDEA.
  User: quang
  Date: 5/26/2023
  Time: 12:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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

</body>
</html>
