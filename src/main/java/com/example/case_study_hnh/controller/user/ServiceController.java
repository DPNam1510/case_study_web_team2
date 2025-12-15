package com.example.case_study_hnh.controller.user;

import com.example.case_study_hnh.entity.Service;
import com.example.case_study_hnh.service.IServiceService;
import com.example.case_study_hnh.service.ServiceService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServiceController", urlPatterns = "/services")
public class ServiceController extends HttpServlet {

    private final IServiceService serviceService = new ServiceService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "search":
                searchService(request, response);
                break;
            default:
                showServiceList(request, response);
                break;
        }
    }
    private void showServiceList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Service> serviceList = serviceService.findAll();
        request.setAttribute("serviceList", serviceList);
        request.getRequestDispatcher("/view/customer/serviceList.jsp").forward(request, response);
    }
    private void searchService(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Service> serviceList = serviceService.findByName(keyword);
        request.setAttribute("keyword", keyword);
        request.setAttribute("serviceList", serviceList);
        request.getRequestDispatcher("/view/customer/serviceList.jsp").forward(request, response);
    }
}
