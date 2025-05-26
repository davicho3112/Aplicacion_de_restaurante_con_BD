package modelo;

import java.math.BigDecimal;

/**
 * Clase que representa un platillo en la base de datos
 */
public class Platillo {
    private int id;
    private String nombre;
    private BigDecimal precio;
    private String acompanantes;

    /**
     * Constructor vacío
     */
    public Platillo() {
    }

    /**
     * Constructor con todos los campos
     * @param id ID del platillo (identificador único)
     * @param nombre Nombre del platillo
     * @param precio Precio del platillo
     * @param acompanantes Acompañantes del platillo
     */
    public Platillo(int id, String nombre, BigDecimal precio, String acompanantes) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.acompanantes = acompanantes;
    }

    /**
     * @return ID del platillo
     */
    public int getId() {
        return id;
    }

    /**
     * @param id ID del platillo
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Nombre del platillo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre Nombre del platillo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return Precio del platillo
     */
    public BigDecimal getPrecio() {
        return precio;
    }

    /**
     * @param precio Precio del platillo
     */
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    /**
     * @return Acompañantes del platillo
     */
    public String getAcompanantes() {
        return acompanantes;
    }

    /**
     * @param acompanantes Acompañantes del platillo
     */
    public void setAcompanantes(String acompanantes) {
        this.acompanantes = acompanantes;
    }

    @Override
    public String toString() {
        return "Platillo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", acompanantes='" + acompanantes + '\'' +
                '}';
    }
}