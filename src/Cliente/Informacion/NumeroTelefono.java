package Cliente.Informacion;

public class NumeroTelefono {

    private int prefijo;
    private long numero;
    private String tipo_uso;

    public int getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(int prefijo) {
        this.prefijo = prefijo;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getTipo_uso() {
        return tipo_uso;
    }

    public void setTipo_uso(String tipo_uso) {
        this.tipo_uso = tipo_uso;
    }

    @Override
    public String toString() {
        return "+" + prefijo + " " + numero + " (" + tipo_uso + ")";
    }
}