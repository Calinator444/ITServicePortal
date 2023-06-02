<%@ taglib prefix="c" uri="/struts-tags" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: calin
  Date: 27/05/2023
  Time: 5:02 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
  .username {
    font-style: italic;
  }
</style>

<script>
  const testJavascript = ()=>{
    alert('Javascript is working');
  }


  const markIssue = ()=>{
    let status = document.querySelector("#mark-issue").value;
    let data = new URLSearchParams();
    data.append("status",status);
    fetch("/ITServicesPortal/MarkIssue", {method: "POST", body:data}).then((res)=>{
      alert("Issue status successfully updated to: "+status)
    })
  }
  const replyToComment = (commentId)=>{
    let item = "#reply"+commentId
    let body = document.querySelector(item).value;
    let data = new URLSearchParams();
    data.append("commentId", commentId);
    data.append("body", body)
    fetch("/ITServicesPortal/AddReply", {method: "POST", body: data}).then((res)=>{
      if(res.redirected)
      {
        window.location.href = res.url;
        console.log(res);
      }
    })
  }
</script>



<html>
<head>
    <title>View Issue</title>
</head>
<body>
  <h1><c:property value="#session.Issue.title"></c:property></h1>

  <p>
    <c:property value="#session.Issue.issueDescript"></c:property>



  </p>
  <label for="mark-issue">Mark Issue</label>
  <select id="mark-issue">
    <option>
      new
    </option>
    <option>
      waiting on third party
    </option>
    <option>
      completed
    </option>
    <option>
      not accepted
    </option>
    <option>
      resolved
    </option>
  </select>
  <button onclick="markIssue()">Mark Issue</button>
  </button>


  <c:form action="/AddComment" method="POST">
    <c:textarea name="body" label="Leave a comment"> </c:textarea>
    <c:submit></c:submit>
  </c:form>

  <p>
  IssueId: <c:property value="#session.Issue.issueId"></c:property>
  </p>
  <h3>Comments</h3>
  <ul>
  <c:iterator value="#session.Issue.comments" var="comment">
      <li>
        <span class="username"><c:property value="#comment.userUsername"/><br></span>

        <c:property value="#comment.commentText"/>


        <br>

        <textarea id="reply${comment.commentId}">

        </textarea>
        <button onclick="replyToComment(${comment.commentId})">Test button</button>
        <ul>
            <c:iterator value="#comment.replies" var="reply">
              <li>
              <c:property value="#reply.user"></c:property><br>
              <c:property value="#reply.body"></c:property>
              </li>
            </c:iterator>

        </ul>
    </li>
  </c:iterator>
  </ul>


</body>
</html>
