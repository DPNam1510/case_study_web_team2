package com.example.case_study_hnh.controller.user;

import com.example.case_study_hnh.dto.CustomerDto;
import com.example.case_study_hnh.entity.Account;
import com.example.case_study_hnh.service.CustomerService;
import com.example.case_study_hnh.service.ICustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "HomeCustomerController", urlPatterns = "/home-customer")
public class HomeCustomerController extends HttpServlet {
    private final ICustomerService customerService = new CustomerService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/customer/home_customer.jsp").forward(request, response);
    }
}
