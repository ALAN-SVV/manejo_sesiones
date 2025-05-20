package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Productos;
import services.LoginService;
import services.LoginServiceSessionImplement;
import services.ProductoService;
import services.ProductoServiceImplement;
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

        out.print("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<title>Lista de Productos</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>Lista de productos</h1>");

        if (usernameOptional.isPresent()) {
            // Usuario logueado - muestra mensaje y tabla completa
            out.println("<h2>Hola usuario " + usernameOptional.get() + "</h2>");

            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>ID PRODUCTO</th>");
            out.println("<th>NOMBRE</th>");
            out.println("<th>CATEGORIA</th>");
            out.println("<th>PRECIO</th>");
            out.println("</tr>");

            productos.forEach(p -> {
                out.println("<tr>");
                out.println("<td>"+p.getId()+"</td>");
                out.println("<td>"+p.getNombre()+"</td>");
                out.println("<td>"+p.getTipo()+"</td>");
                out.println("<td>"+p.getPrecio()+"</td>");
                out.println("</tr>");
            });

            out.println("</table>");
        } else {
            // Usuario no logueado - muestra tabla sin precios
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>ID PRODUCTO</th>");
            out.println("<th>NOMBRE</th>");
            out.println("<th>CATEGORIA</th>");
            out.println("</tr>");

            productos.forEach(p -> {
                out.println("<tr>");
                out.println("<td>"+p.getId()+"</td>");
                out.println("<td>"+p.getNombre()+"</td>");
                out.println("<td>"+p.getTipo()+"</td>");
                out.println("</tr>");
            });

            out.println("</table>");
            out.println("<p>Inicia sesión para ver los precios</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}