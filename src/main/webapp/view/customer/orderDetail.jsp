<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dpnbh
  Date: 16/12/2025
  Time: 1:39 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết đơn hàng</title>

    <!-- Bootstrap + FontAwesome (đồng bộ style) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        body{ background:#f6f7fb; }
        .page-title{ font-weight:800; letter-spacing:.2px; }
        .card{ border-radius:14px; }
        .btn, .form-control, .form-select{ border-radius:12px; }
        .kv{ display:flex; gap:10px; align-items:baseline; padding:10px 0; border-bottom:1px solid #e5e7eb; }
        .kv:last-child{ border-bottom:none; }
        .k{ min-width: 200px; color:#6b7280; font-weight:700; }
        .v{ font-weight:800; color:#111827; }
    </style>
</head>
<body>
<div class="container py-4" style="max-width: 720px;">

    <div class="d-flex align-items-center justify-content-between flex-wrap gap-2 mb-3">
        <h2 class="page-title mb-0"><i class="fa-solid fa-file-invoice"></i> Chi tiết đơn hàng</h2>
        <a class="btn btn-outline-secondary" href="<c:url value="home_customer.jsp"/>">
            <i class="fa-solid fa-arrow-left"></i> Quay lại
        </a>
    </div>

    <div class="card">
        <div class="card-body">
            <div class="kv">
                <div class="k">ID</div>
                <div class="v">${orders.id}</div>
            </div>
            <div class="kv">
                <div class="k">Ngày tạo</div>
                <div class="v">${orders.dateTime}</div>
            </div>
            <div class="kv">
                <div class="k">Hình thức thanh toán</div>
                <div class="v">${orders.payTypeId}</div>
            </div>

            <div class="mt-3 text-muted" style="font-size: 13px;">
                <i class="fa-solid fa-circle-info"></i>
                Thông tin chi tiết sản phẩm/đơn hàng có thể hiển thị ở đây nếu bạn mở rộng OrdersDetail.
            </div>
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
