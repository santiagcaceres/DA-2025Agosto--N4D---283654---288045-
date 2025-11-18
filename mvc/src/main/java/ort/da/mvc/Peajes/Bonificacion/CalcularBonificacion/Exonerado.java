package ort.da.mvc.Peajes.Bonificacion.CalcularBonificacion;

import ort.da.mvc.Peajes.Utils.Exceptions.BonificacionException;

public class Exonerado implements CalcularBonificacion{

    @Override
    public int CalcularDescuento(int monto) throws BonificacionException{
        if (monto>0) {
            return monto * 0;
        }
        throw new BonificacionException("El monto debe ser mayor a 0");
    }

}
