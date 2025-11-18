package ort.da.mvc.Peajes.Bonificacion;

import java.util.Date;

import ort.da.mvc.Peajes.Bonificacion.CalcularBonificacion.Exonerado;
import ort.da.mvc.Peajes.Bonificacion.CalcularBonificacion.Frecuente;
import ort.da.mvc.Peajes.Peaje.PuestoPeaje;
import ort.da.mvc.Peajes.Usuarios.Propietario.Propietario;
import ort.da.mvc.Peajes.Utils.Exceptions.BonificacionException;

public class BonificacionExonerada extends Bonificacion{

    public BonificacionExonerada(){
        super(new Exonerado());
    }

    public BonificacionExonerada( PuestoPeaje p, String nombre, Date fechaAsignacion){
        super(p, nombre, fechaAsignacion);
    }

}
