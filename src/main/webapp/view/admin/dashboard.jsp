<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>

    <!-- giống 4 file bạn gửi -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        .card-hover { transition: transform .15s ease, box-shadow .15s ease; }
        .card-hover:hover { transform: translateY(-2px); box-shadow: 0 .5rem 1rem rgba(0,0,0,.08); }
        .mini { font-size: .92rem; }
        .btn-wide { min-width: 190px; }
    </style>
</head>

<body>
<div class="container mt-4">

    <!-- Header -->
    <div class="d-flex flex-wrap justify-content-between align-items-center gap-2 mb-4">
        <div>
            <h2 class="mb-1"><i class="fas fa-gauge-high"></i> Admin Dashboard</h2>
            <div class="text-muted mini">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        Xin chào, <strong>${sessionScope.user.username}</strong>!
                    </c:when>
                    <c:otherwise>
                        Xin chào Admin!
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <div class="d-flex flex-wrap gap-2">
            <a class="btn btn-outline-primary btn-wide"
               href="${pageContext.request.contextPath}/admin/customers">
                <i class="fas fa-users"></i> Quản lý khách hàng
            </a>
            <a class="btn btn-outline-success btn-wide"
               href="${pageContext.request.contextPath}/admin/services">
                <i class="fas fa-stethoscope"></i> Quản lý dịch vụ
            </a>
        </div>
    </div>

    <!-- Main Cards -->
    <div class="row g-4">

        <!-- Customer -->
        <div class="col-12 col-lg-6">
            <div class="card card-hover h-100">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-start">
                        <div>
                            <h5 class="card-title mb-1">
                                <i class="fas fa-users text-primary"></i> Khách hàng
                            </h5>
                            <div class="text-muted mini mb-3">
                                Danh sách, tìm kiếm, xem chi tiết và xóa khách hàng.
                            </div>
                        </div>
                    </div>
                    <div class="d-flex flex-wrap gap-2">
                        <a class="btn btn-primary"
                           href="${pageContext.request.contextPath}/admin/customers">
                            <i class="fas fa-list"></i> Danh sách khách hàng
                        </a>
                        <a class="btn btn-outline-secondary"
                           href="${pageContext.request.contextPath}/admin/customers?keyword=">
                            <i class="fas fa-magnifying-glass"></i> Tìm kiếm nhanh
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Service -->
        <div class="col-12 col-lg-6">
            <div class="card card-hover h-100">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-start">
                        <div>
                            <h5 class="card-title mb-1">
                                <i class="fas fa-stethoscope text-success"></i> Dịch vụ
                            </h5>
                            <div class="text-muted mini mb-3">
                                Danh sách, tạo mới, sửa và xóa dịch vụ khám chữa bệnh.
                            </div>
                        </div>
                    </div>

                    <div class="d-flex flex-wrap gap-2">
                        <a class="btn btn-success"
                           href="${pageContext.request.contextPath}/admin/services">
                            <i class="fas fa-list"></i> Danh sách dịch vụ
                        </a>
                        <a class="btn btn-outline-success"
                           href="${pageContext.request.contextPath}/admin/services?action=create">
                            <i class="fas fa-plus"></i> Thêm dịch vụ mới
                        </a>
                    </div>
                </div>
            </div>
        </div>

    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>