package org.alan.manejosesiones.models;


// Clase que representa una categoría con sus atributos básicos
public class Categoria {
    // Encapsulamiento usando wrappers para permitir valores null
    private Long idCategoria; // Identificador de la categoría (puede ser null)
    private String nombre;       // Nombre de la categoría
    private String descripcion;  // Descripción de la categoría
    private int condicion;       // Condición (por ejemplo, activo/inactivo)

    // Constructor vacío (por defecto)
    public Categoria() {
    }

    // Constructor con parámetros para inicializar todos los atributos
    public Categoria(Long idCategoria, String nombre, String descripcion, int condicion) {
        this.idCategoria = idCategoria;     // Asigna el valor recibido a idCategoria
        this.nombre = nombre;               // Asigna el valor recibido a nombre
        this.descripcion = descripcion;     // Asigna el valor recibido a descripcion
        this.condicion = condicion;         // Asigna el valor recibido a condicion
    }

    // Métodos getter y setter para idCategoria
    public Long getIdCategoria() {       // Devuelve el valor de idCategoria
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) { // Establece el valor de idCategoria
        this.idCategoria = idCategoria;
    }

    // Métodos getter y setter para nombre
    public String getNombre() {             // Devuelve el valor de nombre
        return nombre;
    }

    public void setNombre(String nombre) {  // Establece el valor de nombre
        this.nombre = nombre;
    }

    // Métodos getter y setter para descripcion
    public String getDescripcion() {        // Devuelve el valor de descripcion
        return descripcion;
    }

    public void setDescripcion(String descripcion) { // Establece el valor de descripcion
        this.descripcion = descripcion;
    }

    // Métodos getter y setter para condicion
    public int getCondicion() {             // Devuelve el valor de condicion
        return condicion;
    }

    public void setCondicion(int condicion) { // Establece el valor de condicion
        this.condicion = condicion;
    }
}
