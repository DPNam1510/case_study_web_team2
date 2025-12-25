<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dịch vụ khám</title>

    <!-- Bootstrap + FontAwesome -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        body {
            background: #f6f7fb;
        }
        .page-title {
            font-weight: 800;
            letter-spacing: .2px;
        }
        .card {
            border-radius: 14px;
        }
        .btn, .form-control {
            border-radius: 12px;
        }
        .table thead th {
            white-space: nowrap;
        }
    </style>
</head>
<body>

<div class="container py-4">

    <!-- ===== TITLE ===== -->
    <div class="d-flex flex-wrap justify-content-between align-items-center gap-2 mb-3">
        <h2 class="page-title mb-0">
            <i class="fa-solid fa-stethoscope"></i> Danh sách dịch vụ khám
        </h2>
    </div>

    <!-- ===== SEARCH FORM ===== -->
    <div class="card mb-3">
        <div class="card-body">
            <form action="<c:url value='/service-list'/>" method="get"
                  class="row g-2 align-items-center">

                <input type="hidden" name="action" value="search">

                <!-- Tên dịch vụ -->
                <div class="col-12 col-md-4">
                    <input type="text"
                           name="name"
                           class="form-control"
                           placeholder="Nhập tên dịch vụ"
                           value="${name}">
                </div>

                <!-- Tên bác sĩ -->
                <div class="col-12 col-md-4">
                    <input type="text"
                           name="doctor_name"
                           class="form-control"
                           placeholder="Nhập tên bác sĩ"
                           value="${doctorName}">
                </div>

                <!-- Nút tìm -->
                <div class="col-6 col-md-2">
                    <button type="submit" class="btn btn-primary w-100">
                        <i class="fa-solid fa-magnifying-glass"></i> Tìm
                    </button>
                </div>

                <!-- Nút tất cả -->
                <div class="col-6 col-md-2">
                    <a class="btn btn-outline-secondary w-100"
                       href="<c:url value='/service-list'/>">
                        Tất cả
                    </a>
                </div>

            </form>
        </div>
    </div>

    <!-- ===== TABLE ===== -->
    <div class="card">
        <div class="card-body">
            <div class="table-responsive">

                <table class="table table-hover align-middle mb-0" border="1" cellpadding="8">
                    <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Tên dịch vụ</th>
                        <th>Bác sĩ</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${serviceList}" var="s">
                        <tr>
                            <td>${s.id}</td>
                            <td>${s.name}</td>
                            <td>${s.doctorName}</td>
                        </tr>
                    </c:forEach>

                    <c:if test="${empty serviceList}">
                        <tr>
                            <td colspan="3"
                                class="text-center text-muted fst-italic">
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
