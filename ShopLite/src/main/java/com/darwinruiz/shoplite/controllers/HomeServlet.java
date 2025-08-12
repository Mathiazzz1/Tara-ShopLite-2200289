
package com.darwinruiz.shoplite.controllers;

import com.darwinruiz.shoplite.models.Product;
import com.darwinruiz.shoplite.repositories.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> all = ProductRepository.findAll();

        int page = 1;
        int size = 6;
        try { page = Integer.parseInt(req.getParameter("page")); } catch (Exception ignored) {}
        try { size = Integer.parseInt(req.getParameter("size")); } catch (Exception ignored) {}
        if (page < 1) page = 1;
        if (size < 1) size = 6;

        int total = all.size();
        int fromIndex = Math.min((page - 1) * size, total);
        int toIndex = Math.min(fromIndex + size, total);

        List<Product> items = all.subList(fromIndex, toIndex);

        req.setAttribute("items", items);
        req.setAttribute("page", page);
        req.setAttribute("size", size);
        req.setAttribute("total", total);

        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
//ZZZ