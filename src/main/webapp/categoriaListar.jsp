
<%--
  Created by IntelliJ IDEA.
  User: Alan Velasco
  Date: 28/5/2025
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, org.alan.manejosesiones.models.*" %>
<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
%>
<html>
<head>
    <title>Listado Categoría</title>
    <style>
        /* Estilos generales */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f8f9fa;
            color: #333;
        }

        /* Contenedor principal */
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background-color: #fff;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        /* Encabezado */
        h1 {
            color: #2c3e50;
            border-bottom: 2px solid #eaeaea;
            padding-bottom: 10px;
            margin-top: 0;
        }

        /* Mensaje de bienvenida */
        .welcome-message {
            color: #3498db;
            font-size: 18px;
            margin-bottom: 20px;
            padding: 10px;
            background-color: #f0f8ff;
            border-left: 4px solid #3498db;
        }

        /* Enlace para nuevo producto */
        .new-product-link {
            display: inline-block;
            margin-bottom: 20px;
            padding: 10px 15px;
            background-color: #2ecc71;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        .new-product-link:hover {
            background-color: #27ae60;
        }

        /* Tabla */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #e0e0e0;
        }

        th {
            background-color: #3498db;
            color: white;
            font-weight: 500;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        /* Botones de acción */
        .btn {
            display: inline-block;
            padding: 8px 12px;
            margin: 0 5px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            font-size: 14px;
            transition: all 0.3s;
        }

        .btn-edit {
            background-color: #3498db;
            color: white;
        }

        .btn-edit:hover {
            background-color: #2980b9;
        }

        .btn-deactivate {
            background-color: #e74c3c;
            color: white;
        }

        .btn-activate {
            background-color: #2ecc71;
            color: white;
        }

        .btn-deactivate:hover {
            background-color: #c0392b;
        }

        .btn-activate:hover {
            background-color: #27ae60;
        }

        /* Estado */
        .status-active {
            color: #2ecc71;
            font-weight: 500;
        }

        .status-inactive {
            color: #e74c3c;
            font-weight: 500;
        }

        /* Sección de acciones */
        .action-cell {
            white-space: nowrap;
        }
    </style>
</head>
<body>
<div class="container">
    <%
        if(username.isPresent()){%>
    <div class="welcome-message">Hola <%= username.get()%>, bienvenido a la aplicación</div>
    <a href="${pageContext.request.contextPath}/categoria/form" class="new-product-link">Nueva Categoría</a>
    <%
        }
    %>

    <h1>Listado de Categorías</h1>
    <table>
        <thead>
        <tr>
            <th>ID Categoría</th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Categoria cat : categorias) {%>
        <tr>
            <td><%= cat.getIdCategoria()%></td>
            <td><%= cat.getNombre()%></td>
            <td><%= cat.getDescripcion()%></td>
            <td class="<%= cat.isCondicion() ? "status-active" : "status-inactive" %>">
                <%= cat.isCondicion() ? "Activo" : "Inactivo" %>
            </td>
            <td class="action-cell">
                <a href="<%=request.getContextPath()%>/categoria/form?id=<%=cat.getIdCategoria()%>"
                   class="btn btn-edit">Editar</a>
                <a href="<%=request.getContextPath()%>/categoria/cambiar-estado?id=<%=cat.getIdCategoria()%>&estado=<%=cat.isCondicion()%>"
                   class="btn <%= cat.isCondicion() ? "btn-deactivate" : "btn-activate" %>">
                    <%= cat.isCondicion() ? "Desactivar" : "Activar" %>
                </a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>