package ort.da.mvc.Peajes.Bonificacion;

import java.util.Date;

import ort.da.mvc.Peajes.Bonificacion.CalcularBonificacion.Trabajador;
import ort.da.mvc.Peajes.Peaje.PuestoPeaje;
 

public class BonificacionTrabajdor extends Bonificacion{

    public BonificacionTrabajdor(){
        super(new Trabajador());
    }

    public BonificacionTrabajdor(PuestoPeaje pp2, String string, Date date) {
        super(pp2, string, date);
    }
}
