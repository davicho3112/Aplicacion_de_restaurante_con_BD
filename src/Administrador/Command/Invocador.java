package Administrador.Command;

public class Invocador {

    public void ejecutarAccion(Command command) {
        command.ejecutar();
    }
}
