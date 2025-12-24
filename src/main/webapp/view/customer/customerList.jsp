<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thông tin cá nhân</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        body { background:#f6f7fb; }
        .page-title { font-weight:800; }
        .card { border-radius:16px; }
        .info-label { color:#6c757d; font-size:14px; }
        .info-value { font-weight:600; font-size:16px; }
    </style>
</head>

<body>
<div class="container py-4" style="max-width: 900px">

    <!-- Header -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="page-title mb-0">
            <i class="fa-solid fa-user"></i> Thông tin cá nhân
        </h2>

        <a href="<c:url value='/customers?action=update'/>"
           class="btn btn-primary">
            <i class="fa-solid fa-user-pen"></i> Cập nhật
        </a>
    </div>

    <!-- Message -->
    <c:if test="${param.message != null}">
        <div class="alert alert-success text-center">
            <i class="fa-solid fa-circle-check"></i>
            Cập nhật thông tin thành công
        </div>
    </c:if>
    <table class="table table-bordered">
        <tr>
            <th>Username</th>
            <th>Customer Type</th>
            <th>Name</th>
            <th>Gender</th>
            <th>Birthday</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Address</th>
        </tr>

        <tr>
            <td>${customer.username}</td>
            <td>${customer.customerTypeName}</td>
            <td>${customer.name}</td>
            <td>
                <c:choose>
                    <c:when test="${customer.gender}">Nam</c:when>
                    <c:otherwise>Nữ</c:otherwise>
                </c:choose>
            </td>
            <td>${customer.birthday}</td>
            <td>${customer.email}</td>
            <td>${customer.phone}</td>
            <td>${customer.address}</td>
        </tr>
    </table>



<%--    <!-- Info Card -->--%>
<%--    <div class="card shadow-sm">--%>
<%--        <div class="card-body">--%>
<%--            <div class="row g-4">--%>

<%--                <div class="col-md-6">--%>
<%--                    <div class="info-label">Username</div>--%>
<%--                    <div class="info-value">${userName}</div>--%>
<%--                </div>--%>

<%--                <div class="col-md-6">--%>
<%--                    <div class="info-label">Họ tên</div>--%>
<%--                    <div class="info-value">${name}</div>--%>
<%--                </div>--%>

<%--                <div class="col-md-6">--%>
<%--                    <div class="info-label">Giới tính</div>--%>
<%--                    <div class="info-value">--%>
<%--                        <c:choose>--%>
<%--                            <c:when test="${customer.gender}">Nam</c:when>--%>
<%--                            <c:otherwise>Nữ</c:otherwise>--%>
<%--                        </c:choose>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--                <div class="col-md-6">--%>
<%--                    <div class="info-label">Ngày sinh</div>--%>
<%--                    <div class="info-value">--%>
<%--                        <fmt:formatDate value="${birthday}" pattern="dd/MM/yyyy"/>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--                <div class="col-md-6">--%>
<%--                    <div class="info-label">Email</div>--%>
<%--                    <div class="info-value">${customer.email}</div>--%>
<%--                </div>--%>

<%--                <div class="col-md-6">--%>
<%--                    <div class="info-label">Số điện thoại</div>--%>
<%--                    <div class="info-value">${customer.phone}</div>--%>
<%--                </div>--%>

<%--                <div class="col-12">--%>
<%--                    <div class="info-label">Địa chỉ</div>--%>
<%--                    <div class="info-value">${customer.address}</div>--%>
<%--                </div>--%>

<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--</div>--%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>