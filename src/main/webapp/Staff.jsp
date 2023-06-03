<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>IT Staff Dashboard</title>
</head>
<body>
<h1>Welcome, ${User.firstName} ${User.lastName}!</h1>
<nav>
    <ul class="navigatonBar">
        <li><a href="./Home.jsp">Home</a></li>
        <li><a href="#Kowledge Base">Kowledge Base</a></li>
        <s:if test='%{#session.User.role == "ITManager"}'>
            <li><a href="/ITServicesPortal/ITManagementView.action">IT Manager Resources</a></li>
        </s:if>
        <li><a href="./IssueReporting.jsp">Submit Issue</a></li>
        <li class="searchLi"><s:form action="search"><s:textfield name="search" class="searchField"/><s:submit value="Search" class="searchButton"/></s:form></li>
        <li><s:form action="toLogin"><s:submit value="Login" class="loginButton"/></s:form></li>
        <li><s:form action="./Logout.jsp"><s:submit value="Logout" class="logoutButton"/></s:form></li>
    </ul>
</nav>




<h2>Assigned Issues</h2>



<table>
    <tr>
        <th>Title</th>
        <th>Status</th>
        <!--<th>Category</th>-->
        <th>Created Date</th>
    </tr>
    <s:iterator value="#session.IssuesForStaff" var="issue">
        <tr>
            <td><a href="./ViewIssue.action?title=${issue.title}"><s:property value="#issue.title"></s:property></a></td>
            <td><s:property value="#issue.issueStatus"></s:property></td>
            <td><s:property value="#issue.dateTimeReport"></s:property></td>
        </tr>
    </s:iterator>
</ul>
</table>



<h2>All Issues</h2>
<table>
    <tr>
        <th>Title</th>
        <th>Status</th>
        <!--<th>Category</th>-->
        <th>Created Date</th>
    </tr>
    <c:forEach var="issue" items="${Issues}">
        <tr>
            <td>${issue.title}</td>
            <td>${issue.issueStatus}</td>
            <td>${issue.dateTimeReport}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>