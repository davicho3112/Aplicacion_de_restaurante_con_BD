import Administrador.AdministrarRestaurante;
import Cliente.FactoryMethod.ClienteFactory;
import Cliente.FactoryMethod.PersonaFactory;
import Cliente.Informacion.NumeroTelefono;
import Cliente.Persona;
import Restaurante.Menu;
import Restaurante.Orden;
import Cliente.Cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        AdministrarRestaurante administrar = new AdministrarRestaurante();
        Scanner scanner = new Scanner(System.in);

        int opcion_usuario = 0;

        do {
            System.out.println("\n╔══════════════════════════════════════════════╗");
            System.out.println("║         BIENVENIDO AL RESTAURANTE           ║");
            System.out.println("║             ROYAL-RESTAURANT                ║");
            System.out.println("╚══════════════════════════════════════════════╝\n");

            System.out.println("¿Cómo deseas ingresar?");
            System.out.println("  1. Ingresar como cliente");
            System.out.println("  2. Ingresar como administrador");
            System.out.println("  3. Salir");
            System.out.print("Seleccione una opción: ");
            if (scanner.hasNextInt()) {
                opcion_usuario = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                opcion_usuario = 0;
            }
            System.out.println();

            switch (opcion_usuario) {
                case 1:
                    menuCliente(scanner, administrar);
                    break;
                case 2:
                    menuAdministrador(scanner, administrar);
                    break;
                case 3:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion_usuario != 3);
    }

    private static void menuCliente(Scanner scanner, AdministrarRestaurante administrar) throws ParseException {
        boolean salir = false;
        do {
            System.out.println("\n╔══════════════════════════════════════════════╗");
            System.out.println("║           ¡BIENVENIDO CLIENTE!              ║");
            System.out.println("║             ROYAL-RESTAURANT                ║");
            System.out.println("╚══════════════════════════════════════════════╝\n");

            System.out.println("1. Registrarse y hacer pedido");
            System.out.println("2. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                Menu menu = new Menu(administrar.gestionarPlatillos);

                System.out.println("\n--- REGISTRO DE CLIENTE ---");
                System.out.print("Ingrese su nombre: ");
                String nombreCliente = scanner.nextLine();

                System.out.print("Ingrese su fecha de nacimiento (dd/MM/yyyy): ");
                String fecha = scanner.nextLine();
                Date fechaNacimiento;
                try {
                    fechaNacimiento = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
                } catch (ParseException e) {
                    System.out.println("Fecha inválida. Intente de nuevo.");
                    continue;
                }

                System.out.print("Ingrese su DNI: ");
                int dni = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Ingrese el prefijo del teléfono: ");
                int prefijo = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Ingrese su número de teléfono: ");
                long numeroTelefono = scanner.nextLong();
                scanner.nextLine();

                String tipoUso = "";
                int tipoOpcion_Uso = 0;

                do {
                    System.out.println("Seleccione el tipo de uso del teléfono:");
                    System.out.println("1. Personal");
                    System.out.println("2. Trabajo");
                    System.out.print("Opción: ");
                    if (scanner.hasNextInt()) {
                        tipoOpcion_Uso = scanner.nextInt();
                        scanner.nextLine();
                    } else {
                        scanner.nextLine();
                        tipoOpcion_Uso = 0;
                    }

                    switch (tipoOpcion_Uso) {
                        case 1:
                            tipoUso = "Personal";
                            break;
                        case 2:
                            tipoUso = "Trabajo";
                            break;
                        default:
                            System.out.println("Opción inválida. Intente de nuevo.");
                    }
                } while (tipoUso.isEmpty());

                NumeroTelefono contacto = new NumeroTelefono();
                contacto.setPrefijo(prefijo);
                contacto.setNumero(numeroTelefono);
                contacto.setTipo_uso(tipoUso);
                PersonaFactory clienteFactory = new ClienteFactory(dni, fechaNacimiento, nombreCliente, contacto);
                Persona cliente = clienteFactory.crearPersona();
                cliente.mostrarDatos();

                Orden orden = new Orden(administrar.getGestionarReservas(), menu);
                orden.setCliente((Cliente) cliente);

                System.out.println("\n╔══════════════════════════════════════════════╗");
                System.out.println("║           ¡BIENVENIDO CLIENTE!              ║");
                System.out.println("║             ROYAL-RESTAURANT                ║");
                System.out.println("╚══════════════════════════════════════════════╝\n");

                System.out.println("¿Desea realizar una reserva?");
                System.out.println("  1. Sí");
                System.out.println("  2. No");
                System.out.print("Seleccione una opción: ");
                int reserva = scanner.nextInt();
                scanner.nextLine();

                if (reserva == 1) {
                    orden.Reservar();
                }

                orden.solicitarPlatillo();
            } else if (opcion == 2) {
                salir = true;
            } else {
                System.out.println("Opción no válida");
            }
        } while (!salir);
    }

    private static void menuAdministrador(Scanner scanner, AdministrarRestaurante administrar) throws ParseException {
        System.out.print("NOMBRE DE USUARIO: ");
        String nombreAdministrador = scanner.nextLine();
        System.out.print("CONTRASEÑA: ");
        String contrasenaAdministrador = scanner.nextLine();

        if (validarAdministrador(nombreAdministrador, contrasenaAdministrador)) {
            int opcion_administrar = 0;
            do {
                System.out.println("Ingrese que acción quieres realizar:"
                        + "\n1. Gestionar las reservas"
                        + "\n2. Gestionar los platillos"
                        + "\n3. Volver al menú principal");
                System.out.print("Seleccione la opción: ");
                if (scanner.hasNextInt()) {
                    opcion_administrar = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    scanner.nextLine();
                    opcion_administrar = 0;
                }
                System.out.println();

                switch (opcion_administrar) {
                    case 1:
                        administrar.administrarReservas();
                        break;
                    case 2:
                        administrar.administrarPlatillos();
                        break;
                    case 3:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Escoja una opción válida");
                }
            } while (opcion_administrar != 3);
        } else {
            System.out.println("Usuario no encontrado");
        }
    }

    public static boolean validarAdministrador(String nombreUsuario, String contrasena) {
        String[] usuarios = {"David01", "Jason02", "Emerson03"};
        String[] contrasenas = {"admin01", "admin02", "admin03"};

        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i].equals(nombreUsuario) && contrasenas[i].equals(contrasena)) {
                return true;
            }
        }
        return false;
    }
}