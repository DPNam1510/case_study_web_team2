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
    <title>HOSPITAL</title>
    <c:import url="/layout/library.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/home.css"/>">
</head>
<body>
<!-- HEADER -->
<header>
    <div class="logo">PHÒNG KHÁM TƯ NHÂN</div>
    <div class="menu-btn">☰</div>
    <nav>
        <ul>
            <li><a href="case_study.html">Trang chủ</a></li>
            <li class="dropdown">
                <a href="#">Dịch vụ ▾</a>

                <ul class="dropdown-menu">
                    <li><a href="login.html">Khám chữa bệnh</a></li>
                    <li><a href="thuoc.html">Thuốc kê đơn</a></li>
                </ul>
            </li>
            <li><a href="sanpham.html">Thực phẩm chức năng</a></li>
            <li><a href="<c:url value='/login'/>" class="login-btn">Đăng nhập</a></li>

        </ul>
    </nav>
</header>

<!-- HERO -->
<section class="hero fade">
    <div class="hero-content">
        <h1>Chăm sóc sức khỏe tận tâm</h1>
        <p>Đội ngũ bác sĩ giàu kinh nghiệm – Trang thiết bị hiện đại</p>
        <a href="<c:url value="/view/customer/addMedicalForm.jsp"/>" class="btn">Đăng ký khám ngay</a>
    </div>
</section>

<!-- SERVICES -->
<section class="services fade">
    <h2>Dịch Vụ Nổi Bật</h2>
    <div class="service-box">
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

<!-- FOOTER -->
<footer>
    <p>© 2025 Phòng khám tư nhân. All Rights Reserved.</p>
    <p>Địa chỉ: Tam Kỳ - Quảng Nam</p>
    <p>Hotline: 0123 456 789</p>
</footer>
<script src="<c:url value="/assets/js/home.js"/>"></script>
</body>
</html>
