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
    <ul>
        <li><a href="./Home.jsp">Home</a></li>
        <li><a href="#Kowledge Base">Kowledge Base</a></li>
        <li><a href="./SubmitIssue.jsp">Submit Issue</a></li>
        <li><s:form action="search"><s:textfield/><s:submit value="Search"/></s:form></li>
        <li><s:form action="toLogin"><s:submit value="Login"/></s:form></li>
        <li><s:form action="./Logout.jsp"><s:submit value="Logout"/></s:form></li>
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
            <td><s:property value="#issue.title"></s:property></td>
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