package Restaurante.Observador;

import Cliente.Cliente;
import Restaurante.Platillo;

import java.util.ArrayList;
import java.util.List;

public class ProcesarPedido {

    //Se agregan a los observadores que se van a notificar
    List<Cliente> clientes;
    private String estadoPedido;
    Observador observador;

    public ProcesarPedido() {
        clientes = new ArrayList<>();
    }

    public void agregarObservador(Cliente cliente) {
        clientes.add(cliente);
    }

    public void procesar(Cliente cliente){

        estadoPedido = "El platillo esta servido!! para el cliente: " + cliente.getNombre();

        // Notificar a todos los observadores (clientes)
        notificar();
    }

    private void notificar() {
        for (Cliente cliente : clientes) {
            cliente.notificacion_pedido(estadoPedido);
        }
    }

}
