<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dpnbh
  Date: 16/12/2025
  Time: 1:25 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Medical Forms</title>

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
        <h2 class="page-title mb-0"><i class="fa-solid fa-file-medical"></i> Danh sách đơn khám bệnh</h2>
        <a class="btn btn-primary" href="<c:url value="/medicalForms?action=add"/>">
            <i class="fa-solid fa-plus"></i> Đăng ký khám mới
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
                        <th>Thời gian khám</th>
                        <th>Trạng thái</th>
                        <th class="text-center">Hành động</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${medicalFormsList}" var="m">
                        <tr>
                            <td>${m.id}</td>
                            <td>${m.dateTime}</td>
                            <td>${m.appointmentTime}</td>
                            <td>${m.status}</td>
                            <td class="text-center">
                                <a class="btn btn-sm btn-danger"
                                   href="<c:url value="home_customer.jsp"/>"
                                   onclick="return confirm('Hủy đơn khám?')">
                                    <i class="fa-solid fa-trash"></i> Hủy
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                    <c:if test="${empty medicalFormsList}">
                        <tr>
                            <td colspan="5" class="text-center text-muted fst-italic">
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