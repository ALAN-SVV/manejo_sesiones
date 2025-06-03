package org.alan.manejosesiones.repository;

import org.alan.manejosesiones.models.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositoryJdbcImplement implements Repository<Categoria> {

    private Connection conn;

    public CategoriaRepositoryJdbcImplement(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM categoria")) {
            while (rs.next()) {
                Categoria c = getCategoria(rs);
                categorias.add(c);
            }
        }
        return categorias;
    }

    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = null;
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM categoria WHERE idcategoria=?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    categoria = getCategoria(rs);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {
        String sql;
        if (categoria.getIdCategoria() != null && categoria.getIdCategoria() > 0) {
            sql = "UPDATE categoria SET nombre=?, descripcion=?, condicion=? WHERE idcategoria=?";
        } else {
            sql = "INSERT INTO categoria(nombre, descripcion, condicion) VALUES(?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());
            stmt.setBoolean(3, categoria.isCondicion()); // Usa isCondicion() para el booleano
            if (categoria.getIdCategoria() != null && categoria.getIdCategoria() > 0) {
                stmt.setLong(4, categoria.getIdCategoria());
            }
            stmt.executeUpdate();
        }
    }

    // Método para cambiar el estado (activo/inactivo)
    public void cambiarEstado(Long id, boolean estado) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE categoria SET condicion=? WHERE idcategoria=?")) {
            stmt.setBoolean(1, estado);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        // Opcional: Puedes implementar un borrado lógico aquí si lo necesitas
        // cambiarEstado(id, false);
    }

    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();
        c.setIdCategoria(rs.getLong("idcategoria"));
        c.setNombre(rs.getString("nombre"));
        c.setDescripcion(rs.getString("descripcion"));
        c.setCondicion(rs.getBoolean("condicion")); // Mapea el campo condicion
        return c;
    }
}