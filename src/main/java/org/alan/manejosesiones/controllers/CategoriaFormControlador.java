package org.alan.manejosesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.alan.manejosesiones.models.Categoria;
import org.alan.manejosesiones.services.CategoriaService;
import org.alan.manejosesiones.services.CategoriaServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/categoria/form")
public class CategoriaFormControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Código existente (sin cambios)
        Connection conn = (Connection) req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);
        Long id;

        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Categoria categorias = new Categoria();
        if (id > 0) {
            Optional<Categoria> optionalCategoria = service.porId(id);
            if (optionalCategoria.isPresent()) {
                categorias = optionalCategoria.get();
            }
        }
        req.setAttribute("categorias", categorias);
        getServletContext().getRequestDispatcher("/formularioCategoria.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);

        // Obtener parámetros
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");

        // Validación de campos vacíos (nuevo código)
        boolean tieneErrores = false;

        if (nombre == null || nombre.trim().isEmpty()) {
            req.setAttribute("errorNombre", "Es obligatorio llenar este campo");
            tieneErrores = true;
        }

        if (descripcion == null || descripcion.trim().isEmpty()) {
            req.setAttribute("errorDescripcion", "Es obligatorio llenar este campo");
            tieneErrores = true;
        }

        // Si hay errores, reenvía al formulario con los mensajes
        if (tieneErrores) {
            Long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException e) {
                id = 0L;
            }

            Categoria categoria = new Categoria();
            categoria.setIdCategoria(id);
            categoria.setNombre(nombre != null ? nombre : "");
            categoria.setDescripcion(descripcion != null ? descripcion : "");

            req.setAttribute("categorias", categoria);
            getServletContext().getRequestDispatcher("/formularioCategoria.jsp").forward(req, resp);
            return;
        }

        // Si no hay errores, continúa con el proceso original
        Long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(id);
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
        service.guardar(categoria);

        resp.sendRedirect(req.getContextPath() + "/categoria");
    }
}