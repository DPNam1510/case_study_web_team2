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
    <title>Tạo đơn hàng</title>

    <!-- Bootstrap + FontAwesome (đồng bộ style) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        body { background: #f6f7fb; }
        .page-title { font-weight: 800; letter-spacing: .2px; }
        .card { border-radius: 14px; }
        .btn, .form-select { border-radius: 12px; }
    </style>
</head>
<body>
<div class="container py-4" style="max-width: 560px;">

    <h2 class="page-title mb-3"><i class="fa-solid fa-receipt"></i> Tạo đơn hàng</h2>

    <div class="card">
        <div class="card-body">
            <form action="<c:url value="/orders"/>" method="post">
                <input type="hidden" name="action" value="add"/>

                <label class="form-label fw-bold">Hình thức thanh toán:</label>
                <select name="payTypeId" class="form-select">
                    <option value="1">Tiền mặt</option>
                    <option value="2">Chuyển khoản</option>
                </select>

                <br><br>

                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-primary flex-fill">
                        <i class="fa-solid fa-plus"></i> Tạo đơn
                    </button>
                    <a class="btn btn-outline-secondary flex-fill" href="<c:url value="home_customer.jsp">
                        Quay lại
                    </a>
                </div>
            </form>
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
