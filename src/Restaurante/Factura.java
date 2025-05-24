package Restaurante;

import java.util.List;

public class Factura {

    private Pago pago;

    public int calcularPago(List<Platillo> platillo) {

        int total = 0;
        for (Platillo precio_platillo : platillo) {
            total += precio_platillo.getPrecio();
        }
        return total;

    }
}
