<%--
  Created by IntelliJ IDEA.
  User: Alan Velasco
  Date: 19/5/2025
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" href="/manejo_sesiones/resources/css/styles.css"> <!-- Estilos generales -->
  <link rel="stylesheet" href="/manejo_sesiones/resources/css/login.css"> <!-- Estilos específicos del login -->
</head>
<body>
<div class="login-container">
  <h1 class="login-title">Login de usuario</h1>
  <form class="login-form" action="login" method="post">
    <div class="form-group">
      <label class="form-label" for="username">Nombre de usuario:</label>
      <input class="form-input" type="text" name="username" id="username" required>
    </div>
    <div class="form-group">
      <label class="form-label" for="pass">Password:</label>
      <input class="form-input" type="password" name="password" id="pass" required>
    </div>
    <div class="form-group">
      <input class="form-submit" type="submit" value="Enviar">
    </div>
  </form>
</div>
</body>
</html>