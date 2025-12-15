<%--
  Created by IntelliJ IDEA.
  User: dpnbh
  Date: 14/12/2025
  Time: 2:52 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Dịch vụ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2><i class="fas fa-stethoscope"></i> Quản lý Dịch vụ Khám chữa bệnh</h2>
        <a href="${pageContext.request.contextPath}/admin/services?action=create" class="btn btn-primary">
            <i class="fas fa-plus"></i> Thêm dịch vụ mới
        </a>
    </div>

    <!-- Alert Messages -->
    <c:if test="${param.success == 'created'}">
        <div class="alert alert-success alert-dismissible fade show">
            <i class="fas fa-check-circle"></i> Thêm dịch vụ thành công!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>
    <c:if test="${param.success == 'updated'}">
        <div class="alert alert-success alert-dismissible fade show">
            <i class="fas fa-check-circle"></i> Cập nhật dịch vụ thành công!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>
    <c:if test="${param.success == 'deleted'}">
        <div class="alert alert-success alert-dismissible fade show">
            <i class="fas fa-check-circle"></i> Xóa dịch vụ thành công!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>
    <c:if test="${param.error == 'deletefailed'}">
        <div class="alert alert-danger alert-dismissible fade show">
            <i class="fas fa-exclamation-circle"></i> Không thể xóa dịch vụ!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>

    <!-- Search Form -->
    <div class="card mb-4">
        <div class="card-body">
            <form method="get" action="${pageContext.request.contextPath}/admin/services" class="row g-3">
                <div class="col-md-10">
                    <input type="text" name="keyword" class="form-control"
                           placeholder="Tìm kiếm theo tên dịch vụ hoặc bác sĩ..."
                           value="${keyword}">
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-primary w-100">
                        <i class="fas fa-search"></i> Tìm kiếm
                    </button>
                </div>
            </form>
        </div>
    </div>

    <!-- Services Table -->
    <div class="card">
        <div class="card-body">
            <c:if test="${empty services}">
                <div class="alert alert-info">
                    <i class="fas fa-info-circle"></i> Không tìm thấy dịch vụ nào.
                </div>
            </c:if>

            <c:if test="${not empty services}">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead class="table-light">
                        <tr>
                            <th>ID</th>
                            <th>Tên dịch vụ</th>
                            <th>Bác sĩ phụ trách</th>
                            <th class="text-center">Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="service" items="${services}">
                            <tr>
                                <td>${service.id}</td>
                                <td>${service.name}</td>
                                <td>${service.doctorName}</td>
                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/admin/services?action=edit&id=${service.id}"
                                       class="btn btn-sm btn-warning">
                                        <i class="fas fa-edit"></i> Sửa
                                    </a>
                                    <button type="button" class="btn btn-sm btn-danger"
                                            onclick="confirmDelete(${service.id}, '${service.name}')">
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function confirmDelete(id, name) {
        if (confirm('Bạn có chắc chắn muốn xóa dịch vụ "' + name + '"?')) {
            window.location.href = '${pageContext.request.contextPath}/admin/services?action=delete&id=' + id;
        }
    }
</script>
</body>
</html>