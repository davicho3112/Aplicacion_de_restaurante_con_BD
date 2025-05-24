package Restaurante;

import Administrador.Gestiones.GestionarPlatillo;

public class Menu {

    GestionarPlatillo menuPlatillos;

    public Menu() {
        menuPlatillos = new GestionarPlatillo();
    }

    public void mostrarMenu(Platillo platillo){

        System.out.println("Este es el menu del restaurante: ");
        menuPlatillos.mostrarPlatillos();
    }


}
