package com.example.case_study_hnh.controller.admin;

import com.example.case_study_hnh.entity.Service;
import com.example.case_study_hnh.repository.IServiceAdminRepository;
import com.example.case_study_hnh.repository.ServiceAdminRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServiceController", urlPatterns = "/admin/services")
public class ServiceController extends HttpServlet {
    private IServiceAdminRepository serviceRepository = new ServiceAdminRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteService(request, response);
                break;
            default:
                listServices(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("create".equals(action)) {
            createService(request, response);
        } else if ("edit".equals(action)) {
            updateService(request, response);
        }
    }

    private void listServices(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Service> services;
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            services = serviceRepository.searchByName(keyword);
            request.setAttribute("keyword", keyword);
        } else {
            services = serviceRepository.findAll();
        }
        
        request.setAttribute("services", services);
        request.getRequestDispatcher("/view/admin/serviceList.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/admin/serviceForm.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Service service = serviceRepository.findById(id);
        
        if (service != null) {
            request.setAttribute("service", service);
            request.getRequestDispatcher("/view/admin/serviceForm.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/services?error=notfound");
        }
    }

    private void createService(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String doctorName = request.getParameter("doctorName");
        
        Service service = new Service(name, doctorName);
        boolean success = serviceRepository.create(service);
        
        if (success) {
            response.sendRedirect(request.getContextPath() + "/admin/services?success=created");
        } else {
            request.setAttribute("error", "Không thể thêm dịch vụ");
            request.setAttribute("service", service);
            request.getRequestDispatcher("/view/admin/serviceForm.jsp").forward(request, response);
        }
    }

    private void updateService(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String doctorName = request.getParameter("doctorName");
        
        Service service = new Service(id, name, doctorName);
        boolean success = serviceRepository.update(service);
        
        if (success) {
            response.sendRedirect(request.getContextPath() + "/admin/services?success=updated");
        } else {
            request.setAttribute("error", "Không thể cập nhật dịch vụ");
            request.setAttribute("service", service);
            request.getRequestDispatcher("/view/admin/serviceForm.jsp").forward(request, response);
        }
    }

    private void deleteService(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean success = serviceRepository.delete(id);
        
        if (success) {
            response.sendRedirect(request.getContextPath() + "/admin/services?success=deleted");
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/services?error=deletefailed");
        }
    }
}
