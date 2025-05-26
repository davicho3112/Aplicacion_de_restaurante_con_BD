package modelo;

/**
 * Clase que representa un detalle de orden en la base de datos
 */
public class DetalleOrden {
    private int id;
    private int cantidad;
    private int fkIdOrden;
    private int fkIdPlatillo;

    /**
     * Constructor vacío
     */
    public DetalleOrden() {
    }

    /**
     * Constructor con todos los campos
     * @param id ID del detalle de orden (identificador único)
     * @param cantidad Cantidad de platillos ordenados
     * @param fkIdOrden ID de la orden asociada (clave foránea)
     * @param fkIdPlatillo ID del platillo asociado (clave foránea)
     */
    public DetalleOrden(int id, int cantidad, int fkIdOrden, int fkIdPlatillo) {
        this.id = id;
        this.cantidad = cantidad;
        this.fkIdOrden = fkIdOrden;
        this.fkIdPlatillo = fkIdPlatillo;
    }

    /**
     * @return ID del detalle de orden
     */
    public int getId() {
        return id;
    }

    /**
     * @param id ID del detalle de orden
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Cantidad de platillos ordenados
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad Cantidad de platillos ordenados
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return ID de la orden asociada (clave foránea)
     */
    public int getFkIdOrden() {
        return fkIdOrden;
    }

    /**
     * @param fkIdOrden ID de la orden asociada (clave foránea)
     */
    public void setFkIdOrden(int fkIdOrden) {
        this.fkIdOrden = fkIdOrden;
    }

    /**
     * @return ID del platillo asociado (clave foránea)
     */
    public int getFkIdPlatillo() {
        return fkIdPlatillo;
    }

    /**
     * @param fkIdPlatillo ID del platillo asociado (clave foránea)
     */
    public void setFkIdPlatillo(int fkIdPlatillo) {
        this.fkIdPlatillo = fkIdPlatillo;
    }

    @Override
    public String toString() {
        return "DetalleOrden{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", fkIdOrden=" + fkIdOrden +
                ", fkIdPlatillo=" + fkIdPlatillo +
                '}';
    }
}