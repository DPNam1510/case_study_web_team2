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
    <title>Orders</title>

    <!-- Bootstrap + FontAwesome (đồng bộ style) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        body{ background:#f6f7fb; }
        .page-title{ font-weight:800; letter-spacing:.2px; }
        .card{ border-radius:14px; }
        .btn, .form-control, .form-select{ border-radius:12px; }
        .table thead th{ white-space:nowrap; }
    </style>
</head>
<body>
<div class="container py-4">

    <div class="d-flex flex-wrap justify-content-between align-items-center gap-2 mb-3">
        <h2 class="page-title mb-0"><i class="fa-solid fa-receipt"></i> Danh sách đơn hàng</h2>
        <a class="btn btn-primary" href="<c:url value="/orders?action=add"/>">
            <i class="fa-solid fa-plus"></i> Tạo đơn mới
        </a>
    </div>

    <div class="card">
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-hover align-middle mb-0" border="1" cellpadding="8">
                    <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Ngày tạo</th>
                        <th>Thanh toán</th>
                        <th class="text-center">Hành động</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${ordersList}" var="o">
                        <tr>
                            <td>${o.id}</td>
                            <td>${o.dateTime}</td>
                            <td>Pay type ${o.payTypeId}</td>
                            <td class="text-center">
                                <a class="btn btn-sm btn-info text-white"
                                   href="<c:url value="/orders?action=view&id=${o.id}"/>">
                                    <i class="fa-solid fa-eye"></i> Chi tiết
                                </a>
                                <a class="btn btn-sm btn-danger"
                                   href="<c:url value="/orders?action=delete&id=${o.id}"/>"
                                   onclick="return confirm('Hủy đơn hàng?')">
                                    <i class="fa-solid fa-trash"></i> Hủy
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty ordersList}">
                        <tr>
                            <td colspan="4" class="text-center text-muted fst-italic">
                                Không có dữ liệu
                            </td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>