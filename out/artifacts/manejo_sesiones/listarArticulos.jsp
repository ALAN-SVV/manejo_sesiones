<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 3/6/2025
  Time: 23:13
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
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
    }
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }
    th, td {
      padding: 10px;
      text-align: left;
      border: 1px solid #ddd;
    }
    th {
      background-color: #f2f2f2;
    }
    .imagen-articulo {
      max-width: 60px;
      max-height: 60px;
      cursor: pointer;
      transition: transform 0.2s;
    }
    .imagen-articulo:hover {
      transform: scale(1.1);
    }
    .sin-imagen {
      width: 60px;
      height: 60px;
      background-color: #f5f5f5;
      display: flex;
      align-items: center;
      justify-content: center;
      border: 1px dashed #ccc;
      color: #777;
      font-size: 10px;
    }
    .modal {
      display: none;
      position: fixed;
      z-index: 1000;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0,0,0,0.9);
    }
    .modal-content {
      display: block;
      margin: 5% auto;
      max-width: 80%;
      max-height: 80%;
    }
    .close {
      position: absolute;
      top: 15px;
      right: 35px;
      color: #f1f1f1;
      font-size: 40px;
      font-weight: bold;
      cursor: pointer;
    }
    .acciones a {
      margin-right: 8px;
      text-decoration: none;
    }
    .welcome-message {
      color: blue;
      margin-bottom: 15px;
    }
  </style>
</head>
<body>
<%
  if(username.isPresent()){%>
<div class="welcome-message">Hola <%= username.get() %>, bienvenido a la aplicación</div>
<div><p><a href="${pageContext.request.contextPath}/articulo/form">Crear nuevo artículo</a></p></div>
<%
  }
%>

<!-- Modal para imagen ampliada -->
<div id="imagenModal" class="modal">
  <span class="close">&times;</span>
  <img class="modal-content" id="imagenAmpliada">
</div>

<h1>Listado Artículos</h1>
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Imagen</th>
    <th>Código</th>
    <th>Nombre</th>
    <th>Categoría</th>
    <th>Stock</th>
    <th>Estado</th>
    <th>Acciones</th>
  </tr>
  </thead>
  <tbody>
  <%
    for (Articulo art : articulos) {%>
  <tr>
    <td><%= art.getIdArticulo() %></td>
    <td>
      <% if (art.getImagen() != null && !art.getImagen().isEmpty()) { %>
      <img src="<%= art.getImagen() %>"
           class="imagen-articulo"
           onclick="mostrarImagen('<%= art.getImagen() %>')"
           onerror="this.onerror=null; this.parentElement.innerHTML='<div class=\'sin-imagen\'>Error</div>'">
      <% } else { %>
      <div class="sin-imagen">Sin imagen</div>
      <% } %>
    </td>
    <td><%= art.getCodigo() %></td>
    <td><%= art.getNombre() %></td>
    <td><%= art.getIdCategoria() %></td>
    <td><%= art.getStock() %></td>
    <td><%= art.isCondicion() ? "Activo" : "Inactivo" %></td>
    <td class="acciones">
      <a href="<%=request.getContextPath()%>/articulo/form?id=<%=art.getIdArticulo()%>">Editar</a>
      <a href="<%=request.getContextPath()%>/articulo/cambiar-estado?id=<%=art.getIdArticulo()%>&estado=<%=art.isCondicion()%>"
         style="<%= art.isCondicion() ? "color:red;" : "color:green;" %>">
        <%= art.isCondicion() ? "Desactivar" : "Activar" %>
      </a>
    </td>
  </tr>
  <% } %>
  </tbody>
</table>

<script>
  // Función para mostrar imagen en modal
  function mostrarImagen(urlImagen) {
    const modal = document.getElementById("imagenModal");
    const modalImg = document.getElementById("imagenAmpliada");

    modal.style.display = "block";
    modalImg.src = urlImagen;

    // Cerrar al hacer clic en la X
    document.getElementsByClassName("close")[0].onclick = function() {
      modal.style.display = "none";
    }

    // Cerrar al hacer clic fuera de la imagen
    window.onclick = function(event) {
      if (event.target == modal) {
        modal.style.display = "none";
      }
    }
  }

  // Carga diferida de imágenes
  document.addEventListener("DOMContentLoaded", function() {
    const imagenes = document.querySelectorAll(".imagen-articulo");

    if ('IntersectionObserver' in window) {
      const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
          if (entry.isIntersecting) {
            const img = entry.target;
            img.src = img.dataset.src || img.src;
            observer.unobserve(img);
          }
        });
      });

      imagenes.forEach(img => {
        img.dataset.src = img.src;
        img.src = "";
        observer.observe(img);
      });
    }
  });
</script>
</body>
</html>