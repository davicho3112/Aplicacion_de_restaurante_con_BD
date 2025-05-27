package Restaurante;

import Administrador.Gestiones.GestionarReserva;
import Cliente.Cliente;
import Restaurante.Observador.Observador;

import java.text.SimpleDateFormat;
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

    public Orden(GestionarReserva fecha_reserva, Menu menu) {
        this.fecha_reserva = fecha_reserva;
        this.menu = menu;
        this.reserva_cliente = new Reserva();
        this.factura_cliente = new Factura();
        estadoOrden = "Pendiente";
        leerDato = new Scanner(System.in);
        contPago = 0;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║         ESTOS SON LOS PLATILLOS             ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        menu.mostrarMenu();

        do {
            System.out.println("Por favor, elija un platillo del menú:");
            System.out.print("Seleccione el número del platillo: ");
            platillo_seleccionar = leerDato.nextInt();
            menu.seleccionarPlatillo(platillo_seleccionar);

            System.out.println("¿Desea seleccionar otro platillo? (1. Sí / 2. No)");
            System.out.print("Seleccione una opción: ");
            opcion = leerDato.nextInt();
        } while (opcion != 2);

        int total = factura_cliente.calcularPago(menu.compras_platillos);

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

        for (int i = 0; i < 3; i++) {
            System.out.println("Procesando su pedido...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        pago = new Pago(contPago, metodo_pago, total);

        mostrarFactura();
        menu.compras_platillos.clear();
    }

    public void mostrarFactura() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                            RESUMEN DE SU FACTURA                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
        if (cliente != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            System.out.printf("│ %-15s: %-30s │\n", "Cliente", cliente.getNombre());
            System.out.printf("│ %-15s: %-30d │\n", "DNI", cliente.getDni());
            System.out.printf("│ %-15s: %-30s │\n", "Nacimiento", formato.format(cliente.getFecha_nacimiento()));
            System.out.printf("│ %-15s: %-30s │\n", "Contacto", (cliente.getNumero().isEmpty() ? "N/A" : cliente.getNumero().get(0)));
        }
        System.out.println("├──────────────────────────────────────────────────────────────────────────────┤");
        if (reserva_cliente.getFecha_reserva() != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            System.out.printf("│ %-20s: %-30s │\n", "Fecha reservada", formato.format(reserva_cliente.getFecha_reserva()));
            System.out.printf("│ %-20s: %-30d │\n", "Mesas reservadas", fecha_reserva.administrar_reservas.getNum_mesas());
            System.out.printf("│ %-20s: %-30d │\n", "Asientos reservados", fecha_reserva.administrar_reservas.getNum_asientos());
        } else {
            System.out.println("│ No se realizó reserva.                                                    │");
        }
        System.out.println("├──────────────────────────────────────────────────────────────────────────────┤");
        System.out.println("│ Platillos seleccionados:                                                    │");
        if (menu.compras_platillos.isEmpty()) {
            System.out.println("│   No hay platillos seleccionados.                                           │");
        } else {
            for (Platillo platillo : menu.compras_platillos) {
                System.out.printf("│   - %-20s | $%-7.2f | %-10s | %-10s │\n",
                        platillo.getNombre_platillo(),
                        platillo.getPrecio(),
                        platillo.getAcompañantes_platillo(),
                        platillo.getCategoria().getNombre_categoria());
            }
        }
        System.out.println("├──────────────────────────────────────────────────────────────────────────────┤");
        System.out.printf("│ %-20s: $%-30s │\n", "Total a pagar", (pago != null ? pago.getTotal_pago() : "0"));
        System.out.printf("│ %-20s: %-30s │\n", "Método de pago", (metodo_pago.isEmpty() ? "No seleccionado" : metodo_pago));
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝\n");
    }

    public void mostrarFechasDisponibles() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        if (fecha_reserva.fechas_disponibles.isEmpty()) {
            System.out.println("No hay fechas disponibles para reservar.");
        } else {
            System.out.println("Fechas disponibles:");
            for (int i = 0; i < fecha_reserva.fechas_disponibles.size(); i++) {
                System.out.printf("  %-2d. %s\n", (i + 1), formato.format(fecha_reserva.fechas_disponibles.get(i)));
            }
        }
        System.out.println("═══════════════════════════════════════════════════════════════════════════════");
    }

    public void actualizarOrden(String estado) {
        System.out.println("Estado de la orden actualizado: " + estado);
        this.estadoOrden = estado;
    }
}