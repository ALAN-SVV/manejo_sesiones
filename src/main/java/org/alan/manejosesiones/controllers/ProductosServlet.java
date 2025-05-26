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

@WebServlet("/products")
public class ProductosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        LoginService auth = new LoginServiceSessionImplement();
        Optional<String> usernameOptional = auth.getUserName(req);
        ProductoService service = new ProductoServiceImplement();
        List<Productos> productos = service.listar();

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Lista de Productos</title>");
        // Enlace al los estilos generales
        out.println("<link rel='stylesheet' href='" + req.getContextPath() + "/resources/css/styles.css'>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>Lista de productos</h1>");

        if (usernameOptional.isPresent()) { // Verifica si el usuario está presente
            out.println("<h2>Hola usuario " + usernameOptional.get() + "</h2>"); // Muestra un saludo al usuario
            out.println("<table>"); // Inicia la tabla de productos
            out.println("<tr><th>ID</th><th>NOMBRE</th><th>CATEGORIA</th><th>PRECIO</th></tr>"); // Encabezados de la tabla
            productos.forEach(p -> { // Itera sobre la lista de productos
                out.println("<tr>"); // Inicia una nueva fila para cada producto
                out.println("<td>" + p.getId() + "</td>"); // Muestra el ID del producto
                out.println("<td>" + p.getNombre() + "</td>"); // Muestra el nombre del producto
                out.println("<td>" + p.getTipo() + "</td>"); // Muestra la categoría del producto
                out.println("<td>" + p.getPrecio() + "</td>"); // Muestra el precio del producto
                out.println("</tr>"); // Cierra la fila del producto
            });
            out.println("</table>"); // Cierra la tabla de productos
        } else { // Si el usuario no está presente
            out.println("<table>"); // Inicia la tabla de productos sin precios
            out.println("<tr><th>ID</th><th>NOMBRE</th><th>CATEGORIA</th></tr>"); // Encabezados de la tabla sin precios
            productos.forEach(p -> { // Itera sobre la lista de productos
                out.println("<tr>"); // Inicia una nueva fila para cada producto
                out.println("<td>" + p.getId() + "</td>"); // Muestra el ID del producto
                out.println("<td>" + p.getNombre() + "</td>"); // Muestra el nombre del producto
                out.println("<td>" + p.getTipo() + "</td>"); // Muestra la categoría del producto
                out.println("</tr>"); // Cierra la fila del producto
            });
            out.println("</table>"); // Cierra la tabla de productos
            out.println("<p>Inicia sesión para ver los precios</p>"); // Mensaje para iniciar sesión
        }

        out.println("</body>"); // Cierra el cuerpo del HTML
        out.println("</html>"); // Cierra el documento HTML
    }
}