package org.alan.manejosesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.alan.manejosesiones.models.Productos;
import org.alan.manejosesiones.services.LoginService;
import org.alan.manejosesiones.services.LoginServiceSessionImplement;
import org.alan.manejosesiones.services.ProductoService;
import org.alan.manejosesiones.services.ProductoServiceImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

// Define el servlet para manejar solicitudes en la ruta "/products"
@WebServlet("/products")
public class ProductosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Autenticación del usuario
        LoginService auth = new LoginServiceSessionImplement();
        Optional<String> usernameOptional = auth.getUserName(req);

        // Obtención de la lista de productos
        ProductoService service = new ProductoServiceImplement();
        List<Productos> productos = service.listar();

        // Configuración de la respuesta
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // Inicio del documento HTML
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Dashboard</title>");
        out.println("<link rel='stylesheet' href='" + req.getContextPath() + "/resources/css/dashboard.css'>");
        out.println("</head>");
        out.println("<body>");

        // Encabezado con información del usuario
        out.println("<div class='header'>");
        out.println("<h1>Productos clases</h1>");
        if (usernameOptional.isPresent()) {
            // Muestra el nombre del usuario y enlace de cierre de sesión
            out.println("<div class='user-info'>");
            out.println("<span>Bienvenido, <strong>" + usernameOptional.get() + "</strong></span>");
            out.println("<a href='" + req.getContextPath() + "/logout' class='logout-link'>Cerrar sesión</a>");
            out.println("</div>");
        }
        out.println("</div>");

        // Contenido principal de la página
        out.println("<main class='dashboard-content'>");
        out.println("<h2>Productos Disponibles</h2>");

        if (usernameOptional.isPresent()) {
            // Tabla completa para usuarios autenticados
            out.println("<table class='product-table'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Categoría</th>");
            out.println("<th>Precio</th>");
            out.println("<th>Acciones</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            // Itera sobre los productos y los muestra en la tabla
            productos.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getTipo() + "</td>");
                out.println("<td>$" + String.format("%.2f", p.getPrecio()) + "</td>");
                out.println("<td class='actions'>");
                out.println("<a href='#' class='btn btn-edit'>Editar</a>");
                out.println("<a href='#' class='btn btn-disable'>Desactivar</a>");
                out.println("<a href='#' class='btn btn-enable'>Activar</a>");
                out.println("</td>");
                out.println("</tr>");
            });

            out.println("</tbody>");
            out.println("</table>");
        } else {
            // Tabla reducida para usuarios no autenticados
            out.println("<table class='product-table'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Categoría</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            // Itera sobre los productos y los muestra sin precios ni acciones
            productos.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getTipo() + "</td>");
                out.println("</tr>");
            });

            out.println("</tbody>");
            out.println("</table>");
            // Mensaje para que los usuarios inicien sesión
            out.println("<p class='login-message'>Inicia sesión para ver precios y opciones de administración</p>");
        }

        out.println("</main>");
        out.println("</body>");
        out.println("</html>");
    }
}
