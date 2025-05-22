package Administrador;

import Administrador.Gestiones.GestionarPlatillo;

public class AdministrarRestaurante {

    private GestionarReservas gestionarReservas;
    private GestionarPlatillo gestionarPlatillos;

    public AdministrarRestaurante() {
        this.gestionarReservas = new GestionarReservas();
        this.gestionarPlatillos = new GestionarPlatillo();
    }

    public void gestionarReservas() {
        // Lógica para gestionar reservas
        //El administrador pueda llamar los metodos asignar mesas,asientos y fechas disponibles
    }

    public void gestionarPlatillos() {
        // Lógica para gestionar platillos
        //El administrador pueda llamar los metodos agregar,eliminar y modificar platillos
    }
}
