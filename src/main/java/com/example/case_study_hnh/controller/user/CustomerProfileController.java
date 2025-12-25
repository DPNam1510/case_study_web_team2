package com.example.case_study_hnh.controller.user;

import com.example.case_study_hnh.entity.Account;
import com.example.case_study_hnh.entity.Customer;
import com.example.case_study_hnh.dto.CustomerDto;
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


@WebServlet(name = "CustomerProfileController", urlPatterns = "/customers")
public class CustomerProfileController extends HttpServlet {

    private final ICustomerService customerService = new CustomerService();
    private final ICustomerTypeService customerTypeService = new CustomerTypeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "profile";
        switch (action) {
            case "profile":
                showProfile(request, response);
                break;
            case "update":
                showFormUpdate(request, response);
                break;
            default:
                response.sendRedirect("/home-customer");
        }
    }

    private void showProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            response.sendRedirect("/login");
            return;
        }
        CustomerDto customerDto = customerService.findByUsername(account.getUsername());
        request.setAttribute("customer", customerDto);
        request.setAttribute("customerTypeList", customerTypeService.findAll());
        request.getRequestDispatcher("/view/customer/customerList.jsp").forward(request, response);
    }

    private void showFormUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = (String) request.getSession().getAttribute("message");
        if (message != null) {
            request.setAttribute("message", message);
            request.getSession().removeAttribute("message");
        }
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        CustomerDto customerDto = customerService.findByUsername(account.getUsername());
        request.setAttribute("customer", customerDto);
        request.setAttribute("customerTypeList", customerTypeService.findAll());
        request.getRequestDispatcher("/view/customer/updateCustomer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "update";
        switch (action) {
            case "update":
                updateCustomer(request, response);
                break;
            default:
                response.sendRedirect("/home-customer");
        }
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        String username = account.getUsername();
        int customerTypeId = Integer.parseInt(request.getParameter("customerTypeId"));
        String name = request.getParameter("name");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String birthdayStr = request.getParameter("birthday");
        LocalDate birthday = null;
        if (birthdayStr != null && !birthdayStr.isEmpty()) {
            birthday = LocalDate.parse(birthdayStr);
        }

        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        Customer customer = new Customer(username, customerTypeId, name, gender, birthday, email, phone, address);


        boolean success = customerService.update(customer);

        request.setAttribute("customer", customerService.findByUsername(account.getUsername()));
        request.setAttribute("customerTypeList", customerTypeService.findAll());
        request.setAttribute("message", success ? "Cập nhật thành công" : "Cập nhật thất bại");
        response.sendRedirect(request.getContextPath() +"/customers");
    }
}


