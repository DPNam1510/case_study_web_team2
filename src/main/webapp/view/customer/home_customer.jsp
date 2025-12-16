<%--
  Created by IntelliJ IDEA.
  User: dpnbh
  Date: 14/12/2025
  Time: 4:48 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>HOSPITAL</title>
    <c:import url="/layout/library.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/home.css"/>">
</head>
<body>

<c:import url="/layout/customerHeader.jsp"/>

<section class="hero">
    <div class="hero-content">
        <h1>Xin chào, ${sessionScope.customer.name}</h1>
        <p>Chăm sóc sức khỏe toàn diện cho bạn</p>
        <a href="<c:url value='/medicalForms?action=add'/>" class="btn">
            Đăng ký khám ngay
        </a>
    </div>
</section>

<section class="services">
    <h2>Chức năng dành cho bạn</h2>

    <div class="service-box">
        <div class="service-item">
            <h3>Dịch vụ khám</h3>
            <p>Xem & tìm dịch vụ khám chữa bệnh</p>
            <a href="<c:url value="/services"/>">Xem</a>
        </div>

        <div class="service-item">
            <h3>Đơn khám</h3>
            <p>Đăng ký & theo dõi lịch khám</p>
            <a href="<c:url value="/medicalForms"/>">Xem</a>
        </div>

        <div class="service-item">
            <h3>Đơn hàng</h3>
            <p>Đặt & theo dõi thực phẩm chức năng</p>
            <a href="<c:url value="/orders"/>">Xem</a>
        </div>
    </div>
</section>
</body>
</html>
