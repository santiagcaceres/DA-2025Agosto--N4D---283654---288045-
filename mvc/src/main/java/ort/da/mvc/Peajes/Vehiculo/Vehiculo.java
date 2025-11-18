package ort.da.mvc.Peajes.Vehiculo;

import ort.da.mvc.Peajes.Usuarios.Propietario.Propietario;
import ort.da.mvc.Peajes.Utils.Exceptions.VehiculoException;

public class Vehiculo {
    private String matricula;
    private String modelo;
    private String color;
    private CategoriaVehiculo categoria;
    private Propietario propietario;

    public Vehiculo(String matricula, String modelo, String color, CategoriaVehiculo categoria, Propietario propietario) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.color = color;
        this.categoria = categoria;
        this.propietario = propietario;
    }

    public String getMatricula() {
        return matricula;
    }

    public void verificarDatos() throws VehiculoException {
        if (matricula == null || matricula.isEmpty()) {
            throw new VehiculoException("La matrícula no puede estar vacía");
        }
        if (modelo == null || modelo.isEmpty()) {
            throw new VehiculoException("El modelo no puede estar vacío");
        }
        if (categoria == null) {
            throw new VehiculoException("La categoría del vehículo no puede ser nula");
        }
        if (propietario == null) {
            throw new VehiculoException("El propietario no puede ser nulo");
        }
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "matricula=" + matricula + '\'' +
                ", modelo=" + modelo + '\'' +
                ", color=" + color + '\'' +
                ", categoria=" + categoria.toString() +
                ", propietario=" + propietario.getNombre() +
                '}';
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public CategoriaVehiculo getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaVehiculo categoria) {
        this.categoria = categoria;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    

}
