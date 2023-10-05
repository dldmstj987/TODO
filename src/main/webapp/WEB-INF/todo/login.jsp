<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<c:if test="${param.result == 'error'}">
    <h1>로그인 에러</h1>
</c:if>
<form action="/login" method="post">
    <p>아이디: <input type="text" name="mid"></p>
    <p>비밀번호: <input type="text" name="mpw"></p>
    <p>자동 로그인: <input type="checkbox" name="auto"></p>
    <button type="submit">Login</button>
</form>
</body>
</html>
