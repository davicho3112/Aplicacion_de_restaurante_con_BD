package Restaurante;

import java.util.Date;

public class Reserva {

    private int identificacion_reserva;
    private int num_asientos;
    private int num_mesas;
    private int num_acompañantes;
    private Date fecha_reserva;

    public int getIdentificacion_reserva() {
        return identificacion_reserva;
    }

    public void setIdentificacion_reserva(int identificacion_reserva) {
        this.identificacion_reserva = identificacion_reserva;
    }

    public int getNum_asientos() {
        return num_asientos;
    }

    public void setNum_asientos(int num_asientos) {
        this.num_asientos = num_asientos;
    }

    public int getNum_mesas() {
        return num_mesas;
    }

    public void setNum_mesas(int num_mesas) {
        this.num_mesas = num_mesas;
    }

    public int getNum_acompañantes() {
        return num_acompañantes;
    }

    public void setNum_acompañantes(int num_acompañantes) {
        this.num_acompañantes = num_acompañantes;
    }

    public Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }
}
