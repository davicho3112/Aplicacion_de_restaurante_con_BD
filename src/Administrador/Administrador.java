package Administrador;

public class Administrador {

    private String nombre;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
    }
}
