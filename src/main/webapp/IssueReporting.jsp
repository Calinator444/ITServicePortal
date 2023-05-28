<%@ taglib prefix="c" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: calin
  Date: 27/05/2023
  Time: 11:53 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Report Issue</title>
</head>
<body>
  <h1>Report Issue</h1>

  <c:form action="SubmitIssue.action">
    <c:textfield label="title" name="title"></c:textfield>
    <c:textarea label="description" name="issueDescript"></c:textarea>
    <c:submit value="Submit"/>
  </c:form>
</body>
</html>
