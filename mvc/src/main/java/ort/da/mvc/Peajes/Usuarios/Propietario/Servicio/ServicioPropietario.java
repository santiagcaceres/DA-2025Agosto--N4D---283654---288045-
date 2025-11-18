package ort.da.mvc.Peajes.Usuarios.Propietario.Servicio;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import ort.da.mvc.Fachada;
import ort.da.mvc.Peajes.Bonificacion.Bonificacion;
import ort.da.mvc.Peajes.Bonificacion.BonificacionExonerada;
import ort.da.mvc.Peajes.Bonificacion.BonificacionFrecuente;
import ort.da.mvc.Peajes.Bonificacion.BonificacionTrabajdor;
import ort.da.mvc.Peajes.Usuarios.Propietario.Propietario;
import ort.da.mvc.Peajes.Utils.Exceptions.BonificacionException;
import ort.da.mvc.Peajes.Utils.Exceptions.UsuarioException;
import ort.da.mvc.Peajes.Vehiculo.Vehiculo;

public class ServicioPropietario {

    private List<Propietario> propietarios;

    public ServicioPropietario() {
        this.propietarios = new ArrayList<>();
    }

    public String crearPropietario(Propietario propietario) {
        try {
            propietario.verificarDatos();
            propietarios.add(propietario);
            return "Propietario creado exitosamente";
        } catch (UsuarioException e) {
            return e.getMessage();
        }
    }

    public ArrayList<Bonificacion> getBonificacionesDePropietario(Propietario p) {
        return (ArrayList) p.getBonificaciones();
    }


}
