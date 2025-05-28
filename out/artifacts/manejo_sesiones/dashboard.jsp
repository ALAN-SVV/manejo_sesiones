<%--
  Created by IntelliJ IDEA.
  User: Alan Velasco
  Date: 28/5/2025
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="org.alan.manejosesiones.models.Productos" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/manejo_sesiones/resources/css/dashboard.css">
</head>
<body>
<header>
    <h1>Productos clases</h1>
    <%
        // Obtiene el nombre de usuario de la sesión
        String username = (String) session.getAttribute("username");
        if (username != null) {
    %>
    <p>Bienvenido, <%= username %> | <a href="${pageContext.request.contextPath}/logout">Cerrar sesión</a></p>
    <%
        }
    %>
</header>

<main>
    <section>
        <h2>Productos</h2>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Categoria</th>
                <th>Precio</th>
                <th>Opciones</th>
            </tr>
            </thead>
            <tbody>
            <%
                // Obtiene la lista de productos del atributo de solicitud
                List<Productos> productos = (List<Productos>) request.getAttribute("productos");
                if (productos != null) {
                    // Itera sobre la lista de productos y genera filas en la tabla
                    for (Productos producto : productos) {
            %>
            <tr>
                <td><%= producto.getId() %></td>
                <td><%= producto.getNombre() %></td>
                <td><%= producto.getTipo() %></td>
                <td>$<%= String.format("%.2f", producto.getPrecio()) %></td>
                <td>
                    <a href="#" class="btn-edit"> Editar</a> |
                    <a href="#" class="btn-disable">Desactivar</a> |
                    <a href="#" class="btn-enable">Activar</a>
                </td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </section>
</main>

<footer>
    <!-- Muestra el nombre del usuario o "sistema" si no hay usuario -->
    <p>Generado, <%= username != null ? username : "sistema" %></p>
</footer>
</body>
</html>
