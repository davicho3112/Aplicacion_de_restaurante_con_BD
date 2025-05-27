package Administrador.Command;

import Administrador.Gestiones.GestionarPlatillo;
import Restaurante.Platillo;

public class AgregarPlatilloCommand implements Command {

    private GestionarPlatillo gestor;
    private Platillo platillo;

    public AgregarPlatilloCommand(GestionarPlatillo gestor, Platillo platillo) {
        this.gestor = gestor;
        this.platillo = platillo;
    }

    public void ejecutar() {
        gestor.agregarPlatillo(platillo);
    }
}
