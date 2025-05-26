package modelo;

import java.sql.Date;

/**
 * Clase que representa a un cliente en la base de datos
 */
public class Cliente {
    private int dni;
    private String nombre;
    private Date fechaNacimiento;

    /**
     * Constructor vacío
     */
    public Cliente() {
    }

    /**
     * Constructor con todos los campos
     * @param dni DNI del cliente (identificador único)
     * @param nombre Nombre del cliente
     * @param fechaNacimiento Fecha de nacimiento del cliente
     */
    public Cliente(int dni, String nombre, Date fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return DNI del cliente
     */
    public int getDni() {
        return dni;
    }

    /**
     * @param dni DNI del cliente
     */
    public void setDni(int dni) {
        this.dni = dni;
    }

    /**
     * @return Nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre Nombre del cliente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return Fecha de nacimiento del cliente
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento Fecha de nacimiento del cliente
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }
}