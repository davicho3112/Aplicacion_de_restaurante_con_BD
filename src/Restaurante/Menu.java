package Restaurante;

import Administrador.Gestiones.GestionarPlatillo;
import java.util.ArrayList;
import java.util.List;

public class Menu {

    public GestionarPlatillo menuPlatillos;
    public List<Platillo> compras_platillos;

    public Menu(GestionarPlatillo menuPlatillos) {
        this.menuPlatillos = menuPlatillos;
        this.compras_platillos = new ArrayList<>();
    }

    public void mostrarMenu() {
        System.out.println("\n==============================================================================================");
        System.out.printf("| %-3s | %-20s | %-10s | %-15s | %-15s | %-20s |\n", "ID", "Nombre", "Precio", "Acompañantes", "Categoría", "Descripción");
        System.out.println("----------------------------------------------------------------------------------------------");
        menuPlatillos.mostrarPlatillos();
    }

    public void seleccionarPlatillo(int numeroPlatillo) {
        int index = numeroPlatillo - 1;
        if (index >= 0 && index < menuPlatillos.platillos.size()) {
            Platillo seleccionado = menuPlatillos.platillos.get(index);
            System.out.println("Platillo seleccionado: " + seleccionado.getNombre_platillo());
            compras_platillos.add(seleccionado);
        } else {
            System.out.println("Opción de platillo no válida.");
        }
    }
}