package com.example.case_study_hnh.controller.auth;

import com.example.case_study_hnh.entity.Account;
import com.example.case_study_hnh.service.AccountService;
import com.example.case_study_hnh.util.CheckValidate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RegisterController", value = "/register")
public class RegisterController extends HttpServlet {
    private final AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String nameErr = "";
        String passErr = "";
        if (username==null || username.trim().isEmpty()){
            nameErr = "username không được để trống";
        } else if (!CheckValidate.checkName(username)) {
            nameErr = "username sai format, chữ cái đầu phải là in hoa và chỉ được sử dụng kí tự dấu gạch '_' ";
        }
        if (password==null || password.trim().isEmpty()){
            passErr = "password không được để trống";
        } else if (!CheckValidate.checkPass(password)) {
            passErr = "password sai format, không được có kí tự đặc biệt";
        }

        if (!nameErr.isEmpty() || !passErr.isEmpty()){
            req.setAttribute("username",username);
            req.setAttribute("password",password);

            req.setAttribute("nameErr",nameErr);
            req.setAttribute("passErr",passErr);
            req.getRequestDispatcher("/view/auth/login.jsp").forward(req,resp);
            return;
        }
        if (accountService.existUsername(username)) {
            req.setAttribute("error", "Username đã tồn tại!");
            req.getRequestDispatcher("/view/auth/login.jsp").forward(req, resp);
            return;
        }

        Account account = new Account(username.trim(), password.trim(), "customer");

        boolean ok = accountService.register(account);
        if (ok) {
            resp.sendRedirect(req.getContextPath() + "/login?mess=register_success");
        } else {
            req.setAttribute("error", "Đăng ký thất bại (DB). Kiểm tra console log!");
            req.getRequestDispatcher("/view/auth/login.jsp").forward(req, resp);
        }
    }
}
