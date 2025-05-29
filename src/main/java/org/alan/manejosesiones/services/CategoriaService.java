package org.alan.manejosesiones.services;

import org.alan.manejosesiones.models.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    // Método para obtener una lista de todas las categorías
    List<Categoria> listar();

    // Método para obtener una categoría específica por su ID, puede devolver un Optional vacío si no se encuentra
    Optional<Categoria> porId(Long id);
    void guardar(Categoria categoria);
}
