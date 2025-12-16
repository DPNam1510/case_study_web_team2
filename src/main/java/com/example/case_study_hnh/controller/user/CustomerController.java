package com.example.case_study_hnh.controller.user;

import com.example.case_study_hnh.entity.Account;
import com.example.case_study_hnh.entity.Customer;
import com.example.case_study_hnh.service.CustomerService;
import com.example.case_study_hnh.service.ICustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;


@WebServlet(name = "CustomerController", urlPatterns = "/customers")
public class CustomerController extends HttpServlet {

    private final ICustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            response.sendRedirect("/login");
            return;
        }

        String action = request.getParameter("action");
        if ("update".equals(action)) {
            showUpdateForm(request, response, account);
        } else {
            showProfile(request, response, account);
        }
    }

    private void showProfile(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {

        Customer customer = customerService.findByUsername(account.getUsername());
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/view/customer/customerList.jsp").forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {

        Customer customer = customerService.findByUsername(account.getUsername());
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/view/customer/updateCustomer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            response.sendRedirect("/login");
            return;
        }

        Customer oldCustomer = customerService.findByUsername(account.getUsername());

        Customer customer = new Customer(
                oldCustomer.getId(),
                oldCustomer.getUsername(),
                oldCustomer.getCustomerTypeId(),
                request.getParameter("name"),
                Boolean.parseBoolean(request.getParameter("gender")),
                LocalDate.parse(request.getParameter("birthday")),
                request.getParameter("email"),
                request.getParameter("phone"),
                request.getParameter("address")
        );

        customerService.update(customer);
        response.sendRedirect("/customers?message=success");
    }
}

