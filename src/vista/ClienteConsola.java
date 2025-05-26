package vista;

import conexion.Conexion;
import modelo.Cliente;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que proporciona una interfaz de consola para interactuar con la tabla Cliente
 */
public class ClienteConsola {
    private Conexion conexion;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;

    /**
     * Constructor
     */
    public ClienteConsola() {
        conexion = new Conexion();
        scanner = new Scanner(System.in);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    /**
     * Muestra el menú principal y procesa la opción seleccionada
     */
    public void mostrarMenu() {
        int opcion = -1;
        do {
            System.out.println("\n===== GESTIÓN DE CLIENTES =====");
            System.out.println("1. Insertar cliente");
            System.out.println("2. Actualizar cliente");
            System.out.println("3. Eliminar cliente");
            System.out.println("4. Buscar cliente por DNI");
            System.out.println("5. Listar todos los clientes");
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
                    insertarCliente();
                    break;
                case 2:
                    actualizarCliente();
                    break;
                case 3:
                    eliminarCliente();
                    break;
                case 4:
                    buscarClientePorDni();
                    break;
                case 5:
                    listarTodosLosClientes();
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
     * Solicita los datos para insertar un nuevo cliente
     */
    private void insertarCliente() {
        System.out.println("\n--- INSERTAR CLIENTE ---");

        try {
            System.out.print("DNI: ");
            int dni = Integer.parseInt(scanner.nextLine());

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Fecha de nacimiento (dd/MM/yyyy): ");
            String fechaStr = scanner.nextLine();
            Date fechaNacimiento = convertirFecha(fechaStr);

            Cliente cliente = new Cliente(dni, nombre, fechaNacimiento);

            if (conexion.insertarCliente(cliente)) {
                System.out.println("Cliente insertado correctamente.");
            } else {
                System.out.println("Error al insertar el cliente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El DNI debe ser un número entero.");
        } catch (ParseException e) {
            System.out.println("Error: Formato de fecha incorrecto. Use dd/MM/yyyy.");
        }
    }

    /**
     * Solicita los datos para actualizar un cliente existente
     */
    private void actualizarCliente() {
        System.out.println("\n--- ACTUALIZAR CLIENTE ---");

        try {
            System.out.print("DNI del cliente a actualizar: ");
            int dni = Integer.parseInt(scanner.nextLine());

            Cliente clienteExistente = conexion.buscarClientePorDni(dni);

            if (clienteExistente == null) {
                System.out.println("No existe un cliente con ese DNI.");
                return;
            }

            System.out.print("Nuevo nombre (deje en blanco para mantener el actual): ");
            String nombre = scanner.nextLine();
            if (!nombre.isEmpty()) {
                clienteExistente.setNombre(nombre);
            }

            System.out.print("Nueva fecha de nacimiento (dd/MM/yyyy) (deje en blanco para mantener la actual): ");
            String fechaStr = scanner.nextLine();
            if (!fechaStr.isEmpty()) {
                Date fechaNacimiento = convertirFecha(fechaStr);
                clienteExistente.setFechaNacimiento(fechaNacimiento);
            }

            if (conexion.actualizarCliente(clienteExistente)) {
                System.out.println("Cliente actualizado correctamente.");
            } else {
                System.out.println("Error al actualizar el cliente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El DNI debe ser un número entero.");
        } catch (ParseException e) {
            System.out.println("Error: Formato de fecha incorrecto. Use dd/MM/yyyy.");
        }
    }

    /**
     * Solicita el DNI del cliente a eliminar
     */
    private void eliminarCliente() {
        System.out.println("\n--- ELIMINAR CLIENTE ---");

        try {
            System.out.print("DNI del cliente a eliminar: ");
            int dni = Integer.parseInt(scanner.nextLine());

            System.out.print("¿Está seguro de eliminar este cliente? (S/N): ");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("S")) {
                if (conexion.eliminarCliente(dni)) {
                    System.out.println("Cliente eliminado correctamente.");
                } else {
                    System.out.println("Error al eliminar el cliente o el cliente no existe.");
                }
            } else {
                System.out.println("Operación cancelada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El DNI debe ser un número entero.");
        }
    }

    /**
     * Solicita el DNI del cliente a buscar y muestra sus datos
     */
    private void buscarClientePorDni() {
        System.out.println("\n--- BUSCAR CLIENTE POR DNI ---");

        try {
            System.out.print("DNI del cliente a buscar: ");
            int dni = Integer.parseInt(scanner.nextLine());

            Cliente cliente = conexion.buscarClientePorDni(dni);

            if (cliente != null) {
                System.out.println("\nDatos del cliente:");
                System.out.println("DNI: " + cliente.getDni());
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("Fecha de nacimiento: " + dateFormat.format(cliente.getFechaNacimiento()));
            } else {
                System.out.println("No existe un cliente con ese DNI.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El DNI debe ser un número entero.");
        }
    }

    /**
     * Lista todos los clientes
     */
    private void listarTodosLosClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");

        List<Cliente> clientes = conexion.listarTodosLosClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            System.out.println("DNI\tNombre\tFecha de nacimiento");
            System.out.println("----------------------------------------");

            for (Cliente cliente : clientes) {
                System.out.println(cliente.getDni() + "\t" + 
                                  cliente.getNombre() + "\t" + 
                                  dateFormat.format(cliente.getFechaNacimiento()));
            }
        }
    }

    /**
     * Convierte una cadena de fecha en formato dd/MM/yyyy a un objeto Date de SQL
     * @param fechaStr Cadena de fecha en formato dd/MM/yyyy
     * @return Objeto Date de SQL
     * @throws ParseException Si el formato de la fecha es incorrecto
     */
    private Date convertirFecha(String fechaStr) throws ParseException {
        java.util.Date utilDate = dateFormat.parse(fechaStr);
        return new Date(utilDate.getTime());
    }
}
