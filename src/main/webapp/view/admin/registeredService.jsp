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
    <!-- Header -->
    <div class="row align-items-center mb-4">
      <div class="col-md-8">
        <h2 class="fw-bold text-primary mb-1">
          Danh sách dịch vụ đã được đăng ký
        </h2>
        <p class="text-muted mb-0">
          Quản lý và theo dõi các dịch vụ y tế mà khách hàng đã đăng ký
        </p>
      </div>
      <div class="col-md-4 text-end">
        <a href="/view/admin/dashboard.jsp" class="btn btn-outline-primary">
          ← Quay lại Dashboard
        </a>
      </div>
    </div>

    <!-- Bảng danh sách phiếu khám -->
    <div class="row">
      <div class="col-lg-9">
        <div class="card shadow-sm border-0">
          <div class="card-body p-0">
            <div class="table-responsive">
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
                <c:if test="${empty adminMedicalFormsDtoList}">
                  <tr>
                    <td colspan="6" class="text-center text-muted py-4">
                      Chưa có dữ liệu đăng ký dịch vụ
                    </td>
                  </tr>
                </c:if>
                <c:forEach var="admf" items="${adminMedicalFormsDtoList}">
                  <tr id="row-${admf.formsId}">
                    <td class="fw-semibold">${admf.formsId}</td>
                    <td>${admf.customerName}</td>
                    <td>${admf.serviceName}</td>
                    <td>${admf.doctorName}</td>
                    <td id="status-${admf.formsId}">${admf.status}</td>
                    <td>
                      <!-- Chỉ hiển thị Duyệt / Hủy khi Pending -->
                      <c:if test="${admf.status eq 'Pending'}">
                        <!-- Duyệt -->
                        <form action="${pageContext.request.contextPath}/admin-update-status"
                              method="post" style="display:inline">
                          <input type="hidden" name="formId" value="${admf.formsId}">
                          <input type="hidden" name="action" value="approve">
                          <button class="btn btn-success btn-sm">Duyệt</button>
                        </form>
                        <!-- Hủy -->
                        <form action="${pageContext.request.contextPath}/admin-update-status"
                              method="post" style="display:inline">
                          <input type="hidden" name="formId" value="${admf.formsId}">
                          <input type="hidden" name="action" value="cancel">
                          <button class="btn btn-danger btn-sm">Hủy</button>
                        </form>
                      </c:if>
                      <!-- Xóa: luôn cho phép -->
                      <form action="${pageContext.request.contextPath}/admin-update-status"
                            method="post"
                            style="display:inline"
                            onsubmit="return confirm('Bạn có chắc chắn muốn xóa không?')">
                        <input type="hidden" name="formId" value="${admf.formsId}">
                        <input type="hidden" name="action" value="delete">
                        <button class="btn btn-warning btn-sm">Xóa</button>
                      </form>
                    </td>

                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>

      <!-- Illustration -->
      <div class="col-lg-3 d-none d-lg-flex align-items-center justify-content-center">
        <img src="https://cdn-icons-png.flaticon.com/512/2966/2966327.png" class="img-fluid" alt="Medical Illustration">
      </div>
    </div>
  </div>
</div>
</body>
</html>
