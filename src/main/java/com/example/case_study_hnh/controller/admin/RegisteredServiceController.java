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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AdminMedicalFormsDto> adminMedicalFormsDtoList = adminMedicalFormsService.getAll();
        req.setAttribute("adminMedicalFormsDtoList",adminMedicalFormsDtoList);
        req.getRequestDispatcher("/view/admin/registeredService.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
