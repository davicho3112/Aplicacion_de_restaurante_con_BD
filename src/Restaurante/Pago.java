package Restaurante;

public class Pago {

    private int id_pago;
    private double total_pago;
    private String metodo_pago;

    public Pago(int id_pago, String metodo_pago, double total_pago) {
        this.id_pago = id_pago;
        this.metodo_pago = metodo_pago;
        this.total_pago = total_pago;
    }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
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
