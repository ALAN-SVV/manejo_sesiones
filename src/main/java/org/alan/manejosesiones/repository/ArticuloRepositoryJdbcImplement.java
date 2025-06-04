package org.alan.manejosesiones.repository;

import org.alan.manejosesiones.models.Articulo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticuloRepositoryJdbcImplement implements Repository<Articulo> {

    private Connection conn;

    public ArticuloRepositoryJdbcImplement(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Articulo> listar() throws SQLException {
        List<Articulo> articulos = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM articulo")) {
            while (rs.next()) {
                Articulo a = getArticulo(rs);
                articulos.add(a);
            }
        }
        return articulos;
    }

    @Override
    public Articulo porId(Long id) throws SQLException {
        Articulo articulo = null;
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM articulo WHERE idarticulo=?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    articulo = getArticulo(rs);
                }
            }
        }
        return articulo;
    }

    @Override
    public void guardar(Articulo articulo) throws SQLException {
        String sql;
        if (articulo.getIdArticulo() != null && articulo.getIdArticulo() > 0) {
            sql = "UPDATE articulo SET idcategoria=?, codigo=?, nombre=?, stock=?, descripcion=?, imagen=?, condicion=? WHERE idarticulo=?";
        } else {
            sql = "INSERT INTO articulo(idcategoria, codigo, nombre, stock, descripcion, imagen, condicion) VALUES(?,?,?,?,?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, articulo.getIdCategoria());
            stmt.setString(2, articulo.getCodigo());
            stmt.setString(3, articulo.getNombre());
            stmt.setLong(4, articulo.getStock());
            stmt.setString(5, articulo.getDescripcion());
            stmt.setString(6, articulo.getImagen());
            stmt.setBoolean(7, articulo.isCondicion());

            if (articulo.getIdArticulo() != null && articulo.getIdArticulo() > 0) {
                stmt.setLong(8, articulo.getIdArticulo());
            }

            stmt.executeUpdate();
        }
    }

    @Override
    public void cambiarEstado(Long id, boolean estado) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE articulo SET condicion=? WHERE idarticulo=?")) {
            stmt.setBoolean(1, estado);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    private static Articulo getArticulo(ResultSet rs) throws SQLException {
        Articulo a = new Articulo();
        a.setIdArticulo(rs.getLong("idarticulo"));
        a.setIdCategoria(rs.getLong("idcategoria"));
        a.setCodigo(rs.getString("codigo"));
        a.setNombre(rs.getString("nombre"));
        a.setStock(rs.getLong("stock"));
        a.setDescripcion(rs.getString("descripcion"));
        a.setImagen(rs.getString("imagen"));
        a.setCondicion(rs.getBoolean("condicion"));
        return a;
    }
}