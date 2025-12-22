package com.example.case_study_hnh.controller.admin;

import com.example.case_study_hnh.dto.AdminMedicalFormsDto;
import com.example.case_study_hnh.service.AdminMedicalFormsService;
import com.example.case_study_hnh.service.IAdminMedicalFormsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RegisteredServiceController",value = "/admin-registered-service")
public class RegisteredServiceController extends HttpServlet {
    private IAdminMedicalFormsService adminMedicalFormsService = new AdminMedicalFormsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "complete":
                showCompletedList(req, resp);
                break;
            case "rejected":
                showListRejected(req,resp);
                break;
            case "search_complete":
                searchComplete(req,resp);
                break;
            case "search_pending":
                searchPending(req,resp);
                break;
            case "search_reject":
                searchReject(req,resp);
                break;
            default:
                showPendingList(req, resp);
                break;
        }
    }

    private void searchReject(HttpServletRequest req, HttpServletResponse resp) {
        String customerName = req.getParameter("name");
        String serviceName = req.getParameter("service");
        List<AdminMedicalFormsDto> adminMedicalFormsDto = null;

        if (!customerName.trim().isEmpty() || !serviceName.trim().isEmpty()){
            adminMedicalFormsDto = adminMedicalFormsService.searchReject(customerName,serviceName);
        }else {
            adminMedicalFormsDto = adminMedicalFormsService.getListRejected();
        }
        req.setAttribute("list",adminMedicalFormsDto);
        req.setAttribute("customerName",customerName);
        req.setAttribute("serviceName",serviceName);
        req.setAttribute("status", "rejected");

        try {
            req.getRequestDispatcher("/view/admin/registeredService.jsp").forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void searchPending(HttpServletRequest req, HttpServletResponse resp) {
        String customerName = req.getParameter("name");
        String serviceName = req.getParameter("service");
        List<AdminMedicalFormsDto> adminMedicalFormsDto = null;

        if (!customerName.trim().isEmpty() || !serviceName.trim().isEmpty()){
            adminMedicalFormsDto = adminMedicalFormsService.searchPending(customerName,serviceName);
        }else {
            adminMedicalFormsDto = adminMedicalFormsService.getAll();
        }
        req.setAttribute("list",adminMedicalFormsDto);
        req.setAttribute("customerName",customerName);
        req.setAttribute("serviceName",serviceName);
        req.setAttribute("status", "pending");

        try {
            req.getRequestDispatcher("/view/admin/registeredService.jsp").forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void searchComplete(HttpServletRequest req, HttpServletResponse resp) {
        String customerName = req.getParameter("name");
        String serviceName = req.getParameter("service");
        List<AdminMedicalFormsDto> adminMedicalFormsDto = null;

        if (!customerName.trim().isEmpty() || !serviceName.trim().isEmpty()){
            adminMedicalFormsDto = adminMedicalFormsService.searchApprove(customerName,serviceName);
        }else {
            adminMedicalFormsDto = adminMedicalFormsService.getList();
        }
        req.setAttribute("list",adminMedicalFormsDto);
        req.setAttribute("customerName",customerName);
        req.setAttribute("serviceName",serviceName);
        req.setAttribute("status", "complete");

        try {
            req.getRequestDispatcher("/view/admin/registeredService.jsp").forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showPendingList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<AdminMedicalFormsDto> list = adminMedicalFormsService.getAll();
        req.setAttribute("list", list);
        req.setAttribute("status", "pending");

        req.getRequestDispatcher("/view/admin/registeredService.jsp")
                .forward(req, resp);
    }

    private void showCompletedList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<AdminMedicalFormsDto> list = adminMedicalFormsService.getList();
        req.setAttribute("list", list);
        req.setAttribute("status", "complete");

        req.getRequestDispatcher("/view/admin/registeredService.jsp")
                .forward(req, resp);
    }

    private void showListRejected(HttpServletRequest req, HttpServletResponse resp) {
        List<AdminMedicalFormsDto> list = adminMedicalFormsService.getListRejected();
        req.setAttribute("list", list);
        req.setAttribute("status", "rejected");

        try {
            req.getRequestDispatcher("/view/admin/registeredService.jsp")
                    .forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "approve":
                approve(req, resp);
                break;
            case "reject":
                reject(req, resp);
                break;
            case "delete":
                delete(req,resp);
                break;
            default:
                showPendingList(req, resp);
                break;
        }
    }


    private void approve(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int formId = Integer.parseInt(req.getParameter("id"));

        boolean success = adminMedicalFormsService.approve(formId);

        String message = success
                ? "Duyệt thành công"
                : "Khách hàng chưa đủ thông tin";

        req.setAttribute("message", message);

        showPendingList(req, resp);
    }
    private void reject(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int formId = Integer.parseInt(req.getParameter("id"));

        boolean success = adminMedicalFormsService.reject(formId);

        String message = success
                ? "Hủy thành công"
                : "Hủy thất bại";

        req.setAttribute("message", message);

        showPendingList(req, resp);
    }


    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean isSuccess = adminMedicalFormsService.delete(id);
        String message = isSuccess ? "Xóa thành công" : "Xóa không thành công";
        req.setAttribute("message",message);
        try {
            showCompletedList(req,resp);
            showPendingList(req,resp);
            showListRejected(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
