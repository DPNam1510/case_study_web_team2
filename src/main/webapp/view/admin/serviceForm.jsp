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
    <title>${service != null ? 'Cập nhật' : 'Thêm'} Dịch vụ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h4 class="mb-0">
                        <i class="fas fa-stethoscope"></i>
                        ${service != null ? 'Cập nhật' : 'Thêm'} Dịch vụ
                    </h4>
                </div>
                <div class="card-body">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">
                            <i class="fas fa-exclamation-circle"></i> ${error}
                        </div>
                    </c:if>

                    <form method="post" action="${pageContext.request.contextPath}/admin/services">
                        <input type="hidden" name="action" value="${service != null ? 'edit' : 'create'}">
                        <c:if test="${service != null}">
                            <input type="hidden" name="id" value="${service.id}">
                        </c:if>

                        <div class="mb-3">
                            <label for="name" class="form-label">
                                Tên dịch vụ <span class="text-danger">*</span>
                            </label>
                            <input type="text" class="form-control" id="name" name="name"
                                   value="${service != null ? service.name : ''}"
                                   required maxlength="50">
                        </div>

                        <div class="mb-3">
                            <label for="doctorName" class="form-label">
                                Bác sĩ phụ trách <span class="text-danger">*</span>
                            </label>
                            <input type="text" class="form-control" id="doctorName" name="doctorName"
                                   value="${service != null ? service.doctorName : ''}"
                                   required maxlength="20">
                        </div>

                        <div class="d-flex justify-content-between">
                            <a href="${pageContext.request.contextPath}/admin/services"
                               class="btn btn-secondary">
                                <i class="fas fa-arrow-left"></i> Quay lại
                            </a>
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-save"></i> ${service != null ? 'Cập nhật' : 'Thêm mới'}
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>