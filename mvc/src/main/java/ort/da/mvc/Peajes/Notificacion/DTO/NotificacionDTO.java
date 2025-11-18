package ort.da.mvc.Peajes.Notificacion.DTO;

import java.util.Date;

import ort.da.mvc.Peajes.Notificacion.Notificacion;

public class NotificacionDTO {
    private String mensaje;
    private String fecha;

    public NotificacionDTO(Notificacion n) {
        this.mensaje = n.getMensaje();
        this.fecha = n.getFecha().toString();
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getFecha() {
        return fecha;
    }
}
