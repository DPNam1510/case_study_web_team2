package com.example.case_study_hnh.controller.user;

import com.example.case_study_hnh.entity.Customer;
import com.example.case_study_hnh.entity.MedicalForms;
import com.example.case_study_hnh.service.CustomerTypeService;
import com.example.case_study_hnh.service.MedicalFormsService;
import com.example.case_study_hnh.service.IMedicalFormsService;
import com.example.case_study_hnh.dto.MedicalHistoryDto;
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
    private final IMedicalFormsService medicalFormsService = new MedicalFormsService();
    private final IServiceService serviceService = new ServiceService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "history":
                showHistory(request, response);
                break;
            default:
                showListForm(request, response);
                break;
        }
    }

    private void showListForm(HttpServletRequest request, HttpServletResponse response) {
        List<MedicalForms> medicalFormsList = medicalFormsService.findAll();
        request.setAttribute("medicalFormsList", medicalFormsList);

        try {
            request.getRequestDispatcher("/view/customer/medicalList.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("serviceList", serviceService.findAll());
        try {
            request.getRequestDispatcher("/view/customer/addMedicalForm.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showHistory(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("historyList", medicalFormsService.findAllHistory());
        try {
            request.getRequestDispatcher("/view/medical_form/history.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
                response.sendRedirect("/medicalForms");
        }
    }

    private void addMedicalForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        int customerId = (int) session.getAttribute("customerId");
        MedicalForms form = new MedicalForms();
        form.setCustomerId(customerId);
        form.setDateTime(LocalDate.now());
        form.setAppointmentTime(LocalDateTime.parse(request.getParameter("appointmentTime")));
        form.setStatus("NEW");
        medicalFormsService.add(form);
        response.sendRedirect("/medicalForms");
    }

    private void deleteMedicalForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        medicalFormsService.delete(id);
        response.sendRedirect("/medicalForms");
    }
}
