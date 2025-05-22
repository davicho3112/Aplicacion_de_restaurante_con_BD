package Restaurante;

import Cliente.Cliente;

import java.util.ArrayList;

public class Orden {

    private Reserva reserva_cliente;
    private Factura factura_cliente;
    private ArrayList<Cliente> clientes;

    public Orden(Reserva reserva_cliente, Factura factura_cliente, ArrayList<Cliente> clientes) {
        this.reserva_cliente = reserva_cliente;
        this.factura_cliente = factura_cliente;
        this.clientes = clientes;
    }

    public void Reservar() {

        //Se implememnta la logica para reservar los asiento o mesas que el cliente solicite
        //Se implementa la logica para mostrar las fechas disponibles llamando al metodo mostrarFechasDisponibles
    }

    public void solicitarPlatillo() {

    }

    public void mostrarFactura() {
        //Se implementa la logica para mostrar la factura al cliente
    }

    private void mostrarFechasDisponibles() {

    }
}
