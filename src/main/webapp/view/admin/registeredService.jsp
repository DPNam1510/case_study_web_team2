<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registered Services | Admin</title>
    <c:import url="/layout/library.jsp"/>
    <c:import url="/layout/navbar.jsp"/>
</head>
<body class="bg-light">

<div class="container-fluid py-5" style="background: linear-gradient(135deg, #e3f2fd, #f1f8ff);">
    <div class="container">

        <!-- ===== HEADER ===== -->
        <div class="row align-items-center mb-4">
            <div class="col-md-10">
                <h2 class="fw-bold text-primary mb-1">
                    Danh sách dịch vụ khách hàng
                </h2>
                <p class="text-muted mb-0">
                    Quản lý các dịch vụ y tế đã được đăng ký bởi khách hàng
                </p>
            </div>

            <div class="col-md-2 text-end">
                <a href="/home-admin" class="btn btn-outline-primary">
                    ← Quay lại Dashboard
                </a>
            </div>
        </div>
        <!-- ===== SEARCH: PENDING ===== -->
        <c:if test="${status == 'pending'}">
            <div class="mb-4">
                <form action="${pageContext.request.contextPath}/admin-registered-service"
                      method="get"
                      class="row g-2 align-items-end">

                    <input type="hidden" name="action" value="search_pending"/>

                    <div class="col-md-4">
                        <label class="form-label fw-semibold">Tên khách hàng</label>
                        <input class="form-control"
                               name="name"
                               value="${customerName}"
                               placeholder="Nhập tên khách hàng">
                    </div>

                    <div class="col-md-4">
                        <label class="form-label fw-semibold">Tên dịch vụ</label>
                        <input class="form-control"
                               name="service"
                               value="${serviceName}"
                               placeholder="Nhập tên dịch vụ">
                    </div>

                    <div class="col-md-1">
                        <button class="btn btn-primary w-100">Tìm kiếm</button>
                    </div>
                    <div class="col-md-1">
                        <a href="/admin-registered-service?action=pending" class="btn btn-dark">Quay lại</a>
                    </div>
                </form>
            </div>
        </c:if>

        <!-- ===== SEARCH: COMPLETE ===== -->
        <c:if test="${status == 'complete'}">
            <div class="mb-4">
                <form action="${pageContext.request.contextPath}/admin-registered-service"
                      method="get"
                      class="row g-2 align-items-end">

                    <input type="hidden" name="action" value="search_complete"/>

                    <div class="col-md-4">
                        <label class="form-label fw-semibold">Tên khách hàng</label>
                        <input class="form-control"
                               name="name"
                               value="${customerName}"
                               placeholder="Nhập tên khách hàng">
                    </div>

                    <div class="col-md-4">
                        <label class="form-label fw-semibold">Tên dịch vụ</label>
                        <input class="form-control"
                               name="service"
                               value="${serviceName}"
                               placeholder="Nhập tên dịch vụ">
                    </div>
                    <div class="col-md-1">
                        <button class="btn btn-primary w-100">Tìm kiếm</button>
                    </div>
                    <div class="col-md-1">
                        <a href="/admin-registered-service?action=complete" class="btn btn-dark">Quay lại</a>
                    </div>
                </form>
            </div>
        </c:if>
        <c:if test="${status == 'rejected'}">
            <div class="mb-4">
                <form action="${pageContext.request.contextPath}/admin-registered-service"
                      method="get"
                      class="row g-2 align-items-end">

                    <input type="hidden" name="action" value="search_reject"/>

                    <div class="col-md-4">
                        <label class="form-label fw-semibold">Tên khách hàng</label>
                        <input class="form-control"
                               name="name"
                               value="${customerName}"
                               placeholder="Nhập tên khách hàng">
                    </div>

                    <div class="col-md-4">
                        <label class="form-label fw-semibold">Tên dịch vụ</label>
                        <input class="form-control"
                               name="service"
                               value="${serviceName}"
                               placeholder="Nhập tên dịch vụ">
                    </div>
                    <div class="col-md-1">
                        <button class="btn btn-primary w-100">Tìm kiếm</button>
                    </div>
                    <div class="col-md-1">
                        <a href="/admin-registered-service?action=rejected" class="btn btn-dark">Quay lại</a>
                    </div>
                </form>
            </div>
        </c:if>
        <!-- ===== TAB ===== -->
        <div class="mb-3">
            <a href="${pageContext.request.contextPath}/admin-registered-service?action=pending"
               class="btn btn-primary ${status=='pending' ? 'active' : ''}">
                Chờ duyệt
            </a>

            <a href="${pageContext.request.contextPath}/admin-registered-service?action=complete"
               class="btn btn-success ${status=='complete' ? 'active' : ''}">
                Đã duyệt
            </a>
            <a href="${pageContext.request.contextPath}/admin-registered-service?action=rejected"
               class="btn btn-success ${status=='rejected' ? 'active' : ''}">
                Đã hủy
            </a>
        </div>

        <!-- ===== TABLE ===== -->
        <div class="row">
            <div class="col-lg-9">
                <div class="card shadow-sm border-0">
                    <div class="card-body p-0">
                        <div class="table-responsive">
                            <c:if test="${not empty param.message}">
                                <div class="alert alert-info">${param.message}</div>
                            </c:if>

                            <table class="table table-hover align-middle mb-0">
                                <thead class="table-primary">
                                <tr>
                                    <th>Forms ID</th>
                                    <th>Customer</th>
                                    <th>Service</th>
                                    <th>Doctor</th>
                                    <th>Trạng thái</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${empty list}">
                                    <tr>
                                        <td colspan="6" class="text-center text-muted py-4">
                                            Chưa có dữ liệu
                                        </td>
                                    </tr>
                                </c:if>

                                <c:forEach var="admf" items="${list}">
                                    <tr>
                                        <td>${admf.formsId}</td>
                                        <td>${admf.customerName}</td>
                                        <td>${admf.serviceName}</td>
                                        <td>${admf.doctorName}</td>
                                        <td>${admf.status}</td>
                                        <td>
                                            <c:if test="${admf.status eq 'Pending'}">
                                                <form method="post"
                                                      action="${pageContext.request.contextPath}/admin-registered-service"
                                                      style="display:inline">
                                                    <input type="hidden" name="action" value="approve"/>
                                                    <input type="hidden" name="id" value="${admf.formsId}"/>
                                                    <button class="btn btn-success btn-sm">Duyệt</button>
                                                </form>
                                                <form method="post"
                                                      action="${pageContext.request.contextPath}/admin-registered-service"
                                                      style="display:inline">
                                                    <input type="hidden" name="action" value="reject"/>
                                                    <input type="hidden" name="id" value="${admf.formsId}"/>
                                                    <button class="btn btn-danger btn-sm">Hủy</button>
                                                </form>
                                            </c:if>
                                            <button type="button" class="btn btn-danger btn-sm"
                                                    data-bs-toggle="modal" data-bs-target="#deleteModal"
                                                    onclick="getInfoToDelete('${admf.formsId}','${admf.customerName}')">
                                                Xóa
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 d-none d-lg-flex align-items-center justify-content-center">
                <img src="https://cdn-icons-png.flaticon.com/512/2966/2966327.png"
                     class="img-fluid"
                     alt="Medical Illustration">
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="deleteModal">
    <div class="modal-dialog">
        <form class="modal-content" action="/admin-registered-service?action=delete" method="post">
            <input type="hidden" id="deleteId" name="id">
            <div class="p-3">
                <p>Xóa khành hàng <b id="deleteName"></b> ?</p>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                <button type="submit" class="btn btn-danger">Xóa</button>
            </div>
        </form>
    </div>
</div>
<c:if test="${not empty message}">
    <div class="modal fade" id="messageModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content p-3 text-center">
                <div class="modal-body">
                    <p class="fs-6 fw-semibold">${message}</p>
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        var messageModal = new bootstrap.Modal(document.getElementById('messageModal'));
        messageModal.show();
    </script>
</c:if>

</body>
<script>
    function getInfoToDelete(id, name) {
        document.getElementById("deleteId").value = id;
        document.getElementById("deleteName").textContent = name;
    }
</script>
</html>
