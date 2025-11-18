package ort.da.mvc.Peajes.Bonificacion;

import java.util.ArrayList;
import java.util.Date;

import ort.da.mvc.Peajes.Bonificacion.CalcularBonificacion.CalcularBonificacion;
import ort.da.mvc.Peajes.Bonificacion.CalcularBonificacion.Exonerado;
import ort.da.mvc.Peajes.Bonificacion.CalcularBonificacion.Frecuente;
import ort.da.mvc.Peajes.Bonificacion.CalcularBonificacion.Trabajador;
import ort.da.mvc.Peajes.Peaje.PuestoPeaje;
import ort.da.mvc.Peajes.Utils.Exceptions.BonificacionException;

public abstract class Bonificacion {
    
    private String nombre;
    private Date fechaAsignacion;
    private PuestoPeaje puestoPeaje;

    private CalcularBonificacion tipoBonificacion;

//#region Constructores

    public Bonificacion(CalcularBonificacion tipo){
        this.tipoBonificacion = tipo;
    }

    public Bonificacion( PuestoPeaje p, String nombre, Date fechaAsignacion){
        this.puestoPeaje = p;
        this.nombre = nombre;
        this.fechaAsignacion = fechaAsignacion;
    }

//#endregion

//#region verificarPuesto

    public void verificarPuesto(PuestoPeaje p) throws BonificacionException {
        if(!puestoPeaje.equals(p)) {
            throw new BonificacionException("La bonificacion no aplica para este puesto de peaje.");
        }
    }

//#endregion


//#region getters

    public PuestoPeaje getPuesto() {
        return puestoPeaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public PuestoPeaje getPuestoPeaje() {
        return puestoPeaje;
    }

    public void setPuestoPeaje(PuestoPeaje puestoPeaje) {
        this.puestoPeaje = puestoPeaje;
    }

    public CalcularBonificacion getTipoBonificacion() {
        return tipoBonificacion;
    }

    public void setTipoBonificacion(CalcularBonificacion tipoBonificacion) {
        this.tipoBonificacion = tipoBonificacion;
    } 

    


//#endregion


@Override
public String toString() {
    return "nombre= " + nombre + ", fechaAsignacion= " + fechaAsignacion + ", puestoPeaje= " + puestoPeaje
            + ", tipoBonificacion= " + tipoBonificacion;
}
}