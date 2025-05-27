package org.alan.manejosesiones.repository;

import org.alan.manejosesiones.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositoryJdbcImplement implements Repository<Categoria>{

    //creamos una variable donde vamos a guardar la conexion
    private Connection conn;
    public CategoriaRepositoryJdbcImplement(Connection conn) {//realiza la conexion de la variable
        this.conn= conn;//obtengo la conexion
    }
    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try (Statement stmt= conn.createStatement();
             ResultSet rs=stmt.executeQuery("select * from categoria")){
            while (rs.next()){// se debe recorrer los datos mienstras sigan existentes
                Categoria c = getCategoria(rs);
                categorias.add(c);
            }
        }
        return categorias;//retornamos la lista categorias una vez se acaba el bucle
    }


    @Override
    public Categoria porId(int id) throws SQLException {
        //creamos un objeto de tipo categoria nulo
        Categoria categoria=null;
        try(PreparedStatement stmt= conn.prepareStatement("select * from categoria where id=?")){
            stmt.setInt(1,id);
            //ejecutamos la consulta
            try (ResultSet rs=stmt.executeQuery()){//traeamos todos los datos del objeto o categoria
                categoria=getCategoria(rs);
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {
        //declaro una variable de tipo String
        String sql;
        if (categoria.getIdCategoria()>0){
            sql ="update categoria set nombre=?,descripcion=?  where idCategoria=?";
        }else {
            sql="insert into categoria(nombre,descripcion,condicion) values(?,?,1)";
        }
        try(PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setString(1,categoria.getNombre());
            stmt.setString(2,categoria.getDescripcion());
            stmt.setInt(3,categoria.getIdCategoria());
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {

    }
    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();//creamos un objeto categoria llamado c
        c.setNombre(rs.getString("nombre"));//setea el nombre del metodo que se obtiene de nuestro javabeans(categoria)
        c.setDescripcion(rs.getString("descripcion"));
        c.setCondicion(rs.getInt("condicion"));
        c.setIdCategoria(rs.getInt("idCategoria"));
        return c;
    }
}

