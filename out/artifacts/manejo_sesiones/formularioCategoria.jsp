<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.alan.manejosesiones.models.Categoria" %>
<%
  Categoria categoria = (Categoria) request.getAttribute("categorias");
  if (categoria == null) {
    categoria = new Categoria();
  }

  String errorNombre = (String) request.getAttribute("errorNombre");
  String errorDescripcion = (String) request.getAttribute("errorDescripcion");
%>
<html>
<head>
  <title><%= (categoria.getIdCategoria() != null && categoria.getIdCategoria() > 0) ? "Editar Categoría" : "Nueva Categoría" %></title>
  <style>
    /* Estilos generales */
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f5f7fa;
      color: #333;
      line-height: 1.6;
    }

    /* Contenedor principal */
    .container {
      max-width: 600px;
      margin: 30px auto;
      padding: 30px;
      background-color: #fff;
      border-radius: 8px;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }

    /* Encabezado */
    h1 {
      color: #2c3e50;
      margin-top: 0;
      padding-bottom: 15px;
      border-bottom: 1px solid #eee;
    }

    /* Grupos de formulario */
    .form-group {
      margin-bottom: 20px;
    }

    .form-group label {
      display: block;
      margin-bottom: 8px;
      font-weight: 600;
      color: #495057;
    }

    /* Campos de formulario */
    input[type="text"],
    textarea {
      width: 100%;
      padding: 12px;
      border: 1px solid #ced4da;
      border-radius: 4px;
      font-size: 16px;
      transition: border-color 0.3s ease;
    }

    input[type="text"]:focus,
    textarea:focus {
      border-color: #3498db;
      outline: none;
      box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.2);
    }

    textarea {
      min-height: 100px;
      resize: vertical;
    }

    /* Mensajes de error */
    .error-message {
      color: #e74c3c;
      font-size: 13px;
      margin-top: 5px;
      font-weight: 500;
    }

    /* Botones */
    .btn {
      display: inline-block;
      padding: 10px 20px;
      border: none;
      border-radius: 4px;
      font-size: 16px;
      cursor: pointer;
      text-decoration: none;
      transition: all 0.3s ease;
    }

    .btn-primary {
      background-color: #3498db;
      color: white;
    }

    .btn-primary:hover {
      background-color: #2980b9;
    }

    .btn-secondary {
      background-color: #95a5a6;
      color: white;
      margin-left: 10px;
    }

    .btn-secondary:hover {
      background-color: #7f8c8d;
    }

    /* Radio buttons para estado */
    .radio-group {
      margin: 20px 0;
    }

    .radio-group label {
      display: inline-block;
      margin-right: 20px;
      cursor: pointer;
      position: relative;
      padding-left: 25px;
    }

    .radio-group input[type="radio"] {
      position: absolute;
      opacity: 0;
      cursor: pointer;
    }

    .radio-group label:before {
      content: "";
      position: absolute;
      left: 0;
      top: 2px;
      width: 18px;
      height: 18px;
      border: 2px solid #adb5bd;
      border-radius: 50%;
      background-color: #fff;
    }

    .radio-group input[type="radio"]:checked + label:before {
      border-color: #3498db;
    }

    .radio-group label:after {
      content: "";
      position: absolute;
      left: 5px;
      top: 7px;
      width: 10px;
      height: 10px;
      border-radius: 50%;
      background: #3498db;
      opacity: 0;
      transition: opacity 0.2s;
    }

    .radio-group input[type="radio"]:checked + label:after {
      opacity: 1;
    }
  </style>
</head>
<body>
<div class="container">
  <h1><%= (categoria.getIdCategoria() != null && categoria.getIdCategoria() > 0) ? "Editar Categoría" : "Nueva Categoría" %></h1>

  <form action="<%= request.getContextPath() %>/categoria/form" method="post">
    <input type="hidden" name="id" value="<%=categoria.getIdCategoria()%>">

    <div class="form-group">
      <label for="nombre">Nombre:</label>
      <input type="text" id="nombre" name="nombre"
             value="<%= categoria.getNombre() != null ? categoria.getNombre() : "" %>">
      <% if (errorNombre != null) { %>
      <div class="error-message"><%= errorNombre %></div>
      <% } %>
    </div>

    <div class="form-group">
      <label for="descripcion">Descripción:</label>
      <textarea id="descripcion" name="descripcion"><%= categoria.getDescripcion() != null ? categoria.getDescripcion() : "" %></textarea>
      <% if (errorDescripcion != null) { %>
      <div class="error-message"><%= errorDescripcion %></div>
      <% } %>
    </div>

    <div class="radio-group">
      <label>Estado:</label>
      <input type="radio" id="activo" name="condicion" value="true" <%= categoria.isCondicion() ? "checked" : "" %>>
      <label for="activo">Activo</label>

      <input type="radio" id="inactivo" name="condicion" value="false" <%= !categoria.isCondicion() ? "checked" : "" %>>
      <label for="inactivo">Inactivo</label>
    </div>

    <div>
      <input type="submit" class="btn btn-primary"
             value="<%=(categoria.getIdCategoria()!=null && categoria.getIdCategoria()>0)? "GUARDAR CAMBIOS" : "CREAR CATEGORÍA"%>">
      <a href="<%= request.getContextPath() %>/categoria" class="btn btn-secondary">CANCELAR</a>
    </div>
  </form>
</div>
</body>
</html>