package vista;

import conexion.Conexion;
import modelo.Factura;
import modelo.Pago;
import modelo.Reserva;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;

/**
 * Clase que proporciona una interfaz de consola para interactuar con la tabla Factura
 */
public class FacturaConsola {
    private Conexion conexion;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;

    /**
     * Constructor
     */
    public FacturaConsola() {
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
            System.out.println("\n===== GESTIÓN DE FACTURAS =====");
            System.out.println("1. Insertar factura");
            System.out.println("2. Actualizar factura");
            System.out.println("3. Eliminar factura");
            System.out.println("4. Buscar factura por ID");
            System.out.println("5. Listar todas las facturas");
            System.out.println("6. Buscar facturas por ID de pago");
            System.out.println("7. Buscar facturas por ID de reserva");
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
                    insertarFactura();
                    break;
                case 2:
                    actualizarFactura();
                    break;
                case 3:
                    eliminarFactura();
                    break;
                case 4:
                    buscarFacturaPorId();
                    break;
                case 5:
                    listarTodasLasFacturas();
                    break;
                case 6:
                    buscarFacturasPorIdPago();
                    break;
                case 7:
                    buscarFacturasPorIdReserva();
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
     * Solicita los datos para insertar una nueva factura
     */
    private void insertarFactura() {
        System.out.println("\n--- INSERTAR FACTURA ---");

        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("ID del pago: ");
            int idPago = Integer.parseInt(scanner.nextLine());

            // Verificar que el pago existe
            Pago pago = conexion.buscarPagoPorId(idPago);
            if (pago == null) {
                System.out.println("Error: No existe un pago con ese ID.");
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

            Factura factura = new Factura(id, idPago, idReserva);

            if (conexion.insertarFactura(factura)) {
                System.out.println("Factura insertada correctamente.");
            } else {
                System.out.println("Error al insertar la factura.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Los campos numéricos deben ser números enteros.");
        }
    }

    /**
     * Solicita los datos para actualizar una factura existente
     */
    private void actualizarFactura() {
        System.out.println("\n--- ACTUALIZAR FACTURA ---");

        try {
            System.out.print("ID de la factura a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine());

            Factura facturaExistente = conexion.buscarFacturaPorId(id);

            if (facturaExistente == null) {
                System.out.println("No existe una factura con ese ID.");
                return;
            }

            System.out.print("Nuevo ID del pago (deje en blanco para mantener el actual): ");
            String idPagoStr = scanner.nextLine();
            if (!idPagoStr.isEmpty()) {
                int idPago = Integer.parseInt(idPagoStr);
                
                // Verificar que el pago existe
                Pago pago = conexion.buscarPagoPorId(idPago);
                if (pago == null) {
                    System.out.println("Error: No existe un pago con ese ID.");
                    return;
                }
                
                facturaExistente.setFkIdPago(idPago);
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
                
                facturaExistente.setFkIdReserva(idReserva);
            }

            if (conexion.actualizarFactura(facturaExistente)) {
                System.out.println("Factura actualizada correctamente.");
            } else {
                System.out.println("Error al actualizar la factura.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Los campos numéricos deben ser números enteros.");
        }
    }

    /**
     * Solicita el ID de la factura a eliminar
     */
    private void eliminarFactura() {
        System.out.println("\n--- ELIMINAR FACTURA ---");

        try {
            System.out.print("ID de la factura a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("¿Está seguro de eliminar esta factura? (S/N): ");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("S")) {
                if (conexion.eliminarFactura(id)) {
                    System.out.println("Factura eliminada correctamente.");
                } else {
                    System.out.println("Error al eliminar la factura o la factura no existe.");
                }
            } else {
                System.out.println("Operación cancelada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
        }
    }

    /**
     * Solicita el ID de la factura a buscar y muestra sus datos
     */
    private void buscarFacturaPorId() {
        System.out.println("\n--- BUSCAR FACTURA POR ID ---");

        try {
            System.out.print("ID de la factura a buscar: ");
            int id = Integer.parseInt(scanner.nextLine());

            Factura factura = conexion.buscarFacturaPorId(id);

            if (factura != null) {
                mostrarDetallesFactura(factura);
            } else {
                System.out.println("No existe una factura con ese ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
        }
    }

    /**
     * Lista todas las facturas
     */
    private void listarTodasLasFacturas() {
        System.out.println("\n--- LISTA DE FACTURAS ---");

        List<Factura> facturas = conexion.listarTodasLasFacturas();

        if (facturas.isEmpty()) {
            System.out.println("No hay facturas registradas.");
        } else {
            System.out.println("ID\tID Pago\tID Reserva");
            System.out.println("----------------------------------------");

            for (Factura factura : facturas) {
                System.out.println(factura.getId() + "\t" + 
                                  factura.getFkIdPago() + "\t" + 
                                  factura.getFkIdReserva());
                
                // Mostrar detalles adicionales
                mostrarDetallesFacturaResumidos(factura);
            }
        }
    }

    /**
     * Busca facturas por ID de pago
     */
    private void buscarFacturasPorIdPago() {
        System.out.println("\n--- BUSCAR FACTURAS POR ID DE PAGO ---");

        try {
            System.out.print("ID del pago: ");
            int idPago = Integer.parseInt(scanner.nextLine());

            // Verificar que el pago existe
            Pago pago = conexion.buscarPagoPorId(idPago);
            if (pago == null) {
                System.out.println("Error: No existe un pago con ese ID.");
                return;
            }

            List<Factura> facturas = conexion.buscarFacturasPorIdPago(idPago);

            if (facturas.isEmpty()) {
                System.out.println("No hay facturas registradas para este pago.");
            } else {
                System.out.println("\nPago ID: " + pago.getId());
                System.out.println("Método de pago: " + pago.getMetodoPago());
                System.out.println("Total: " + pago.getTotal());
                
                System.out.println("\nFacturas asociadas:");
                System.out.println("ID\tID Reserva\tFecha y hora");
                System.out.println("----------------------------------------");

                for (Factura factura : facturas) {
                    Reserva reserva = conexion.buscarReservaPorId(factura.getFkIdReserva());
                    String fechaHora = reserva != null ? dateFormat.format(reserva.getFechaReserva()) : "N/A";
                    
                    System.out.println(factura.getId() + "\t" + 
                                      factura.getFkIdReserva() + "\t\t" + 
                                      fechaHora);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
        }
    }

    /**
     * Busca facturas por ID de reserva
     */
    private void buscarFacturasPorIdReserva() {
        System.out.println("\n--- BUSCAR FACTURAS POR ID DE RESERVA ---");

        try {
            System.out.print("ID de la reserva: ");
            int idReserva = Integer.parseInt(scanner.nextLine());

            // Verificar que la reserva existe
            Reserva reserva = conexion.buscarReservaPorId(idReserva);
            if (reserva == null) {
                System.out.println("Error: No existe una reserva con ese ID.");
                return;
            }

            List<Factura> facturas = conexion.buscarFacturasPorIdReserva(idReserva);

            if (facturas.isEmpty()) {
                System.out.println("No hay facturas registradas para esta reserva.");
            } else {
                System.out.println("\nReserva ID: " + reserva.getId());
                System.out.println("Fecha y hora: " + dateFormat.format(reserva.getFechaReserva()));
                System.out.println("Asientos: " + reserva.getNumAsientos() + ", Mesas: " + reserva.getNumMesas());
                
                System.out.println("\nFacturas asociadas:");
                System.out.println("ID\tID Pago\tMétodo de pago\tTotal");
                System.out.println("----------------------------------------");

                for (Factura factura : facturas) {
                    Pago pago = conexion.buscarPagoPorId(factura.getFkIdPago());
                    String metodoPago = pago != null ? pago.getMetodoPago() : "N/A";
                    String total = pago != null ? pago.getTotal().toString() : "N/A";
                    
                    System.out.println(factura.getId() + "\t" + 
                                      factura.getFkIdPago() + "\t" + 
                                      metodoPago + "\t\t" + 
                                      total);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
        }
    }

    /**
     * Muestra los detalles de una factura
     * @param factura Factura a mostrar
     */
    private void mostrarDetallesFactura(Factura factura) {
        System.out.println("\nDatos de la factura:");
        System.out.println("ID: " + factura.getId());
        System.out.println("ID del pago: " + factura.getFkIdPago());
        System.out.println("ID de la reserva: " + factura.getFkIdReserva());

        // Mostrar detalles del pago
        Pago pago = conexion.buscarPagoPorId(factura.getFkIdPago());
        if (pago != null) {
            System.out.println("\nDatos del pago:");
            System.out.println("Método de pago: " + pago.getMetodoPago());
            System.out.println("Total: " + pago.getTotal());
        }

        // Mostrar detalles de la reserva
        Reserva reserva = conexion.buscarReservaPorId(factura.getFkIdReserva());
        if (reserva != null) {
            System.out.println("\nDatos de la reserva:");
            System.out.println("Fecha y hora: " + dateFormat.format(reserva.getFechaReserva()));
            System.out.println("Número de asientos: " + reserva.getNumAsientos());
            System.out.println("Número de mesas: " + reserva.getNumMesas());
            System.out.println("Número de acompañantes: " + reserva.getNumAcompanantes());
        }
    }

    /**
     * Muestra un resumen de los detalles de una factura
     * @param factura Factura a mostrar
     */
    private void mostrarDetallesFacturaResumidos(Factura factura) {
        // Mostrar detalles del pago
        Pago pago = conexion.buscarPagoPorId(factura.getFkIdPago());
        if (pago != null) {
            System.out.println("  Pago: " + pago.getMetodoPago() + ", Total: " + pago.getTotal());
        }

        // Mostrar detalles de la reserva
        Reserva reserva = conexion.buscarReservaPorId(factura.getFkIdReserva());
        if (reserva != null) {
            System.out.println("  Reserva: " + dateFormat.format(reserva.getFechaReserva()) + 
                              ", Asientos: " + reserva.getNumAsientos() + 
                              ", Mesas: " + reserva.getNumMesas());
        }
        
        System.out.println("----------------------------------------");
    }
}