package Cliente;

import Cliente.Informacion.Direccion;
import Cliente.Informacion.NumeroTelefono;

import java.util.Date;
import java.util.List;

public class Cliente implements Persona {

    private int dni;
    private Date fecha_nacimiento;
    private String nombre;
    private List<Direccion> direccion;
    private List<NumeroTelefono> numero;

    public void contacto(List<NumeroTelefono> numero) {

    }

    public void residencia(List<Direccion> direccion) {

    }

    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("DNI: " + dni);
        System.out.println("Fecha de Nacimiento: " + fecha_nacimiento);
        System.out.println("Dirección: " + direccion);
        System.out.println("Teléfono: " + numero);
    }
}