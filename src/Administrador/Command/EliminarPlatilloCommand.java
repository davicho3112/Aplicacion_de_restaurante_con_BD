package Administrador.Command;

import Administrador.Gestiones.GestionarPlatillo;
import Restaurante.Platillo;

public class EliminarPlatilloCommand implements Command {

    private GestionarPlatillo gestor;
    private Platillo platillo;

    public EliminarPlatilloCommand(GestionarPlatillo gestor, Platillo platillo) {
        this.gestor = gestor;
        this.platillo = platillo;
    }

    public void ejecutar() {
        gestor.eliminarPlatillo(platillo);
    }
}
