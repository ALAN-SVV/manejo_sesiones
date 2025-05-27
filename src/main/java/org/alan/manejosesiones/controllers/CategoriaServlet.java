package org.alan.manejosesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.alan.manejosesiones.models.Categorias;
import org.alan.manejosesiones.services.CategoriaService;
import org.alan.manejosesiones.services.CategoriaServiceImplement;
import org.alan.manejosesiones.services.LoginService;
import org.alan.manejosesiones.services.LoginServiceSessionImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet("/categorias")
public class CategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        LoginService auth = new LoginServiceSessionImplement();
        Optional<String> usernameOptional = auth.getUserName(req);
        CategoriaService service = new CategoriaServiceImplement();
        List<Categorias> categorias = service.listar();

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Lista de Categorías</title>");
        out.println("<link rel='stylesheet' href='" + req.getContextPath() + "/resources/css/styles.css'>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>Lista de categorías</h1>");

        if (usernameOptional.isPresent()) {
            out.println("<h2>Bienvenido " + usernameOptional.get() + "</h2>");
            out.println("<table>");
            out.println("<tr><th>ID</th><th>NOMBRE</th><th>DESCRIPCIÓN</th><th>ESTADO</th></tr>");
            categorias.forEach(c -> {
                out.println("<tr>");
                out.println("<td>" + c.getId() + "</td>");
                out.println("<td>" + c.getNombre() + "</td>");
                out.println("<td>" + c.getDescripcion() + "</td>");
                out.println("<td>" + (c.isActiva() ? "Activa" : "Inactiva") + "</td>");
                out.println("</tr>");
            });
            out.println("</table>");
        } else {
            out.println("<table>");
            out.println("<tr><th>ID</th><th>NOMBRE</th><th>DESCRIPCIÓN</th></tr>");
            categorias.forEach(c -> {
                out.println("<tr>");
                out.println("<td>" + c.getId() + "</td>");
                out.println("<td>" + c.getNombre() + "</td>");
                out.println("<td>" + c.getDescripcion() + "</td>");
                out.println("</tr>");
            });
            out.println("</table>");
            out.println("<p>Inicia sesión para ver el estado de las categorías</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}