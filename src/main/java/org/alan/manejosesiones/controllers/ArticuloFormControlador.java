package org.alan.manejosesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.alan.manejosesiones.models.Articulo;
import org.alan.manejosesiones.services.ArticuloService;
import org.alan.manejosesiones.services.ArticuloServiceJdbcImplement;
import org.alan.manejosesiones.services.CategoriaService;
import org.alan.manejosesiones.services.CategoriaServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/articulo/form")
public class ArticuloFormControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");

        CategoriaService categoriaService = new CategoriaServiceJdbcImplement(conn);
        req.setAttribute("categorias", categoriaService.listar());

        Long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Articulo articulo = new Articulo();
        if (id > 0) {
            ArticuloService service = new ArticuloServiceJdbcImplement(conn);
            Optional<Articulo> optionalArticulo = service.porId(id);
            if (optionalArticulo.isPresent()) {
                articulo = optionalArticulo.get();
            }
        }

        req.setAttribute("articulo", articulo);
        req.setAttribute("title", (id > 0) ? "Editar Artículo" : "Crear Artículo");
        getServletContext().getRequestDispatcher("/formularioArticulo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ArticuloService service = new ArticuloServiceJdbcImplement(conn);

        // Obtener parámetros
        Long idCategoria;
        Long stock;
        boolean condicion = "1".equals(req.getParameter("condicion")); // Convertir a boolean
        Long idArticulo;

        try {
            idCategoria = Long.parseLong(req.getParameter("idCategoria"));
            stock = Long.parseLong(req.getParameter("stock"));
            idArticulo = req.getParameter("idArticulo").isEmpty() ? 0L :
                    Long.parseLong(req.getParameter("idArticulo"));
        } catch (NumberFormatException e) {
            idCategoria = 0L;
            stock = 0L;
            idArticulo = 0L;
        }

        String codigo = req.getParameter("codigo");
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        String imagen = req.getParameter("imagen");

        // Validación de campos
        boolean tieneErrores = false;

        if (nombre == null || nombre.trim().isEmpty()) {
            req.setAttribute("errorNombre", "El nombre es obligatorio");
            tieneErrores = true;
        }

        if (codigo == null || codigo.trim().isEmpty()) {
            req.setAttribute("errorCodigo", "El código es obligatorio");
            tieneErrores = true;
        }

        if (idCategoria <= 0) {
            req.setAttribute("errorCategoria", "Debe seleccionar una categoría");
            tieneErrores = true;
        }

        if (stock < 0) {
            req.setAttribute("errorStock", "El stock no puede ser negativo");
            tieneErrores = true;
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            req.setAttribute("errorDescripcion", "No puede estar vacia la descripcion");
            tieneErrores = true;
        }
        if (descripcion == null || imagen.trim().isEmpty()) {
            req.setAttribute("errorImagen", "La imagen no puede estar vacia");
            tieneErrores = true;
        }


        // Si hay errores, reenviamos al formulario
        if (tieneErrores) {
            CategoriaService categoriaService = new CategoriaServiceJdbcImplement(conn);
            req.setAttribute("categorias", categoriaService.listar());

            Articulo articulo = new Articulo();
            articulo.setIdArticulo(idArticulo);
            articulo.setIdCategoria(idCategoria);
            articulo.setCodigo(codigo != null ? codigo : "");
            articulo.setNombre(nombre != null ? nombre : "");
            articulo.setStock(stock);
            articulo.setDescripcion(descripcion != null ? descripcion : "");
            articulo.setImagen(imagen != null ? imagen : "");
            articulo.setCondicion(condicion);

            req.setAttribute("articulo", articulo);
            req.setAttribute("title", (idArticulo > 0) ? "Editar Artículo" : "Crear Artículo");
            getServletContext().getRequestDispatcher("/formularioArticulo.jsp").forward(req, resp);
            return;
        }

        // Si no hay errores, procedemos a guardar
        Articulo articulo = new Articulo();
        articulo.setIdArticulo(idArticulo);
        articulo.setIdCategoria(idCategoria);
        articulo.setCodigo(codigo);
        articulo.setNombre(nombre);
        articulo.setStock(stock);
        articulo.setDescripcion(descripcion);
        articulo.setImagen(imagen);
        articulo.setCondicion(condicion);

        service.guardar(articulo);
        resp.sendRedirect(req.getContextPath() + "/articulo");
    }
}