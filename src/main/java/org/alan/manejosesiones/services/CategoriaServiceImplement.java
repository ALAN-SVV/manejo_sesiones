package org.alan.manejosesiones.services;

import org.alan.manejosesiones.models.Categorias;

import java.util.Arrays;
import java.util.List;

// Implementación de la interfaz CategoriaService
public class CategoriaServiceImplement implements CategoriaService {

    @Override
    public List<Categorias> listar() {
        // Retorna una lista inmutable con tres categorías de ejemplo
        return Arrays.asList(
                new Categorias(1L, "Electrónicos", "Dispositivos electrónicos y accesorios", true),
                new Categorias(2L, "Mueble", "Productos para el hogar y decoración", true),
                new Categorias(3L, "Soporte", "Suministros y equipos de oficina", false)
        );
    }
}

