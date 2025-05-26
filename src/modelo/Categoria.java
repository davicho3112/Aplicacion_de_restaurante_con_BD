package modelo;

/**
 * Clase que representa una categoría en la base de datos
 */
public class Categoria {
    private int id;
    private String nombre;
    private String descripcion;

    /**
     * Constructor vacío
     */
    public Categoria() {
    }

    /**
     * Constructor con todos los campos
     * @param id ID de la categoría (identificador único)
     * @param nombre Nombre de la categoría
     * @param descripcion Descripción de la categoría
     */
    public Categoria(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * @return ID de la categoría
     */
    public int getId() {
        return id;
    }

    /**
     * @param id ID de la categoría
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Nombre de la categoría
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre Nombre de la categoría
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return Descripción de la categoría
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion Descripción de la categoría
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}