package modelo;

/**
 * Clase que representa una factura en la base de datos
 */
public class Factura {
    private int id;
    private int fkIdPago;
    private int fkIdReserva;

    /**
     * Constructor vacío
     */
    public Factura() {
    }

    /**
     * Constructor con todos los campos
     * @param id ID de la factura (identificador único)
     * @param fkIdPago ID del pago asociado (clave foránea)
     * @param fkIdReserva ID de la reserva asociada (clave foránea)
     */
    public Factura(int id, int fkIdPago, int fkIdReserva) {
        this.id = id;
        this.fkIdPago = fkIdPago;
        this.fkIdReserva = fkIdReserva;
    }

    /**
     * @return ID de la factura
     */
    public int getId() {
        return id;
    }

    /**
     * @param id ID de la factura
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return ID del pago asociado (clave foránea)
     */
    public int getFkIdPago() {
        return fkIdPago;
    }

    /**
     * @param fkIdPago ID del pago asociado (clave foránea)
     */
    public void setFkIdPago(int fkIdPago) {
        this.fkIdPago = fkIdPago;
    }

    /**
     * @return ID de la reserva asociada (clave foránea)
     */
    public int getFkIdReserva() {
        return fkIdReserva;
    }

    /**
     * @param fkIdReserva ID de la reserva asociada (clave foránea)
     */
    public void setFkIdReserva(int fkIdReserva) {
        this.fkIdReserva = fkIdReserva;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", fkIdPago=" + fkIdPago +
                ", fkIdReserva=" + fkIdReserva +
                '}';
    }
}