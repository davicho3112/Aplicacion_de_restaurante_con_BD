package vista;

import conexion.Conexion;
import modelo.Orden;
import modelo.Cliente;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que proporciona una interfaz de consola para interactuar con la tabla Orden
 */
public class OrdenConsola {
    private Conexion conexion;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;

    /**
     * Constructor
     */
    public OrdenConsola() {
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
            System.out.println("\n===== GESTIÓN DE ÓRDENES =====");
            System.out.println("1. Crear nueva orden");
            System.out.println("2. Actualizar orden");
            System.out.println("3. Eliminar orden");
            System.out.println("4. Buscar orden por ID");
            System.out.println("5. Listar todas las órdenes");
            System.out.println("6. Listar órdenes por cliente");
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
                    crearOrden();
                    break;
                case 2:
                    actualizarOrden();
                    break;
                case 3:
                    eliminarOrden();
                    break;
                case 4:
                    buscarOrdenPorId();
                    break;
                case 5:
                    listarTodasLasOrdenes();
                    break;
                case 6:
                    listarOrdenesPorCliente();
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
     * Solicita los datos para crear una nueva orden
     */
    private void crearOrden() {
        System.out.println("\n--- CREAR NUEVA ORDEN ---");

        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("DNI del cliente: ");
            int dniCliente = Integer.parseInt(scanner.nextLine());

            // Verificar que el cliente existe
            Cliente cliente = conexion.buscarClientePorDni(dniCliente);
            if (cliente == null) {
                System.out.println("Error: No existe un cliente con ese DNI.");
                return;
            }

            System.out.print("Fecha y hora de la orden (dd/MM/yyyy HH:mm): ");
            String fechaStr = scanner.nextLine();
            Timestamp fechaOrden = convertirFecha(fechaStr);

            Orden orden = new Orden(id, fechaOrden, dniCliente);

            if (conexion.insertarOrden(orden)) {
                System.out.println("Orden creada correctamente.");
            } else {
                System.out.println("Error al crear la orden.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Los campos numéricos deben ser números enteros.");
        } catch (ParseException e) {
            System.out.println("Error: Formato de fecha y hora incorrecto. Use dd/MM/yyyy HH:mm.");
        }
    }

    /**
     * Solicita los datos para actualizar una orden existente
     */
    private void actualizarOrden() {
        System.out.println("\n--- ACTUALIZAR ORDEN ---");

        try {
            System.out.print("ID de la orden a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine());

            Orden ordenExistente = conexion.buscarOrdenPorId(id);

            if (ordenExistente == null) {
                System.out.println("No existe una orden con ese ID.");
                return;
            }

            System.out.print("Nueva fecha y hora de la orden (dd/MM/yyyy HH:mm) (deje en blanco para mantener la actual): ");
            String fechaStr = scanner.nextLine();
            if (!fechaStr.isEmpty()) {
                Timestamp fechaOrden = convertirFecha(fechaStr);
                ordenExistente.setFechaOrden(fechaOrden);
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
                
                ordenExistente.setFkDniCliente(dniCliente);
            }

            if (conexion.actualizarOrden(ordenExistente)) {
                System.out.println("Orden actualizada correctamente.");
            } else {
                System.out.println("Error al actualizar la orden.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Los campos numéricos deben ser números enteros.");
        } catch (ParseException e) {
            System.out.println("Error: Formato de fecha y hora incorrecto. Use dd/MM/yyyy HH:mm.");
        }
    }

    /**
     * Solicita el ID de la orden a eliminar
     */
    private void eliminarOrden() {
        System.out.println("\n--- ELIMINAR ORDEN ---");

        try {
            System.out.print("ID de la orden a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("¿Está seguro de eliminar esta orden? (S/N): ");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("S")) {
                if (conexion.eliminarOrden(id)) {
                    System.out.println("Orden eliminada correctamente.");
                } else {
                    System.out.println("Error al eliminar la orden o la orden no existe.");
                }
            } else {
                System.out.println("Operación cancelada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
        }
    }

    /**
     * Solicita el ID de la orden a buscar y muestra sus datos
     */
    private void buscarOrdenPorId() {
        System.out.println("\n--- BUSCAR ORDEN POR ID ---");

        try {
            System.out.print("ID de la orden a buscar: ");
            int id = Integer.parseInt(scanner.nextLine());

            Orden orden = conexion.buscarOrdenPorId(id);

            if (orden != null) {
                mostrarDetallesOrden(orden);
            } else {
                System.out.println("No existe una orden con ese ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
        }
    }

    /**
     * Lista todas las órdenes
     */
    private void listarTodasLasOrdenes() {
        System.out.println("\n--- LISTA DE ÓRDENES ---");

        List<Orden> ordenes = conexion.listarTodasLasOrdenes();

        if (ordenes.isEmpty()) {
            System.out.println("No hay órdenes registradas.");
        } else {
            System.out.println("ID\tFecha y hora\t\tDNI Cliente\tNombre Cliente");
            System.out.println("--------------------------------------------------------------");

            for (Orden orden : ordenes) {
                Cliente cliente = conexion.buscarClientePorDni(orden.getFkDniCliente());
                String nombreCliente = cliente != null ? cliente.getNombre() : "N/A";
                
                System.out.println(orden.getId() + "\t" + 
                                  dateFormat.format(orden.getFechaOrden()) + "\t" + 
                                  orden.getFkDniCliente() + "\t\t" + 
                                  nombreCliente);
            }
        }
    }

    /**
     * Lista todas las órdenes de un cliente específico
     */
    private void listarOrdenesPorCliente() {
        System.out.println("\n--- LISTAR ÓRDENES POR CLIENTE ---");

        try {
            System.out.print("DNI del cliente: ");
            int dniCliente = Integer.parseInt(scanner.nextLine());

            // Verificar que el cliente existe
            Cliente cliente = conexion.buscarClientePorDni(dniCliente);
            if (cliente == null) {
                System.out.println("Error: No existe un cliente con ese DNI.");
                return;
            }

            List<Orden> ordenes = conexion.buscarOrdenesPorCliente(dniCliente);

            if (ordenes.isEmpty()) {
                System.out.println("No hay órdenes registradas para este cliente.");
            } else {
                System.out.println("\nCliente: " + cliente.getNombre() + " (DNI: " + cliente.getDni() + ")");
                System.out.println("\nÓrdenes asociadas:");
                System.out.println("ID\tFecha y hora");
                System.out.println("-------------------------------");

                for (Orden orden : ordenes) {
                    System.out.println(orden.getId() + "\t" + 
                                      dateFormat.format(orden.getFechaOrden()));
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El DNI debe ser un número entero.");
        }
    }

    /**
     * Muestra los detalles de una orden
     * @param orden Orden a mostrar
     */
    private void mostrarDetallesOrden(Orden orden) {
        System.out.println("\nDatos de la orden:");
        System.out.println("ID: " + orden.getId());
        System.out.println("Fecha y hora: " + dateFormat.format(orden.getFechaOrden()));
        System.out.println("DNI del cliente: " + orden.getFkDniCliente());

        // Mostrar detalles del cliente
        Cliente cliente = conexion.buscarClientePorDni(orden.getFkDniCliente());
        if (cliente != null) {
            System.out.println("\nDatos del cliente:");
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Fecha de nacimiento: " + (cliente.getFechaNacimiento() != null ? new SimpleDateFormat("dd/MM/yyyy").format(cliente.getFechaNacimiento()) : "N/A"));
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