package ort.da.mvc.Peajes.Utils;

import java.util.ArrayList;
import java.util.List;

import ort.da.mvc.Peajes.Usuarios.Administrador.Administrador;
import ort.da.mvc.Peajes.Usuarios.Propietario.Propietario;
import ort.da.mvc.Peajes.Utils.Exceptions.UsuarioException;
import ort.da.mvc.Peajes.Utils.Exceptions.UsuarioException;
import ort.da.mvc.Peajes.Utils.Sesion.Sesion;

public class ServicioAccesos {

    private List<Administrador> administradores;
    private List<Propietario> propietarios;
    // private List<Usuario> usuarios;
    private ArrayList<Sesion> sesiones;

    public ServicioAccesos() {
        this.propietarios = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.sesiones = new ArrayList<>();
        // this.usuarios = new ArrayList<>();
    }

    // #region login

    // #endregion

    // #region loginPropietario

    public Propietario loginPropietario(int ci, String contrasenia) throws UsuarioException {
        for (Propietario p : propietarios) {
            if (p.getCi() == ci) {
                try {
                    p.verificarDeshabilitado();
                    p.ValidarCi(ci);
                    p.ValidarContraseña(contrasenia);
                    p.verificarDatos();
                    return p;

                } catch (UsuarioException e) {
                    throw e;
                }

            }
        }
        throw new UsuarioException("Acceso denegado");
    }

    public Propietario getPropietarioPorCI(int ci) throws UsuarioException {
        for (Propietario p : propietarios) {
            if (p.getCi() == ci) {
                return p;
            }
        }
        throw new UsuarioException("No existe el propietario");
    }

    // #endregion

    // #region loginAdministrador

    public Administrador loginAdministrador(int ci, String contrasenia) throws UsuarioException {
        for (Administrador a : administradores) {
            if (a.getCi() == ci) {
                try {
                    a.ValidarContraseña(contrasenia);
                    a.ValidarCi(ci);
                    return a;
                } catch (UsuarioException e) {
                    throw e;
                }
            }
        }
        throw new UsuarioException("Acceso denegado");
    }

    // #endregion

    // #endregion

    // #region agregarPropietario

    public String agregarPropietario(Propietario propietario) {
        try {
            propietario.verificarDatos();
            propietarios.add(propietario);
            return "Propietario creado exitosamente";
        } catch (UsuarioException e) {
            return e.getMessage();
        }
    }

    // #endregion

    // #region agregarAdministrador

    public String crearAdministrador(Administrador admin) {
        try {
            admin.verificarDatos();
            administradores.add(admin);
            return "Administrador creado exitosamente";
        } catch (UsuarioException e) {
            return e.getMessage();
        }
    }

    // #endregion

    public Object getSesionesActivasInfo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSesionesActivasInfo'");
    }

    public List<Propietario> getPropietarios() {
        return this.propietarios;
    }
}
