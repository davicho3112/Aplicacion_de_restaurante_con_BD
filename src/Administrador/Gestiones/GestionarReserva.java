package Administrador.Gestiones;

import Restaurante.Reserva;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestionarReserva {

    public Reserva administrar_reservas;
    public List<Date> fechas_disponibles;

    private final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    public GestionarReserva() {
        this.administrar_reservas = new Reserva();
        this.fechas_disponibles = new ArrayList<>();
    }

    public void agregarMesa(int numero_mesas) {
        if (numero_mesas <= 0) {
            System.out.println("\n========================================");
            System.out.printf(" %-38s\n", "El número de mesas debe ser mayor a cero.");
            System.out.println("========================================\n");
            return;
        }
        int total = administrar_reservas.getNum_mesas() + numero_mesas;
        administrar_reservas.setNum_mesas(total);
        System.out.println("\n========================================");
        System.out.printf(" %-38s\n", "Mesas asignadas correctamente.");
        System.out.printf(" %-20s: %d\n", "Total de mesas", total);
        System.out.println("========================================\n");
    }

    public void agregarAsiento(int numero_asientos) {
        if (numero_asientos <= 0) {
            System.out.println("\n========================================");
            System.out.printf(" %-38s\n", "El número de asientos debe ser mayor a cero.");
            System.out.println("========================================\n");
            return;
        }
        int total = administrar_reservas.getNum_asientos() + numero_asientos;
        administrar_reservas.setNum_asientos(total);
        System.out.println("\n========================================");
        System.out.printf(" %-38s\n", "Asientos asignados correctamente.");
        System.out.printf(" %-20s: %d\n", "Total de asientos", total);
        System.out.println("========================================\n");
    }

    public void agregarFechasDisponible(Date fecha) {
        if (fecha == null) {
            System.out.println("\n========================================");
            System.out.printf(" %-38s\n", "Fecha inválida.");
            System.out.println("========================================\n");
            return;
        }
        if (!fechas_disponibles.contains(fecha)) {
            fechas_disponibles.add(fecha);
            System.out.println("\n========================================");
            System.out.printf(" %-38s\n", "Fecha disponible agregada.");
            System.out.printf(" %-20s: %s\n", "Fecha", formatoFecha.format(fecha));
            System.out.println("========================================\n");
        } else {
            System.out.println("\n========================================");
            System.out.printf(" %-38s\n", "La fecha ya está registrada.");
            System.out.printf(" %-20s: %s\n", "Fecha", formatoFecha.format(fecha));
            System.out.println("========================================\n");
        }
    }

    public void mostrarEstadoReserva() {
        System.out.println("\n================================================================================");
        System.out.printf("| %-76s |\n", "ESTADO ACTUAL DE LA RESERVA");
        System.out.println("================================================================================");
        System.out.printf("| %-20s | %-20s |\n", "Mesas asignadas", administrar_reservas.getNum_mesas());
        System.out.printf("| %-20s | %-20s |\n", "Asientos asignados", administrar_reservas.getNum_asientos());
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("| %-76s |\n", "Fechas disponibles:");
        if (fechas_disponibles.isEmpty()) {
            System.out.printf("| %-76s |\n", "(No hay fechas disponibles)");
        } else {
            for (Date fecha : fechas_disponibles) {
                System.out.printf("|   - %-72s |\n", formatoFecha.format(fecha));
            }
        }
        System.out.println("================================================================================\n");
    }
}