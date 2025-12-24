<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dpnbh
  Date: 16/12/2025
  Time: 12:08 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Customer</title>

    <!-- Bootstrap + FontAwesome (đồng bộ style) -->
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

        .btn, .form-control, .form-select {
            border-radius: 12px;
        }
    </style>
</head>
<body>
<div class="container py-4" style="max-width: 720px;">

    <h2 class="page-title mb-3 text-center">
        <i class="fa-solid fa-user-pen"></i> Cập nhật thông tin cá nhân
    </h2>

    <div class="card">
        <div class="card-body">
            <form method="post" action="<c:url value="${pageContext.request.contextPath}/customers"/>">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="${customer.id}">
                <div class="mb-3">
                    <label class="form-label fw-bold">Username</label>
                    <input class="form-control" type="text" name="username" value="${customer.username}" readonly>
                </div>
                <div class="mb-3">
                    <label class="form-label fw-bold">Type Customer</label>
                    <select class="form-select" name="customerTypeId">
                        <c:forEach var="ct" items="${customerTypeList}">
                            <option value="${ct.id}"
                                ${ct.name == customer.customerTypeName ? "selected" : ""}>
                                    ${ct.name}
                            </option>
                        </c:forEach>
                    </select>

                </div>
                <div class="mb-3">
                    <label class="form-label fw-bold">Họ tên</label>
                    <input class="form-control" type="text" name="name" value="${customer.name}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">Giới tính</label>
                    <select class="form-select" name="gender">
                        <option value="true" ${customer.gender ? "selected" : ""}>Nam</option>
                        <option value="false" ${!customer.gender ? "selected" : ""}>Nữ</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">Ngày sinh</label>
                    <input class="form-control" type="date" name="birthday" value="${customer.birthday}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">Email</label>
                    <input class="form-control" type="email" name="email" value="${customer.email}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">Số điện thoại</label>
                    <input class="form-control" type="text" name="phone" value="${customer.phone}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">Địa chỉ</label>
                    <input class="form-control" type="text" name="address" value="${customer.address}" required>
                </div>

                <button type="submit" class="btn btn-primary w-100">
                    <i class="fa-solid fa-floppy-disk"></i> Cập nhật
                </button>
            </form>
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>