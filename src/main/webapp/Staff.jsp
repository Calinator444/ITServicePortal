<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>IT Staff Dashboard</title>
    <nav>
        <ul>
            <li><a href="#Home">Home</a></li>
            <li><a href="#Kowledge Base">Kowledge Base</a></li>
            <li><a href="#Submit Issue">Submit Issue</a></li>
            <li><form><input type = "text"><input type = "submit" value = "search"></form></li>
            <li><form><input type="hidden" value="login"><input type="submit" value = "Login"></form></li>
            <li><form><input type="hidden" value="logout"><input type="submit" value = "Logout"></form></li>
        </ul>
    </nav>
</head>
<body>
<h1>Welcome, ${user.firstName} ${user.lastName}!</h1>

<h2>All Issues</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Status</th>
        <th>Category</th>
        <th>Created Date</th>
    </tr>
    <c:forEach var="issue" items="${allIssues}">
        <tr>
            <td>${issue.id}</td>
            <td>${issue.title}</td>
            <td>${issue.status}</td>
            <td>${issue.category}</td>
            <td>${issue.createdDate}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>