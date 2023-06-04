<%--
  Created by IntelliJ IDEA.
  User: ryanb
  Date: 4/06/2023
  Time: 7:19 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<nav>
  <ul class="navigatonBar">
    <li><a href="./Home.jsp">Home</a></li>
    <li><a href="/KnowledgeBase.jsp">Knowledge Base</a></li>
    <s:if test='%{#session.User.role == "ITManager"}'>
      <li><a href="/ITServicesPortal/ITManagementView.action">IT Manager Resources</a></li>
    </s:if>
    <li><a href="./IssueReporting.jsp">Submit Issue</a></li>
    <li class="searchLi"><s:form action="search"><s:textfield name="search" class="searchField"/><s:submit value="Search" class="searchButton"/></s:form></li>
    <li><s:form action="./Login.jsp"><s:submit value="Login" class="loginButton"/></s:form></li>
    <li><s:form action="./Logout.jsp"><s:submit value="Logout" class="logoutButton"/></s:form></li>
  </ul>
</nav>

<h1>KnowledgeBase Articles</h1>
<ul>
  <s:iterator value="#session.Issues" var="issue">
    <s:if test="#issue.issueStatus == 'completed'">
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
