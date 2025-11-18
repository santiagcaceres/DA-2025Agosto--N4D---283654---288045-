package ort.da.mvc.Peajes.Usuarios.Administrador.Servicio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ort.da.mvc.Peajes.Peaje.PuestoPeaje;
import ort.da.mvc.Peajes.Usuarios.Administrador.Administrador;
import ort.da.mvc.Peajes.Usuarios.Propietario.Propietario;
import ort.da.mvc.Peajes.Utils.Exceptions.UsuarioException;

public class ServicioAdministrador {

    private List<Administrador> administradores;

    public ServicioAdministrador() {
        this.administradores = new ArrayList<>();
    }
    


    public String crearAdministrador(Administrador admin) {
        try {
            admin.verificarDatos();
            administradores.add(admin);
            return "Administrador creado exitosamente";
        } catch (UsuarioException e) {
            return e.getMessage();
        }
    }



}



