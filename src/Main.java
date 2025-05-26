import vista.ClienteConsola;
import vista.NumeroTelefonoConsola;
import vista.ReservaConsola;
import vista.ReservaClienteConsola;
import vista.OrdenConsola;
import vista.PagoConsola;
import vista.FacturaConsola;
import vista.PlatilloConsola;
import java.util.Scanner;

/**
 * Clase principal que inicia la aplicación
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Números de Teléfono");
            System.out.println("3. Gestión de Reservas");
            System.out.println("4. Gestión de Relaciones Cliente-Reserva");
            System.out.println("5. Gestión de Órdenes");
            System.out.println("6. Gestión de Pagos");
            System.out.println("7. Gestión de Facturas");
            System.out.println("8. Gestión de Platillos");
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
                    // Gestión de Clientes
                    ClienteConsola clienteConsola = new ClienteConsola();
                    clienteConsola.mostrarMenu();
                    break;
                case 2:
                    // Gestión de Números de Teléfono
                    NumeroTelefonoConsola numeroTelefonoConsola = new NumeroTelefonoConsola();
                    numeroTelefonoConsola.mostrarMenu();
                    break;
                case 3:
                    // Gestión de Reservas
                    ReservaConsola reservaConsola = new ReservaConsola();
                    reservaConsola.mostrarMenu();
                    break;
                case 4:
                    // Gestión de Relaciones Cliente-Reserva
                    ReservaClienteConsola reservaClienteConsola = new ReservaClienteConsola();
                    reservaClienteConsola.mostrarMenu();
                    break;
                case 5:
                    // Gestión de Órdenes
                    OrdenConsola ordenConsola = new OrdenConsola();
                    ordenConsola.mostrarMenu();
                    break;
                case 6:
                    // Gestión de Pagos
                    PagoConsola pagoConsola = new PagoConsola();
                    pagoConsola.mostrarMenu();
                    break;
                case 7:
                    // Gestión de Facturas
                    FacturaConsola facturaConsola = new FacturaConsola();
                    facturaConsola.mostrarMenu();
                    break;
                case 8:
                    // Gestión de Platillos
                    PlatilloConsola platilloConsola = new PlatilloConsola();
                    platilloConsola.mostrarMenu();
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}
