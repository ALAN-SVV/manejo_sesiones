package org.alan.manejosesiones.filtro;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.alan.manejosesiones.util.Conexion;
import org.alan.manejosesiones.services.ServiceJdbcException;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
//esta clase es mas para mnejo de errores, por ejemplo si una aplicacion de banca se tranfiere dinero pero el usuaio se quivoca esta cuenta
//lo que hace esta clase es verificar que todos los parametros esten correcto caso contrario no afecta la base de datos ni se hace la funcion
@WebFilter("/*")
public class ConexionFilter implements Filter {
    @Override
    //filtramos conexiion del cliente y el servidor
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //Crear una variable de tipo conexion
        try {
            Connection conn = Conexion.getConnection();
            //confirmamos que la coneccion este configurada de forma automatica, si esto pasa lo seteamos para ver sino es es falso
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try{ //si todo esta correcto realiza el filtrado
                request.setAttribute("conn", conn);
                chain.doFilter(request, response);
                conn.commit();//se configura el commit
            }catch (SQLException | ServiceJdbcException e){//si tiene alguna problema configuramos el clase de error
                //sino esta corecto retrocede y deja mi base de datos tal como estaba antes
                conn.rollback();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
