package Cliente.FactoryMethod;

import Cliente.Cliente;
import Cliente.Persona;

public class ClienteFactory  extends  PersonaFactory{

    public Persona crearPersona(){

        return new Cliente();
    }
}
