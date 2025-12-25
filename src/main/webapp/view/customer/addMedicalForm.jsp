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
    <title>Đăng ký khám</title>

    <!-- Bootstrap + FontAwesome (đồng bộ style) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        body{ background:#f6f7fb; }
        .page-title{ font-weight:800; letter-spacing:.2px; }
        .card{ border-radius:14px; }
        .btn, .form-control, .form-select{ border-radius:12px; }
    </style>
</head>
<body>
<div class="container py-4" style="max-width: 640px;">

    <div class="d-flex align-items-center justify-content-between flex-wrap gap-2 mb-3">
        <h2 class="page-title mb-0"><i class="fa-solid fa-calendar-check"></i> Đăng ký đơn khám bệnh</h2>
        <a class="btn btn-outline-secondary" href="<c:url value="/medicalForms"/>">
            <i class="fa-solid fa-arrow-left"></i> Quay lại
        </a>
    </div>

    <div class="card">
        <div class="card-body">
            <form action="<c:url value="/medicalForms"/>" method="post">
                <input type="hidden" name="action" value="add"/>
                <input type="hidden" name="customerId" value="${customerId}">
                <div class="mb-3">
                    <label class="form-label fw-bold">Dịch vụ khám</label>
                    <select class="form-select" name="serviceId" required>
                        <option value="">-- Chọn dịch vụ --</option>
                        <c:forEach var="s" items="${serviceList}">
                            <option value="${s.id}">
                                    ${s.name} - ${s.doctorName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label fw-bold">Ngày khám</label>
                    <input type="date" name="medicalDate"
                           class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label fw-bold">Thời gian khám</label>
                    <input type="datetime-local" name="appointmentTime"
                           class="form-control" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">
                    <i class="fa-solid fa-plus"></i> Đăng ký
                </button>

            </form>

            <div class="mt-3 text-muted" style="font-size: 13px;">
                <i class="fa-solid fa-circle-info"></i>
                Vui lòng chọn thời gian trong giờ làm việc để được xác nhận nhanh.
            </div>
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
