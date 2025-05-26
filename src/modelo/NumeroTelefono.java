package modelo;

/**
 * Clase que representa un número de teléfono en la base de datos
 */
public class NumeroTelefono {
    private int id;
    private long numero;
    private String prefijo;
    private String tipoUso;

    /**
     * Constructor vacío
     */
    public NumeroTelefono() {
    }

    /**
     * Constructor con todos los campos
     * @param id ID del número de teléfono (identificador único)
     * @param numero Número de teléfono
     * @param prefijo Prefijo del número de teléfono
     * @param tipoUso Tipo de uso del número de teléfono
     */
    public NumeroTelefono(int id, long numero, String prefijo, String tipoUso) {
        this.id = id;
        this.numero = numero;
        this.prefijo = prefijo;
        this.tipoUso = tipoUso;
    }

    /**
     * @return ID del número de teléfono
     */
    public int getId() {
        return id;
    }

    /**
     * @param id ID del número de teléfono
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Número de teléfono
     */
    public long getNumero() {
        return numero;
    }

    /**
     * @param numero Número de teléfono
     */
    public void setNumero(long numero) {
        this.numero = numero;
    }

    /**
     * @return Prefijo del número de teléfono
     */
    public String getPrefijo() {
        return prefijo;
    }

    /**
     * @param prefijo Prefijo del número de teléfono
     */
    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    /**
     * @return Tipo de uso del número de teléfono
     */
    public String getTipoUso() {
        return tipoUso;
    }

    /**
     * @param tipoUso Tipo de uso del número de teléfono
     */
    public void setTipoUso(String tipoUso) {
        this.tipoUso = tipoUso;
    }

    @Override
    public String toString() {
        return "NumeroTelefono{" +
                "id=" + id +
                ", numero=" + numero +
                ", prefijo='" + prefijo + '\'' +
                ", tipoUso='" + tipoUso + '\'' +
                '}';
    }
}