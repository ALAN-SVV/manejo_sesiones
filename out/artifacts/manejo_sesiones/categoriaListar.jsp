
<%--
  Created by IntelliJ IDEA.
  User: ESTUDIANTE
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
    <title>Listado Categoria</title>
</head>
<body>
<%
    if(username.isPresent()){%>
<div style="color:blue;">Hola<%= username.get()%>,  bienvenido a la apliación</div>
<div><p><a href="${pageContext.request.contextPath}/categoria/form">Ingrese el producto</a></p></div>
<%
    }
%>

<h1> Listado Categoria</h1>
<table>
    <thead>
    <th>Id Categoria</th>
    <th>Nombre</th>
    <th>Descripcion</th>
    <th>Condicion</th>
    </thead>
    <%
        for (Categoria cat : categorias) {%>
    <tbody>
    <td><%= cat.getIdCategoria()%></td>
    <td><%= cat.getNombre()%></td>
    <td><%= cat.getDescripcion()%></td>
    <td><%= cat.isCondicion()%></td>



    <td><a href="<%=request.getContextPath()%>/categoria/form?id=<%=cat.getIdCategoria()%>">Editar</a></td>
    <td>
        <a href="<%=request.getContextPath()%>/categoria/cambiar-estado?id=<%=cat.getIdCategoria()%>&estado=<%=cat.isCondicion()%>"
           style="<%= cat.isCondicion() ? "color:red;" : "color:green;" %>">
            <%= cat.isCondicion() ? "Desactivar" : "Activar" %>
        </a>
    </td>

    </tbody>
    <% } %>

</table>
</body>
</html>
