<%--
  Created by IntelliJ IDEA.
  User: dpnbh
  Date: 14/12/2025
  Time: 2:53 CH
  To change this template use File | Settings | File Templates.
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
</head>
<body>
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2><i class="fas fa-users"></i> Quản lý Khách hàng</h2>
    </div>

    <!-- Alert Messages -->
    <c:if test="${param.success == 'deleted'}">
        <div class="alert alert-success alert-dismissible fade show">
            <i class="fas fa-check-circle"></i> Xóa khách hàng thành công!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>
    <c:if test="${param.error == 'hasservice'}">
        <div class="alert alert-danger alert-dismissible fade show">
            <i class="fas fa-exclamation-circle"></i> Không thể xóa! Khách hàng đã sử dụng dịch vụ.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>
    <c:if test="${param.error == 'deletefailed'}">
        <div class="alert alert-danger alert-dismissible fade show">
            <i class="fas fa-exclamation-circle"></i> Không thể xóa khách hàng!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>
    <c:if test="${param.error == 'notfound'}">
        <div class="alert alert-danger alert-dismissible fade show">
            <i class="fas fa-exclamation-circle"></i> Không tìm thấy khách hàng!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>

    <!-- Search Form -->
    <div class="card mb-4">
        <div class="card-body">
            <form method="get" action="${pageContext.request.contextPath}/admin/customers" class="row g-3">
                <div class="col-md-10">
                    <input type="text" name="keyword" class="form-control"
                           placeholder="Tìm kiếm theo tên, email hoặc số điện thoại..."
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

    <!-- Customers Table -->
    <div class="card">
        <div class="card-body">
            <c:if test="${empty customers}">
                <div class="alert alert-info">
                    <i class="fas fa-info-circle"></i> Không tìm thấy khách hàng nào.
                </div>
            </c:if>

            <c:if test="${not empty customers}">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead class="table-light">
                        <tr>
                            <th>ID</th>
                            <th>Tên khách hàng</th>
                            <th>Email</th>
                            <th>Số điện thoại</th>
                            <th>Địa chỉ</th>
                            <th class="text-center">Thao tác</th>
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
                                            onclick="confirmDelete(${customer.id}, '${customer.name}')">
                                        <i class="fas fa-trash"></i> Xóa
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <a href="/view/admin/dashboard.jsp"
                   class="btn btn-secondary">
                    <i class="fas fa-arrow-left"></i> Quay lại
                </a>
            </c:if>
        </div>
    </div>
</div>

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
