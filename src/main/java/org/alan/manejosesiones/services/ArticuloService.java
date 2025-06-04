package org.alan.manejosesiones.services;

import org.alan.manejosesiones.models.Articulo;
import java.util.List;
import java.util.Optional;

public interface ArticuloService {
    List<Articulo> listar();
    Optional<Articulo> porId(Long id);
    void guardar(Articulo articulo);

    void cambiarEstado(Long id, Boolean estado);
}