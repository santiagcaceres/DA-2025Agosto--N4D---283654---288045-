package ort.da.mvc.Peajes.Notificacion;

import java.util.Date;

public class Notificacion {
    private String mensaje;
    private Date fecha;

    public Notificacion(String string, Date date) {
        this.mensaje = string;
        this.fecha = date;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    

}
