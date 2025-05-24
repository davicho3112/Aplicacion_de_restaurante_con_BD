package Administrador;

import Administrador.Command.*;
import Administrador.Gestiones.GestionarPlatillo;
import Administrador.Gestiones.GestionarReserva;
import Restaurante.Categoria;
import Restaurante.Platillo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class AdministrarRestaurante {

    private GestionarReserva gestionarReservas;
    public GestionarPlatillo gestionarPlatillos;

    public AdministrarRestaurante() {
        this.gestionarReservas = new GestionarReserva();
        this.gestionarPlatillos = new GestionarPlatillo();
    }

    public void administrarReservas() throws ParseException {

        Scanner leerOpcionReserva = new Scanner(System.in);

        System.out.println("Selecciona una opcion: "
                + "\n1. Asignar mesas"
                + "\n2. Asignar asientos"
                + "\n3. Asignar fechas disponibles"
                + "\n4. Salir");
        System.out.print("Selecciona una opcion: ");
        int opcion_reserva = leerOpcionReserva.nextInt();

        do {
            switch (opcion_reserva) {
                case 1:
                    System.out.print("Cuantas mesas desea asignar: ");
                    int numero_mesas = leerOpcionReserva.nextInt();
                    gestionarReservas.agregarMesa(numero_mesas);
                    break;
                case 2:
                    System.out.print("Cuantos asientos desea asignar: ");
                    int numero_asientos = leerOpcionReserva.nextInt();
                    gestionarReservas.agregarAsiento(numero_asientos);
                    break;
                case 3:
                    System.out.print("Ingrese la fecha disponible: ");
                    String fecha_disponible = leerOpcionReserva.nextLine();
                    // Convertir la fecha a un objeto Date
                    Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fecha_disponible);
                    gestionarReservas.agregarFechasDisponible(fecha);
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion no valida");
            }

        }while(opcion_reserva != 4);
    }

    // Lógica para gestionar platillos
    //El administrador pueda llamar los metodos agregar,eliminar y modificar platillos
    //Manejo de los platillos usando el patron Command
    public void administrarPlatillos() {

        int opcion_platillo = 0;
        int agregar_platillo = 0;
        int modificar_platillo = 0;
        int eliminar_platillo = 0;
        int mostrar_platillo = 0;
        int id_platillo = 0;
        int id_categoria = 0;
        Scanner leerOpcionPlatillo = new Scanner(System.in);
        Invocador invocador = new Invocador();

        do {
            System.out.println("Que accion quieres realizar: "
                    + "\n1. Agregar platillo"
                    + "\n2. Modificar platillo"
                    + "\n3. Eliminar platillo"
                    + "\n4. Mostrar platillos"
                    + "\n5.Salir");
            System.out.print("Selecciona una opcion: ");
            opcion_platillo = leerOpcionPlatillo.nextInt();
            System.out.println();

            switch (opcion_platillo) {

                //Si el administrador desea agregar un platillo
                case 1:
                    do {
                        System.out.println("Ingresa las propiedades del platillo");
                        System.out.print("Nombre del platillo: ");
                        String nombre_platillo = leerOpcionPlatillo.nextLine();
                        System.out.println();
                        System.out.print("Acompañantes del platillo: ");
                        String acompañantes_platillo = leerOpcionPlatillo.nextLine();
                        System.out.println();
                        System.out.print("Nombre de la categoria del platillo: ");
                        String nombre_categoria = leerOpcionPlatillo.nextLine();
                        System.out.println();
                        System.out.print("Descripcion de la categoria: ");
                        String descripcion_categoria = leerOpcionPlatillo.nextLine();
                        System.out.println();
                        System.out.print("Precio del platillo: ");
                        double precio_platillo = leerOpcionPlatillo.nextDouble();
                        leerOpcionPlatillo.nextLine();
                        id_platillo++;
                        id_categoria++;

                        Categoria categoriaPlatillo = new Categoria(nombre_categoria, descripcion_categoria, id_categoria);
                        Platillo p1 = new Platillo(id_platillo, precio_platillo, nombre_platillo, acompañantes_platillo, categoriaPlatillo); // Cuando se define las propiedades del platillo
                        Command agregar = new AgregarPlatilloCommand(gestionarPlatillos, p1); //Si se desea agregar otro platillo
                        invocador.ejecutarAccion(agregar);

                        System.out.println("Registro exitoso");
                        System.out.println();

                        System.out.println("Desea agregar otro platillo "
                                + "\n1. SI"
                                + "\n2. NO");
                        System.out.print("Seleccionar una opcion: ");
                        agregar_platillo = leerOpcionPlatillo.nextInt();

                    } while (agregar_platillo != 2);
                    break;

                // Si el administrador desea modificar un platillo
                case 2:

                    int id_modificar = 0;
                    int opcion_modificar = 0;

                    do {
                        System.out.println("Estos son los platillos que ofrece tu restaurante");
                        gestionarPlatillos.mostrarPlatillos();  // Mostrar los platillos

                        System.out.print("Digite el id del platillo que desea modificar: ");
                        id_modificar = leerOpcionPlatillo.nextInt();
                        System.out.println();
                        leerOpcionPlatillo.nextLine();

                        if (id_modificar == gestionarPlatillos.platillos.get(id_modificar - 1).getCodigo_platillo()) {

                            System.out.println("Que datos deseas modificar del platillo?");
                            System.out.println("1. Nombre del platillo");
                            System.out.println("2. Nombre de la categoria del platillo");
                            System.out.println("3. Acompañante del platillo");
                            System.out.println("4. Precio del platillo");
                            System.out.println("5. Descripcion de la categoria");
                            try {
                                System.out.print("Digite una opcion: ");
                                opcion_modificar = leerOpcionPlatillo.nextInt();
                                System.out.println();
                                leerOpcionPlatillo.nextLine();
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                                leerOpcionPlatillo.nextLine();
                                System.out.print("Por favor, digite una informacion valida: ");
                                while (!leerOpcionPlatillo.hasNextInt()) {
                                    System.out.println("Eso no es un número válido.");
                                    System.out.print("Digita la opcion correspondiente: ");
                                    leerOpcionPlatillo.next();
                                }
                                opcion_modificar = leerOpcionPlatillo.nextInt();
                                System.out.println();
                            }
                            String nombre_platillo = "";
                            String categoria_platillo = "";
                            String acompañantes_platillo = "";
                            String descripcion_categoria = "";
                            double precio_platillo = 0.0;

                            switch (opcion_modificar) {
                                case 1:
                                    System.out.print("Digite el nuevo nombre del platillo: ");
                                    nombre_platillo = leerOpcionPlatillo.nextLine();
                                    System.out.println();
                                    break;
                                case 2:
                                    System.out.print("Digite la nueva categoria del platillo: ");
                                    categoria_platillo = leerOpcionPlatillo.nextLine();
                                    System.out.println();
                                    leerOpcionPlatillo.nextLine();
                                    break;
                                case 3:
                                    System.out.print("Digite el nuevo acompañante del platillo: ");
                                    acompañantes_platillo = leerOpcionPlatillo.nextLine();
                                    System.out.println();
                                    break;
                                case 4:
                                    System.out.print("Digite el nuevo precio del platillo: ");
                                    try {
                                        precio_platillo = leerOpcionPlatillo.nextDouble();
                                    } catch (Exception e) {
                                        System.out.println("Error: Debe ingresar un valor numerico");
                                        leerOpcionPlatillo.nextLine(); // Limpiar el buffer del scanner
                                        System.out.print("Por favor, digite una opcion valida: ");
                                        while (!leerOpcionPlatillo.hasNextInt()) { // Mientras no se ingrese un número entero
                                            System.out.println("Eso no es un número válido.");
                                            System.out.print("Digita el precio correspondiente: ");
                                            leerOpcionPlatillo.next(); // Descartar la entrada inválida
                                        }
                                        precio_platillo = leerOpcionPlatillo.nextDouble();
                                    }
                                    System.out.println();
                                    break;
                                case 5:
                                    System.out.print("Digite la nueva descripcion de la categoria: ");
                                    descripcion_categoria = leerOpcionPlatillo.nextLine();
                                    System.out.println();
                                    break;
                                default:
                                    System.out.println("Opcion no valida");
                                    break;
                            }

                            Categoria nueva_categoria = new Categoria(categoria_platillo, descripcion_categoria, id_categoria);
                            Platillo nuevo_platillo = new Platillo(id_platillo, precio_platillo, nombre_platillo, acompañantes_platillo, nueva_categoria); // Cuando se define las propiedades del platillo
                            Command modificar = new ModificarPlatilloCommand(gestionarPlatillos, nuevo_platillo); // Si se desea modificar otro platillo
                            invocador.ejecutarAccion(modificar);

                            System.out.println("Desea modificar otro platillo "
                                    + "\n1. SI"
                                    + "\n2. NO");
                            System.out.print("Seleccionar una opcion: ");
                            modificar_platillo = leerOpcionPlatillo.nextInt();
                        } else {
                            System.out.println("El id del platillo no existe");
                        }
                    } while (modificar_platillo != 2);
                    break;

                //Si el administrador desea eliminar un platillo
                case 3:

                    int opcion_eliminar = 0;

                    System.out.println("Estos son los platillos que ofrece tu restaurante");
                    gestionarPlatillos.mostrarPlatillos();  // Mostrar los platillos

                    if (gestionarPlatillos.platillos.isEmpty()) {
                        System.out.println("No hay platillos en la orden");
                    } else {
                        do {

                            try {
                                System.out.print("Digite el id del platillo que desea eliminar: ");
                                opcion_eliminar = leerOpcionPlatillo.nextInt();
                                System.out.println();
                                leerOpcionPlatillo.nextLine();
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                                leerOpcionPlatillo.nextLine();
                                System.out.print("Por favor, digite una informacion valida: ");
                                while (!leerOpcionPlatillo.hasNextInt()) {
                                    System.out.println("Eso no es un número válido.");
                                    System.out.print("Digita la opcion correspondiente: ");
                                    leerOpcionPlatillo.next();
                                }
                                opcion_eliminar = leerOpcionPlatillo.nextInt();
                            }

                            if (opcion_eliminar == gestionarPlatillos.platillos.get(opcion_eliminar - 1).getCodigo_platillo()) {

                                Command eliminar = new EliminarPlatilloCommand(gestionarPlatillos, gestionarPlatillos.platillos.get(opcion_eliminar - 1)); // Si se desea eliminar otro platillo
                                invocador.ejecutarAccion(eliminar);

                            } else {
                                System.out.println("El id del platillo no existe");
                            }

                            System.out.println("Desea eliminar otro platillo "
                                    + "\n1. SI"
                                    + "\n2. NO");
                            System.out.print("Seleccionar una opcion: ");
                            eliminar_platillo = leerOpcionPlatillo.nextInt();
                            System.out.println();
                            leerOpcionPlatillo.nextLine();
                        } while (eliminar_platillo != 2);
                    }
                    break;

                // Mostrar  los platillos
                case 4:
                    gestionarPlatillos.mostrarPlatillos();  // Mostrar los platillos

                    break;
                default:
                    System.out.println("Opcion no valida");
            }

        } while (opcion_platillo != 5);
    }
}
