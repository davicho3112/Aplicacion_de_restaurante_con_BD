package vista;

import conexion.Conexion;
import modelo.ReservaCliente;
import modelo.Cliente;
import modelo.Reserva;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;

/**
 * Clase que proporciona una interfaz de consola para interactuar con la tabla Reserva_Cliente
 */
public class ReservaClienteConsola {
    private Conexion conexion;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;

    /**
     * Constructor
     */
    public ReservaClienteConsola() {
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
            System.out.println("\n===== GESTIÓN DE RELACIONES CLIENTE-RESERVA =====");
            System.out.println("1. Asignar cliente a reserva");
            System.out.println("2. Actualizar relación cliente-reserva");
            System.out.println("3. Eliminar relación cliente-reserva");
            System.out.println("4. Buscar relación por ID");
            System.out.println("5. Listar todas las relaciones");
            System.out.println("6. Listar relaciones por cliente");
            System.out.println("7. Listar relaciones por reserva");
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
                    asignarClienteAReserva();
                    break;
                case 2:
                    actualizarRelacion();
                    break;
                case 3:
                    eliminarRelacion();
                    break;
                case 4:
                    buscarRelacionPorId();
                    break;
                case 5:
                    listarTodasLasRelaciones();
                    break;
                case 6:
                    listarRelacionesPorCliente();
                    break;
                case 7:
                    listarRelacionesPorReserva();
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
     * Solicita los datos para asignar un cliente a una reserva
     */
    private void asignarClienteAReserva() {
        System.out.println("\n--- ASIGNAR CLIENTE A RESERVA ---");

        try {
            System.out.print("ID de la relación: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("DNI del cliente: ");
            int dniCliente = Integer.parseInt(scanner.nextLine());

            // Verificar que el cliente existe
            Cliente cliente = conexion.buscarClientePorDni(dniCliente);
            if (cliente == null) {
                System.out.println("Error: No existe un cliente con ese DNI.");
                return;
            }

            System.out.print("ID de la reserva: ");
            int idReserva = Integer.parseInt(scanner.nextLine());

            // Verificar que la reserva existe
            Reserva reserva = conexion.buscarReservaPorId(idReserva);
            if (reserva == null) {
                System.out.println("Error: No existe una reserva con ese ID.");
                return;
            }

            ReservaCliente reservaCliente = new ReservaCliente(id, dniCliente, idReserva);

            if (conexion.insertarReservaCliente(reservaCliente)) {
                System.out.println("Cliente asignado a la reserva correctamente.");
            } else {
                System.out.println("Error al asignar el cliente a la reserva.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Los campos numéricos deben ser números enteros.");
        }
    }

    /**
     * Solicita los datos para actualizar una relación cliente-reserva existente
     */
    private void actualizarRelacion() {
        System.out.println("\n--- ACTUALIZAR RELACIÓN CLIENTE-RESERVA ---");

        try {
            System.out.print("ID de la relación a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine());

            ReservaCliente relacionExistente = conexion.buscarReservaClientePorId(id);

            if (relacionExistente == null) {
                System.out.println("No existe una relación con ese ID.");
                return;
            }

            System.out.print("Nuevo DNI del cliente (deje en blanco para mantener el actual): ");
            String dniClienteStr = scanner.nextLine();
            if (!dniClienteStr.isEmpty()) {
                int dniCliente = Integer.parseInt(dniClienteStr);
                
                // Verificar que el cliente existe
                Cliente cliente = conexion.buscarClientePorDni(dniCliente);
                if (cliente == null) {
                    System.out.println("Error: No existe un cliente con ese DNI.");
                    return;
                }
                
                relacionExistente.setFkDniCliente(dniCliente);
            }

            System.out.print("Nuevo ID de la reserva (deje en blanco para mantener el actual): ");
            String idReservaStr = scanner.nextLine();
            if (!idReservaStr.isEmpty()) {
                int idReserva = Integer.parseInt(idReservaStr);
                
                // Verificar que la reserva existe
                Reserva reserva = conexion.buscarReservaPorId(idReserva);
                if (reserva == null) {
                    System.out.println("Error: No existe una reserva con ese ID.");
                    return;
                }
                
                relacionExistente.setFkReserva(idReserva);
            }

            if (conexion.actualizarReservaCliente(relacionExistente)) {
                System.out.println("Relación actualizada correctamente.");
            } else {
                System.out.println("Error al actualizar la relación.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Los campos numéricos deben ser números enteros.");
        }
    }

    /**
     * Solicita el ID de la relación a eliminar
     */
    private void eliminarRelacion() {
        System.out.println("\n--- ELIMINAR RELACIÓN CLIENTE-RESERVA ---");

        try {
            System.out.print("ID de la relación a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("¿Está seguro de eliminar esta relación? (S/N): ");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("S")) {
                if (conexion.eliminarReservaCliente(id)) {
                    System.out.println("Relación eliminada correctamente.");
                } else {
                    System.out.println("Error al eliminar la relación o la relación no existe.");
                }
            } else {
                System.out.println("Operación cancelada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
        }
    }

    /**
     * Solicita el ID de la relación a buscar y muestra sus datos
     */
    private void buscarRelacionPorId() {
        System.out.println("\n--- BUSCAR RELACIÓN POR ID ---");

        try {
            System.out.print("ID de la relación a buscar: ");
            int id = Integer.parseInt(scanner.nextLine());

            ReservaCliente relacion = conexion.buscarReservaClientePorId(id);

            if (relacion != null) {
                mostrarDetallesRelacion(relacion);
            } else {
                System.out.println("No existe una relación con ese ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
        }
    }

    /**
     * Lista todas las relaciones cliente-reserva
     */
    private void listarTodasLasRelaciones() {
        System.out.println("\n--- LISTA DE RELACIONES CLIENTE-RESERVA ---");

        List<ReservaCliente> relaciones = conexion.listarTodasLasReservasClientes();

        if (relaciones.isEmpty()) {
            System.out.println("No hay relaciones registradas.");
        } else {
            System.out.println("ID\tDNI Cliente\tID Reserva");
            System.out.println("----------------------------------------");

            for (ReservaCliente relacion : relaciones) {
                System.out.println(relacion.getId() + "\t" + 
                                  relacion.getFkDniCliente() + "\t\t" + 
                                  relacion.getFkReserva());
                
                // Mostrar detalles adicionales
                mostrarDetallesRelacionResumidos(relacion);
            }
        }
    }

    /**
     * Lista todas las relaciones para un cliente específico
     */
    private void listarRelacionesPorCliente() {
        System.out.println("\n--- LISTAR RELACIONES POR CLIENTE ---");

        try {
            System.out.print("DNI del cliente: ");
            int dniCliente = Integer.parseInt(scanner.nextLine());

            // Verificar que el cliente existe
            Cliente cliente = conexion.buscarClientePorDni(dniCliente);
            if (cliente == null) {
                System.out.println("Error: No existe un cliente con ese DNI.");
                return;
            }

            List<ReservaCliente> relaciones = conexion.buscarReservaClientePorCliente(dniCliente);

            if (relaciones.isEmpty()) {
                System.out.println("No hay relaciones registradas para este cliente.");
            } else {
                System.out.println("\nCliente: " + cliente.getNombre() + " (DNI: " + cliente.getDni() + ")");
                System.out.println("\nReservas asociadas:");
                System.out.println("ID Relación\tID Reserva\tFecha y hora\tAsientos\tMesas\tAcompañantes");
                System.out.println("--------------------------------------------------------------------------------");

                for (ReservaCliente relacion : relaciones) {
                    Reserva reserva = conexion.buscarReservaPorId(relacion.getFkReserva());
                    if (reserva != null) {
                        System.out.println(relacion.getId() + "\t\t" + 
                                          reserva.getId() + "\t\t" + 
                                          dateFormat.format(reserva.getFechaReserva()) + "\t" + 
                                          reserva.getNumAsientos() + "\t\t" + 
                                          reserva.getNumMesas() + "\t" + 
                                          reserva.getNumAcompanantes());
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El DNI debe ser un número entero.");
        }
    }

    /**
     * Lista todas las relaciones para una reserva específica
     */
    private void listarRelacionesPorReserva() {
        System.out.println("\n--- LISTAR RELACIONES POR RESERVA ---");

        try {
            System.out.print("ID de la reserva: ");
            int idReserva = Integer.parseInt(scanner.nextLine());

            // Verificar que la reserva existe
            Reserva reserva = conexion.buscarReservaPorId(idReserva);
            if (reserva == null) {
                System.out.println("Error: No existe una reserva con ese ID.");
                return;
            }

            List<ReservaCliente> relaciones = conexion.buscarReservaClientePorReserva(idReserva);

            if (relaciones.isEmpty()) {
                System.out.println("No hay relaciones registradas para esta reserva.");
            } else {
                System.out.println("\nReserva ID: " + reserva.getId());
                System.out.println("Fecha y hora: " + dateFormat.format(reserva.getFechaReserva()));
                System.out.println("Asientos: " + reserva.getNumAsientos() + ", Mesas: " + reserva.getNumMesas() + ", Acompañantes: " + reserva.getNumAcompanantes());
                
                System.out.println("\nClientes asociados:");
                System.out.println("ID Relación\tDNI Cliente\tNombre\t\tFecha de nacimiento");
                System.out.println("--------------------------------------------------------------------------------");

                for (ReservaCliente relacion : relaciones) {
                    Cliente cliente = conexion.buscarClientePorDni(relacion.getFkDniCliente());
                    if (cliente != null) {
                        System.out.println(relacion.getId() + "\t\t" + 
                                          cliente.getDni() + "\t\t" + 
                                          cliente.getNombre() + "\t\t" + 
                                          (cliente.getFechaNacimiento() != null ? new SimpleDateFormat("dd/MM/yyyy").format(cliente.getFechaNacimiento()) : "N/A"));
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
        }
    }

    /**
     * Muestra los detalles de una relación cliente-reserva
     * @param relacion Relación a mostrar
     */
    private void mostrarDetallesRelacion(ReservaCliente relacion) {
        System.out.println("\nDatos de la relación:");
        System.out.println("ID: " + relacion.getId());
        System.out.println("DNI del cliente: " + relacion.getFkDniCliente());
        System.out.println("ID de la reserva: " + relacion.getFkReserva());

        // Mostrar detalles del cliente
        Cliente cliente = conexion.buscarClientePorDni(relacion.getFkDniCliente());
        if (cliente != null) {
            System.out.println("\nDatos del cliente:");
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Fecha de nacimiento: " + (cliente.getFechaNacimiento() != null ? new SimpleDateFormat("dd/MM/yyyy").format(cliente.getFechaNacimiento()) : "N/A"));
        }

        // Mostrar detalles de la reserva
        Reserva reserva = conexion.buscarReservaPorId(relacion.getFkReserva());
        if (reserva != null) {
            System.out.println("\nDatos de la reserva:");
            System.out.println("Fecha y hora: " + dateFormat.format(reserva.getFechaReserva()));
            System.out.println("Número de asientos: " + reserva.getNumAsientos());
            System.out.println("Número de mesas: " + reserva.getNumMesas());
            System.out.println("Número de acompañantes: " + reserva.getNumAcompanantes());
        }
    }

    /**
     * Muestra un resumen de los detalles de una relación cliente-reserva
     * @param relacion Relación a mostrar
     */
    private void mostrarDetallesRelacionResumidos(ReservaCliente relacion) {
        // Mostrar detalles del cliente
        Cliente cliente = conexion.buscarClientePorDni(relacion.getFkDniCliente());
        if (cliente != null) {
            System.out.println("  Cliente: " + cliente.getNombre());
        }

        // Mostrar detalles de la reserva
        Reserva reserva = conexion.buscarReservaPorId(relacion.getFkReserva());
        if (reserva != null) {
            System.out.println("  Reserva: " + dateFormat.format(reserva.getFechaReserva()) + 
                              ", Asientos: " + reserva.getNumAsientos() + 
                              ", Mesas: " + reserva.getNumMesas());
        }
        
        System.out.println("----------------------------------------");
    }
}