package com.example.case_study_hnh.controller.user;

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
import java.util.List;

@WebServlet(name = "CustomerController", urlPatterns = "/customers")
public class CustomerController extends HttpServlet {
    //    Xem & sửa thông tin cá nhân
    private final ICustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "update":
                showFormUpdate(request, response);
                break;
            default:
                showListForm(request, response);
                break;
        }
    }

    private void showListForm(HttpServletRequest request, HttpServletResponse response) {
        List<Customer> customerList = customerService.findAll();
        request.setAttribute("customerList", customerList);
        try {
            request.getRequestDispatcher("/view/customer/customerList.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showFormUpdate(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerService.findById(id);
        request.setAttribute("customer", customer);
        try {
            request.getRequestDispatcher("/view/customer/updateCustomer.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "update":
                updateCustomer(request, response);
                break;
            default:
                response.sendRedirect("/customers");
        }
    }
    protected void updateCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String userName = request.getParameter("userName");
        int customerTypeId = Integer.parseInt(request.getParameter("customer_type_id"));
        String name = request.getParameter("name");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        LocalDate birthday = LocalDate.parse(request.getParameter("birthday"));
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        Customer customer = new Customer(id, userName, customerTypeId, name, gender, birthday, email, phone, address);
        boolean result = customerService.update(customer);
        String message = result ? "success" : "fail";
        try {
            response.sendRedirect("/customers?message="+message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
