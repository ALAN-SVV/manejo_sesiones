<%--
  Created by IntelliJ IDEA.
  User: Alan VElasco
  Date: 3/6/2025
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, org.alan.manejosesiones.models.*" %>
<%
  List<Articulo> articulos = (List<Articulo>) request.getAttribute("articulos");
  Optional<String> username = (Optional<String>) request.getAttribute("username");
%>
<html>
<head>
  <title>Listado Artículos</title>
</head>
<body>
<%
  if(username.isPresent()){%>
<div style="color:blue;">Hola <%= username.get() %>, bienvenido a la aplicación</div>
<div><p><a href="${pageContext.request.contextPath}/articulo/form">Crear nuevo artículo</a></p></div>
<%
  }
%>

<h1>Listado Artículos</h1>
<table border="1">
  <thead>
  <th>ID</th>
  <th>Código</th>
  <th>Nombre</th>
  <th>Categoría</th>
  <th>Stock</th>
  <th>Estado</th>
  <th>Acciones</th>
  </thead>
  <%
    for (Articulo art : articulos) {%>
  <tbody>
  <td><%= art.getIdArticulo() %></td>
  <td><%= art.getCodigo() %></td>
  <td><%= art.getNombre() %></td>
  <td><%= art.getIdCategoria() %></td>
  <td><%= art.getStock() %></td>
  <td><%= art.isCondicion() ? "Activo" : "Inactivo" %></td>
  <td>
    <a href="<%=request.getContextPath()%>/articulo/form?id=<%=art.getIdArticulo()%>">Editar</a>
    <a href="<%=request.getContextPath()%>/articulo/cambiar-estado?id=<%=art.getIdArticulo()%>&estado=<%=art.isCondicion()%>"
       style="<%= art.isCondicion() ? "color:red;" : "color:green;" %>">
      <%= art.isCondicion() ? "Desactivar" : "Activar" %>
    </a>
  </td>

  </tbody>
  <% } %>
</table>
</body>
</html>
