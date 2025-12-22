package com.example.case_study_hnh.controller.user;

import com.example.case_study_hnh.entity.Account;
import com.example.case_study_hnh.entity.Customer;
import com.example.case_study_hnh.service.CustomerService;
import com.example.case_study_hnh.service.CustomerTypeService;
import com.example.case_study_hnh.service.ICustomerService;
import com.example.case_study_hnh.service.ICustomerTypeService;
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
    private final ICustomerTypeService customerTypeService = new CustomerTypeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "update":
                showFormUpdate(request, response);
                break;
            default:
        }
    }

    private void showFormUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            response.sendRedirect("/login");
            return;
        }
        Customer customer = customerService.findByUsername(account.getUsername());
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/view/customer/updateCustomer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "update":
                updateProduct(request, response);
                break;
            default:
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String customerTypeName = request.getParameter("customerTypeName");
        String name = request.getParameter("name");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        LocalDate birthday = LocalDate.parse(request.getParameter("birthday"));
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        Customer customer = new Customer(username, customerTypeName, name, gender, birthday, email, phone, address);
        request.setAttribute("customer", customerService.update(customer));
        request.setAttribute("customerTypeList", customerTypeService.findAll());
        try {
            request.getRequestDispatcher("/view/customer/updateCustomer.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }


    }
}

