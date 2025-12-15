<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dpnbh
  Date: 14/12/2025
  Time: 2:57 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="/layout/library.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/login.css"/>">
</head>
<body>

<div class="bg-overlay"></div>

<div class="login-box">
    <img src="https://designercomvn.s3.ap-southeast-1.amazonaws.com/wp-content/uploads/2020/06/05231353/vinmec-logo.jpg"
         alt="Logo" class="logo">

    <!-- Hai nút chuyển form -->
    <div class="tab-buttons">
        <button id="btnSignIn" class="active">Sign In</button>
        <button id="btnRegister">Register</button>
    </div>

    <!-- FORM ĐĂNG NHẬP -->
    <form id="formSignIn" class="form active" action="<c:url value='/login'/>" method="post">
        <label for="email">Username or email</label>
        <input type="text" id="email" name="username" placeholder="example@gmail.com"
               value="<c:out value='${username}'/>">
        <c:if test="${not empty nameErr}">
            <div class="error-msg">${nameErr}</div>
        </c:if>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="********">
        <c:if test="${not empty passErr}">
            <div class="error-msg">${passErr}</div>
        </c:if>

        <c:if test="${not empty loginErr}">
            <div class="error-msg">${loginErr}</div>
        </c:if>

        <button type="submit" class="btn-login">Sign In</button>
    </form>

    <!-- FORM ĐĂNG KÝ -->
    <form id="formRegister" class="form" action="<c:url value='/register'/>" method="post">
        <label>Username</label>
        <input type="text" name="username" placeholder="username"
               value="<c:out value='${username}'/>">
        <c:if test="${not empty nameErr}">
            <div class="error-msg">${nameErr}</div>
        </c:if>
        <label>Password</label>
        <input type="password" name="password" placeholder="********">
        <c:if test="${not empty passErr}">
            <div class="error-msg">${passErr}</div>
        </c:if>
        <c:if test="${not empty registerErr}">
            <div class="error-msg">${registerErr}</div>
        </c:if>
        <c:if test="${param.mess == 'register_success'}">
            <div class="success-msg">Đăng ký thành công. Vui lòng đăng nhập!</div>
        </c:if>
        <!-- Account type mặc định -->
        <input type="hidden" name="role" value="CUSTOMER">
        <button type="submit" class="btn-login">Register</button>
    </form>

</div>
<script src="<c:url value="/assets/js/login.js"/>"></script>
</body>
</html>
