package Cliente.FactoryMethod;

import Cliente.Cliente;
import Cliente.Informacion.NumeroTelefono;
import Cliente.Persona;

import java.util.Date;

public class ClienteFactory  extends  PersonaFactory{

    private int dni_cliente;
    private Date fecha_nacimiento_cliente;
    private String nombre_cliente;
    private NumeroTelefono contacto_cliente;



    public ClienteFactory(int dni_cliente, Date fecha_nacimiento_cliente, String nombre_cliente, NumeroTelefono contacto_cliente) {
        this.dni_cliente = dni_cliente;
        this.fecha_nacimiento_cliente = fecha_nacimiento_cliente;
        this.nombre_cliente = nombre_cliente;
        this.contacto_cliente = contacto_cliente;
    }

    public Persona crearPersona() {

        return new Cliente(dni_cliente,fecha_nacimiento_cliente,nombre_cliente,contacto_cliente);
    }
}
