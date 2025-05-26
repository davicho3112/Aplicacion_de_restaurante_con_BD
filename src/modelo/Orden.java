package modelo;

import java.sql.Timestamp;

/**
 * Clase que representa una orden en la base de datos
 */
public class Orden {
    private int id;
    private Timestamp fechaOrden;
    private int fkDniCliente;

    /**
     * Constructor vacío
     */
    public Orden() {
    }

    /**
     * Constructor con todos los campos
     * @param id ID de la orden (identificador único)
     * @param fechaOrden Fecha y hora de la orden
     * @param fkDniCliente DNI del cliente (clave foránea)
     */
    public Orden(int id, Timestamp fechaOrden, int fkDniCliente) {
        this.id = id;
        this.fechaOrden = fechaOrden;
        this.fkDniCliente = fkDniCliente;
    }

    /**
     * @return ID de la orden
     */
    public int getId() {
        return id;
    }

    /**
     * @param id ID de la orden
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Fecha y hora de la orden
     */
    public Timestamp getFechaOrden() {
        return fechaOrden;
    }

    /**
     * @param fechaOrden Fecha y hora de la orden
     */
    public void setFechaOrden(Timestamp fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    /**
     * @return DNI del cliente (clave foránea)
     */
    public int getFkDniCliente() {
        return fkDniCliente;
    }

    /**
     * @param fkDniCliente DNI del cliente (clave foránea)
     */
    public void setFkDniCliente(int fkDniCliente) {
        this.fkDniCliente = fkDniCliente;
    }

    @Override
    public String toString() {
        return "Orden{" +
                "id=" + id +
                ", fechaOrden=" + fechaOrden +
                ", fkDniCliente=" + fkDniCliente +
                '}';
    }
}