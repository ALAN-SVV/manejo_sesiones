package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.LoginService;
import services.LoginServiceImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

// Definición del Servlet que manejará las rutas /login y /login.html
@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    // Credenciales fijas para autenticación (en producción usar base de datos)
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    // Método para manejar solicitudes GET (cuando se accede a la página)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Obtener o generar todas las cookies del request, si no hay crear arreglo vacío
        /*Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];

        // Buscar la cookie llamada "username" usando Streams de Java
        Optional<String> cookieOptional = Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))  // Filtrar por nombre de cookie
                .map(Cookie::getValue)  // Obtener solo el valor de la cookie
                .findAny();  // Tomar la primera coincidencia*/

        //Creamos el nuevo objeto de la cookie
        LoginService auth= new LoginServiceImplement();
        Optional<String> cookieOptional = auth.getUserName(req);


        // Si existe la cookie (usuario ya autenticado)
        if (cookieOptional.isPresent()) {
            // Configurar tipo de contenido de la respuesta, se mostara un documento que diga que ya iniciaste sesion
            resp.setContentType("text/html;charset=UTF-8");

            // Usar try-with-resources para manejo automático del PrintWriter
            try (PrintWriter out = resp.getWriter()) {
                // Generar HTML de respuesta dinámicamente
                out.print("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\">");  // Especificar encoding
                out.println("<title>Bienvenido</title>");  // Título de la pestaña
                out.println("</head>");
                out.println("<body>");
                // Mostrar mensaje personalizado con el nombre de usuario
                out.println("<h1>Hola "+ cookieOptional.get()+" ya iniciaste sesión anteriormente!</h1>");
                // Enlace para volver al inicio
                out.println("<p><a href='index.html'>Volver al inicio</a></p>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            // Si no hay cookie, mostrar el formulario de login (JSP), es manejo de cabeceros
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    // Método para manejar solicitudes POST (envío del formulario de login)
    @Override
    //el metodo dopost va a procesar los parametros
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Obtener parámetros del formulario
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Validar credenciales
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            // Si son válidas, crear cookie de autenticación
            Cookie usernameCookie = new Cookie("username", username);
            // Añadir cookie a la respuesta
            resp.addCookie(usernameCookie);

            // Configurar tipo de contenido
            resp.setContentType("text/html;charset=UTF-8");

            // Generar página de bienvenida
            try (PrintWriter out = resp.getWriter()) {
                out.print("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\">");
                out.println("<title>Bienvenido a la app</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Bienvenido a mi APP</h1>");
                out.println("</body>");
                out.println("</html>");
            }

            // Redirigir a la página de login (mostrará mensaje de bienvenida)
            resp.sendRedirect("login.html");
        } else {
            // Si credenciales son inválidas, enviar error HTTP 401 (No autorizado)
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no tiene acceso");
        }
    }
}