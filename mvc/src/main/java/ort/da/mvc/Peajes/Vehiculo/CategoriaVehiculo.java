package ort.da.mvc.Peajes.Vehiculo;

public class CategoriaVehiculo {

    private String nombre;

    public CategoriaVehiculo(String string) {
        this.nombre = string;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
