<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        :root{
            --clinic-1:#0ea5e9;
            --clinic-2:#22c55e;
            --clinic-3:#0f172a;
            --soft:#f7fbff;
        }

        body{
            background: radial-gradient(1200px 500px at 10% 0%, rgba(14,165,233,.18), transparent 60%),
            radial-gradient(900px 400px at 90% 10%, rgba(34,197,94,.14), transparent 55%),
            linear-gradient(#ffffff, #fbfdff);
            min-height: 100vh;
        }

        .top-hero{
            border: 0;
            border-radius: 18px;
            overflow: hidden;
            background: linear-gradient(135deg, rgba(14,165,233,.95), rgba(34,197,94,.85));
            color: #fff;
            box-shadow: 0 18px 40px rgba(2, 8, 23, .12);
        }

        .top-hero .badge{
            background: rgba(255,255,255,.18);
            border: 1px solid rgba(255,255,255,.22);
        }

        .glass{
            background: rgba(255,255,255,.78);
            backdrop-filter: blur(8px);
            border: 1px solid rgba(226,232,240,.9);
            border-radius: 18px;
            box-shadow: 0 10px 26px rgba(2,8,23,.06);
        }

        .card-hover{
            transition: transform .15s ease, box-shadow .15s ease;
            border: 0;
            border-radius: 18px;
        }
        .card-hover:hover{
            transform: translateY(-3px);
            box-shadow: 0 18px 40px rgba(2,8,23,.10);
        }

        .icon-bubble{
            width: 44px; height: 44px;
            border-radius: 14px;
            display: grid;
            place-items: center;
            color: #fff;
            box-shadow: 0 10px 22px rgba(2,8,23,.12);
        }
        .b-primary{ background: linear-gradient(135deg, #2563eb, #0ea5e9); }
        .b-success{ background: linear-gradient(135deg, #16a34a, #22c55e); }
        .b-dark{ background: linear-gradient(135deg, #0f172a, #334155); }

        .mini{ font-size: .92rem; color: #64748b; }
        .btn-wide{ min-width: 190px; border-radius: 12px; }
        .btn-round{ border-radius: 12px; }

        .quick a{
            text-decoration: none;
            color: inherit;
        }

        .quick-tile{
            border-radius: 16px;
            padding: 14px;
            border: 1px solid rgba(226,232,240,.9);
            background: #fff;
            transition: transform .15s ease, box-shadow .15s ease;
        }
        .quick-tile:hover{
            transform: translateY(-2px);
            box-shadow: 0 14px 30px rgba(2,8,23,.08);
        }

        .divider{
            height: 1px;
            background: rgba(255,255,255,.22);
            margin: 12px 0 14px;
        }
    </style>
</head>

<body>
<div class="container py-4">

    <!-- HERO HEADER -->
    <div class="top-hero p-4 p-lg-5 mb-4">
        <div class="d-flex flex-wrap justify-content-between align-items-start gap-3">
            <div>
                <div class="d-flex align-items-center gap-2 mb-2">
          <span class="badge rounded-pill px-3 py-2">
            <i class="fa-solid fa-heart-pulse me-1"></i> Clinic Admin
          </span>
                </div>

                <h2 class="mb-1 fw-semibold">
                    <i class="fas fa-gauge-high me-2"></i> Bảng điều khiển
                </h2>

                <div class="opacity-90">
                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            Xin chào, <strong>${sessionScope.user.username}</strong>! Chúc bạn một ngày làm việc hiệu quả.
                        </c:when>
                        <c:otherwise>
                            Xin chào Admin! Chúc bạn một ngày làm việc hiệu quả.
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="divider"></div>

                <div class="d-flex flex-wrap gap-2">
                    <a class="btn btn-light btn-round btn-wide"
                       href="${pageContext.request.contextPath}/view/home.jsp">
                        <i class="fas fa-house me-1"></i> Trang chủ
                    </a>

                    <a class="btn btn-outline-light btn-round btn-wide"
                       href="${pageContext.request.contextPath}/admin/customers">
                        <i class="fas fa-users me-1"></i> Khách hàng
                    </a>

                    <a class="btn btn-outline-light btn-round btn-wide"
                       href="${pageContext.request.contextPath}/admin/services">
                        <i class="fas fa-stethoscope me-1"></i> Dịch vụ
                    </a>
                </div>
            </div>

            <div class="d-none d-lg-block">
                <div class="glass p-3">
                    <div class="d-flex align-items-center gap-3">
                        <div class="icon-bubble b-dark">
                            <i class="fa-solid fa-shield-halved"></i>
                        </div>
                        <div>
                            <div class="fw-semibold text-dark">Gợi ý vận hành</div>
                            <div class="mini">Kiểm tra khách mới đăng ký & cập nhật dịch vụ.</div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <!-- QUICK ACTIONS -->
    <div class="glass p-3 p-lg-4 mb-4">
        <div class="d-flex flex-wrap justify-content-between align-items-center gap-2 mb-3">
            <div>
                <div class="fw-semibold text-dark"><i class="fa-solid fa-bolt me-2 text-primary"></i>Thao tác nhanh</div>
                <div class="mini">Đi tắt tới các màn hình thao tác thường dùng.</div>
            </div>
        </div>

        <div class="row g-3 quick">
            <div class="col-12 col-md-6 col-lg-3">
                <a href="${pageContext.request.contextPath}/admin/customers">
                    <div class="quick-tile h-100">
                        <div class="d-flex align-items-center gap-3">
                            <div class="icon-bubble b-primary"><i class="fa-solid fa-list"></i></div>
                            <div>
                                <div class="fw-semibold">Danh sách KH</div>
                                <div class="mini">Xem, tìm kiếm, chi tiết</div>
                            </div>
                        </div>
                    </div>
                </a>
            </div>

            <div class="col-12 col-md-6 col-lg-3">
                <a href="${pageContext.request.contextPath}/admin/customers?keyword=">
                    <div class="quick-tile h-100">
                        <div class="d-flex align-items-center gap-3">
                            <div class="icon-bubble b-dark"><i class="fa-solid fa-magnifying-glass"></i></div>
                            <div>
                                <div class="fw-semibold">Tìm nhanh KH</div>
                                <div class="mini">Tên / email / sđt</div>
                            </div>
                        </div>
                    </div>
                </a>
            </div>

            <div class="col-12 col-md-6 col-lg-3">
                <a href="${pageContext.request.contextPath}/admin/services">
                    <div class="quick-tile h-100">
                        <div class="d-flex align-items-center gap-3">
                            <div class="icon-bubble b-success"><i class="fa-solid fa-stethoscope"></i></div>
                            <div>
                                <div class="fw-semibold">Danh sách DV</div>
                                <div class="mini">Quản lý dịch vụ khám</div>
                            </div>
                        </div>
                    </div>
                </a>
            </div>

            <div class="col-12 col-md-6 col-lg-3">
                <a href="${pageContext.request.contextPath}/admin/services?action=create">
                    <div class="quick-tile h-100">
                        <div class="d-flex align-items-center gap-3">
                            <div class="icon-bubble b-success"><i class="fa-solid fa-plus"></i></div>
                            <div>
                                <div class="fw-semibold">Thêm dịch vụ</div>
                                <div class="mini">Tạo dịch vụ mới</div>
                            </div>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </div>

    <!-- MAIN FEATURE CARDS -->
    <div class="row g-4">

        <!-- Customer -->
        <div class="col-12 col-lg-6">
            <div class="card card-hover h-100">
                <div class="card-body p-4">
                    <div class="d-flex align-items-start justify-content-between">
                        <div class="d-flex align-items-start gap-3">
                            <div class="icon-bubble b-primary">
                                <i class="fas fa-users"></i>
                            </div>
                            <div>
                                <h5 class="mb-1 fw-semibold">Khách hàng</h5>
                                <div class="mini">Danh sách, tìm kiếm, xem chi tiết và xóa khách hàng.</div>
                            </div>
                        </div>
                    </div>

                    <div class="d-flex flex-wrap gap-2 mt-3">
                        <a class="btn btn-primary btn-round"
                           href="${pageContext.request.contextPath}/admin/customers">
                            <i class="fas fa-list me-1"></i> Danh sách khách hàng
                        </a>
                        <a class="btn btn-outline-secondary btn-round"
                           href="${pageContext.request.contextPath}/admin/customers?keyword=">
                            <i class="fas fa-magnifying-glass me-1"></i> Tìm kiếm nhanh
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Service -->
        <div class="col-12 col-lg-6">
            <div class="card card-hover h-100">
                <div class="card-body p-4">
                    <div class="d-flex align-items-start justify-content-between">
                        <div class="d-flex align-items-start gap-3">
                            <div class="icon-bubble b-success">
                                <i class="fas fa-stethoscope"></i>
                            </div>
                            <div>
                                <h5 class="mb-1 fw-semibold">Dịch vụ</h5>
                                <div class="mini">Danh sách, tạo mới, sửa và xóa dịch vụ khám chữa bệnh.</div>
                            </div>
                        </div>
                    </div>

                    <div class="d-flex flex-wrap gap-2 mt-3">
                        <a class="btn btn-success btn-round"
                           href="${pageContext.request.contextPath}/admin/services">
                            <i class="fas fa-list me-1"></i> Danh sách dịch vụ
                        </a>
                        <a class="btn btn-success"
                           href="/admin-registered-service">
                            <i class="fas fa-list"></i> Dịch vụ đã được đăng ký
                        </a>
                        <a class="btn btn-outline-success"
                           href="${pageContext.request.contextPath}/admin/services?action=create">
                            <i class="fas fa-plus me-1"></i> Thêm dịch vụ mới
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="text-center text-muted mini mt-4">
        <i class="fa-regular fa-hospital me-1"></i> Hệ thống quản lý phòng khám • Admin Panel
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <title>Admin Dashboard</title>--%>

<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">--%>
<%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">--%>

<%--    <style>--%>
<%--        .card-hover { transition: transform .15s ease, box-shadow .15s ease; }--%>
<%--        .card-hover:hover { transform: translateY(-2px); box-shadow: 0 .5rem 1rem rgba(0,0,0,.08); }--%>
<%--        .mini { font-size: .92rem; }--%>
<%--        .btn-wide { min-width: 190px; }--%>
<%--    </style>--%>
<%--</head>--%>

<%--<body>--%>
<%--<div class="container mt-4">--%>

<%--    <!-- Header -->--%>
<%--    <div class="d-flex flex-wrap justify-content-between align-items-center gap-2 mb-4">--%>
<%--        <div>--%>
<%--            <h2 class="mb-1"><i class="fas fa-gauge-high"></i> Admin Dashboard</h2>--%>
<%--            <div class="text-muted mini">--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${not empty sessionScope.user}">--%>
<%--                        Xin chào, <strong>${sessionScope.user.username}</strong>!--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        Xin chào Admin!--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
<%--            </div>--%>
<%--        </div>--%>

<%--        <div class="d-flex flex-wrap gap-2">--%>
<%--            <a class="btn btn-outline-dark btn-wide"--%>
<%--               href="${pageContext.request.contextPath}/view/home.jsp">--%>
<%--                <i class="fas fa-house"></i> Trang chủ--%>
<%--            </a>--%>

<%--            <a class="btn btn-outline-primary btn-wide"--%>
<%--               href="${pageContext.request.contextPath}/admin/customers">--%>
<%--                <i class="fas fa-users"></i> Quản lý khách hàng--%>
<%--            </a>--%>

<%--            <a class="btn btn-outline-success btn-wide"--%>
<%--               href="${pageContext.request.contextPath}/admin/services">--%>
<%--                <i class="fas fa-stethoscope"></i> Quản lý dịch vụ--%>
<%--            </a>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    <!-- Main Cards -->--%>
<%--    <div class="row g-4">--%>

<%--        <!-- Customer -->--%>
<%--        <div class="col-12 col-lg-6">--%>
<%--            <div class="card card-hover h-100">--%>
<%--                <div class="card-body">--%>
<%--                    <div class="d-flex justify-content-between align-items-start">--%>
<%--                        <div>--%>
<%--                            <h5 class="card-title mb-1">--%>
<%--                                <i class="fas fa-users text-primary"></i> Khách hàng--%>
<%--                            </h5>--%>
<%--                            <div class="text-muted mini mb-3">--%>
<%--                                Danh sách, tìm kiếm, xem chi tiết và xóa khách hàng.--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="d-flex flex-wrap gap-2">--%>
<%--                        <a class="btn btn-primary"--%>
<%--                           href="${pageContext.request.contextPath}/admin/customers">--%>
<%--                            <i class="fas fa-list"></i> Danh sách khách hàng--%>
<%--                        </a>--%>
<%--                        <a class="btn btn-outline-secondary"--%>
<%--                           href="${pageContext.request.contextPath}/admin/customers?keyword=">--%>
<%--                            <i class="fas fa-magnifying-glass"></i> Tìm kiếm nhanh--%>
<%--                        </a>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>

<%--        <!-- Service -->--%>
<%--        <div class="col-12 col-lg-6">--%>
<%--            <div class="card card-hover h-100">--%>
<%--                <div class="card-body">--%>
<%--                    <div class="d-flex justify-content-between align-items-start">--%>
<%--                        <div>--%>
<%--                            <h5 class="card-title mb-1">--%>
<%--                                <i class="fas fa-stethoscope text-success"></i> Dịch vụ--%>
<%--                            </h5>--%>
<%--                            <div class="text-muted mini mb-3">--%>
<%--                                Danh sách, tạo mới, sửa và xóa dịch vụ khám chữa bệnh.--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>

<%--                    <div class="d-flex flex-wrap gap-2">--%>
<%--                        <a class="btn btn-success"--%>
<%--                           href="${pageContext.request.contextPath}/admin/services">--%>
<%--                            <i class="fas fa-list"></i> Danh sách dịch vụ--%>
<%--                        </a>--%>
<%--                        <a class="btn btn-outline-success"--%>
<%--                           href="${pageContext.request.contextPath}/admin/services?action=create">--%>
<%--                            <i class="fas fa-plus"></i> Thêm dịch vụ mới--%>
<%--                        </a>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>

<%--    </div>--%>

<%--</div>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>--%>
<%--</body>--%>
<%--</html>--%>
