package com.example.case_study_hnh.controller.user;

import com.example.case_study_hnh.dto.MedicalFormAddDto;
import com.example.case_study_hnh.entity.Account;
import com.example.case_study_hnh.entity.MedicalForms;
import com.example.case_study_hnh.service.MedicalFormsService;
import com.example.case_study_hnh.service.IMedicalFormsService;
import com.example.case_study_hnh.service.IServiceService;
import com.example.case_study_hnh.service.ServiceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "MedicalFormsController", urlPatterns = "/medicalForms")
public class MedicalFormsController extends HttpServlet {
    private IMedicalFormsService medicalFormsService = new MedicalFormsService();
    private IServiceService serviceService = new ServiceService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "add":
                showFormAdd(request, response);
                break;
            case "list":
                showList(request, response);
                break;
            default:
                response.sendRedirect("/home-customer");
        }
    }

    private void showFormAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId");

        if (customerId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setAttribute("customerId", customerId);
        request.setAttribute("serviceList", serviceService.findAll());
        request.getRequestDispatcher("/view/customer/addMedicalForm.jsp").forward(request, response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        request.setAttribute("medicalForms", medicalFormsService.findByUsername(username));
        request.getRequestDispatcher("/view/customer/medicalList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "add":
                addMedicalForm(request, response);
                break;
            case "delete":
                deleteMedicalForm(request, response);
                break;
            default:
                response.sendRedirect("/home-customer");
        }
    }

    private void addMedicalForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));
        LocalDate medicalDate = LocalDate.parse(request.getParameter("medicalDate"));
        LocalDateTime appointmentTime = LocalDateTime.parse(request.getParameter("appointmentTime"));
        MedicalFormAddDto medicalFormAddDto = new MedicalFormAddDto(customerId, serviceId, medicalDate, appointmentTime);
        boolean isAddSuccess = medicalFormsService.add(medicalFormAddDto);
        String mess = isAddSuccess ? "Product added successfully" : "Product not added";
        try {
            response.sendRedirect("/medicalForms?action=list&message=" + mess);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteMedicalForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int medicalFormId = Integer.parseInt(request.getParameter("id"));
        medicalFormsService.deleteById(medicalFormId);

        response.sendRedirect(request.getContextPath()
                + "/medicalForms?action=list");
    }
}
