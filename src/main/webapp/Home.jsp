<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
  Created by IntelliJ IDEA.
  User: calin
  Date: 17/05/2023
  Time: 11:43 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <script>


        const filterIssues = ()=>{
            let tagId = document.querySelector("#category").value;
            let subTagId = document.querySelector("#subcategory"+tagId).value
            let data = new URLSearchParams();
            data.append("subTagId", subTagId);
            data.append("tagId", tagId);
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
</head>
<body>
<h1>Welcome to IT Services portal!</h1>
<nav>
    <ul>



        <li><a href="./Home.jsp">Home</a></li>
        <li><a href="#Kowledge Base">Kowledge Base</a></li>
        <li><a href="./SubmitIssue.jsp">Submit Issue</a></li>
        <s:if test='%{#session.User.role == "ITManager"}'>
            <li><a href="/ITServicesPortal/ITManagementView.action">IT Manager Resources</a></li>
        </s:if>
        <li><a href="./IssueReporting.jsp">Submit Issue</a></li>
        <li><s:form action="search"><s:textfield/><s:submit value="Search"/></s:form></li>
        <li><s:form action="toLogin"><s:submit value="Login"/></s:form></li>
        <li><s:form action="./Logout.jsp"><s:submit value="Logout"/></s:form></li>
    </ul>
</nav>

    <h1>Issues</h1>
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

</body>
</html>
