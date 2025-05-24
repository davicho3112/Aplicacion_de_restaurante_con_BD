package Restaurante;

import Administrador.Gestiones.GestionarReserva;
import Cliente.Cliente;
import Restaurante.Observador.Observador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Orden implements Observador {

    private Reserva reserva_cliente;
    private Factura factura_cliente;
    private Menu menu;
    private Cliente cliente;
    private GestionarReserva fecha_reserva;
    private String estadoOrden;
    private Scanner leerDato;
    private int contPago;
    String metodo_pago = "";
    Pago pago;


    public Orden() {
        estadoOrden = "Pendiente";
        leerDato = new Scanner(System.in);
        contPago = 0;
    }

    public void Reservar() {

        mostrarFechasDisponibles();

        System.out.println("Por favor, elija una fecha :");
        int fechaSeleccionada = leerDato.nextInt();
        if (fechaSeleccionada < 1 || fechaSeleccionada > fecha_reserva.fechas_disponibles.size()) {
            System.out.println("Fecha no válida.");
            return;
        }

        reserva_cliente.setFecha_reserva(fecha_reserva.fechas_disponibles.get(fechaSeleccionada - 1));

        System.out.println("Esta es la cantidad de asientos y mesas disponibles: ");
        System.out.println("Mesas disponibles: " + fecha_reserva.administrar_reservas.getNum_mesas());
        System.out.println("Asientos disponibles: " + fecha_reserva.administrar_reservas.getNum_asientos());
        System.out.println("¿Cuántas mesas desea reservar?");
        int num_mesas = leerDato.nextInt();
        if(num_mesas > fecha_reserva.administrar_reservas.getNum_mesas()) {
            System.out.println("No hay suficientes mesas disponibles.");
            return;
        }else{
            fecha_reserva.administrar_reservas.setNum_mesas(num_mesas);
        }
        System.out.println("¿Cuántos asientos desea reservar?");
        int num_asientos = leerDato.nextInt();
        if(num_asientos > fecha_reserva.administrar_reservas.getNum_asientos()) {
            System.out.println("No hay suficientes asientos disponibles.");
            return;
        }else{
            fecha_reserva.administrar_reservas.setNum_asientos(num_asientos);
        }

    }

    public void solicitarPlatillo() {

        Scanner leerDato = new Scanner(System.in);
        int platillo_seleccionar = 0;
        int opcion = 0;
        int tiempo_espera = 0;


        System.out.println("Estos son los platillos que ofrecemos");
        menu.mostrarMenu();

            do {
                //Se le muestra al cliente el menu y se le pide que seleccione un platillo
                System.out.println("Por favor, elija un platillo del menú:");
                System.out.print("Seleccione el número del platillo: ");
                platillo_seleccionar = leerDato.nextInt();
                menu.seleccionarPlatillo(platillo_seleccionar); // Por medio de este metodo el cliente va a tratar de seleccionar un platillo

                System.out.println("¿Desea seleccionar otro platillo? (1. Sí / 2. No)");
                System.out.print("Seleccione una opción: ");
                opcion = leerDato.nextInt();
            } while (opcion != 2);

        //Procesar la preparacion del plantillo
        //Un bucle que simule el tiempo de espera para la preparacion del platillo
        while (tiempo_espera < 5) {
            System.out.println("Esperando...");
            try {
                Thread.sleep(1000); // Espera 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tiempo_espera++;
        }

        System.out.println(estadoOrden);

        int total = factura_cliente.calcularPago(menu.compras_platillos); // Se calcula el total de la factura

        //Se le pregunta al cliente por el metodo de pago
        System.out.println("¿Cómo desea pagar? (1. Efectivo / 2. Tarjeta)");
        System.out.print("Seleccione una opción: ");
        int opcion_pago = leerDato.nextInt();
        if (opcion_pago == 1) {
            metodo_pago = "Pago en efectivo seleccionado.";
        } else if (opcion_pago == 2) {
            metodo_pago = "Pago con tarjeta seleccionado.";
        } else {
            System.out.println("Opción no válida.");
        }
        contPago++;

        pago = new Pago(contPago,metodo_pago, total);

        //Se le muetra la factura al cliente
        mostrarFactura();

        menu.compras_platillos.clear();// Cuando ya culmine las compras del cliente se limpian los platillos seleccionados
    }

    public void mostrarFactura() {

        System.out.println("Factura:");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Fecha de reserva: " + reserva_cliente.getFecha_reserva());
        System.out.println("Platillos seleccionados:");
        for (Platillo platillo : menu.compras_platillos) {
            System.out.println(" - " + platillo.getCodigo_platillo() + " " + platillo.getNombre_platillo() + ": $" + platillo.getPrecio() +" " + platillo.getAcompañantes_platillo() + " " + platillo.getCategoria().getNombre_categoria() + " " + platillo.getCategoria().getDescripcion_categoria());
        }
        System.out.println("Total a pagar: $" + pago.getTotal_pago());
        System.out.println("Método de pago: " + metodo_pago);

    }

    private void mostrarFechasDisponibles() {

        System.out.println("Estas son las fechas disponibles para reservar: ");
        for (int i = 0; i < fecha_reserva.fechas_disponibles.size(); i++) {
            System.out.println("Fecha " + (i + 1) + ": " + fecha_reserva.fechas_disponibles.get(i));
        }
    }

    public void actualizarOrden(String estado) {
        // Lógica para actualizar el estado de la orden
        System.out.println("Estado de la orden actualizado: " + estado);
        this.estadoOrden = estado;
    }
}
