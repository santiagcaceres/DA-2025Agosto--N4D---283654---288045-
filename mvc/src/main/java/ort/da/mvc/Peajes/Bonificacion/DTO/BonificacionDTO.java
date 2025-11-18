package ort.da.mvc.Peajes.Bonificacion.DTO;

import java.util.Date;

import ort.da.mvc.Peajes.Bonificacion.Bonificacion;
import ort.da.mvc.Peajes.Peaje.PuestoPeaje;
import ort.da.mvc.Peajes.Peaje.DTO.PeajeDTO;

public class BonificacionDTO {

    private String nombre;
    private String fechaAsignacion;
    private String puestoPeaje;

    public BonificacionDTO(Bonificacion b) {
        this.nombre = b.getNombre();
        this.puestoPeaje = b.getPuestoPeaje().toString();
        this.fechaAsignacion = b.getFechaAsignacion().toString();
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaAsignacion() {
        return fechaAsignacion;
    }

    public String getPuestoPeaje() {
        return puestoPeaje;
    }
    

}
