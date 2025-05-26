package vista;

import conexion.Conexion;
import modelo.Reserva;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que proporciona una interfaz de consola para interactuar con la tabla Reserva
 */
public class ReservaConsola {
    private Conexion conexion;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;

    /**
     * Constructor
     */
    public ReservaConsola() {
        conexion = new Conexion();
        scanner = new Scanner(System.in);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    }

    /**
     * Muestra el menú principal y procesa la opción seleccionada
     */
    public void mostrarMenu() {
        int opcion = -1;
        do {
            System.out.println("\n===== GESTIÓN DE RESERVAS =====");
            System.out.println("1. Insertar reserva");
            System.out.println("2. Actualizar reserva");
            System.out.println("3. Eliminar reserva");
            System.out.println("4. Buscar reserva por ID");
            System.out.println("5. Listar todas las reservas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número. Intente de nuevo.");
                opcion = -1;
                continue;
            }

            switch (opcion) {
                case 1:
                    insertarReserva();
                    break;
                case 2:
                    actualizarReserva();
                    break;
                case 3:
                    eliminarReserva();
                    break;
                case 4:
                    buscarReservaPorId();
                    break;
                case 5:
                    listarTodasLasReservas();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    /**
     * Solicita los datos para insertar una nueva reserva
     */
    private void insertarReserva() {
        System.out.println("\n--- INSERTAR RESERVA ---");

        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Número de asientos: ");
            int numAsientos = Integer.parseInt(scanner.nextLine());

            System.out.print("Número de mesas: ");
            int numMesas = Integer.parseInt(scanner.nextLine());

            System.out.print("Fecha y hora de la reserva (dd/MM/yyyy HH:mm): ");
            String fechaStr = scanner.nextLine();
            Timestamp fechaReserva = convertirFecha(fechaStr);

            System.out.print("Número de acompañantes: ");
            int numAcompanantes = Integer.parseInt(scanner.nextLine());

            Reserva reserva = new Reserva(id, numAsientos, numMesas, fechaReserva, numAcompanantes);

            if (conexion.insertarReserva(reserva)) {
                System.out.println("Reserva insertada correctamente.");
            } else {
                System.out.println("Error al insertar la reserva.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Los campos numéricos deben ser números enteros.");
        } catch (ParseException e) {
            System.out.println("Error: Formato de fecha y hora incorrecto. Use dd/MM/yyyy HH:mm.");
        }
    }

    /**
     * Solicita los datos para actualizar una reserva existente
     */
    private void actualizarReserva() {
        System.out.println("\n--- ACTUALIZAR RESERVA ---");

        try {
            System.out.print("ID de la reserva a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine());

            Reserva reservaExistente = conexion.buscarReservaPorId(id);

            if (reservaExistente == null) {
                System.out.println("No existe una reserva con ese ID.");
                return;
            }

            System.out.print("Nuevo número de asientos (deje en blanco para mantener el actual): ");
            String numAsientosStr = scanner.nextLine();
            if (!numAsientosStr.isEmpty()) {
                reservaExistente.setNumAsientos(Integer.parseInt(numAsientosStr));
            }

            System.out.print("Nuevo número de mesas (deje en blanco para mantener el actual): ");
            String numMesasStr = scanner.nextLine();
            if (!numMesasStr.isEmpty()) {
                reservaExistente.setNumMesas(Integer.parseInt(numMesasStr));
            }

            System.out.print("Nueva fecha y hora de la reserva (dd/MM/yyyy HH:mm) (deje en blanco para mantener la actual): ");
            String fechaStr = scanner.nextLine();
            if (!fechaStr.isEmpty()) {
                Timestamp fechaReserva = convertirFecha(fechaStr);
                reservaExistente.setFechaReserva(fechaReserva);
            }

            System.out.print("Nuevo número de acompañantes (deje en blanco para mantener el actual): ");
            String numAcompanantesStr = scanner.nextLine();
            if (!numAcompanantesStr.isEmpty()) {
                reservaExistente.setNumAcompanantes(Integer.parseInt(numAcompanantesStr));
            }

            if (conexion.actualizarReserva(reservaExistente)) {
                System.out.println("Reserva actualizada correctamente.");
            } else {
                System.out.println("Error al actualizar la reserva.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Los campos numéricos deben ser números enteros.");
        } catch (ParseException e) {
            System.out.println("Error: Formato de fecha y hora incorrecto. Use dd/MM/yyyy HH:mm.");
        }
    }

    /**
     * Solicita el ID de la reserva a eliminar
     */
    private void eliminarReserva() {
        System.out.println("\n--- ELIMINAR RESERVA ---");

        try {
            System.out.print("ID de la reserva a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("¿Está seguro de eliminar esta reserva? (S/N): ");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("S")) {
                if (conexion.eliminarReserva(id)) {
                    System.out.println("Reserva eliminada correctamente.");
                } else {
                    System.out.println("Error al eliminar la reserva o la reserva no existe.");
                }
            } else {
                System.out.println("Operación cancelada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
        }
    }

    /**
     * Solicita el ID de la reserva a buscar y muestra sus datos
     */
    private void buscarReservaPorId() {
        System.out.println("\n--- BUSCAR RESERVA POR ID ---");

        try {
            System.out.print("ID de la reserva a buscar: ");
            int id = Integer.parseInt(scanner.nextLine());

            Reserva reserva = conexion.buscarReservaPorId(id);

            if (reserva != null) {
                System.out.println("\nDatos de la reserva:");
                System.out.println("ID: " + reserva.getId());
                System.out.println("Número de asientos: " + reserva.getNumAsientos());
                System.out.println("Número de mesas: " + reserva.getNumMesas());
                System.out.println("Fecha y hora de la reserva: " + dateFormat.format(reserva.getFechaReserva()));
                System.out.println("Número de acompañantes: " + reserva.getNumAcompanantes());
            } else {
                System.out.println("No existe una reserva con ese ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
        }
    }

    /**
     * Lista todas las reservas
     */
    private void listarTodasLasReservas() {
        System.out.println("\n--- LISTA DE RESERVAS ---");

        List<Reserva> reservas = conexion.listarTodasLasReservas();

        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
        } else {
            System.out.println("ID\tAsientos\tMesas\tFecha y hora\tAcompañantes");
            System.out.println("--------------------------------------------------------------");

            for (Reserva reserva : reservas) {
                System.out.println(reserva.getId() + "\t" + 
                                  reserva.getNumAsientos() + "\t\t" + 
                                  reserva.getNumMesas() + "\t" + 
                                  dateFormat.format(reserva.getFechaReserva()) + "\t" + 
                                  reserva.getNumAcompanantes());
            }
        }
    }

    /**
     * Convierte una cadena de fecha y hora en formato dd/MM/yyyy HH:mm a un objeto Timestamp
     * @param fechaStr Cadena de fecha y hora en formato dd/MM/yyyy HH:mm
     * @return Objeto Timestamp
     * @throws ParseException Si el formato de la fecha y hora es incorrecto
     */
    private Timestamp convertirFecha(String fechaStr) throws ParseException {
        java.util.Date utilDate = dateFormat.parse(fechaStr);
        return new Timestamp(utilDate.getTime());
    }
}