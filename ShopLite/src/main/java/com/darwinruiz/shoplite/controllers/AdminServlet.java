package com.darwinruiz.shoplite.controllers;

import com.darwinruiz.shoplite.models.Product;
import com.darwinruiz.shoplite.repositories.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/admin", "/admin/create"})
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("""
            <h1>Panel de Administración</h1>
            %s
            <form action='%s/admin/create' method='post'>
              <label>Nombre: <input name='name' required></label><br>
              <label>Precio: <input name='price' type='number' step='0.01' min='0.01' required></label><br>
              <button type='submit'>Crear</button>
            </form>
        """.formatted(
                "1".equals(req.getParameter("err")) ? "<p style='color:red'>Datos inválidos</p>" : "",
                req.getContextPath()
        ));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");

        double price = Double.NaN;
        if (priceStr != null) {
            try {
                price = Double.parseDouble(priceStr.trim().replace(',', '.'));
            } catch (NumberFormatException ignored) {}
        }

        if (name == null || name.trim().isEmpty() || !(price > 0.0)) {
            resp.sendRedirect(req.getContextPath() + "/admin?err=1");
            return;
        }

        long id = ProductRepository.nextId();
        Product p = new Product(id, name.trim(), price);
        ProductRepository.save(p);

        resp.sendRedirect(req.getContextPath() + "/home");
    }
}

//ZZZ