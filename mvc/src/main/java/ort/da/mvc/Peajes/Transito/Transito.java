package ort.da.mvc.Peajes.Transito;

import java.util.Date;

import ort.da.mvc.Peajes.Vehiculo.Vehiculo;

public class Transito {
    private Vehiculo vehiculo;
    private Date fecha;
    private double costo;

    public Transito(Vehiculo vehiculo, Date fecha, double costo) {
        this.vehiculo = vehiculo;
        this.fecha = fecha;
        this.costo = costo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    public Date getFecha() {
        return fecha;
    }
    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}
