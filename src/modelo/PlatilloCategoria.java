package modelo;

/**
 * Clase que representa la relación entre un platillo y una categoría en la base de datos
 */
public class PlatilloCategoria {
    private int id;
    private int fkPlatillo;
    private int fkCategoria;

    /**
     * Constructor vacío
     */
    public PlatilloCategoria() {
    }

    /**
     * Constructor con todos los campos
     * @param id ID de la relación (identificador único)
     * @param fkPlatillo ID del platillo asociado (clave foránea)
     * @param fkCategoria ID de la categoría asociada (clave foránea)
     */
    public PlatilloCategoria(int id, int fkPlatillo, int fkCategoria) {
        this.id = id;
        this.fkPlatillo = fkPlatillo;
        this.fkCategoria = fkCategoria;
    }

    /**
     * @return ID de la relación
     */
    public int getId() {
        return id;
    }

    /**
     * @param id ID de la relación
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return ID del platillo asociado (clave foránea)
     */
    public int getFkPlatillo() {
        return fkPlatillo;
    }

    /**
     * @param fkPlatillo ID del platillo asociado (clave foránea)
     */
    public void setFkPlatillo(int fkPlatillo) {
        this.fkPlatillo = fkPlatillo;
    }

    /**
     * @return ID de la categoría asociada (clave foránea)
     */
    public int getFkCategoria() {
        return fkCategoria;
    }

    /**
     * @param fkCategoria ID de la categoría asociada (clave foránea)
     */
    public void setFkCategoria(int fkCategoria) {
        this.fkCategoria = fkCategoria;
    }

    @Override
    public String toString() {
        return "PlatilloCategoria{" +
                "id=" + id +
                ", fkPlatillo=" + fkPlatillo +
                ", fkCategoria=" + fkCategoria +
                '}';
    }
}