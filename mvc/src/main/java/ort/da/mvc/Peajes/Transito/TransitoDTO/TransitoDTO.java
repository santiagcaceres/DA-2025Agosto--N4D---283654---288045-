package ort.da.mvc.Peajes.Transito.TransitoDTO;

import ort.da.mvc.Peajes.Transito.Transito;
import ort.da.mvc.Peajes.Vehiculo.DTO.VehiculoDTO;

public class TransitoDTO {
    private String vehiculo;
    private String fecha;
    private double costo;

    public TransitoDTO(Transito t) {
        this.vehiculo = t.getVehiculo().getMatricula();
        this.fecha = t.getFecha().toString();
        this.costo = costo;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public String getFecha() {
        return fecha;
    }

    public double getCosto() {
        return costo;
    }
}
