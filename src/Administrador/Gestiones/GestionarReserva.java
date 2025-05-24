package Administrador.Gestiones;

import Restaurante.Reserva;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestionarReserva {

    private Reserva administrar_reservas;
    private List<Date> fechas_disponibles;

    public GestionarReserva() {
        this.administrar_reservas = new Reserva();
        fechas_disponibles = new ArrayList<>();
    }

    public void agregarMesa(int numero_mesas) {

        // Lógica para asignar el numero de mesas
        administrar_reservas.setNum_mesas(numero_mesas);
        System.out.println("Número de mesas asignadas: " + numero_mesas);
    }

    public void agregarAsiento(int numero_asientos) {
        // Lógica para asignar el numero de asientos
        administrar_reservas.setNum_asientos(numero_asientos);
        System.out.println("Número de asientos asignados: " + numero_asientos);
    }

    public void agregarFechasDisponible(Date fecha) {
        // Lógica para agregar fechas disponibles
        if (fechas_disponibles.contains(fecha)) {
            fechas_disponibles.add(fecha);
        } else {
            System.out.println("Fecha ya reservada: " + fecha);
        }
    }
}
