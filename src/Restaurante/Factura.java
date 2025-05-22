package Restaurante;

public class Factura {

    private Pago pago;

    public int calcularPago(int id_pago, double monto_pago, String metodo_pago, double total_pago) {

            this.pago = new Pago(id_pago, monto_pago, metodo_pago, total_pago);
            return id_pago;

    }
}
