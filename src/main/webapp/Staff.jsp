<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>IT Staff Dashboard</title>
    <script>
        const filterIssues = ()=>{
            let tagId = document.querySelector("#category").value;
            let subTagId = document.querySelector("#subcategory"+tagId).value
            let userTagId = document.querySelector("#tag-list").value;
            let data = new URLSearchParams();
            data.append("subTagId", subTagId);
            data.append("tagId", tagId);
            data.append("userTagid", userTagId);
            fetch("/ITServicesPortal/FilterIssues", {body: data, method: "post"}).then((res)=>{
                if(res.redirected)
                    window.location.href= res.url;
            })

        }
        window.onload = (e)=> {
            //setting up some event handlers
            document.querySelector("#category").onchange = (e) => {
                document.querySelector("#filterBtn").style="display: inline-block;"
                console.log("change event fired");
                document.querySelector("#subcategory1-wrapper").style = "display:none;";
                document.querySelector("#subcategory2-wrapper").style = "display:none;";
                document.querySelector("#subcategory3-wrapper").style = "display:none;";
                document.querySelector("#subcategory4-wrapper").style = "display:none;";
                document.querySelector("#subcategory5-wrapper").style = "display:none;";
                let selectedValue = e.target.value;
                console.log(selectedValue);



                document.querySelector("#subcategory"+selectedValue+"-wrapper").style = "display:inline-block;"
            }
        }
    </script>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h1>Welcome, ${User.firstName} ${User.lastName}!</h1>
<nav>
    <ul class="navigatonBar">
        <li><a href="./Home.jsp">Home</a></li>
        <li><a href="#Kowledge Base">Kowledge Base</a></li>
        <li><a href="./IssueReporting.jsp">Submit Issue</a></li>
        <li class="searchLi"><s:form action="search"><s:textfield name="search" class="searchField"/><s:submit value="Search" class="searchButton"/></s:form></li>
        <li><s:form action="toLogin"><s:submit value="Login" class="loginButton"/></s:form></li>
        <li><s:form action="./Logout.jsp"><s:submit value="Logout" class="logoutButton"/></s:form></li>
    </ul>
</nav>

<h2>Filtering</h2>
<label for="category">Category</label>
<select id="category">
    <option value="0">Select category</option>
    <option value="1">Network</option>
    <option value="2">Software</option>
    <option value="3">Hardware</option>
    <option value="4">Email</option>
    <option value="5">Account</option>
</select>

<div id="subcategory1-wrapper" style="display:none;">
    <label for="subcategory1">Subcategory</label>
    <select id="subcategory1">
        <option value="1">can't connect</option>
        <option value="2">speed</option>
        <option value="3">constant dropouts</option>
    </select>
</div>
<div id="subcategory2-wrapper" style="display:none;">
    <label for="subcategory2">Subcategory</label>
    <select id="subcategory2">
        <option value="4">slow to load</option>
        <option value="5">won't load at all</option>
    </select>
</div>
<div id="subcategory3-wrapper" style="display:none;">
    <label for="subcategory3">Subcategory</label>
    <select id="subcategory3">
        <option value="6">computer won't turn on</option>
        <option value="7">computer blue screens</option>
        <option value="8">disk drive</option>
        <option value="9">peripherals</option>
    </select>
</div>
<div id="subcategory4-wrapper" style="display:none;">
    <label for="subcategory4">Subcategory</label>
    <select id="subcategory4">
        <option value="10">can't send</option>
        <option value="11">can't receive</option>
        <option value="12">spam/phishing</option>
    </select>
</div>
<div id="subcategory5-wrapper" style="display:none;">
    <label for="subcategory5">Subcategory</label>
    <select id="subcategory5">
        <option value="13">password reset</option>
        <option value="14">wrong details</option>
    </select>
</div>


<button id="filterBtn" style="display: none" onclick="filterIssues()">Apply Filter</button>
<ul>
    <s:iterator value="#session.Issues" var="issue">
        <li>
            <a href="/ITServicesPortal/ViewIssue.action?title=${issue.title}">
                <s:property value="#issue.title"/>
            </a>
        </li>
    </s:iterator>
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