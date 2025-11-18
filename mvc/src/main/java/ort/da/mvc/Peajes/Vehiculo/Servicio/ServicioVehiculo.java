package ort.da.mvc.Peajes.Vehiculo.Servicio;

import java.util.ArrayList;
import java.util.List;

import ort.da.mvc.Peajes.Utils.Exceptions.VehiculoException;
import ort.da.mvc.Peajes.Vehiculo.Vehiculo;

public class ServicioVehiculo {

    private List<Vehiculo> vehiculos;

    public ServicioVehiculo() {
        this.vehiculos = new ArrayList<>();
    }

    public Vehiculo verificarMatricula(String matricula) throws VehiculoException {
        if(matricula == null || matricula.isEmpty()) {
            throw new VehiculoException("La matricula no puede estar vacia");
        }
        if (vehiculos.isEmpty()) {
            throw new VehiculoException("No existen vehiculos registrados");
        }
        for(Vehiculo v : vehiculos){
            if(v.getMatricula().equals(matricula)){
                return v;    
            }
        }
        throw new VehiculoException("No existe el vehiculo");
    }

    public String crearVehiculo(Vehiculo v) {
        //HAY QUE VERIFICAR QUE NO EXISTA YA UN VEHICULO CON ESA MATRICULA SIN REPETIR CODIGO
        try {
            v.verificarDatos();
            vehiculos.add(v);
            return "Vehículo creado con éxito";
        } catch (VehiculoException e) {
            return e.getMessage();
        }
    }

    public Vehiculo buscarVehiculoPorMatricula(String matricula) throws VehiculoException {
        if(matricula == null) {
            throw new VehiculoException("La matrícula no puede ser nula");
        }
        if (vehiculos.isEmpty()) {
            throw new VehiculoException("No existen vehículos registrados");
        }
        for (Vehiculo v : vehiculos) {
            if (v.getMatricula().equals(matricula)) {
                return v;
            }
        }
        throw new VehiculoException("No existe el vehículo con la matrícula especificada");
    }

    

}
