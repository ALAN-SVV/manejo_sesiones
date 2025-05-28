package org.alan.manejosesiones.services;

import org.alan.manejosesiones.models.Categoria;
import org.alan.manejosesiones.repository.CategoriaRepositoryJdbcImplement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoriaServiceJdbcImplement implements CategoriaService {
    //creamos una variable de tipo CategoriaRepositoryJdbcimplemen
    //creamos una variable tipo coneccion
    private CategoriaRepositoryJdbcImplement repositoryJdbc;
    //creamos un constructor
    public CategoriaServiceJdbcImplement(Connection conn){
        this.repositoryJdbc = new CategoriaRepositoryJdbcImplement(conn);
    }


    @Override
    public List<Categoria> listar() {
        try{
            return repositoryJdbc.listar();
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porId(Long id) {
        try{
            return Optional.ofNullable(repositoryJdbc.porId(id));
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
