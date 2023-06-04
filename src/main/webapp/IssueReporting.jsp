<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <s:form action="./SubmitIssue.action" onsubmit="return validation()">
    <s:textfield label="title" name="title"></s:textfield>
    <s:textarea label="description" name="issueDescript"></s:textarea>
    <s:textfield id="tags" placeolder="tag1;tag2;... etc" label="tags" name="tags"></s:textfield>
    <s:select value="#session.SelectedCategory" label="category" id="tagId" onchange="updateSubCategories()" name="tagId" list="#{ 1: 'Network',  2 :'Software', 3: 'Hardware', 4: 'Email', 5: 'Account'}">
    </s:select>
    <s:select list="#session.Subcategories" listKey="key" listValue="value" name="subTagId"></s:select>
    <s:submit value="Submit"/>
  </s:form>
</body>
</html>
