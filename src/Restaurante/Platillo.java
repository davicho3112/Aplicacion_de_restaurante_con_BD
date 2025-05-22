package Restaurante;

public class Platillo {

    private int codigo_platillo;
    private String nombre_platillo;
    private String acompañantes_platillo;
    private Categoria categoria;

    public Platillo(int codigo_platillo, String nombre_platillo, String acompañantes_platillo, Categoria categoria) {
        this.codigo_platillo = codigo_platillo;
        this.nombre_platillo = nombre_platillo;
        this.acompañantes_platillo = acompañantes_platillo;
        this.categoria = categoria;
    }

    public int getCodigo_platillo() {
        return codigo_platillo;
    }

    public String getNombre_platillo() {
        return nombre_platillo;
    }

    public String getAcompañantes_platillo() {
        return acompañantes_platillo;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
