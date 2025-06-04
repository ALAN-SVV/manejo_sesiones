<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 3/6/2025
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.alan.manejosesiones.models.Articulo, org.alan.manejosesiones.models.Categoria, java.util.List" %>
<%
  Articulo articulo = (Articulo) request.getAttribute("articulo");
  if (articulo == null) {
    articulo = new Articulo(); // artículo vacío para creación
  }

  List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");

  // Obtener errores de validación si existen
  String errorNombre = (String) request.getAttribute("errorNombre");
  String errorCodigo = (String) request.getAttribute("errorCodigo");
  String errorStock = (String) request.getAttribute("errorStock");
  String errorCategoria = (String) request.getAttribute("errorCategoria");
  String errorDescripcion = (String) request.getAttribute("errorDescripcion");
  String errorImagen = (String) request.getAttribute("errorImagen");
%>
<html>
<head>
  <title><%= (articulo.getIdArticulo() != null && articulo.getIdArticulo() > 0) ? "Editar Artículo" : "Nuevo Artículo" %></title>
  <style>
    .error-message {
      color: red;
      font-size: 12px;
      margin-top: 5px;
    }
    input[type="text"], textarea, select, input[type="number"] {
      margin-bottom: 5px;
      width: 300px;
    }
  </style>
</head>
<body>

<h1><%= (articulo.getIdArticulo() != null && articulo.getIdArticulo() > 0) ? "Editar Artículo" : "Nuevo Artículo" %></h1>

<form action="<%= request.getContextPath() %>/articulo/form" method="post">
  <input type="hidden" name="idArticulo" value="<%=articulo.getIdArticulo()%>">

  <div>
    <label for="idCategoria">Categoría:</label><br>
    <select id="idCategoria" name="idCategoria">
      <option value="">-- Seleccione --</option>
      <% for (Categoria cat : categorias) { %>
      <option value="<%=cat.getIdCategoria()%>"
              <%= articulo.getIdCategoria() != null && articulo.getIdCategoria().equals(cat.getIdCategoria()) ? "selected" : "" %>>
        <%=cat.getNombre()%>
      </option>
      <% } %>
    </select>
    <% if (errorCategoria != null) { %>
    <div class="error-message"><%= errorCategoria %></div>
    <% } %>
  </div>
  <br>

  <div>
    <label for="codigo">Código:</label><br>
    <input type="text" id="codigo" name="codigo" value="<%= articulo.getCodigo() != null ? articulo.getCodigo() : "" %>">
    <% if (errorCodigo != null) { %>
    <div class="error-message"><%= errorCodigo %></div>
    <% } %>
  </div>
  <br>

  <div>
    <label for="nombre">Nombre:</label><br>
    <input type="text" id="nombre" name="nombre" value="<%= articulo.getNombre() != null ? articulo.getNombre() : "" %>">
    <% if (errorNombre != null) { %>
    <div class="error-message"><%= errorNombre %></div>
    <% } %>
  </div>
  <br>

  <div>
    <label for="stock">Stock:</label><br>
    <input type="number" id="stock" name="stock" value="<%= articulo.getStock() != null ? articulo.getStock() : 0 %>" min="0">
    <% if (errorStock != null) { %>
    <div class="error-message"><%= errorStock %></div>
    <% } %>
  </div>
  <br>

  <div>
    <label for="descripcion">Descripción:</label><br>
    <textarea id="descripcion" name="descripcion"><%= articulo.getDescripcion() != null ? articulo.getDescripcion() : "" %></textarea>
    <% if (errorCodigo != null) { %>
    <div class="error-message"><%= errorDescripcion %></div>
    <% } %>
  </div>
  <br>

  <div>
    <label for="imagen">Imagen (URL):</label><br>
    <input type="text" id="imagen" name="imagen" value="<%= articulo.getImagen() != null ? articulo.getImagen() : "" %>">
    <% if (errorCodigo != null) { %>
    <div class="error-message"><%= errorImagen %></div>
    <% } %>
  </div>
  <br>

  <div>
    <label>Estado:</label><br>
    <input type="radio" id="activo" name="condicion" value="1" <%= articulo.isCondicion() ? "checked" : "" %>>
    <label for="activo">Activo</label>

    <input type="radio" id="inactivo" name="condicion" value="0" <%= !articulo.isCondicion() ? "checked" : "" %>>
    <label for="inactivo">Inactivo</label>
  </div>
  <br>

  <input type="submit" value="<%=(articulo.getIdArticulo()!=null && articulo.getIdArticulo()>0)? "EDITAR" : "CREAR"%>">
  <a href="<%= request.getContextPath() %>/articulo">Cancelar</a>
</form>

</body>
</html>
