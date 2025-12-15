//package com.example.case_study_hnh.util;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import com.example.case_study_hnh.entity.Customer;
//
//import java.io.IOException;
//
//    @WebFilter(urlPatterns = {"/admin/*", "/user/*"})
//    public class AuthFilter implements Filter {
//
//        @Override
//        public void doFilter(ServletRequest req, ServletResponse res,
//                             FilterChain chain)
//                throws IOException, ServletException {
//
//            HttpServletRequest request = (HttpServletRequest) req;
//            HttpServletResponse response = (HttpServletResponse) res;
//
//            HttpSession session = request.getSession(false);
//            Customer customer = (session == null) ? null :
//                    (Customer) session.getAttribute("user");
//
//            String uri = request.getRequestURI();
//
//            // ❌ CHƯA ĐĂNG NHẬP
//            if (customer == null) {
//                response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
//                return;
//            }
//
//            // 🔒 ADMIN URL – CHỈ ADMIN ĐƯỢC VÀO
//            if (uri.startsWith(request.getContextPath() + "/admin")
//                    && !"ADMIN".equals(customer)) {
//                response.sendRedirect(request.getContextPath() + "/user/home");
//                return;
//            }
//
//            // 🔓 USER URL – ADMIN & CUSTOMER đều vào được
//            chain.doFilter(req, res);
//        }
//    }
//
