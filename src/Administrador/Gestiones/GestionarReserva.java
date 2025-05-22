package Administrador.Gestiones;

import java.util.Date;
import java.util.List;

public class GestionarReserva {

    private Reserva administrar_reservas;
    private List<Date> fechas_disponibles;

    public GestionarReserva() {
        this.administrar_reservas = new Reserva();
        this.fechas_disponibles = administrar_reservas.getFechasDisponibles();
    }

    public void agregarMesa(int numero_mesas) {
        // Lógica para asignar el numero de mesas
    }

    public void agregarAsiento(int numero_asientos) {
        // Lógica para asignar el numero de asientos
    }

    public void agregarFechasDisponible(Date fecha) {
        // Lógica para agregar fechas disponibles
        if (fechas_disponibles.contains(fecha)) {
            System.out.println("Fecha disponible: " + fecha);
        } else {
            System.out.println("Fecha no disponible: " + fecha);
        }
    }
}
