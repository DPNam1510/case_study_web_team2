package com.example.case_study_hnh.controller.user;

import com.example.case_study_hnh.entity.Customer;
import com.example.case_study_hnh.entity.MedicalForms;
import com.example.case_study_hnh.service.MedicalFormsService;
import com.example.case_study_hnh.service.IMedicalFormsService;
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
    private final IMedicalFormsService medicalFormsService = new MedicalFormsService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "delete":
                deleteMedicalForm(request, response);
                break;
            default:
                showListForm(request, response);
                break;
        }
    }

    private void showListForm(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        List<MedicalForms> medicalFormsList = medicalFormsService.findByCustomer(customer.getId());
        request.setAttribute("medicalFormsList", medicalFormsList);
        try {
            request.getRequestDispatcher("/view/medical/medicalList.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/view/medical/addMedicalForm.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        if ("add".equals(action)) {
            addMedicalForm(request, response);
        } else {
            response.sendRedirect("/medicalForms");
        }
    }

    private void addMedicalForm(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        LocalDate dateTime = LocalDate.now();
        LocalDateTime appointmentTime = LocalDateTime.parse(request.getParameter("appointmentTime"));
        MedicalForms medicalForms = new MedicalForms(0, customer.getId(), dateTime, appointmentTime, "status");
        medicalFormsService.add(medicalForms);
        response.sendRedirect("/medicalForms");
    }

    private void deleteMedicalForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        medicalFormsService.delete(id);
        response.sendRedirect("/medicalForms");
    }
}
