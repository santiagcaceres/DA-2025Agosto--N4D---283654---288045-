package ort.da.mvc.Peajes.Peaje;

import java.util.ArrayList;
import java.util.List;

import ort.da.mvc.Peajes.Tarifa.Tarifa;
import ort.da.mvc.Peajes.Utils.Exceptions.PuestoException;
import ort.da.mvc.Peajes.Vehiculo.CategoriaVehiculo;

public class PuestoPeaje {

    private String nombre;
    private String direccion;
    private List<Tarifa> tarifas = new ArrayList<Tarifa>();

    
    public PuestoPeaje(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public PuestoPeaje(String nombre, String direccion, List<Tarifa> tarifas) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.tarifas = tarifas;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getDireccion() {
        return direccion;
    }


    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(Tarifa tarifa) {
        tarifas.add(tarifa);
    }


    public void verificarDatos() throws PuestoException {
        if(this.nombre == null || this.nombre.isEmpty()){
            throw new PuestoException("El nombre del puesto no puede ser nulo o vacío");
        }
        if(this.direccion == null || this.direccion.isEmpty()){
            throw new PuestoException("La dirección del puesto no puede ser nula o vacía");
        }
    }

    @Override  
    public String toString() {
        return 
                "nombre: " + nombre +
                ", direccion: " + direccion ;
    }
}
