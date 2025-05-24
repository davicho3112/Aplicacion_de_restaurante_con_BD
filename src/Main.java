
import Administrador.AdministrarRestaurante;
import Cliente.FactoryMethod.ClienteFactory;
import Cliente.FactoryMethod.PersonaFactory;
import Cliente.Persona;

import java.text.ParseException;
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
                //1. Si ingresa el cliente
                // Para la creacion de clientes usando el patron Factory Method
                PersonaFactory clienteFactory = new ClienteFactory();
                Persona cliente = clienteFactory.crearPersona();
                cliente.mostrarDatos();  // Usuario tipo Cliente

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