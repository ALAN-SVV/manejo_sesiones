package org.alan.manejosesiones.services;

import org.alan.manejosesiones.models.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> listar();
    Optional<Categoria> porId(Long id);
}
