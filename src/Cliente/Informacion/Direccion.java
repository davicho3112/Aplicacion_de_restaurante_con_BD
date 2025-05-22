package Cliente.Informacion;

public class Direccion {
    private String ciudad;
    private String departamento;
    private String barrio;
    private String nombreDireccion;

    public Direccion(String ciudad, String departamento, String barrio, String nombreDireccion) {
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.barrio = barrio;
        this.nombreDireccion = nombreDireccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getNombreDireccion() {
        return nombreDireccion;
    }

    public void setNombreDireccion(String nombreDireccion) {
        this.nombreDireccion = nombreDireccion;
    }
}
