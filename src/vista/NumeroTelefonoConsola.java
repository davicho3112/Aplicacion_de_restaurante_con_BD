package vista;

import conexion.Conexion;
import modelo.NumeroTelefono;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que proporciona una interfaz de consola para interactuar con la tabla Numero_Telefono
 */
public class NumeroTelefonoConsola {
    private Conexion conexion;
    private Scanner scanner;

    /**
     * Constructor
     */
    public NumeroTelefonoConsola() {
        conexion = new Conexion();
        scanner = new Scanner(System.in);
    }

    /**
     * Muestra el menú principal y procesa la opción seleccionada
     */
    public void mostrarMenu() {
        int opcion = -1;
        do {
            System.out.println("\n===== GESTIÓN DE NÚMEROS DE TELÉFONO =====");
            System.out.println("1. Insertar número de teléfono");
            System.out.println("2. Actualizar número de teléfono");
            System.out.println("3. Eliminar número de teléfono");
            System.out.println("4. Buscar número de teléfono por ID");
            System.out.println("5. Listar todos los números de teléfono");
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
                    insertarNumeroTelefono();
                    break;
                case 2:
                    actualizarNumeroTelefono();
                    break;
                case 3:
                    eliminarNumeroTelefono();
                    break;
                case 4:
                    buscarNumeroTelefonoPorId();
                    break;
                case 5:
                    listarTodosLosNumerosTelefono();
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
     * Solicita los datos para insertar un nuevo número de teléfono
     */
    private void insertarNumeroTelefono() {
        System.out.println("\n--- INSERTAR NÚMERO DE TELÉFONO ---");

        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Número: ");
            long numero = Long.parseLong(scanner.nextLine());

            System.out.print("Prefijo: ");
            String prefijo = scanner.nextLine();

            System.out.print("Tipo de uso: ");
            String tipoUso = scanner.nextLine();

            NumeroTelefono numeroTelefono = new NumeroTelefono(id, numero, prefijo, tipoUso);

            if (conexion.insertarNumeroTelefono(numeroTelefono)) {
                System.out.println("Número de teléfono insertado correctamente.");
            } else {
                System.out.println("Error al insertar el número de teléfono.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID y el número deben ser valores numéricos.");
        }
    }

    /**
     * Solicita los datos para actualizar un número de teléfono existente
     */
    private void actualizarNumeroTelefono() {
        System.out.println("\n--- ACTUALIZAR NÚMERO DE TELÉFONO ---");

        try {
            System.out.print("ID del número de teléfono a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine());

            NumeroTelefono numeroTelefonoExistente = conexion.buscarNumeroTelefonoPorId(id);

            if (numeroTelefonoExistente == null) {
                System.out.println("No existe un número de teléfono con ese ID.");
                return;
            }

            System.out.print("Nuevo número (deje en blanco para mantener el actual): ");
            String numeroStr = scanner.nextLine();
            if (!numeroStr.isEmpty()) {
                numeroTelefonoExistente.setNumero(Long.parseLong(numeroStr));
            }

            System.out.print("Nuevo prefijo (deje en blanco para mantener el actual): ");
            String prefijo = scanner.nextLine();
            if (!prefijo.isEmpty()) {
                numeroTelefonoExistente.setPrefijo(prefijo);
            }

            System.out.print("Nuevo tipo de uso (deje en blanco para mantener el actual): ");
            String tipoUso = scanner.nextLine();
            if (!tipoUso.isEmpty()) {
                numeroTelefonoExistente.setTipoUso(tipoUso);
            }

            if (conexion.actualizarNumeroTelefono(numeroTelefonoExistente)) {
                System.out.println("Número de teléfono actualizado correctamente.");
            } else {
                System.out.println("Error al actualizar el número de teléfono.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID y el número deben ser valores numéricos.");
        }
    }

    /**
     * Solicita el ID del número de teléfono a eliminar
     */
    private void eliminarNumeroTelefono() {
        System.out.println("\n--- ELIMINAR NÚMERO DE TELÉFONO ---");

        try {
            System.out.print("ID del número de teléfono a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("¿Está seguro de eliminar este número de teléfono? (S/N): ");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("S")) {
                if (conexion.eliminarNumeroTelefono(id)) {
                    System.out.println("Número de teléfono eliminado correctamente.");
                } else {
                    System.out.println("Error al eliminar el número de teléfono o el número de teléfono no existe.");
                }
            } else {
                System.out.println("Operación cancelada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
        }
    }

    /**
     * Solicita el ID del número de teléfono a buscar y muestra sus datos
     */
    private void buscarNumeroTelefonoPorId() {
        System.out.println("\n--- BUSCAR NÚMERO DE TELÉFONO POR ID ---");

        try {
            System.out.print("ID del número de teléfono a buscar: ");
            int id = Integer.parseInt(scanner.nextLine());

            NumeroTelefono numeroTelefono = conexion.buscarNumeroTelefonoPorId(id);

            if (numeroTelefono != null) {
                System.out.println("\nDatos del número de teléfono:");
                System.out.println("ID: " + numeroTelefono.getId());
                System.out.println("Número: " + numeroTelefono.getNumero());
                System.out.println("Prefijo: " + numeroTelefono.getPrefijo());
                System.out.println("Tipo de uso: " + numeroTelefono.getTipoUso());
            } else {
                System.out.println("No existe un número de teléfono con ese ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
        }
    }

    /**
     * Lista todos los números de teléfono
     */
    private void listarTodosLosNumerosTelefono() {
        System.out.println("\n--- LISTA DE NÚMEROS DE TELÉFONO ---");

        List<NumeroTelefono> numerosTelefono = conexion.listarTodosLosNumerosTelefono();

        if (numerosTelefono.isEmpty()) {
            System.out.println("No hay números de teléfono registrados.");
        } else {
            System.out.println("ID\tNúmero\tPrefijo\tTipo de uso");
            System.out.println("----------------------------------------");

            for (NumeroTelefono numeroTelefono : numerosTelefono) {
                System.out.println(numeroTelefono.getId() + "\t" + 
                                  numeroTelefono.getNumero() + "\t" + 
                                  numeroTelefono.getPrefijo() + "\t" + 
                                  numeroTelefono.getTipoUso());
            }
        }
    }
}