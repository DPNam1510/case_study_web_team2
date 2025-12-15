
package com.example.case_study_hnh.controller.auth;

import com.example.case_study_hnh.entity.Account;
import com.example.case_study_hnh.service.AccountService;
import com.example.case_study_hnh.service.IAccountService;
import com.example.case_study_hnh.util.CheckValidate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LoginController",value = "/login")
public class LoginController extends HttpServlet {
//    Xử lý đăng nhập, tạo session, phân luồng admin/user
    private final IAccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/auth/login.jsp").forward(req,resp);
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
        Account account = accountService.login(username,password);
        if (account == null) {
            req.setAttribute("loginErr", "username or password không trùng khớp");
            req.getRequestDispatcher("/view/auth/login.jsp").forward(req, resp);
            return;
        }

        req.getSession().setAttribute("account", account);

        if ("admin".equalsIgnoreCase(account.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/view/admin/dashboard.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/view/customer/home.jsp");
        }
    }
}