<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.alan.manejosesiones.models.Categoria" %>
<%
  Categoria categoria = (Categoria) request.getAttribute("categorias");
  if (categoria == null) {
    categoria = new Categoria(); // categoría vacía para creación
  }

  // Obtener errores de validación si existen
  String errorNombre = (String) request.getAttribute("errorNombre");
  String errorDescripcion = (String) request.getAttribute("errorDescripcion");
%>
<html>
<head>
  <title><%= (categoria.getIdCategoria() != null && categoria.getIdCategoria() > 0) ? "Editar Categoría" : "Nueva Categoría" %></title>
  <style>
    .error-message {
      color: red;
      font-size: 12px;
      margin-top: 5px;
    }
    input[type="text"], textarea {
      margin-bottom: 5px;
    }
  </style>
</head>
<body>

<h1><%= (categoria.getIdCategoria() != null && categoria.getIdCategoria() > 0) ? "Editar Categoría" : "Nueva Categoría" %></h1>

<form action="<%= request.getContextPath() %>/categoria/form" method="post">
  <input type="text" name="id" value="<%=categoria.getIdCategoria()%>">

  <div>
    <label for="nombre">Nombre:</label><br>
    <input type="text" id="nombre" name="nombre" value="<%= categoria.getNombre() != null ? categoria.getNombre() : "" %>">
    <% if (errorNombre != null) { %>
    <div class="error-message"><%= errorNombre %></div>
    <% } %>
  </div>
  <br>

  <div>
    <label for="descripcion">Descripción:</label><br>
    <textarea id="descripcion" name="descripcion"><%= categoria.getDescripcion() != null ? categoria.getDescripcion() : "" %></textarea>
    <% if (errorDescripcion != null) { %>
    <div class="error-message"><%= errorDescripcion %></div>
    <% } %>
  </div>
  <br>

  <input type="submit" value="<%=(categoria.getIdCategoria()!=null && categoria.getIdCategoria()>0)? "EDITAR" : "CREAR"%>">
  <a href="<%= request.getContextPath() %>/categoria">Cancelar</a>
</form>

</body>
</html>