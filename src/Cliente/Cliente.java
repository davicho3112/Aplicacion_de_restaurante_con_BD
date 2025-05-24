package Cliente;

import Cliente.Informacion.NumeroTelefono;
import Restaurante.Orden;

import java.util.Date;
import java.util.List;

public class Cliente implements Persona {

    private int dni;
    private Date fecha_nacimiento;
    private String nombre;
    private List<NumeroTelefono> contacto;
    private Orden solicitarOrden;

    public Cliente(int dni, Date fecha_nacimiento, String nombre, NumeroTelefono contacto_cliente) {
        this.dni = dni;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nombre = nombre;
        contacto.add(contacto_cliente);
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<NumeroTelefono> getNumero() {
        return contacto;
    }

    public void setNumero(List<NumeroTelefono> numero) {
        this.contacto = numero;
    }

    public Orden getSolicitarOrden() {
        return solicitarOrden;
    }

    public void setSolicitarOrden(Orden solicitarOrden) {
        this.solicitarOrden = solicitarOrden;
    }

    public void contacto(List<NumeroTelefono> numero) {

    }

    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("DNI: " + dni);
        System.out.println("Fecha de Nacimiento: " + fecha_nacimiento);
        System.out.println("Contacto: " + contacto);
    }

    public void notificacion_pedido(String estado) {
        solicitarOrden.actualizarOrden(estado);
     }
}