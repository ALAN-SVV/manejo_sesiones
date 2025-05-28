package org.alan.manejosesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.alan.manejosesiones.models.Categoria;
import org.alan.manejosesiones.services.CategoriaService;
import org.alan.manejosesiones.services.CategoriaServiceJdbcImplement;
import org.alan.manejosesiones.services.LoginService;
import org.alan.manejosesiones.services.LoginServiceSessionImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/categoria")

public class CategoriaServlet extends HttpServlet {
//sobreesceribir el metodo do get

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //creamos la conexion
        Connection conn= (Connection) req.getAttribute("conn");
        //creamos el nuevo objeto de categoria y mandamos la conexion
        CategoriaService service= new CategoriaServiceJdbcImplement(conn);
        List<Categoria> categorias = service.listar();//en una lista de tipo categoria mandar todas las categorias

        //obtengo el username y me trae las categorias
        LoginService auth= new LoginServiceSessionImplement();
        Optional<String> userName= auth.getUserName(req);

        //seteamos los parametros
        req.setAttribute("categorias", categorias);
        req.setAttribute("username", userName);
        //redireccionamos a la vista categoria
        getServletContext().getRequestDispatcher("/categoriaListar.jsp").forward(req,resp);
    }
}
