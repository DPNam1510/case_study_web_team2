package com.example.case_study_hnh.controller.user;

import com.example.case_study_hnh.entity.Customer;
import com.example.case_study_hnh.entity.Orders;
import com.example.case_study_hnh.service.IOrdersService;
import com.example.case_study_hnh.service.OrdersService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "OrdersController", urlPatterns = "/orders")
public class OrdersController extends HttpServlet {

    private final IOrdersService ordersService = new OrdersService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "view":
                showOrderDetail(request, response);
                break;
            case "delete":
                deleteOrder(request, response);
                break;
            default:
                showOrderList(request, response);
                break;
        }
    }
    private void showOrderList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        List<Orders> ordersList = ordersService.findByCustomer(customer.getId());

        request.setAttribute("ordersList", ordersList);
        request.getRequestDispatcher("/view/order/orderList.jsp")
                .forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/order/addOrder.jsp")
                .forward(request, response);
    }
    private void showOrderDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Orders orders = ordersService.findById(id);

        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/view/order/orderDetail.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addOrder(request, response);
        } else {
            response.sendRedirect("/orders");
        }
    }
    private void addOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        int payTypeId = Integer.parseInt(request.getParameter("payTypeId"));
        LocalDate dateTime = LocalDate.now();
        Orders orders = new Orders(0, customer.getId(), payTypeId, dateTime);
        ordersService.add(orders);
        response.sendRedirect("/orders");
    }
    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ordersService.delete(id);
        response.sendRedirect("/orders");
    }
}
