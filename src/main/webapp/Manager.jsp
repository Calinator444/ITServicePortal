
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
    <link rel="stylesheet" href="style.css">
</head>
<script>
    const assignStaffMember = (id)=>{
        let form = document.querySelector("#issue-select"+id);
        let username = form.value;
        let data = new URLSearchParams();
        data.append("issueId", id);
        data.append("username",username);
        fetch("/ITServicesPortal/AssignStaffToIssue", {method: "POST", body: data}).then((res)=>{
            confirm("Issue was successfully assigned to "+username);
            window.location.href = "/ITServicesPortal/ITManagementView.action";
        })
    }
    const handleSelectChanged = (id)=>{
        let dropDown = document.querySelector("#issue-select"+id);
        let value = dropDown.value;
        let btn = document.querySelector("#issue-btn"+id);
        if(value == "select staff member")
            btn.disabled = true;
        else
            btn.disabled = false;
        console.log(`id: ${id} value: ${value}}`);
    }
</script>
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
    <h2>Unassigned Issues</h2>
    <ul>
        <s:iterator value="#session.NewIssues" var="issue">
            <li><s:property value="#issue.title"></s:property>
                <select id="issue-select${issue.issueId}" onchange="handleSelectChanged(${issue.issueId})">
                    <option>select staff member</option>

                    <s:iterator value="#session.ITStaff" var="staffMember">
                        <option value="${staffMember.username}"><s:property value="#staffMember.firstName"></s:property> <s:property value="#staffMember.lastName"></s:property></option>
                    </s:iterator>
                </select><button disabled="true" onclick="assignStaffMember(${issue.issueId})" id="issue-btn${issue.issueId}">assign staff member
                </button>
            </li>
        </s:iterator>
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

<h1>Statistics</h1>

<h2> Number of issues in each category</h2>

<table>
    <tr>
        <th>Category</th>
        <th>Number of Issues</th>
    </tr>
    <s:iterator value="#session.Category" var="categoryNumber">
        <tr>
            <td><s:property value="#categoryNumber.categoryName"/></td>
            <td><s:property value="#categoryNumber.count"/></td>
        </tr>
    </s:iterator>
</table>


<h2> Number of issues in each status</h2>

<table>
    <tr>
        <th>Issues</th>
        <th>Number of Issues</th>
    </tr>
    <s:iterator value="#session.status" var="statusNumber">
        <tr>
            <td><s:property value="#statusNumber.status"/></td>
            <td><s:property value="#statusNumber.statusCount"/></td>
        </tr>
    </s:iterator>
</table>

<h2> Number of issues that each IT staff is working on</h2>

<table>
    <tr>
        <th>Issues</th>
        <th>Number of Issues</th>
    </tr>
    <s:iterator value="#session.fixer" var="fixerNumber">
        <s:if test="#fixerNumber.fixer != null">
           <tr>
               <td><s:property value="#fixerNumber.fixer"/></td>
               <td><s:property value="#fixerNumber.fixerCount"/></td>
           </tr>
        </s:if>
    </s:iterator>
</table>


<h2> Average time for n issue to get resolved in the last 30 days</h2>
<table>
    <tr>
        <th>Issues</th>
    </tr>
    <s:iterator value="#session.averageOver30Days" var="averageBeanList">
        <tr>
            <td><s:property value="#averageBeanList.averageOver30Dayss"/></td>
        </tr>
    </s:iterator>
</table>


<h2> The top 5 longest un-resolved issues in the current system</h2>
<table>
    <tr>
        <th>Issues</th>
        <th>Date Created</th>
    </tr>
<s:iterator value="#session.topFiveName" var="topFive">
    <tr>
        <td><s:property value="#topFive.name"/></td>
        <td><s:property value="#topFive.beginDate"/></td>
    </tr>
</s:iterator>
</table>

</body>
</html>
