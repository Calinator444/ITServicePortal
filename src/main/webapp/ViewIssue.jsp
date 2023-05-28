<%@ taglib prefix="c" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: calin
  Date: 27/05/2023
  Time: 5:02 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Issue</title>
</head>
<body>
  <h1><c:property value="#session.Issue.title"></c:property></h1>

  <p>
    <c:property value="#session.Issue.issueDescript"></c:property>
      
  </p>


    <c:form action="LeaveComment.action">
        <c:textarea name="commentBody" label="Leave a comment"></c:textarea>
        <c:submit></c:submit>
    </c:form>
</body>
</html>
