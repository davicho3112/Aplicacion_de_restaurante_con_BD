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
        platillos.add(platillo);
        System.out.println("\n========================================");
        System.out.printf(" %-35s\n", "¡Platillo agregado exitosamente!");
        System.out.println("----------------------------------------");
        System.out.printf(" %-15s: %s\n", "Nombre", platillo.getNombre_platillo());
        System.out.printf(" %-15s: $%.2f\n", "Precio", platillo.getPrecio());
        System.out.printf(" %-15s: %s\n", "Acompañantes", platillo.getAcompañantes_platillo());
        System.out.printf(" %-15s: %s\n", "Categoría", platillo.getCategoria().getNombre_categoria());
        System.out.printf(" %-15s: %s\n", "Descripción", platillo.getCategoria().getDescripcion_categoria());
        System.out.println("========================================\n");
    }

    public void eliminarPlatillo(Platillo platillo) {
        System.out.println("\n========================================");
        if (platillos.remove(platillo)) {
            System.out.printf(" %-35s\n", "¡Platillo eliminado exitosamente!");
            System.out.printf(" %-15s: %s\n", "Nombre", platillo.getNombre_platillo());
        } else {
            System.out.printf(" %-35s\n", "Platillo no encontrado.");
        }
        System.out.println("========================================\n");
    }

    public void modificarPlatillo(Platillo platilloModificado) {
        System.out.println("\n========================================");
        for (int i = 0; i < platillos.size(); i++) {
            if (platillos.get(i).getCodigo_platillo() == platilloModificado.getCodigo_platillo()) {
                platillos.set(i, platilloModificado);
                System.out.printf(" %-35s\n", "¡Platillo modificado exitosamente!");
                System.out.printf(" %-15s: %s\n", "Nombre", platilloModificado.getNombre_platillo());
                System.out.println("========================================\n");
                return;
            }
        }
        System.out.printf(" %-35s\n", "Platillo no encontrado.");
        System.out.println("========================================\n");
    }

    public void mostrarPlatillos() {
        if (platillos.isEmpty()) {
            System.out.println("\n========================================");
            System.out.println(" No hay platillos en la orden");
            System.out.println("========================================\n");
        } else {
            System.out.println("\n==============================================================================================");
            System.out.printf("| %-3s | %-20s | %-10s | %-15s | %-15s | %-20s |\n", "ID", "Nombre", "Precio", "Acompañantes", "Categoría", "Descripción");
            System.out.println("----------------------------------------------------------------------------------------------");
            for (Platillo platillo : platillos) {
                System.out.printf("| %-3d | %-20s | $%-9.2f | %-15s | %-15s | %-20s |\n",
                        platillo.getCodigo_platillo(),
                        platillo.getNombre_platillo(),
                        platillo.getPrecio(),
                        platillo.getAcompañantes_platillo(),
                        platillo.getCategoria().getNombre_categoria(),
                        platillo.getCategoria().getDescripcion_categoria());
            }
            System.out.println("==============================================================================================\n");
        }
    }
}