package Administrador.Gestiones;

import java.util.ArrayList;

public class GestionarPlatillo {

    private List<Platillo> platillos;

    public GestionarPlatillo() {
        this.platillos = new ArrayList<>();
    }

    public void agregarPlatillo(Platillo platillo) {

        //Se agrega a la clase AgregarPlatilloCommand
        platillos.add(platillo);
        System.out.println("Restaurante.Platillo agregado: " + platillo.getNombre());
    }

    public void eliminarPlatillo(Platillo platillo) {
        //Se agrega a la clase EliminarPlatilloCommand
        if (platillos.remove(platillo)) {
            System.out.println("Restaurante.Platillo eliminado: " + platillo.getNombre());
        } else {
            System.out.println("Restaurante.Platillo no encontrado: " + platillo.getNombre());
        }
    }

    public void modificarPlatillo(Platillo platillo, String nuevoNombre, double nuevoPrecio) {
        //Se agrega a la clase ModificarPlatilloCommand
        platillo.setNombre(nuevoNombre);
        platillo.setPrecio(nuevoPrecio);
        System.out.println("Restaurante.Platillo modificado: " + platillo.getNombre());
    }

    public void mostrarPlatillos() {
        System.out.println("Lista de Platillos:");
        for (Platillo platillo : platillos) {
            System.out.println("- " + platillo.getNombre() + ": $" + platillo.getPrecio());
        }
    }
}
