package Administrador.Command;

import Administrador.Gestiones.GestionarPlatillo;
import Restaurante.Platillo;

public class ModificarPlatilloCommand implements Command {

    //Pasa el metodo que le corresponde a la clase para que tenga el comportamiento de modificar el platillo
    private GestionarPlatillo gestor;
    private Platillo platillo;

    public ModificarPlatilloCommand(GestionarPlatillo gestor, Platillo platillo) {
        this.gestor = gestor;
        this.platillo = platillo;
    }

    public void ejecutar() {
        gestor.modificarPlatillo(platillo);
    }
}
