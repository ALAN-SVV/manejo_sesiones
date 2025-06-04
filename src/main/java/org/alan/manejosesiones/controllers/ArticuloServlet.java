package org.alan.manejosesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.alan.manejosesiones.models.Articulo;
import org.alan.manejosesiones.services.ArticuloService;
import org.alan.manejosesiones.services.ArticuloServiceJdbcImplement;
import org.alan.manejosesiones.services.LoginService;
import org.alan.manejosesiones.services.LoginServiceSessionImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/articulo")
public class ArticuloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Obtenemos la conexión a la base de datos desde el atributo de la solicitud
        Connection conn = (Connection) req.getAttribute("conn");

        // Creamos una instancia del servicio de artículos con la conexión actual
        ArticuloService service = new ArticuloServiceJdbcImplement(conn);

        // Obtenemos la lista de todos los artículos desde la base de datos
        List<Articulo> articulos = service.listar();

        // Creamos una instancia del servicio de autenticación para obtener el nombre del usuario actual
        LoginService auth = new LoginServiceSessionImplement();
        Optional<String> userName = auth.getUserName(req);

        // Establecemos la lista de artículos como atributo de la solicitud para enviarla a la vista
        req.setAttribute("articulos", articulos);

        // Establecemos el título de la página
        req.setAttribute("title", "Listado de Artículos");

        // Establecemos el nombre del usuario como atributo de la solicitud (si está presente)
        req.setAttribute("username", userName);

        // Redirigimos la solicitud y la respuesta a la vista JSP que mostrará los artículos
        getServletContext().getRequestDispatcher("/listarArticulos.jsp").forward(req, resp);
    }
}