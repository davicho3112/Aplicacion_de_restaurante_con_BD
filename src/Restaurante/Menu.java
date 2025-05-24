package Restaurante;

import Administrador.Gestiones.GestionarPlatillo;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    GestionarPlatillo menuPlatillos;
    List<Platillo> compras_platillos;

    public Menu() {
        menuPlatillos = new GestionarPlatillo();
        compras_platillos = new ArrayList<>();
    }

    /**
     * Mostrar el menú del restaurante con los platillos disponibles.
     * @param
     */
    public void mostrarMenu(){

        System.out.println("Este es el menu del restaurante: ");
        menuPlatillos.mostrarPlatillos();
    }


    public void seleccionarPlatillo(int codigoPlatillo) {

        // Lógica para seleccionar un platillo del menú
        System.out.println("Platillo seleccionado: " + menuPlatillos.platillos.get(codigoPlatillo).getNombre_platillo());
        compras_platillos.add(menuPlatillos.platillos.get(codigoPlatillo)); // Se agregan los platillos seleccionados a la lista de compras

    }
}
