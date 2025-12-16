package com.example.case_study_hnh.controller.admin;

import com.example.case_study_hnh.service.AdminMedicalFormsService;
import com.example.case_study_hnh.service.IAdminMedicalFormsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "UpdateMedicalFormsController",value = "/admin-update-status")
public class UpdateMedicalFormsController extends HttpServlet {
    private IAdminMedicalFormsService adminMedicalFormsService = new AdminMedicalFormsService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int formId = Integer.parseInt(req.getParameter("formId"));

        if ("approve".equals(action)) {
           adminMedicalFormsService.updateStatus(formId, "Completed");

        } else if ("cancel".equals(action)) {
            adminMedicalFormsService.updateStatus(formId, "Rejected");

        } else if ("delete".equals(action)) {
            adminMedicalFormsService.delete(formId);
        }

        resp.sendRedirect(req.getContextPath() + "/admin-registered-service");
    }
}
