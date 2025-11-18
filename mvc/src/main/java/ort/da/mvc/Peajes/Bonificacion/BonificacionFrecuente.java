package ort.da.mvc.Peajes.Bonificacion;

import java.util.Date;

import ort.da.mvc.Peajes.Bonificacion.CalcularBonificacion.Frecuente;
import ort.da.mvc.Peajes.Peaje.PuestoPeaje;
import ort.da.mvc.Peajes.Utils.Exceptions.BonificacionException;

public class BonificacionFrecuente extends Bonificacion{

    public BonificacionFrecuente(){
        super(new Frecuente());
    }

    public BonificacionFrecuente(PuestoPeaje pp2, String string, Date date) {
        super(pp2, string, date);
    }

}
