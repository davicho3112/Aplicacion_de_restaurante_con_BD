package vista;

import conexion.Conexion;
import modelo.Pago;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que proporciona una interfaz de consola para interactuar con la tabla Pago
 */
public class PagoConsola {
    private Conexion conexion;
    private Scanner scanner;

    /**
     * Constructor
     */
    public PagoConsola() {
        conexion = new Conexion();
        scanner = new Scanner(System.in);
    }

    /**
     * Muestra el menú principal y procesa la opción seleccionada
     */
    public void mostrarMenu() {
        int opcion = -1;
        do {
            System.out.println("\n===== GESTIÓN DE PAGOS =====");
            System.out.println("1. Insertar pago");
            System.out.println("2. Actualizar pago");
            System.out.println("3. Eliminar pago");
            System.out.println("4. Buscar pago por ID");
            System.out.println("5. Listar todos los pagos");
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
                    insertarPago();
                    break;
                case 2:
                    actualizarPago();
                    break;
                case 3:
                    eliminarPago();
                    break;
                case 4:
                    buscarPagoPorId();
                    break;
                case 5:
                    listarTodosLosPagos();
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
     * Solicita los datos para insertar un nuevo pago
     */
    private void insertarPago() {
        System.out.println("\n--- INSERTAR PAGO ---");

        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Método de pago: ");
            String metodoPago = scanner.nextLine();

            System.out.print("Total: ");
            BigDecimal total = new BigDecimal(scanner.nextLine());

            Pago pago = new Pago(id, metodoPago, total);

            if (conexion.insertarPago(pago)) {
                System.out.println("Pago insertado correctamente.");
            } else {
                System.out.println("Error al insertar el pago.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero y el total debe ser un número decimal.");
        }
    }

    /**
     * Solicita los datos para actualizar un pago existente
     */
    private void actualizarPago() {
        System.out.println("\n--- ACTUALIZAR PAGO ---");

        try {
            System.out.print("ID del pago a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine());

            Pago pagoExistente = conexion.buscarPagoPorId(id);

            if (pagoExistente == null) {
                System.out.println("No existe un pago con ese ID.");
                return;
            }

            System.out.print("Nuevo método de pago (deje en blanco para mantener el actual): ");
            String metodoPago = scanner.nextLine();
            if (!metodoPago.isEmpty()) {
                pagoExistente.setMetodoPago(metodoPago);
            }

            System.out.print("Nuevo total (deje en blanco para mantener el actual): ");
            String totalStr = scanner.nextLine();
            if (!totalStr.isEmpty()) {
                pagoExistente.setTotal(new BigDecimal(totalStr));
            }

            if (conexion.actualizarPago(pagoExistente)) {
                System.out.println("Pago actualizado correctamente.");
            } else {
                System.out.println("Error al actualizar el pago.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero y el total debe ser un número decimal.");
        }
    }

    /**
     * Solicita el ID del pago a eliminar
     */
    private void eliminarPago() {
        System.out.println("\n--- ELIMINAR PAGO ---");

        try {
            System.out.print("ID del pago a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("¿Está seguro de eliminar este pago? (S/N): ");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("S")) {
                if (conexion.eliminarPago(id)) {
                    System.out.println("Pago eliminado correctamente.");
                } else {
                    System.out.println("Error al eliminar el pago o el pago no existe.");
                }
            } else {
                System.out.println("Operación cancelada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
        }
    }

    /**
     * Solicita el ID del pago a buscar y muestra sus datos
     */
    private void buscarPagoPorId() {
        System.out.println("\n--- BUSCAR PAGO POR ID ---");

        try {
            System.out.print("ID del pago a buscar: ");
            int id = Integer.parseInt(scanner.nextLine());

            Pago pago = conexion.buscarPagoPorId(id);

            if (pago != null) {
                System.out.println("\nDatos del pago:");
                System.out.println("ID: " + pago.getId());
                System.out.println("Método de pago: " + pago.getMetodoPago());
                System.out.println("Total: " + pago.getTotal());
            } else {
                System.out.println("No existe un pago con ese ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
        }
    }

    /**
     * Lista todos los pagos
     */
    private void listarTodosLosPagos() {
        System.out.println("\n--- LISTA DE PAGOS ---");

        List<Pago> pagos = conexion.listarTodosLosPagos();

        if (pagos.isEmpty()) {
            System.out.println("No hay pagos registrados.");
        } else {
            System.out.println("ID\tMétodo de pago\tTotal");
            System.out.println("----------------------------------------");

            for (Pago pago : pagos) {
                System.out.println(pago.getId() + "\t" + 
                                  pago.getMetodoPago() + "\t\t" + 
                                  pago.getTotal());
            }
        }
    }
}