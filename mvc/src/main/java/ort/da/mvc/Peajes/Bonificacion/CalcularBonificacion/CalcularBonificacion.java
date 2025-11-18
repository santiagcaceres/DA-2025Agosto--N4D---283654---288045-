package ort.da.mvc.Peajes.Bonificacion.CalcularBonificacion;

import ort.da.mvc.Peajes.Utils.Exceptions.BonificacionException;

public interface CalcularBonificacion {

    public int CalcularDescuento(int monto) throws BonificacionException;
    
}
