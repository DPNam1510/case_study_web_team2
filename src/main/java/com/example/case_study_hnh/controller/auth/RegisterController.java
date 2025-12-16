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

@WebServlet(name = "RegisterController", value = "/register")
public class RegisterController extends HttpServlet {

    private final IAccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Mở trang đăng ký: dùng trang login.jsp (chứa 2 tab) và bật tab Register
        req.setAttribute("tab", "register");
        req.getRequestDispatcher("/view/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String nameErr = "";
        String passErr = "";

        // Luôn giữ tab Register sau khi submit
        req.setAttribute("tab", "register");

        // Validate username
        if (username == null || username.trim().isEmpty()) {
            nameErr = "username không được để trống";
        } else if (!CheckValidate.checkName(username)) {
            nameErr = "username sai format, chữ cái đầu phải là in hoa và chỉ được sử dụng kí tự dấu gạch '_' ";
        }

        // Validate password
        if (password == null || password.trim().isEmpty()) {
            passErr = "password không được để trống";
        } else if (!CheckValidate.checkPass(password)) {
            passErr = "password sai format, không được có kí tự đặc biệt";
        }

        // Nếu có lỗi -> vẫn ở Register và show lỗi
        if (!nameErr.isEmpty() || !passErr.isEmpty()) {
            req.setAttribute("username", username);
            req.setAttribute("nameErr", nameErr);
            req.setAttribute("passErr", passErr);
            req.getRequestDispatcher("/view/auth/login.jsp").forward(req, resp);
            return;
        }

        // Check username tồn tại
        if (accountService.existUsername(username.trim())) {
            req.setAttribute("username", username);
            req.setAttribute("error", "Username đã tồn tại!");
            req.getRequestDispatcher("/view/auth/login.jsp").forward(req, resp);
            return;
        }

        // Register
        Account account = new Account(username.trim(), password.trim(), "customer");
        boolean ok = accountService.register(account);

        if (ok) {
            req.setAttribute("tab", "register");
            req.setAttribute("success", "Đăng ký thành công!");
            req.setAttribute("username", username); // GIỮ NGUYÊN username, không cho biến mất
            req.getRequestDispatcher("/view/auth/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("username", username);
            req.setAttribute("error", "Đăng ký thất bại (DB). Kiểm tra console log!");
            req.getRequestDispatcher("/view/auth/login.jsp").forward(req, resp);
        }
    }
}