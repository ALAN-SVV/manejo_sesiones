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
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
      line-height: 1.6;
    }
    h1 {
      color: #333;
      border-bottom: 1px solid #eee;
      padding-bottom: 10px;
    }
    .error-message {
      color: #d9534f;
      font-size: 13px;
      margin-top: 5px;
    }
    input[type="text"], input[type="number"], input[type="url"], textarea, select {
      width: 100%;
      max-width: 400px;
      padding: 8px;
      margin-bottom: 10px;
      border: 1px solid #ddd;
      border-radius: 4px;
      box-sizing: border-box;
    }
    textarea {
      height: 100px;
      resize: vertical;
    }
    .form-group {
      margin-bottom: 15px;
    }
    .radio-group {
      margin: 10px 0;
    }
    .radio-group label {
      margin-right: 15px;
      cursor: pointer;
    }
    .actions {
      margin-top: 20px;
    }
    .btn {
      padding: 8px 15px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      text-decoration: none;
      display: inline-block;
      font-size: 14px;
    }
    .btn-primary {
      background-color: #337ab7;
      color: white;
    }
    .btn-danger {
      background-color: #d9534f;
      color: white;
      margin-left: 10px;
    }
    #imagePreview {
      margin-top: 10px;
    }
    #imagePreview img {
      max-width: 200px;
      max-height: 200px;
      border: 1px solid #ddd;
      border-radius: 4px;
      padding: 5px;
    }
    .preview-placeholder {
      color: #777;
      font-style: italic;
    }
  </style>
</head>
<body>

<h1><%= (articulo.getIdArticulo() != null && articulo.getIdArticulo() > 0) ? "Editar Artículo" : "Nuevo Artículo" %></h1>

<form action="<%= request.getContextPath() %>/articulo/form" method="post">
  <input type="hidden" name="idArticulo" value="<%=articulo.getIdArticulo()%>">

  <div class="form-group">
    <label for="idCategoria">Categoría:</label>
    <select id="idCategoria" name="idCategoria" class="form-control">
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

  <div class="form-group">
    <label for="codigo">Código:</label>
    <input type="text" id="codigo" name="codigo" class="form-control"
           value="<%= articulo.getCodigo() != null ? articulo.getCodigo() : "" %>">
    <% if (errorCodigo != null) { %>
    <div class="error-message"><%= errorCodigo %></div>
    <% } %>
  </div>

  <div class="form-group">
    <label for="nombre">Nombre:</label>
    <input type="text" id="nombre" name="nombre" class="form-control"
           value="<%= articulo.getNombre() != null ? articulo.getNombre() : "" %>">
    <% if (errorNombre != null) { %>
    <div class="error-message"><%= errorNombre %></div>
    <% } %>
  </div>

  <div class="form-group">
    <label for="stock">Stock:</label>
    <input type="number" id="stock" name="stock" class="form-control"
           value="<%= articulo.getStock() != null ? articulo.getStock() : 0 %>" min="0">
    <% if (errorStock != null) { %>
    <div class="error-message"><%= errorStock %></div>
    <% } %>
  </div>

  <div class="form-group">
    <label for="descripcion">Descripción:</label>
    <textarea id="descripcion" name="descripcion" class="form-control"><%= articulo.getDescripcion() != null ? articulo.getDescripcion() : "" %></textarea>
    <% if (errorDescripcion != null) { %>
    <div class="error-message"><%= errorDescripcion %></div>
    <% } %>
  </div>

  <div class="form-group">
    <label for="imagen">Imagen (URL):</label>
    <input type="url" id="imagen" name="imagen" class="form-control"
           placeholder="https://ejemplo.com/imagen.jpg"
           value="<%= articulo.getImagen() != null ? articulo.getImagen() : "" %>"
           oninput="previewImage(this.value)">
    <% if (errorImagen != null) { %>
    <div class="error-message"><%= errorImagen %></div>
    <% } %>
    <div id="imagePreview">
      <% if (articulo.getImagen() != null && !articulo.getImagen().isEmpty()) { %>
      <img src="<%= articulo.getImagen() %>" alt="Previsualización">
      <% } else { %>
      <div class="preview-placeholder">Vista previa de la imagen aparecerá aquí</div>
      <% } %>
    </div>
  </div>

  <div class="form-group">
    <label>Estado:</label>
    <div class="radio-group">
      <input type="radio" id="activo" name="condicion" value="1" <%= articulo.isCondicion() ? "checked" : "" %>>
      <label for="activo">Activo</label>

      <input type="radio" id="inactivo" name="condicion" value="0" <%= !articulo.isCondicion() ? "checked" : "" %>>
      <label for="inactivo">Inactivo</label>
    </div>
  </div>

  <div class="actions">
    <input type="submit" class="btn btn-primary"
           value="<%=(articulo.getIdArticulo()!=null && articulo.getIdArticulo()>0)? "Guardar Cambios" : "Crear Artículo"%>">
    <a href="<%= request.getContextPath() %>/articulo" class="btn btn-danger">Cancelar</a>
  </div>
</form>

<script>
  function previewImage(url) {
    const preview = document.getElementById('imagePreview');

    if (!url || url.trim() === '') {
      preview.innerHTML = '<div class="preview-placeholder">Vista previa de la imagen aparecerá aquí</div>';
      return;
    }

    try {
      new URL(url); // Validación básica de URL
      preview.innerHTML = `<img src="${url}" alt="Previsualización" onerror="this.onerror=null;this.style.display='none';document.getElementById('imagePreview').innerHTML='<div class=\'error-message\'>No se pudo cargar la imagen</div>'">`;
    } catch (e) {
      preview.innerHTML = '<div class="error-message">URL no válida</div>';
    }
  }
</script>

</body>
</html>