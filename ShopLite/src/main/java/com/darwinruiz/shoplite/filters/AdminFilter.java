package com.darwinruiz.shoplite.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/admin", "/admin/*"})
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req  = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        boolean hasSession = session != null && Boolean.TRUE.equals(session.getAttribute("auth"));
        String role = hasSession ? String.valueOf(session.getAttribute("role")) : null;

        if (!hasSession || !"ADMIN".equals(role)) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            RequestDispatcher rd = req.getRequestDispatcher("/403.jsp");
            rd.forward(req, resp);
            return;
        }

        chain.doFilter(request, response);
    }
}



//ZZZ