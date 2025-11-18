package ort.da.mvc.Peajes.Transito.Servicio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ort.da.mvc.Fachada;
import ort.da.mvc.Peajes.Peaje.PuestoPeaje;
import ort.da.mvc.Peajes.Transito.Transito;
import ort.da.mvc.Peajes.Usuarios.Propietario.Propietario;
import ort.da.mvc.Peajes.Utils.Exceptions.PuestoException;
import ort.da.mvc.Peajes.Utils.Exceptions.VehiculoException;
import ort.da.mvc.Peajes.Vehiculo.Vehiculo;

public class ServicioTransito {

    private List<Transito> transitos;

    public ServicioTransito() {
        this.transitos = new ArrayList<>();
    }

    public void emularTransito(String matricula,  String puesto, Date fechaHora) throws PuestoException, VehiculoException {

        PuestoPeaje puestoPeaje = null;

        try {
            puestoPeaje = Fachada.getInstancia().getPuestoNombre(puesto);// PREGUNTAR SI ROMPE DIV LOGICA -- CONSULTA

        } catch (PuestoException e) {
            throw new PuestoException("Puesto de peaje no encontrado");
        }

        Vehiculo vehiculo = null;


        try {

            vehiculo = Fachada.getInstancia().buscarVehiculoPorMatricula(matricula); // PREGUNTAR SI ROMPE DIV LOGICA -- CONSULTA

        } catch (VehiculoException e) {
            throw new VehiculoException("Vehículo no encontrado");
        }

        if (puestoPeaje == null) {
            throw new PuestoException("Puesto de peaje no encontrado");
        }

        if (vehiculo == null) {
            throw new VehiculoException("Vehículo no encontrado");
        }

        Transito transito = new Transito(vehiculo, fechaHora,00 /*calcularCosto(vehiculo, puestoPeaje)*/);

        //FALTA RESTAR DEL SALDO DEL PROPIETARIO
        //vehiculo.getPropietario().restarSaldo(transito.getCosto());
        
        System.out.println("Transito creado: " + transito.toString());

        transitos.add(transito);
    }

    public List<Transito> getTransitosPorPropietario(Propietario propietario) {
        List<Transito> resultado = new ArrayList<>();
        for (Transito t : transitos) {
            if (t.getVehiculo().getPropietario().equals(propietario)) {
                resultado.add(t);
            }
        }
        return resultado;
    }

    public List<Transito> getTransitosPorVehiculo(Vehiculo vehiculo) {
        List<Transito> resultado = new ArrayList<>();
        for (Transito t : transitos) {
            if (t.getVehiculo().equals(vehiculo)) {
                resultado.add(t);
            }
        }
        return resultado;
    }

    public int getCostoEnTransitosPorVehiculo(Vehiculo vehiculo) {
        int suma = 0;
        for (Transito t : transitos) {
            if (t.getVehiculo().equals(vehiculo)) {
                suma += t.getCosto();
            }
        }
        return suma;
    }

    public void crearTransito(Transito t) {
        transitos.add(t);
    }

}
