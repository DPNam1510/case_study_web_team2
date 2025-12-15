<%--
  Created by IntelliJ IDEA.
  User: dpnbh
  Date: 14/12/2025
  Time: 4:24 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HOSPITAL</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Team 2</a>
<c:redirect url="/view/home.jsp"/>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Team 2</a>
</body>
</html>
