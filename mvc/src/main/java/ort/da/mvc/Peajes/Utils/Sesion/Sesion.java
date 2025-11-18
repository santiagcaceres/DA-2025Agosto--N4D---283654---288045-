package ort.da.mvc.Peajes.Utils.Sesion;

import java.sql.Date;

import ort.da.mvc.Peajes.Usuarios.Propietario.Propietario;

public class Sesion {

    private Date fechaIngreso = new Date(System.currentTimeMillis()); //CAMBIARRR
    private Propietario usuario;

    public Sesion(Propietario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public Propietario getUsuario() {
        return usuario;
    }
}
