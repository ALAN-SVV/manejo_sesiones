package org.alan.manejosesiones.services;

import org.alan.manejosesiones.models.Articulo;
import org.alan.manejosesiones.repository.ArticuloRepositoryJdbcImplement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class ArticuloServiceJdbcImplement implements ArticuloService {
    private ArticuloRepositoryJdbcImplement repository;

    public ArticuloServiceJdbcImplement(Connection conn) {
        this.repository = new ArticuloRepositoryJdbcImplement(conn);
    }

    @Override
    public List<Articulo> listar() {
        try {
            return repository.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Articulo> porId(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Articulo articulo) {
        try {
            // Validaciones antes de guardar
            if (articulo.getNombre() == null || articulo.getNombre().trim().isEmpty()) {
                throw new ServiceJdbcException("El nombre del artículo es requerido");
            }
            if (articulo.getStock() == null || articulo.getStock() < 0) {
                throw new ServiceJdbcException("El stock no puede ser negativo");
            }

            repository.guardar(articulo);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }


    @Override
    public void cambiarEstado(Long id, Boolean estado) {
        try {
            repository.cambiarEstado(id, estado);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
