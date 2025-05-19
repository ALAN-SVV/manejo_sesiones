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
</head>
<body>
<h1>Login de usuario</h1>
<form action="login" method="post">
  <div>
    <label for="username">Nombre de usuario:</label>
    <input type="text" name="username" id="username" required>
  </div>
  <div>
    <label for="pass">Password:</label>
    <input type="password" name="password" id="pass" required>
  </div>
  <div>
    <input type="submit" value="Enviar">
  </div>
</form>
</body>
</html>