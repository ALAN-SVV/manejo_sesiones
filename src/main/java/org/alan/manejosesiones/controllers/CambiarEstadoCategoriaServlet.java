package org.alan.manejosesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.alan.manejosesiones.services.CategoriaService;
import org.alan.manejosesiones.services.CategoriaServiceJdbcImplement;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/categoria/cambiar-estado")
public class CambiarEstadoCategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);

        try {
            Long id = Long.parseLong(req.getParameter("id"));
            boolean estadoActual = Boolean.parseBoolean(req.getParameter("estado"));

            // Cambia al estado opuesto
            ((CategoriaServiceJdbcImplement) service).cambiarEstado(id, !estadoActual);

        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/categoria"); // Redirige al listado
    }
}