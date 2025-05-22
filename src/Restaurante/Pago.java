package Restaurante;

public class Pago {

    private int id_pago;
    private double monto_pago;
    private String metodo_pago;
    private double total_pago;

    public Pago(int id_pago, double monto_pago, String metodo_pago, double total_pago) {
        this.id_pago = id_pago;
        this.monto_pago = monto_pago;
        this.metodo_pago = metodo_pago;
        this.total_pago = total_pago;
    }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public double getMonto_pago() {
        return monto_pago;
    }

    public void setMonto_pago(double monto_pago) {
        this.monto_pago = monto_pago;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public double getTotal_pago() {
        return total_pago;
    }

    public void setTotal_pago(double total_pago) {
        this.total_pago = total_pago;
    }
}
