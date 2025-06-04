package org.alan.manejosesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.alan.manejosesiones.services.ArticuloService;
import org.alan.manejosesiones.services.ArticuloServiceJdbcImplement;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/articulo/cambiar-estado")
public class CambiarEstadoArticuloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ArticuloService service = new ArticuloServiceJdbcImplement(conn);

        try {
            Long id = Long.parseLong(req.getParameter("id"));
            boolean estadoActual = "1".equals(req.getParameter("estado")); // Convertir a boolean

            // Cambia al estado opuesto
            ((ArticuloServiceJdbcImplement) service).cambiarEstado(id, !estadoActual);

        } catch (Exception e) {
            e.printStackTrace();
            req.getSession().setAttribute("error", "Error al cambiar estado: " + e.getMessage());
        }

        resp.sendRedirect(req.getContextPath() + "/articulo"); // Redirige al listado
    }
}