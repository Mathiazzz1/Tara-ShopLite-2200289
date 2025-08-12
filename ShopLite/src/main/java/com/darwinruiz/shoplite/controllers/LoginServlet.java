package com.darwinruiz.shoplite.controllers;

import com.darwinruiz.shoplite.models.User;
import com.darwinruiz.shoplite.repositories.UserRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

    private final UserRepository userRepository = new UserRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || password == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?err=1");
            return;
        }

        email = email.trim().toLowerCase();
        password = password.trim();

        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty() || !userOpt.get().getPassword().equals(password)) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?err=1");
            return;
        }

        HttpSession old = req.getSession(false);
        if (old != null) old.invalidate();

        User user = userOpt.get();
        HttpSession session = req.getSession(true);
        session.setAttribute("auth", true);
        session.setAttribute("userEmail", user.getEmail());
        session.setAttribute("role", user.getRole());
        session.setMaxInactiveInterval(30 * 60);


        String dest = (String) session.getAttribute("dest");
        if (dest != null) {
            session.removeAttribute("dest");
            resp.sendRedirect(dest);
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }
}






//ZZZ