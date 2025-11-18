package ort.da.mvc.Peajes.Usuarios.Administrador;

import ort.da.mvc.Peajes.Utils.Exceptions.UsuarioException;

public class Administrador {

    private int ci;
    private String contrsenia;
    private String nombreCompleto;

    public Administrador(int ci, String password, String nombreCompleto) {
        this.ci = ci;
        this.contrsenia = password;
        this.nombreCompleto = nombreCompleto;
    }

    // #region verificaciones

    public void verificarDatos() throws UsuarioException {

        ciMayorA0();

        nombreDistintoANull();

        passwordDistintoANull();
    }

    public void ciMayorA0() throws UsuarioException {
        if (getCi() <= 0) {
            throw new UsuarioException("La cédula de identidad no puede ser menor o igual a cero.");
        }
    }

    public void nombreDistintoANull() throws UsuarioException {
        if (getNombreCompleto() == null || getNombreCompleto().isEmpty()) {
            throw new UsuarioException("El nombre no puede estar vacío.");
        }
    }

    public void passwordDistintoANull() throws UsuarioException {
        if (getPassword() == null || getPassword().isEmpty()) {
            throw new UsuarioException("La contraseña no puede estar vacía.");
        }
    }

    // #endregion

    // #region getter and setter

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getPassword() {
        return contrsenia;
    }

    public void setPassword(String contrsenia) {
        this.contrsenia = contrsenia;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void ValidarContraseña(String contrasenia) throws UsuarioException {
        if (!this.contrsenia.equals(contrasenia)) {
            throw new UsuarioException("Contraseña incorrecta.");
        }

    }

    public void ValidarCi(int ci2) throws UsuarioException {
        if (this.ci != ci2) {
            throw new UsuarioException("Cédula de identidad incorrecta");
        }
    }

    //#endregion


    @Override
    public String toString() {
        return "Administrador: " +
                "ci- " + ci +
                ", contrsenia- '" + contrsenia + '\'' +
                ", nombreCompleto- '" + nombreCompleto;
    }
}
