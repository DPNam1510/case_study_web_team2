<%--
  Created by IntelliJ IDEA.
  User: dpnbh
  Date: 14/12/2025
  Time: 2:53 CH
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Khách hàng</title>

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

        .container{
            max-width: 1140px;
        }

        /* Sticky header (clinic glass) */
        .sticky-header{
            position: sticky;
            top: 14px;
            z-index: 1000;
            background: rgba(255,255,255,.86);
            backdrop-filter: blur(8px);
            border: 1px solid rgba(226,232,240,.85);
            border-radius: 18px;
            padding: 14px 14px 12px;
            box-shadow: 0 14px 30px rgba(2,8,23,.06);
        }

        .page-title{
            font-weight: 800;
            color: var(--ink);
            letter-spacing: .2px;
        }
        .page-title i{ color: var(--clinic-1); }

        .mini{ font-size: .92rem; color: var(--muted); }

        /* Cards */
        .card{
            border: 0;
            border-radius: 18px;
            box-shadow: 0 14px 30px rgba(2,8,23,.06);
        }

        /* Search */
        .form-control{
            border-radius: 14px;
            border: 1px solid var(--line);
        }
        .form-control:focus{
            border-color: rgba(14,165,233,.55);
            box-shadow: 0 0 0 .25rem rgba(14,165,233,.12);
        }

        .btn{ border-radius: 14px; }
        .btn-primary{
            background: linear-gradient(135deg, var(--clinic-1), #2563eb);
            border: none;
        }
        .btn-primary:hover{ filter: brightness(.98); }

        /* Table */
        .table thead th{
            color: var(--muted);
            font-weight: 800;
            letter-spacing: .2px;
            border-bottom: 1px solid rgba(226,232,240,.95) !important;
        }
        .table tbody td{
            vertical-align: middle;
            border-top: 1px solid rgba(226,232,240,.75);
        }
        .table-hover tbody tr:hover{
            background: rgba(14,165,233,.06);
        }

        /* Gender pills */
        .gender-pill{
            display: inline-flex;
            align-items: center;
            gap: 6px;
            padding: 6px 10px;
            border-radius: 999px;
            font-weight: 700;
            font-size: .88rem;
            border: 1px solid rgba(226,232,240,.95);
            background: #fff;
            white-space: nowrap;
        }
        .gender-pill.male{ color: #1d4ed8; }
        .gender-pill.female{ color: #be185d; }
        .gender-pill.unknown{ color: #64748b; }

        /* Alerts */
        .alert{
            border-radius: 16px;
            border: 1px solid rgba(226,232,240,.9);
            box-shadow: 0 10px 22px rgba(2,8,23,.05);
        }

        /* Floating back */
        .back-fixed{
            position: fixed;
            right: 20px;
            bottom: 20px;
            z-index: 99999;
            border-radius: 999px;
            padding: 10px 14px;
            box-shadow: 0 10px 26px rgba(2,8,23,.12);
        }
    </style>
</head>

<body>
<div class="container py-4">

    <!-- STICKY: Title + Search -->
    <div class="sticky-header">
        <div class="d-flex flex-wrap justify-content-between align-items-center gap-2 mb-3">
            <div>
                <h2 class="mb-1 page-title"><i class="fas fa-users"></i> Quản lý Khách hàng</h2>
                <div class="mini">Danh sách, tìm kiếm, xem chi tiết và xóa khách hàng.</div>
            </div>
        </div>

        <!-- Search Form -->
        <div class="card mb-0">
            <div class="card-body">
                <form method="get" action="${pageContext.request.contextPath}/admin/customers" class="row g-2">
                    <div class="col-md-10">
                        <input type="text" name="keyword" class="form-control"
                               placeholder="Tìm kiếm theo tên, email hoặc số điện thoại..."
                               value="${keyword}">
                    </div>
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-primary w-100">
                            <i class="fas fa-search me-1"></i> Tìm kiếm
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Alerts -->
    <c:if test="${param.success == 'deleted'}">
        <div class="alert alert-success alert-dismissible fade show mt-3">
            <i class="fas fa-check-circle"></i> Xóa khách hàng thành công!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>

    <c:if test="${param.error == 'hasservice'}">
        <div class="alert alert-danger alert-dismissible fade show mt-3">
            <i class="fas fa-exclamation-circle"></i> Không thể xóa! Khách hàng đã sử dụng dịch vụ.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>

    <c:if test="${param.error == 'deletefailed'}">
        <div class="alert alert-danger alert-dismissible fade show mt-3">
            <i class="fas fa-exclamation-circle"></i> Không thể xóa khách hàng!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>

    <c:if test="${param.error == 'notfound'}">
        <div class="alert alert-danger alert-dismissible fade show mt-3">
            <i class="fas fa-exclamation-circle"></i> Không tìm thấy khách hàng!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>

    <!-- Customers Table -->
    <div class="card mt-3">
        <div class="card-body">
            <c:if test="${empty customers}">
                <div class="alert alert-info mb-0">
                    <i class="fas fa-info-circle"></i> Không tìm thấy khách hàng nào.
                </div>
            </c:if>

            <c:if test="${not empty customers}">
                <div class="table-responsive">
                    <table class="table table-hover align-middle mb-0">
                        <thead class="table-light">
                        <tr>
                            <th style="width:70px;">ID</th>
                            <th>Tên khách hàng</th>
                            <th style="width:140px;">Giới tính</th>
                            <th>Email</th>
                            <th style="width:140px;">Số điện thoại</th>
                            <th>Địa chỉ</th>
                            <th class="text-center" style="width:160px;">Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="customer" items="${customers}">
                            <tr>
                                <td>${customer.id}</td>

                                <td>
                                    <c:choose>
                                        <c:when test="${empty customer.name}">
                                            <span class="text-muted fst-italic">Chưa cập nhật</span>
                                        </c:when>
                                        <c:otherwise>${customer.name}</c:otherwise>
                                    </c:choose>
                                </td>

                                <td>
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
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${empty customer.email}">
                                            <span class="text-muted fst-italic">Chưa cập nhật</span>
                                        </c:when>
                                        <c:otherwise>${customer.email}</c:otherwise>
                                    </c:choose>
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${empty customer.phone}">
                                            <span class="text-muted fst-italic">Chưa cập nhật</span>
                                        </c:when>
                                        <c:otherwise>${customer.phone}</c:otherwise>
                                    </c:choose>
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${empty customer.address}">
                                            <span class="text-muted fst-italic">Chưa cập nhật</span>
                                        </c:when>
                                        <c:otherwise>${customer.address}</c:otherwise>
                                    </c:choose>
                                </td>

                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/admin/customers?action=view&id=${customer.id}"
                                       class="btn btn-sm btn-info text-white">
                                        <i class="fas fa-eye"></i> Xem
                                    </a>

                                    <button type="button" class="btn btn-sm btn-danger"
                                            onclick="confirmDelete(${customer.id}, '${empty customer.name ? 'khách hàng này' : customer.name}')">
                                        <i class="fas fa-trash"></i> Xóa
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </div>

</div>

<!-- Floating back button -->
<a href="/home-admin"
   class="btn btn-dark back-fixed">
    <i class="fas fa-arrow-left"></i> Quay lại
</a>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function confirmDelete(id, name) {
        if (confirm('Bạn có chắc chắn muốn xóa khách hàng "' + name + '"?\n\nLưu ý: Chỉ có thể xóa khách hàng chưa từng sử dụng dịch vụ.')) {
            window.location.href = '${pageContext.request.contextPath}/admin/customers?action=delete&id=' + id;
        }
    }
</script>
</body>
</html>

<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: dpnbh--%>
<%--  Date: 14/12/2025--%>
<%--  Time: 2:53 CH--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <title>Quản lý Khách hàng</title>--%>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">--%>
<%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">--%>

<%--    <style>--%>
<%--        .back-fixed{--%>
<%--            position: fixed;--%>
<%--            right: 20px;--%>
<%--            bottom: 20px;--%>
<%--            z-index: 99999;--%>
<%--            border-radius: 999px;--%>
<%--            padding: 10px 14px;--%>
<%--            box-shadow: 0 6px 16px rgba(0,0,0,0.15);--%>
<%--        }--%>

<%--        /* Sticky header cho tiêu đề + search (giống home, kéo không mất) */--%>
<%--        .sticky-header{--%>
<%--            position: sticky;--%>
<%--            top: 0;--%>
<%--            z-index: 1000;--%>
<%--            background: #fff;--%>
<%--            padding-top: 12px;--%>
<%--            padding-bottom: 12px;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>

<%--<body>--%>
<%--<div class="container mt-4">--%>

<%--    <!-- STICKY: Tiêu đề + Search -->--%>
<%--    <div class="sticky-header">--%>
<%--        <div class="d-flex justify-content-between align-items-center mb-3">--%>
<%--            <h2 class="mb-0"><i class="fas fa-users"></i> Quản lý Khách hàng</h2>--%>
<%--        </div>--%>

<%--        <!-- Search Form -->--%>
<%--        <div class="card mb-0">--%>
<%--            <div class="card-body">--%>
<%--                <form method="get" action="${pageContext.request.contextPath}/admin/customers" class="row g-3">--%>
<%--                    <div class="col-md-10">--%>
<%--                        <input type="text" name="keyword" class="form-control"--%>
<%--                               placeholder="Tìm kiếm theo tên, email hoặc số điện thoại..."--%>
<%--                               value="${keyword}">--%>
<%--                    </div>--%>
<%--                    <div class="col-md-2">--%>
<%--                        <button type="submit" class="btn btn-primary w-100">--%>
<%--                            <i class="fas fa-search"></i> Tìm kiếm--%>
<%--                        </button>--%>
<%--                    </div>--%>
<%--                </form>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    <!-- Alert Messages -->--%>
<%--    <c:if test="${param.success == 'deleted'}">--%>
<%--        <div class="alert alert-success alert-dismissible fade show mt-3">--%>
<%--            <i class="fas fa-check-circle"></i> Xóa khách hàng thành công!--%>
<%--            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>--%>
<%--        </div>--%>
<%--    </c:if>--%>
<%--    <c:if test="${param.error == 'hasservice'}">--%>
<%--        <div class="alert alert-danger alert-dismissible fade show mt-3">--%>
<%--            <i class="fas fa-exclamation-circle"></i> Không thể xóa! Khách hàng đã sử dụng dịch vụ.--%>
<%--            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>--%>
<%--        </div>--%>
<%--    </c:if>--%>
<%--    <c:if test="${param.error == 'deletefailed'}">--%>
<%--        <div class="alert alert-danger alert-dismissible fade show mt-3">--%>
<%--            <i class="fas fa-exclamation-circle"></i> Không thể xóa khách hàng!--%>
<%--            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>--%>
<%--        </div>--%>
<%--    </c:if>--%>
<%--    <c:if test="${param.error == 'notfound'}">--%>
<%--        <div class="alert alert-danger alert-dismissible fade show mt-3">--%>
<%--            <i class="fas fa-exclamation-circle"></i> Không tìm thấy khách hàng!--%>
<%--            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>--%>
<%--        </div>--%>
<%--    </c:if>--%>

<%--    <!-- Customers Table -->--%>
<%--    <div class="card mt-3">--%>
<%--        <div class="card-body">--%>
<%--            <c:if test="${empty customers}">--%>
<%--                <div class="alert alert-info">--%>
<%--                    <i class="fas fa-info-circle"></i> Không tìm thấy khách hàng nào.--%>
<%--                </div>--%>
<%--            </c:if>--%>

<%--            <c:if test="${not empty customers}">--%>
<%--                <div class="table-responsive">--%>
<%--                    <table class="table table-hover">--%>
<%--                        <thead class="table-light">--%>
<%--                        <tr>--%>
<%--                            <th>ID</th>--%>
<%--                            <th>Tên khách hàng</th>--%>
<%--                            <th>Giới tính</th>--%>
<%--                            <th>Email</th>--%>
<%--                            <th>Số điện thoại</th>--%>
<%--                            <th>Địa chỉ</th>--%>
<%--                            <th class="text-center">Thao tác</th>--%>
<%--                        </tr>--%>
<%--                        </thead>--%>
<%--                        <tbody>--%>
<%--                        <c:forEach var="customer" items="${customers}">--%>
<%--                            <tr>--%>
<%--                                <td>${customer.id}</td>--%>

<%--                                <td>--%>
<%--                                    <c:choose>--%>
<%--                                        <c:when test="${empty customer.name}">--%>
<%--                                            <span class="text-muted fst-italic">Chưa cập nhật</span>--%>
<%--                                        </c:when>--%>
<%--                                        <c:otherwise>${customer.name}</c:otherwise>--%>
<%--                                    </c:choose>--%>
<%--                                </td>--%>

<%--                                <td>--%>
<%--                                    <c:choose>--%>
<%--                                        <c:when test="${customer.gender == null}">--%>
<%--                                            <span class="text-muted fst-italic">Chưa cập nhật</span>--%>
<%--                                        </c:when>--%>
<%--                                        <c:when test="${customer.gender == true}">--%>
<%--                                            Nam--%>
<%--                                        </c:when>--%>
<%--                                        <c:otherwise>--%>
<%--                                            Nữ--%>
<%--                                        </c:otherwise>--%>
<%--                                    </c:choose>--%>
<%--                                </td>--%>


<%--                                <td>--%>
<%--                                    <c:choose>--%>
<%--                                        <c:when test="${empty customer.email}">--%>
<%--                                            <span class="text-muted fst-italic">Chưa cập nhật</span>--%>
<%--                                        </c:when>--%>
<%--                                        <c:otherwise>${customer.email}</c:otherwise>--%>
<%--                                    </c:choose>--%>
<%--                                </td>--%>

<%--                                <td>--%>
<%--                                    <c:choose>--%>
<%--                                        <c:when test="${empty customer.phone}">--%>
<%--                                            <span class="text-muted fst-italic">Chưa cập nhật</span>--%>
<%--                                        </c:when>--%>
<%--                                        <c:otherwise>${customer.phone}</c:otherwise>--%>
<%--                                    </c:choose>--%>
<%--                                </td>--%>

<%--                                <td>--%>
<%--                                    <c:choose>--%>
<%--                                        <c:when test="${empty customer.address}">--%>
<%--                                            <span class="text-muted fst-italic">Chưa cập nhật</span>--%>
<%--                                        </c:when>--%>
<%--                                        <c:otherwise>${customer.address}</c:otherwise>--%>
<%--                                    </c:choose>--%>
<%--                                </td>--%>

<%--                                <td class="text-center">--%>
<%--                                    <a href="${pageContext.request.contextPath}/admin/customers?action=view&id=${customer.id}"--%>
<%--                                       class="btn btn-sm btn-info text-white">--%>
<%--                                        <i class="fas fa-eye"></i> Xem--%>
<%--                                    </a>--%>
<%--                                    <button type="button" class="btn btn-sm btn-danger"--%>
<%--                                            onclick="confirmDelete(${customer.id}, '${empty customer.name ? 'khách hàng này' : customer.name}')"--%>
<%--                                        <i class="fas fa-trash"></i> Xóa--%>
<%--                                    </button>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                        </c:forEach>--%>
<%--                        </tbody>--%>
<%--                    </table>--%>
<%--                </div>--%>
<%--            </c:if>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--</div>--%>

<%--<!-- Floating back button (luôn hiện) -->--%>
<%--<a href="${pageContext.request.contextPath}/view/admin/dashboard.jsp"--%>
<%--   class="btn btn-secondary back-fixed">--%>
<%--    <i class="fas fa-arrow-left"></i> Quay lại--%>
<%--</a>--%>

<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>--%>
<%--<script>--%>
<%--    function confirmDelete(id, name) {--%>
<%--        if (confirm('Bạn có chắc chắn muốn xóa khách hàng "' + name + '"?\n\nLưu ý: Chỉ có thể xóa khách hàng chưa từng sử dụng dịch vụ.')) {--%>
<%--            window.location.href = '${pageContext.request.contextPath}/admin/customers?action=delete&id=' + id;--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>