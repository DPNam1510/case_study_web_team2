<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/15/2025
  Time: 2:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết Khách hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-10">
            <div class="card">
                <div class="card-header bg-info text-white">
                    <h4 class="mb-0">
                        <i class="fas fa-user"></i> Thông tin Khách hàng
                    </h4>
                </div>
                <div class="card-body">
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <h6 class="text-muted">ID</h6>
                            <p class="fw-bold">${customer.id}</p>
                        </div>
                        <div class="col-md-6">
                            <h6 class="text-muted">Username</h6>
                            <p class="fw-bold">
                                <c:choose>
                                    <c:when test="${empty customer.username}">
                                        <span class="text-muted fst-italic">Chưa cập nhật</span>
                                    </c:when>
                                    <c:otherwise>${customer.username}</c:otherwise>
                                </c:choose>
                            </p>

                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <h6 class="text-muted">Họ và tên</h6>
                            <p class="fw-bold">
                                <c:choose>
                                    <c:when test="${empty customer.name}">
                                        <span class="text-muted fst-italic">Chưa cập nhật</span>
                                    </c:when>
                                    <c:otherwise>${customer.name}</c:otherwise>
                                </c:choose>
                            </p>
                        </div>
                        <div class="col-md-6">
                            <h6 class="text-muted">Giới tính</h6>
                            <p class="fw-bold">
                                <c:choose>
                                    <c:when test="${empty customer.gender}">
                                        <span class="text-muted fst-italic">Chưa cập nhật</span>
                                    </c:when>
                                    <c:otherwise>${customer.gender ? 'Nam' : 'Nữ'}</c:otherwise>
                                </c:choose>
                            </p>

                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <h6 class="text-muted">Ngày sinh</h6>
                            <p class="fw-bold">
                                <c:choose>
                                    <c:when test="${empty customer.birthday}">
                                        <span class="text-muted fst-italic">Chưa cập nhật</span>
                                    </c:when>
                                    <c:otherwise>${customer.birthday}</c:otherwise>
                                </c:choose>
                            </p>

                        </div>
                        <div class="col-md-6">
                            <h6 class="text-muted">Email</h6>
                            <p class="fw-bold">
                                <c:choose>
                                    <c:when test="${empty customer.email}">
                                        <span class="text-muted fst-italic">Chưa cập nhật</span>
                                    </c:when>
                                    <c:otherwise>${customer.email}</c:otherwise>
                                </c:choose>
                            </p>

                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <h6 class="text-muted">Số điện thoại</h6>
                            <p class="fw-bold">
                                <c:choose>
                                    <c:when test="${empty customer.phone}">
                                        <span class="text-muted fst-italic">Chưa cập nhật</span>
                                    </c:when>
                                    <c:otherwise>${customer.phone}</c:otherwise>
                                </c:choose>
                            </p>

                        </div>
                        <div class="col-md-6">
                            <h6 class="text-muted">Địa chỉ</h6>
                            <p class="fw-bold">
                                <c:choose>
                                    <c:when test="${empty customer.address}">
                                        <span class="text-muted fst-italic">Chưa cập nhật</span>
                                    </c:when>
                                    <c:otherwise>${customer.address}</c:otherwise>
                                </c:choose>
                            </p>

                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-12">
                            <h6 class="text-muted">Trạng thái sử dụng dịch vụ</h6>
                            <c:choose>
                                <c:when test="${hasUsedService}">
                                        <span class="badge bg-success fs-6">
                                            <i class="fas fa-check-circle"></i> Đã sử dụng dịch vụ
                                        </span>
                                </c:when>
                                <c:otherwise>
                                        <span class="badge bg-warning fs-6">
                                            <i class="fas fa-exclamation-circle"></i> Chưa sử dụng dịch vụ
                                        </span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <hr>

                    <div class="d-flex justify-content-between">
                        <a href="${pageContext.request.contextPath}/admin/customers"
                           class="btn btn-secondary">
                            <i class="fas fa-arrow-left"></i> Quay lại
                        </a>
                        <c:if test="${!hasUsedService}">
                            <button type="button" class="btn btn-danger"
                                    onclick="confirmDelete(${customer.id}, '${customer.name}')">
                                <i class="fas fa-trash"></i> Xóa khách hàng
                            </button>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function confirmDelete(id, name) {
        if (confirm('Bạn có chắc chắn muốn xóa khách hàng "' + name + '"?')) {
            window.location.href = '${pageContext.request.contextPath}/admin/customers?action=delete&id=' + id;
        }
    }
</script>
</body>
</html>