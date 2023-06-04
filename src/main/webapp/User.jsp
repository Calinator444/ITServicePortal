<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Dashboard</title>
</head>
<body>
<h1>Welcome, ${user.firstName} ${user.lastName}!</h1>
<nav>
    <ul>
        <li><a href="./Home.jsp">Home</a></li>
        <li><a href="./KowledgeBase.jsp">Kowledge Base</a></li>
        <li><a href="./SubmitIssue.jsp">Submit Issue</a></li>
        <li><s:form action="search"><s:textfield/><s:submit value="Search"/></s:form></li>
        <li><s:form action="toLogin"><s:submit value="Login"/></s:form></li>
        <li class="searchLi"><s:form action="search"><s:textfield name="search" class="searchField"/><s:submit value="Search" class="searchButton"/></s:form></li>
        <li><s:form action="./Login.jsp"><s:submit value="Login" class="loginButton"/></s:form></li>
        <li><s:form action="./Logout.jsp"><s:submit value="Logout" class="logoutButton"/></s:form></li>
    </ul>
</nav>
<h2>My Issues</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Status</th>
        <th>Category</th>
        <th>Created Date</th>
    </tr>
    <c:forEach var="issue" items="${myIssues}">
        <c:if test="${issue.reporter == currentUser.username}">
        <tr>
            <td>${issue.id}</td>
            <td>${issue.title}</td>
            <td>${issue.status}</td>
            <td>${issue.category}</td>
            <td>${issue.createdDate}</td>
        </tr>
        </c:if>
    </c:forEach>
</table>

<h2>Knowledge Base</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Resolved Date</th>
    </tr>
    <c:forEach var="article" items="${knowledgeBase}">
        <tr>
            <td>${article.id}</td>
            <td>${article.title}</td>
            <td>${article.resolvedDate}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>