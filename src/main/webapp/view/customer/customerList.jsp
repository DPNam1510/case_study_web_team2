<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dpnbh
  Date: 14/12/2025
  Time: 2:55 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer List</title>

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

    <h2 class="page-title mb-3 text-center">
        <i class="fa-solid fa-users"></i> Danh sách khách hàng
    </h2>

    <c:if test="${param.message != null}">
        <div class="alert alert-success text-center">
            <i class="fa-solid fa-circle-check"></i> ${param.message}
        </div>
    </c:if>

    <div class="card">
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-hover align-middle mb-0" border="1" cellpadding="8">
                    <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Họ tên</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th class="text-center">Action</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${customerList}" var="c">
                        <tr>
                            <td>${c.id}</td>
                            <td>${c.userName}</td>
                            <td>${c.name}</td>
                            <td>${c.email}</td>
                            <td>${c.phone}</td>
                            <td class="text-center">
                                <a class="btn btn-sm btn-primary"
                                   href="<c:url value="/customers?action=update&id=${c.id}"/>">
                                    <i class="fa-solid fa-pen-to-square"></i> Cập nhật
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                    <c:if test="${empty customerList}">
                        <tr>
                            <td colspan="6" class="text-center text-muted fst-italic">
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