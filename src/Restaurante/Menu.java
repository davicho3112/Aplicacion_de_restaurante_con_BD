package Restaurante;

public class Menu {

    public void mostrarPlatillos(Platillo platillo){
        System.out.println("Código del platillo: " + platillo.getCodigo_platillo());
        System.out.println("Nombre del platillo: " + platillo.getNombre_platillo());
        System.out.println("Acompañantes del platillo: " + platillo.getAcompañantes_platillo());
        System.out.println("Categoría del platillo: " + platillo.getCategoria().getNombre_categoria());

    }
}
