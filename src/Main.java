
import Administrador.AdministrarRestaurante;
import Cliente.FactoryMethod.ClienteFactory;
import Cliente.FactoryMethod.PersonaFactory;
import Cliente.Informacion.NumeroTelefono;
import Cliente.Persona;
import Restaurante.Menu;
import Restaurante.Orden;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {

    AdministrarRestaurante administrar = new AdministrarRestaurante();
    Scanner leerOpcion = new Scanner(System.in);
    Scanner tipoUsuario = new Scanner(System.in);

    int opcion_administrar = 0;
    int opcion_usuario = 0;

    do{

        System.out.println("Como deseas ingresar:"
        +"\n1. Ingresar como cliente"
        +"\n2. Ingresar como administrador"
        +"\n3. Salir");
        System.out.print("Seleccione un opcion: ");
        opcion_usuario = tipoUsuario.nextInt();
        System.out.println();

        switch(opcion_usuario){

            case 1:

                Menu menu = new Menu();
                Scanner leerDato = new Scanner(System.in);

                //1. Si ingresa el cliente
                System.out.print("Ingrese su nombre: ");
                String nombreCliente = leerOpcion.nextLine();
                System.out.print("Ingrese su fecha de nacimiento (dd/MM/yyyy): ");
                String fecha = leerOpcion.nextLine();
                Date fechaNacimiento = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
                System.out.print("Ingrese su DNI: ");
                int dni = leerOpcion.nextInt();
                leerOpcion.nextLine(); // Consumir el salto de línea
                System.out.print("Ingrese el prefijo  del telefono : ");
                int prefijo = leerOpcion.nextInt();
                System.out.print("Ingrese su numero de telefono: ");
                int numeroTelefono = leerOpcion.nextInt();
                System.out.print("Ingrese el tipo de uso para el telefono: ");
                String tipoUso = leerOpcion.nextLine();

                //Se registra el cliente al sistema
                NumeroTelefono contacto = new NumeroTelefono();
                PersonaFactory clienteFactory = new ClienteFactory(dni,fechaNacimiento, nombreCliente, contacto);
                Persona cliente = clienteFactory.crearPersona();
                cliente.mostrarDatos();  // Usuario tipo Cliente
                Orden orden = new Orden();

                //Interaccion del cliente con el sistema para solicitar un platillo o reservar una mesa
                System.out.println("BIENVENIDO CLIENTE AL RESTAURANTE ROYAL-RESTAURANT");

                    System.out.println("Desea realizar una reserva? (1. Sí / 2. No)");
                    System.out.print("Seleccione una opción: ");
                    int reserva = leerDato.nextInt();

                    if(reserva == 1) {

                        //Se implementa la logica para reservar los asiento o mesas que el cliente solicite
                        //Se implementa la logica para mostrar las fechas disponibles llamando al metodo mostrarFechasDisponibles
                        orden.Reservar();
                    }

                    //El cliente solicita un platillo
                    orden.solicitarPlatillo();

                    // Se le muetra la factura al cliente
                    orden.mostrarFactura();

                break;

            case 2:

                //Preguntarle primero por su nombre de usuario y contraseña

                System.out.print("NOMBRE DE USUARIO: ");
                String nombreAdministrador = leerOpcion.nextLine();
                System.out.print("CONTRASEÑA: ");
                String contraseñaAdministrador = leerOpcion.nextLine();

                if(validarAdministrador(nombreAdministrador,contraseñaAdministrador)) {

                    System.out.println("Ingrese que accion quieres realizar:"
                            + "\n1. Gestionar las reservas"
                            + "\n2. Gestionar los platillos"
                            + "\n3. Salir");
                    System.out.print("Seleccione la opcion: ");
                    opcion_administrar = leerOpcion.nextInt();
                    System.out.println();

                    do {
                        switch (opcion_administrar) {
                            case 1:
                                administrar.administrarReservas();
                                break;
                            case 2:
                                administrar.administrarPlatillos();
                                break;
                            case 3:
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Escoga una opcion valida");
                        }
                        break;

                    } while (opcion_administrar != 3);

                }else{
                    System.out.println("Usuario no encontrado");
                }

            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Opcion no valida");
        }

    }while(opcion_usuario != 3);

    }

    // Analizar bien esta logica
    public static boolean validarAdministrador(String nombreUsuario, String contraseña){

        String [] usuarios = {"David01","Juan02","Pedro03"};
        String [] contraseñas = {"admin01","admin02","admin03"};

        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i].equals(nombreUsuario) && contraseñas[i].equals(contraseña)) {
                return true;
            }
        }
        return false;
    }
}