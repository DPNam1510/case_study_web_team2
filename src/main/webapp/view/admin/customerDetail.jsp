<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/15/2025
  Time: 2:23 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết Khách hàng</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        :root{
            --clinic-1:#0ea5e9;
            --clinic-2:#22c55e;
            --ink:#0f172a;
            --muted:#64748b;
            --line:#e2e8f0;
        }

        body{
            background:
                    radial-gradient(1200px 520px at 10% 0%, rgba(14,165,233,.16), transparent 60%),
                    radial-gradient(900px 420px at 90% 10%, rgba(34,197,94,.12), transparent 55%),
                    linear-gradient(#ffffff, #fbfdff);
            min-height: 100vh;
        }

        .container{ max-width: 1050px; }

        .card{
            border: 0;
            border-radius: 18px;
            box-shadow: 0 16px 36px rgba(2,8,23,.08);
            overflow: hidden;
        }

        .card-header{
            border: 0;
            background: linear-gradient(135deg, rgba(14,165,233,.95), rgba(34,197,94,.85)) !important;
        }

        .header-sub{
            font-size: .92rem;
            opacity: .92;
        }

        .info-box{
            border: 1px solid rgba(226,232,240,.9);
            border-radius: 16px;
            padding: 14px 14px;
            background: rgba(255,255,255,.85);
            box-shadow: 0 10px 22px rgba(2,8,23,.05);
            height: 100%;
        }

        .info-label{
            color: var(--muted);
            font-size: .9rem;
            margin-bottom: 6px;
        }

        .info-value{
            color: var(--ink);
            font-weight: 800;
            margin: 0;
            word-break: break-word;
        }

        .muted-empty{
            color: #94a3b8;
            font-style: italic;
            font-weight: 600;
        }

        .btn{ border-radius: 14px; }
        .btn-secondary{ background: #0f172a; border: none; }
        .btn-secondary:hover{ background: #111827; }

        .badge{
            border-radius: 999px;
            padding: 10px 12px;
            border: 1px solid rgba(226,232,240,.85);
        }

        .gender-pill{
            display: inline-flex;
            align-items: center;
            gap: 6px;
            padding: 6px 10px;
            border-radius: 999px;
            font-weight: 800;
            font-size: .9rem;
            border: 1px solid rgba(226,232,240,.95);
            background: #fff;
        }
        .gender-pill.male{ color: #1d4ed8; }
        .gender-pill.female{ color: #be185d; }
        .gender-pill.unknown{ color: #64748b; }

        hr{ border-top: 1px solid rgba(226,232,240,.95); }
    </style>
</head>

<body>
<div class="container py-4">
    <div class="row justify-content-center">
        <div class="col-12 col-lg-10">
            <div class="card">
                <div class="card-header text-white p-4">
                    <div class="d-flex align-items-start justify-content-between gap-2">
                        <div>
                            <h4 class="mb-1 fw-semibold">
                                <i class="fas fa-user me-2"></i> Thông tin Khách hàng
                            </h4>
                            <div class="header-sub">
                                Xem chi tiết hồ sơ khách hàng và trạng thái sử dụng dịch vụ.
                            </div>
                        </div>
                        <span class="badge bg-light text-dark">
                            <i class="fa-regular fa-id-badge me-1"></i> ID: ${customer.id}
                        </span>
                    </div>
                </div>

                <div class="card-body p-4">
                    <!-- Row 1 -->
                    <div class="row g-3 mb-1">
                        <div class="col-md-6">
                            <div class="info-box">
                                <div class="info-label">Username</div>
                                <p class="info-value">
                                    <c:choose>
                                        <c:when test="${empty customer.username}">
                                            <span class="muted-empty">Chưa cập nhật</span>
                                        </c:when>
                                        <c:otherwise>${customer.username}</c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="info-box">
                                <div class="info-label">Họ và tên</div>
                                <p class="info-value">
                                    <c:choose>
                                        <c:when test="${empty customer.name}">
                                            <span class="muted-empty">Chưa cập nhật</span>
                                        </c:when>
                                        <c:otherwise>${customer.name}</c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </div>
                    </div>

                    <!-- Row 2 -->
                    <div class="row g-3 mt-1 mb-1">
                        <div class="col-md-6">
                            <div class="info-box">
                                <div class="info-label">Giới tính</div>
                                <p class="info-value">
                                    <c:choose>
                                        <c:when test="${customer.gender == null}">
                                            <span class="gender-pill unknown">
                                                <i class="fa-regular fa-circle-question"></i> Chưa cập nhật
                                            </span>
                                        </c:when>
                                        <c:when test="${customer.gender == true}">
                                            <span class="gender-pill male">
                                                <i class="fa-solid fa-mars"></i> Nam
                                            </span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="gender-pill female">
                                                <i class="fa-solid fa-venus"></i> Nữ
                                            </span>
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="info-box">
                                <div class="info-label">Ngày sinh</div>
                                <p class="info-value">
                                    <c:choose>
                                        <c:when test="${empty customer.birthday}">
                                            <span class="muted-empty">Chưa cập nhật</span>
                                        </c:when>
                                        <c:otherwise>${customer.birthday}</c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </div>
                    </div>

                    <!-- Row 3 -->
                    <div class="row g-3 mt-1 mb-1">
                        <div class="col-md-6">
                            <div class="info-box">
                                <div class="info-label">Email</div>
                                <p class="info-value">
                                    <c:choose>
                                        <c:when test="${empty customer.email}">
                                            <span class="muted-empty">Chưa cập nhật</span>
                                        </c:when>
                                        <c:otherwise>${customer.email}</c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="info-box">
                                <div class="info-label">Số điện thoại</div>
                                <p class="info-value">
                                    <c:choose>
                                        <c:when test="${empty customer.phone}">
                                            <span class="muted-empty">Chưa cập nhật</span>
                                        </c:when>
                                        <c:otherwise>${customer.phone}</c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </div>
                    </div>

                    <!-- Row 4 -->
                    <div class="row g-3 mt-1">
                        <div class="col-md-12">
                            <div class="info-box">
                                <div class="info-label">Địa chỉ</div>
                                <p class="info-value">
                                    <c:choose>
                                        <c:when test="${empty customer.address}">
                                            <span class="muted-empty">Chưa cập nhật</span>
                                        </c:when>
                                        <c:otherwise>${customer.address}</c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </div>
                    </div>

                    <!-- Service status -->
                    <div class="mt-4">
                        <div class="d-flex align-items-center justify-content-between flex-wrap gap-2 mb-2">
                            <div class="text-muted fw-semibold">
                                <i class="fa-solid fa-circle-info me-1"></i> Trạng thái sử dụng dịch vụ
                            </div>

                            <c:choose>
                                <c:when test="${hasUsedService}">
                                    <span class="badge bg-success fs-6">
                                        <i class="fas fa-check-circle me-1"></i> Đã sử dụng dịch vụ
                                    </span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge bg-warning text-dark fs-6">
                                        <i class="fas fa-exclamation-circle me-1"></i> Chưa sử dụng dịch vụ
                                    </span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <hr class="my-4">

                    <!-- Actions -->
                    <div class="d-flex justify-content-between flex-wrap gap-2">
                        <a href="${pageContext.request.contextPath}/admin/customers"
                           class="btn btn-secondary">
                            <i class="fas fa-arrow-left me-1"></i> Quay lại
                        </a>

                        <c:if test="${!hasUsedService}">
                            <button type="button" class="btn btn-danger"
                                    onclick="confirmDelete(${customer.id}, '${empty customer.name ? 'khách hàng này' : customer.name}')">
                                <i class="fas fa-trash me-1"></i> Xóa khách hàng
                            </button>
                        </c:if>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function confirmDelete(id, name) {
        if (confirm('Bạn có chắc chắn muốn xóa khách hàng "' + name + '"?')) {
            window.location.href = '${pageContext.request.contextPath}/admin/customers?action=delete&id=' + id;
        }
    }
</script>
</body>
</html>

<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: DELL--%>
<%--  Date: 12/15/2025--%>
<%--  Time: 2:23 PM--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <title>Chi tiết Khách hàng</title>--%>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">--%>
<%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="container mt-4">--%>
<%--    <div class="row justify-content-center">--%>
<%--        <div class="col-md-10">--%>
<%--            <div class="card">--%>
<%--                <div class="card-header bg-info text-white">--%>
<%--                    <h4 class="mb-0">--%>
<%--                        <i class="fas fa-user"></i> Thông tin Khách hàng--%>
<%--                    </h4>--%>
<%--                </div>--%>
<%--                <div class="card-body">--%>
<%--                    <div class="row mb-3">--%>
<%--                        <div class="col-md-6">--%>
<%--                            <h6 class="text-muted">ID</h6>--%>
<%--                            <p class="fw-bold">${customer.id}</p>--%>
<%--                        </div>--%>
<%--                        <div class="col-md-6">--%>
<%--                            <h6 class="text-muted">Username</h6>--%>
<%--                            <p class="fw-bold">--%>
<%--                                <c:choose>--%>
<%--                                    <c:when test="${empty customer.username}">--%>
<%--                                        <span class="text-muted fst-italic">Chưa cập nhật</span>--%>
<%--                                    </c:when>--%>
<%--                                    <c:otherwise>${customer.username}</c:otherwise>--%>
<%--                                </c:choose>--%>
<%--                            </p>--%>

<%--                        </div>--%>
<%--                    </div>--%>

<%--                    <div class="row mb-3">--%>
<%--                        <div class="col-md-6">--%>
<%--                            <h6 class="text-muted">Họ và tên</h6>--%>
<%--                            <p class="fw-bold">--%>
<%--                                <c:choose>--%>
<%--                                    <c:when test="${empty customer.name}">--%>
<%--                                        <span class="text-muted fst-italic">Chưa cập nhật</span>--%>
<%--                                    </c:when>--%>
<%--                                    <c:otherwise>${customer.name}</c:otherwise>--%>
<%--                                </c:choose>--%>
<%--                            </p>--%>
<%--                        </div>--%>
<%--                        <div class="col-md-6">--%>
<%--                            <h6 class="text-muted">Giới tính</h6>--%>
<%--                            <p class="fw-bold">--%>
<%--                                <c:choose>--%>
<%--                                    <c:when test="${customer.gender == null}">--%>
<%--                                        <span class="text-muted fst-italic">Chưa cập nhật</span>--%>
<%--                                    </c:when>--%>
<%--                                    <c:when test="${customer.gender == true}">--%>
<%--                                        Nam--%>
<%--                                    </c:when>--%>
<%--                                    <c:otherwise>--%>
<%--                                        Nữ--%>
<%--                                    </c:otherwise>--%>
<%--                                </c:choose>--%>
<%--                            </p>--%>

<%--                        </div>--%>
<%--                    </div>--%>

<%--                    <div class="row mb-3">--%>
<%--                        <div class="col-md-6">--%>
<%--                            <h6 class="text-muted">Ngày sinh</h6>--%>
<%--                            <p class="fw-bold">--%>
<%--                                <c:choose>--%>
<%--                                    <c:when test="${empty customer.birthday}">--%>
<%--                                        <span class="text-muted fst-italic">Chưa cập nhật</span>--%>
<%--                                    </c:when>--%>
<%--                                    <c:otherwise>${customer.birthday}</c:otherwise>--%>
<%--                                </c:choose>--%>
<%--                            </p>--%>

<%--                        </div>--%>
<%--                        <div class="col-md-6">--%>
<%--                            <h6 class="text-muted">Email</h6>--%>
<%--                            <p class="fw-bold">--%>
<%--                                <c:choose>--%>
<%--                                    <c:when test="${empty customer.email}">--%>
<%--                                        <span class="text-muted fst-italic">Chưa cập nhật</span>--%>
<%--                                    </c:when>--%>
<%--                                    <c:otherwise>${customer.email}</c:otherwise>--%>
<%--                                </c:choose>--%>
<%--                            </p>--%>

<%--                        </div>--%>
<%--                    </div>--%>

<%--                    <div class="row mb-3">--%>
<%--                        <div class="col-md-6">--%>
<%--                            <h6 class="text-muted">Số điện thoại</h6>--%>
<%--                            <p class="fw-bold">--%>
<%--                                <c:choose>--%>
<%--                                    <c:when test="${empty customer.phone}">--%>
<%--                                        <span class="text-muted fst-italic">Chưa cập nhật</span>--%>
<%--                                    </c:when>--%>
<%--                                    <c:otherwise>${customer.phone}</c:otherwise>--%>
<%--                                </c:choose>--%>
<%--                            </p>--%>

<%--                        </div>--%>
<%--                        <div class="col-md-6">--%>
<%--                            <h6 class="text-muted">Địa chỉ</h6>--%>
<%--                            <p class="fw-bold">--%>
<%--                                <c:choose>--%>
<%--                                    <c:when test="${empty customer.address}">--%>
<%--                                        <span class="text-muted fst-italic">Chưa cập nhật</span>--%>
<%--                                    </c:when>--%>
<%--                                    <c:otherwise>${customer.address}</c:otherwise>--%>
<%--                                </c:choose>--%>
<%--                            </p>--%>

<%--                        </div>--%>
<%--                    </div>--%>

<%--                    <div class="row mb-3">--%>
<%--                        <div class="col-md-12">--%>
<%--                            <h6 class="text-muted">Trạng thái sử dụng dịch vụ</h6>--%>
<%--                            <c:choose>--%>
<%--                                <c:when test="${hasUsedService}">--%>
<%--                                        <span class="badge bg-success fs-6">--%>
<%--                                            <i class="fas fa-check-circle"></i> Đã sử dụng dịch vụ--%>
<%--                                        </span>--%>
<%--                                </c:when>--%>
<%--                                <c:otherwise>--%>
<%--                                        <span class="badge bg-warning fs-6">--%>
<%--                                            <i class="fas fa-exclamation-circle"></i> Chưa sử dụng dịch vụ--%>
<%--                                        </span>--%>
<%--                                </c:otherwise>--%>
<%--                            </c:choose>--%>
<%--                        </div>--%>
<%--                    </div>--%>

<%--                    <hr>--%>

<%--                    <div class="d-flex justify-content-between">--%>
<%--                        <a href="${pageContext.request.contextPath}/admin/customers"--%>
<%--                           class="btn btn-secondary">--%>
<%--                            <i class="fas fa-arrow-left"></i> Quay lại--%>
<%--                        </a>--%>
<%--                        <c:if test="${!hasUsedService}">--%>
<%--                            <button type="button" class="btn btn-danger"--%>
<%--                                    onclick="confirmDelete(${customer.id}, '${empty customer.name ? 'khách hàng này' : customer.name}')"--%>
<%--                                <i class="fas fa-trash"></i> Xóa khách hàng--%>
<%--                            </button>--%>
<%--                        </c:if>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>--%>
<%--<script>--%>
<%--    function confirmDelete(id, name) {--%>
<%--        if (confirm('Bạn có chắc chắn muốn xóa khách hàng "' + name + '"?')) {--%>
<%--            window.location.href = '${pageContext.request.contextPath}/admin/customers?action=delete&id=' + id;--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>