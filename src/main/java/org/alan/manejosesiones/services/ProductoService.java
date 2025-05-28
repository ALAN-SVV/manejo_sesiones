package org.alan.manejosesiones.services;

import org.alan.manejosesiones.models.Productos;

import java.util.List;

public interface ProductoService {
    // Método para obtener la lista de productos
    List<Productos> listar();
}