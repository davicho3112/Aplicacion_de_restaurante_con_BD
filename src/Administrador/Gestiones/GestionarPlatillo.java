package Administrador.Gestiones;

import Restaurante.Platillo;

import java.util.ArrayList;
import java.util.List;

public class GestionarPlatillo {

    public List<Platillo> platillos;

    public GestionarPlatillo() {
        this.platillos = new ArrayList<>();
    }

    public void agregarPlatillo(Platillo platillo) {

        //Se agrega a la clase AgregarPlatilloCommand
        platillos.add(platillo);
        System.out.println("Restaurante.Platillo agregado: " + platillo.getNombre_platillo());
    }

    public void eliminarPlatillo(Platillo platillo) {
        //Se agrega a la clase EliminarPlatilloCommand
        if (platillos.remove(platillo)) {
            System.out.println("Restaurante.Platillo eliminado: " + platillo.getNombre_platillo());
        } else {
            System.out.println("Restaurante.Platillo no encontrado: " + platillo.getNombre_platillo());
        }
    }

    public void modificarPlatillo(Platillo platilloModificado) {
        //Se agrega a la clase ModificarPlatilloCommand
        for (int i = 0; i < platillos.size(); i++) {
            if (platillos.get(i).getCodigo_platillo() == platilloModificado.getCodigo_platillo()) {
                platillos.set(i, platilloModificado);
                System.out.println("Restaurante.Platillo modificado: " + platilloModificado.getNombre_platillo());
                return;
            }
        }
        System.out.println("Restaurante.Platillo no encontrado: " + platilloModificado.getNombre_platillo());
    }

    public void mostrarPlatillos() {

        if (platillos.isEmpty()) {
            System.out.println("No hay platillos en la orden");
        }else {
            System.out.println("Lista de Platillos:");
            for (Platillo platillo : platillos) {
                System.out.println("- " + platillo.getNombre_platillo() + ": $" + platillo.getPrecio() +" " + platillo.getAcompañantes_platillo() + " " + platillo.getCategoria().getNombre_categoria() + " " + platillo.getCategoria().getDescripcion_categoria());
            }
        }
    }
}
