package org.alan.manejosesiones.models;


// Clase que representa una categoría con sus atributos básicos
public class Categorias {
    // Identificador único de la categoría
    private Long id;
    // Nombre de la categoría
    private String nombre;
    // Descripción de la categoría
    private String descripcion;
    // Estado de la categoría (activa/inactiva)
    private boolean activa;

    // Constructor vacío necesario para algunos frameworks o serialización
    public Categorias() {
    }

    // Constructor con parámetros para crear una categoría completa
    public Categorias(Long id, String nombre, String descripcion, boolean activa) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activa = activa;
    }

    // Método getter para obtener el ID de la categoría
    public Long getId() {
        return id;
    }

    // Método setter para establecer el ID de la categoría
    public void setId(Long id) {
        this.id = id;
    }

    // Método getter para obtener el nombre de la categoría
    public String getNombre() {
        return nombre;
    }

    // Método setter para asignar el nombre de la categoría
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método getter para obtener la descripción de la categoría
    public String getDescripcion() {
        return descripcion;
    }

    // Método setter para asignar la descripción de la categoría
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método getter para obtener el estado de la categoría
    public boolean isActiva() {
        return activa;
    }

    // Método setter para asignar el estado de la categoría
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    // Método toString para representación textual del objeto
    @Override
    public String toString() {
        return "Categorias{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", activa=" + activa +
                '}';
    }
}
