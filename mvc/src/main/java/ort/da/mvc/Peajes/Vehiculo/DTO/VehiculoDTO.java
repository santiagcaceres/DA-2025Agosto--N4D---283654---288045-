package ort.da.mvc.Peajes.Vehiculo.DTO;

import ort.da.mvc.Peajes.Usuarios.Propietario.Propietario;
import ort.da.mvc.Peajes.Vehiculo.CategoriaVehiculo;
import ort.da.mvc.Peajes.Vehiculo.Vehiculo;

public class VehiculoDTO {

    private String matricula;
    private String modelo;
    private String color;
    private String categoria;
    private String propietario;
    private int cantidadTransitos;
    private int costoTotalTransitos;

    public VehiculoDTO(Vehiculo v) {
        this.matricula = v.getMatricula();
        this.modelo = v.getModelo();
        this.color = v.getColor();
        this.categoria = v.getCategoria().getNombre();
        this.propietario = v.getPropietario().getNombre();
    }

    public VehiculoDTO(Vehiculo v, int cantidadTransitos, int costoTotalTransitos) {
        this.matricula = v.getMatricula();
        this.modelo = v.getModelo();
        this.color = v.getColor();
        this.categoria = v.getCategoria().getNombre();
        this.propietario = v.getPropietario().getNombre();
        this.cantidadTransitos = cantidadTransitos;
        this.costoTotalTransitos = costoTotalTransitos;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getPropietario() {
        return propietario;
    }

}