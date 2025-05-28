package org.alan.manejosesiones.filtro;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.alan.manejosesiones.services.LoginService;
import org.alan.manejosesiones.services.LoginServiceSessionImplement;

import java.io.IOException;
import java.util.Optional;
@WebFilter({"/products","/categorias","/dashboard.jsp"})

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginService service= new LoginServiceSessionImplement();
        Optional<String> username= service.getUserName((HttpServletRequest) request);
        //Realizo una condicional para ver si esta presente el nombre del usuario
        if(username.isPresent()){
            chain.doFilter(request,response);
        } else {
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED,  "Lo sentimos no estas autorizado para ingresar a esta pagina");
        }
    }
}
