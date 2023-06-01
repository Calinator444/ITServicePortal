<%--
  Created by IntelliJ IDEA.
  User: quang
  Date: 5/26/2023
  Time: 12:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
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
<h1>Issues In Progress</h1>
<ul>
    <s:iterator value="#session.Issues" var="issue">
        <s:if test="!#issue.dateTimeResolved">
        <li>
            <a href="/ITServicesPortal/ViewIssue.action?title=${issue.title}">
                <s:property value="#issue.title"/>
            </a>
        </li>
        </s:if>
    </s:iterator>
</ul>
</body>
</html>
