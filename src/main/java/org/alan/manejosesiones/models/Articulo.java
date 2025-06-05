package org.alan.manejosesiones.models;

// Clase que representa un artículo con sus atributos básicos
public class Articulo {
    // Encapsulamiento usando wrappers para permitir valores null
    private Long idArticulo;      // Identificador del artículo (puede ser null)
    private Long idCategoria;     // ID de la categoría a la que pertenece
    private String codigo;        // Código único del artículo
    private String nombre;        // Nombre del artículo
    private Long stock;          // Cantidad disponible en inventario
    private String descripcion;   // Descripción detallada
    private String imagen;        // Ruta o nombre de la imagen asociada
    private boolean condicion;    // Estado (activo/inactivo)

    // Constructor vacío (por defecto)
    public Articulo() {
    }

    // Constructor con parámetros para inicializar todos los atributos
    public Articulo(Long idArticulo, Long idCategoria, String codigo,
                    String nombre, Long stock, String descripcion,
                    String imagen, boolean condicion) {
        this.idArticulo = idArticulo;
        this.idCategoria = idCategoria;
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.condicion = condicion;
    }

    // Métodos getter y setter para idArticulo
    public Long getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Long idArticulo) {
        this.idArticulo = idArticulo;
    }

    // Métodos getter y setter para idCategoria
    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    // Métodos getter y setter para codigo
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    // Métodos getter y setter para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos getter y setter para stock
    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    // Métodos getter y setter para descripcion
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Métodos getter y setter para imagen
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public boolean tieneImagen() {
        return imagen != null && !imagen.trim().isEmpty();
    }

    public String getImagenThumbnail() {
        if (!tieneImagen()) return "";

        // Opcional: Transformar URL para obtener thumbnail si usas un servicio como Cloudinary
        return imagen + "?width=100"; // Ejemplo para algunos servicios
    }

    // Métodos getter y setter para condicion
    public boolean isCondicion() {
        return condicion;
    }

    public void setCondicion(boolean condicion) {
        this.condicion = condicion;
    }
}