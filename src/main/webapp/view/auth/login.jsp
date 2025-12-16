<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <c:import url="/layout/library.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/login.css"/>">

    <!-- Font Awesome (nếu library.jsp chưa có thì giữ dòng này) -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        /* icon mắt */
        .pw-wrap{ position: relative; }
        .pw-wrap input{ padding-right: 42px; }
        .pw-eye{
            position:absolute;
            right:12px;
            top:50%;
            transform:translateY(-50%);
            cursor:pointer;
            opacity:.75;
        }
        .pw-eye:hover{ opacity:1; }

        /* thông báo thành công */
        .success-msg{
            margin-top:10px;
            padding:10px 12px;
            border-radius:8px;
            background:#d1e7dd;
            color:#0f5132;
            font-size:14px;
        }
    </style>
</head>
<body>

<div class="bg-overlay"></div>

<div class="login-box">
    <img src="https://designercomvn.s3.ap-southeast-1.amazonaws.com/wp-content/uploads/2020/06/05231353/vinmec-logo.jpg"
         alt="Logo" class="logo">

    <!-- Hai nút chuyển form -->
    <div class="tab-buttons">
        <button id="btnSignIn" class="${tab == 'register' ? '' : 'active'}">Sign In</button>
        <button id="btnRegister" class="${tab == 'register' ? 'active' : ''}">Register</button>
    </div>

    <!-- FORM ĐĂNG NHẬP -->
    <form id="formSignIn" class="form ${tab == 'register' ? '' : 'active'}"
          action="<c:url value='/login'/>" method="post">

        <label for="email">Username or email</label>
        <input type="text" id="email" name="username" placeholder="example@gmail.com"
               value="<c:out value='${username}'/>">
        <c:if test="${not empty nameErr}">
            <div class="error-msg">${nameErr}</div>
        </c:if>

        <label for="passwordLogin">Password</label>
        <div class="pw-wrap">
            <input type="password" id="passwordLogin" name="password" placeholder="********"/>
            <i class="fa-solid fa-eye pw-eye" onclick="togglePassword('passwordLogin', this)"></i>
        </div>

        <c:if test="${not empty passErr}">
            <div class="error-msg">${passErr}</div>
        </c:if>

        <c:if test="${not empty loginErr}">
            <div class="error-msg">${loginErr}</div>
        </c:if>

        <button type="submit" class="btn-login">Sign In</button>
    </form>

    <!-- FORM ĐĂNG KÝ -->
    <form id="formRegister" class="form ${tab == 'register' ? 'active' : ''}"
          action="<c:url value='/register'/>" method="post">

        <label>Username</label>
        <input type="text" name="username" placeholder="username"
               value="<c:out value='${username}'/>">
        <c:if test="${not empty nameErr}">
            <div class="error-msg">${nameErr}</div>
        </c:if>

        <label>Password</label>
        <div class="pw-wrap">
            <input type="password" id="passwordRegister" name="password" placeholder="********">
            <i class="fa-solid fa-eye pw-eye" onclick="togglePassword('passwordRegister', this)"></i>
        </div>

        <c:if test="${not empty passErr}">
            <div class="error-msg">${passErr}</div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="error-msg">${error}</div>
        </c:if>

        <!-- ✅ THÀNH CÔNG: đứng ở Register và hiện thông báo -->
        <c:if test="${not empty success}">
            <div class="success-msg">${success}</div>
        </c:if>

        <!-- Account type mặc định -->
        <input type="hidden" name="role" value="CUSTOMER">

        <button type="submit" class="btn-login">Register</button>
    </form>

</div>

<script src="<c:url value="/assets/js/login.js"/>"></script>

<script>
    function togglePassword(inputId, icon) {
        const input = document.getElementById(inputId);
        if (!input) return;

        if (input.type === "password") {
            input.type = "text";
            icon.classList.remove("fa-eye");
            icon.classList.add("fa-eye-slash");
        } else {
            input.type = "password";
            icon.classList.remove("fa-eye-slash");
            icon.classList.add("fa-eye");
        }
    }
</script>

</body>
</html>

<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: dpnbh--%>
<%--  Date: 14/12/2025--%>
<%--  Time: 2:57 CH--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Title</title>--%>
<%--    <c:import url="/layout/library.jsp"/>--%>
<%--    <link rel="stylesheet" href="<c:url value="/assets/css/login.css"/>">--%>
<%--</head>--%>
<%--<body>--%>

<%--<div class="bg-overlay"></div>--%>

<%--<div class="login-box">--%>
<%--    <img src="https://designercomvn.s3.ap-southeast-1.amazonaws.com/wp-content/uploads/2020/06/05231353/vinmec-logo.jpg"--%>
<%--         alt="Logo" class="logo">--%>

<%--    <!-- Hai nút chuyển form -->--%>
<%--    <div class="tab-buttons">--%>
<%--        <button id="btnSignIn" class="active">Sign In</button>--%>
<%--        <button id="btnRegister">Register</button>--%>
<%--    </div>--%>

<%--    <!-- FORM ĐĂNG NHẬP -->--%>
<%--    <form id="formSignIn" class="form active" action="<c:url value='/login'/>" method="post">--%>
<%--        <label for="email">Username or email</label>--%>
<%--        <input type="text" id="email" name="username" placeholder="example@gmail.com"--%>
<%--               value="<c:out value='${username}'/>">--%>
<%--        <c:if test="${not empty nameErr}">--%>
<%--            <div class="error-msg">${nameErr}</div>--%>
<%--        </c:if>--%>

<%--        <label for="password">Password</label>--%>
<%--        <input type="password" id="password" name="password" placeholder="********"/>--%>
<%--        <c:if test="${not empty passErr}">--%>
<%--            <div class="error-msg">${passErr}</div>--%>
<%--        </c:if>--%>

<%--        <c:if test="${not empty loginErr}">--%>
<%--            <div class="error-msg">${loginErr}</div>--%>
<%--        </c:if>--%>

<%--        <button type="submit" class="btn-login">Sign In</button>--%>
<%--    </form>--%>

<%--    <!-- FORM ĐĂNG KÝ -->--%>
<%--    <form id="formRegister" class="form" action="<c:url value='/register'/>" method="post">--%>
<%--        <label>Username</label>--%>
<%--        <input type="text" name="username" placeholder="username"--%>
<%--               value="<c:out value='${username}'/>">--%>
<%--        <c:if test="${not empty nameErr}">--%>
<%--            <div class="error-msg">${nameErr}</div>--%>
<%--        </c:if>--%>
<%--        <label>Password</label>--%>
<%--        <input type="password" name="password" placeholder="********">--%>
<%--        <c:if test="${not empty passErr}">--%>
<%--            <div class="error-msg">${passErr}</div>--%>
<%--        </c:if>--%>
<%--        <c:if test="${not empty error}">--%>
<%--            <div class="error-msg">${error}</div>--%>
<%--        </c:if>--%>
<%--        <c:if test="${param.mess == 'register_success'}">--%>
<%--            <div class="success-msg">Đăng ký thành công. Vui lòng đăng nhập!</div>--%>
<%--        </c:if>--%>
<%--        <!-- Account type mặc định -->--%>
<%--        <input type="hidden" name="role" value="CUSTOMER">--%>
<%--        <button type="submit" class="btn-login">Register</button>--%>
<%--    </form>--%>

<%--</div>--%>
<%--<script src="<c:url value="/assets/js/login.js"/>"></script>--%>
<%--</body>--%>
<%--</html>--%>