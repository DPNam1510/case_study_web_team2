<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <meta charset="UTF-8">
    <div class="logo">PHÒNG KHÁM</div>

    <nav>
        <ul>
            <li><a href="">Trang chủ</a></li>

            <li class="dropdown">
                <a>Khám bệnh ▾</a>
                <ul class="dropdown-menu">
                    <li><a href="<c:url value="/view/customer/serviceList.jsp"/>">Danh sách dịch vụ</a></li>
                    <li><a href="<c:url value="/view/customer/addMedicalForm.jsp"/>">Đăng ký khám</a></li>
                    <li><a href="<c:url value="/view/customer/medicalList.jsp"/>">Lịch sử khám</a></li>
                </ul>
            </li>

            <li class="dropdown">
                <a>Đơn hàng ▾</a>
                <ul class="dropdown-menu">
                    <li><a href="<c:url value="/view/customer/addOrder.jsp"/>">Đặt hàng</a></li>
                    <li><a href="<c:url value="/view/customer/orderDetail.jsp"/>">Đơn hàng của tôi</a></li>
                    <li><a href="<c:url value="/view/customer/orderList.jsp"/>">Lịch sử đơn hàng</a></li>
                </ul>
            </li>

            <li class="dropdown">
                <a>Tài khoản ▾</a>
                <ul class="dropdown-menu">
                    <li><a href="<c:url value="/view/customer/customerList.jsp"/>">Thông tin cá nhân</a></li>
                    <li><a href="<c:url value="/view/home.jsp"/>">Đăng xuất</a></li>
                </ul>
            </li>
        </ul>
    </nav>
</header>

