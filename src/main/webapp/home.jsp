<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dpnbh
  Date: 14/12/2025
  Time: 3:18 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HOSPITAL</title>
    <c:import url="./layout/library.jsp"/>
<%--    <link rel="stylesheet" href="assets/css/home.css">--%>
</head>
<body>
<%--header--%>
<header class="header">
    <div class="logo">PHÒNG KHÁM TƯ NHÂN</div>
    <nav class="nav">
        <a href="#">Trang chủ</a>
        <a href="#">Dịch vụ</a>
        <a href="#">Thực phẩm chức năng</a>
        <button class="btn-login">Đăng nhập</button>
    </nav>
</header>
<%--HERO BANNER--%>
<section class="hero">
    <div class="overlay"></div>
    <div class="hero-content">
        <h1>Chăm sóc sức khỏe tận tâm</h1>
        <p>Đội ngũ bác sĩ giàu kinh nghiệm – Trang thiết bị hiện đại</p>
        <button class="btn-primary">Đăng ký khám ngay</button>
    </div>
</section>
<%--DỊCH VỤ NỔI BẬT--%>
<section class="services">
    <h2>Dịch Vụ Nổi Bật</h2>

    <div class="service-list">

        <div class="service-item">
            <h3>Khám tổng quát</h3>
            <p>Kiểm tra sức khỏe chuyên sâu.</p>
        </div>

        <div class="service-item">
            <h3>Khám nội khoa</h3>
            <p>Chẩn đoán và điều trị bệnh nội khoa.</p>
        </div>

        <div class="service-item">
            <h3>Xét nghiệm</h3>
            <p>Hơn 50 loại xét nghiệm nhanh chóng.</p>
        </div>

    </div>
</section>
<%--FOOTER--%>
<footer class="footer">
    <p>© 2025 Phòng khám tư nhân. All Rights Reserved.</p>
    <p>Địa chỉ: Tam Kỳ - Quảng Nam</p>
    <p>Hotline: 0123 456 789</p>
</footer>
<%--<script src="assets/js/home.js"></script>--%>
</body>
</html>
