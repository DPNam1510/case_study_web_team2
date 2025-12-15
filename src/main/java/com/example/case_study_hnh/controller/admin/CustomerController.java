package com.example.case_study_hnh.controller.admin;

import com.example.case_study_hnh.entity.Customer;
import com.example.case_study_hnh.repository.CustomerAdminRepository;
import com.example.case_study_hnh.repository.ICustomerAdminRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerController", urlPatterns = "/admin/customers")
public class CustomerController extends HttpServlet {
    private ICustomerAdminRepository customerRepository = new CustomerAdminRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "view":
                viewCustomer(request, response);
                break;
            case "delete":
                deleteCustomer(request, response);
                break;
            default:
                listCustomers(request, response);
                break;
        }
    }

    private void listCustomers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Customer> customers;

        if (keyword != null && !keyword.trim().isEmpty()) {
            customers = customerRepository.searchByName(keyword);
            request.setAttribute("keyword", keyword);
        } else {
            customers = customerRepository.findAll();
        }

        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/view/admin/customerList.jsp").forward(request, response);
    }

    private void viewCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerRepository.findById(id);

        if (customer != null) {
            boolean hasUsedService = customerRepository.hasUsedService(id);
            request.setAttribute("customer", customer);
            request.setAttribute("hasUsedService", hasUsedService);
            request.getRequestDispatcher("/view/admin/customerDetail.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/customers?error=notfound");
        }
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        // Check if customer has used service
        if (customerRepository.hasUsedService(id)) {
            response.sendRedirect(request.getContextPath() + "/admin/customers?error=hasservice");
            return;
        }

        boolean success = customerRepository.deleteCustomer(id);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/admin/customers?success=deleted");
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/customers?error=deletefailed");
        }
    }
}