package vista;

import conexion.Conexion;
import modelo.Platillo;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que proporciona una interfaz de consola para interactuar con la tabla Platillo
 */
public class PlatilloConsola {
    private Conexion conexion;
    private Scanner scanner;

    /**
     * Constructor
     */
    public PlatilloConsola() {
        conexion = new Conexion();
        scanner = new Scanner(System.in);
    }

    /**
     * Muestra el menú principal y procesa la opción seleccionada
     */
    public void mostrarMenu() {
        int opcion = -1;
        do {
            System.out.println("\n===== GESTIÓN DE PLATILLOS =====");
            System.out.println("1. Insertar platillo");
            System.out.println("2. Actualizar platillo");
            System.out.println("3. Eliminar platillo");
            System.out.println("4. Buscar platillo por ID");
            System.out.println("5. Listar todos los platillos");
            System.out.println("6. Buscar platillos por nombre");
            System.out.println("7. Buscar platillos por rango de precio");
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
                    insertarPlatillo();
                    break;
                case 2:
                    actualizarPlatillo();
                    break;
                case 3:
                    eliminarPlatillo();
                    break;
                case 4:
                    buscarPlatilloPorId();
                    break;
                case 5:
                    listarTodosLosPlatillos();
                    break;
                case 6:
                    buscarPlatillosPorNombre();
                    break;
                case 7:
                    buscarPlatillosPorRangoPrecio();
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
     * Solicita los datos para insertar un nuevo platillo
     */
    private void insertarPlatillo() {
        System.out.println("\n--- INSERTAR PLATILLO ---");

        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Precio: ");
            BigDecimal precio = new BigDecimal(scanner.nextLine());

            System.out.print("Acompañantes: ");
            String acompanantes = scanner.nextLine();

            Platillo platillo = new Platillo(id, nombre, precio, acompanantes);

            if (conexion.insertarPlatillo(platillo)) {
                System.out.println("Platillo insertado correctamente.");
            } else {
                System.out.println("Error al insertar el platillo.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero y el precio debe ser un número decimal.");
        }
    }

    /**
     * Solicita los datos para actualizar un platillo existente
     */
    private void actualizarPlatillo() {
        System.out.println("\n--- ACTUALIZAR PLATILLO ---");

        try {
            System.out.print("ID del platillo a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine());

            Platillo platilloExistente = conexion.buscarPlatilloPorId(id);

            if (platilloExistente == null) {
                System.out.println("No existe un platillo con ese ID.");
                return;
            }

            System.out.print("Nuevo nombre (deje en blanco para mantener el actual): ");
            String nombre = scanner.nextLine();
            if (!nombre.isEmpty()) {
                platilloExistente.setNombre(nombre);
            }

            System.out.print("Nuevo precio (deje en blanco para mantener el actual): ");
            String precioStr = scanner.nextLine();
            if (!precioStr.isEmpty()) {
                platilloExistente.setPrecio(new BigDecimal(precioStr));
            }

            System.out.print("Nuevos acompañantes (deje en blanco para mantener los actuales): ");
            String acompanantes = scanner.nextLine();
            if (!acompanantes.isEmpty()) {
                platilloExistente.setAcompanantes(acompanantes);
            }

            if (conexion.actualizarPlatillo(platilloExistente)) {
                System.out.println("Platillo actualizado correctamente.");
            } else {
                System.out.println("Error al actualizar el platillo.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero y el precio debe ser un número decimal.");
        }
    }

    /**
     * Solicita el ID del platillo a eliminar
     */
    private void eliminarPlatillo() {
        System.out.println("\n--- ELIMINAR PLATILLO ---");

        try {
            System.out.print("ID del platillo a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("¿Está seguro de eliminar este platillo? (S/N): ");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("S")) {
                if (conexion.eliminarPlatillo(id)) {
                    System.out.println("Platillo eliminado correctamente.");
                } else {
                    System.out.println("Error al eliminar el platillo o el platillo no existe.");
                }
            } else {
                System.out.println("Operación cancelada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
        }
    }

    /**
     * Solicita el ID del platillo a buscar y muestra sus datos
     */
    private void buscarPlatilloPorId() {
        System.out.println("\n--- BUSCAR PLATILLO POR ID ---");

        try {
            System.out.print("ID del platillo a buscar: ");
            int id = Integer.parseInt(scanner.nextLine());

            Platillo platillo = conexion.buscarPlatilloPorId(id);

            if (platillo != null) {
                System.out.println("\nDatos del platillo:");
                System.out.println("ID: " + platillo.getId());
                System.out.println("Nombre: " + platillo.getNombre());
                System.out.println("Precio: " + platillo.getPrecio());
                System.out.println("Acompañantes: " + platillo.getAcompanantes());
            } else {
                System.out.println("No existe un platillo con ese ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
        }
    }

    /**
     * Lista todos los platillos
     */
    private void listarTodosLosPlatillos() {
        System.out.println("\n--- LISTA DE PLATILLOS ---");

        List<Platillo> platillos = conexion.listarTodosLosPlatillos();

        if (platillos.isEmpty()) {
            System.out.println("No hay platillos registrados.");
        } else {
            System.out.println("ID\tNombre\t\tPrecio\tAcompañantes");
            System.out.println("----------------------------------------");

            for (Platillo platillo : platillos) {
                System.out.println(platillo.getId() + "\t" + 
                                  platillo.getNombre() + "\t\t" + 
                                  platillo.getPrecio() + "\t" + 
                                  platillo.getAcompanantes());
            }
        }
    }

    /**
     * Busca platillos por nombre
     */
    private void buscarPlatillosPorNombre() {
        System.out.println("\n--- BUSCAR PLATILLOS POR NOMBRE ---");

        System.out.print("Nombre o parte del nombre: ");
        String nombre = scanner.nextLine();

        List<Platillo> platillos = conexion.buscarPlatillosPorNombre(nombre);

        if (platillos.isEmpty()) {
            System.out.println("No se encontraron platillos con ese nombre.");
        } else {
            System.out.println("\nPlatillos encontrados:");
            System.out.println("ID\tNombre\t\tPrecio\tAcompañantes");
            System.out.println("----------------------------------------");

            for (Platillo platillo : platillos) {
                System.out.println(platillo.getId() + "\t" + 
                                  platillo.getNombre() + "\t\t" + 
                                  platillo.getPrecio() + "\t" + 
                                  platillo.getAcompanantes());
            }
        }
    }

    /**
     * Busca platillos por rango de precio
     */
    private void buscarPlatillosPorRangoPrecio() {
        System.out.println("\n--- BUSCAR PLATILLOS POR RANGO DE PRECIO ---");

        try {
            System.out.print("Precio mínimo: ");
            BigDecimal precioMinimo = new BigDecimal(scanner.nextLine());

            System.out.print("Precio máximo: ");
            BigDecimal precioMaximo = new BigDecimal(scanner.nextLine());

            List<Platillo> platillos = conexion.buscarPlatillosPorRangoPrecio(precioMinimo, precioMaximo);

            if (platillos.isEmpty()) {
                System.out.println("No se encontraron platillos en ese rango de precio.");
            } else {
                System.out.println("\nPlatillos encontrados:");
                System.out.println("ID\tNombre\t\tPrecio\tAcompañantes");
                System.out.println("----------------------------------------");

                for (Platillo platillo : platillos) {
                    System.out.println(platillo.getId() + "\t" + 
                                      platillo.getNombre() + "\t\t" + 
                                      platillo.getPrecio() + "\t" + 
                                      platillo.getAcompanantes());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Los precios deben ser números decimales.");
        }
    }
}