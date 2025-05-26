package modelo;

/**
 * Clase que representa la relación entre un cliente y una reserva en la base de datos
 */
public class ReservaCliente {
    private int id;
    private int fkDniCliente;
    private int fkReserva;

    /**
     * Constructor vacío
     */
    public ReservaCliente() {
    }

    /**
     * Constructor con todos los campos
     * @param id ID de la relación (identificador único)
     * @param fkDniCliente DNI del cliente (clave foránea)
     * @param fkReserva ID de la reserva (clave foránea)
     */
    public ReservaCliente(int id, int fkDniCliente, int fkReserva) {
        this.id = id;
        this.fkDniCliente = fkDniCliente;
        this.fkReserva = fkReserva;
    }

    /**
     * @return ID de la relación
     */
    public int getId() {
        return id;
    }

    /**
     * @param id ID de la relación
     */
    public void setId(int id) {
        this.id = id;
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

    /**
     * @return ID de la reserva (clave foránea)
     */
    public int getFkReserva() {
        return fkReserva;
    }

    /**
     * @param fkReserva ID de la reserva (clave foránea)
     */
    public void setFkReserva(int fkReserva) {
        this.fkReserva = fkReserva;
    }

    @Override
    public String toString() {
        return "ReservaCliente{" +
                "id=" + id +
                ", fkDniCliente=" + fkDniCliente +
                ", fkReserva=" + fkReserva +
                '}';
    }
}