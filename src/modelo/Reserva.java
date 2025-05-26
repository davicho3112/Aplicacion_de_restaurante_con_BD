package modelo;

import java.sql.Timestamp;

/**
 * Clase que representa una reserva en la base de datos
 */
public class Reserva {
    private int id;
    private int numAsientos;
    private int numMesas;
    private Timestamp fechaReserva;
    private int numAcompanantes;

    /**
     * Constructor vacío
     */
    public Reserva() {
    }

    /**
     * Constructor con todos los campos
     * @param id ID de la reserva (identificador único)
     * @param numAsientos Número de asientos reservados
     * @param numMesas Número de mesas reservadas
     * @param fechaReserva Fecha y hora de la reserva
     * @param numAcompanantes Número de acompañantes
     */
    public Reserva(int id, int numAsientos, int numMesas, Timestamp fechaReserva, int numAcompanantes) {
        this.id = id;
        this.numAsientos = numAsientos;
        this.numMesas = numMesas;
        this.fechaReserva = fechaReserva;
        this.numAcompanantes = numAcompanantes;
    }

    /**
     * @return ID de la reserva
     */
    public int getId() {
        return id;
    }

    /**
     * @param id ID de la reserva
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Número de asientos reservados
     */
    public int getNumAsientos() {
        return numAsientos;
    }

    /**
     * @param numAsientos Número de asientos reservados
     */
    public void setNumAsientos(int numAsientos) {
        this.numAsientos = numAsientos;
    }

    /**
     * @return Número de mesas reservadas
     */
    public int getNumMesas() {
        return numMesas;
    }

    /**
     * @param numMesas Número de mesas reservadas
     */
    public void setNumMesas(int numMesas) {
        this.numMesas = numMesas;
    }

    /**
     * @return Fecha y hora de la reserva
     */
    public Timestamp getFechaReserva() {
        return fechaReserva;
    }

    /**
     * @param fechaReserva Fecha y hora de la reserva
     */
    public void setFechaReserva(Timestamp fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    /**
     * @return Número de acompañantes
     */
    public int getNumAcompanantes() {
        return numAcompanantes;
    }

    /**
     * @param numAcompanantes Número de acompañantes
     */
    public void setNumAcompanantes(int numAcompanantes) {
        this.numAcompanantes = numAcompanantes;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", numAsientos=" + numAsientos +
                ", numMesas=" + numMesas +
                ", fechaReserva=" + fechaReserva +
                ", numAcompanantes=" + numAcompanantes +
                '}';
    }
}