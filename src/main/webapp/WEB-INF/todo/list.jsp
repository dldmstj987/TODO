<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Todo List</h1>
<h2>${logInInfo}</h2>
<h2>${loginInfo.mname}</h2>
<ul>
  <c:forEach var="dto" items="${dtoList}">
    <li>
        <span><a href="/todo/read?tno=${dto.tno}">${dto.tno}</a></span>
        <span>${dto.title}</span>
        <span>${dto.dueDate}</span>
        <span>${dto.finished ? "DONE" : "NOT YET"}</span>
    </li>
  </c:forEach>
</ul>
<div><a href="./register.jsp">REGISTER</a></div>
<div><a href="/logout">LOGOUT</a></div>
</body>
</html>
