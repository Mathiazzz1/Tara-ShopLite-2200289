package com.darwinruiz.shoplite.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private static final Set<String> PUBLIC_PATHS = Set.of(
            "/", "/index.jsp", "/login.jsp", "/auth/login"
    );

    private boolean isStatic(String p) {
        String path = p.toLowerCase();
        return path.startsWith("/assets/")
                || path.startsWith("/static/")
                || path.startsWith("/resources/")
                || path.startsWith("/webjars/")
                || path.startsWith("/favicon")
                || path.endsWith(".css") || path.endsWith(".js")
                || path.endsWith(".png") || path.endsWith(".jpg")
                || path.endsWith(".jpeg") || path.endsWith(".gif")
                || path.endsWith(".svg") || path.endsWith(".ico")
                || path.endsWith(".woff") || path.endsWith(".woff2")
                || path.endsWith(".ttf");
    }

    private String requestPath(HttpServletRequest req) {
        final String uri = req.getRequestURI();
        final String ctx = req.getContextPath();
        String path = uri.substring(ctx.length());
        if (path.isEmpty()) path = "/";
        if (path.length() > 1 && path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }

    private boolean isPublic(HttpServletRequest req) {
        String path = requestPath(req);
        if (isStatic(path)) return true;
        return PUBLIC_PATHS.contains(path);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req  = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        if (isPublic(req)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        boolean authenticated = session != null && Boolean.TRUE.equals(session.getAttribute("auth"));

        if (!authenticated) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}
//ZZZ