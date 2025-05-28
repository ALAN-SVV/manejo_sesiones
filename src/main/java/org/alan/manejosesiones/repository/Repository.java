package org.alan.manejosesiones.repository;
import java.util.List;
import java.sql.SQLException;

/*la variable T es un parametor generico que puede tomar cualquier estado que permite que la interface sea utilizada
 * como se desee o cualquier objeto entidad que se deseee manejar*/
public interface Repository <T> {
    List<T> listar () throws SQLException;// se ve cualquier erro en la base de datos
    T porId(Long id) throws SQLException;//busca el elemnto de la base por su identificador o id
    void guardar (T t) throws SQLException;// va a recibir un parametor generico
    void eliminar (Long id) throws SQLException;//metodo para eliminar un registro de la base de datos
}
