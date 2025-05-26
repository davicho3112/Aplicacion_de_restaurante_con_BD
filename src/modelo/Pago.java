package modelo;

import java.math.BigDecimal;

/**
 * Clase que representa un pago en la base de datos
 */
public class Pago {
    private int id;
    private String metodoPago;
    private BigDecimal total;

    /**
     * Constructor vacío
     */
    public Pago() {
    }

    /**
     * Constructor con todos los campos
     * @param id ID del pago (identificador único)
     * @param metodoPago Método de pago utilizado
     * @param total Monto total del pago
     */
    public Pago(int id, String metodoPago, BigDecimal total) {
        this.id = id;
        this.metodoPago = metodoPago;
        this.total = total;
    }

    /**
     * @return ID del pago
     */
    public int getId() {
        return id;
    }

    /**
     * @param id ID del pago
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Método de pago utilizado
     */
    public String getMetodoPago() {
        return metodoPago;
    }

    /**
     * @param metodoPago Método de pago utilizado
     */
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    /**
     * @return Monto total del pago
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total Monto total del pago
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "id=" + id +
                ", metodoPago='" + metodoPago + '\'' +
                ", total=" + total +
                '}';
    }
}