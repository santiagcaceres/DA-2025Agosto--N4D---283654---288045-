package ort.da.mvc.Peajes.Usuarios.Propietario;

import java.util.ArrayList;
import java.util.List;

import ort.da.mvc.Peajes.Bonificacion.Bonificacion;
import ort.da.mvc.Peajes.Notificacion.Notificacion;
import ort.da.mvc.Peajes.Utils.Exceptions.BonificacionException;
import ort.da.mvc.Peajes.Utils.Exceptions.UsuarioException;
import ort.da.mvc.Peajes.Vehiculo.Vehiculo;

public class Propietario {

    private int ci;
    private String contrsenia;
    private String nombreCompleto;
    private int saldoActual;
    private int saldoMinimo;
    private EstadoPropietario estado;
    private List<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
    private List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
    private List<Notificacion> notificaciones = new ArrayList<Notificacion>();

    // #region constructor

    public Propietario(int ci, String password, String nombre) {
        this.ci = ci;
        this.contrsenia = password;
        this.nombreCompleto = nombre;
    }

    public Propietario(int ci, String password, String nombre, int saldoActual, int saldoMinimo,
            EstadoPropietario estado) {
        this.ci = ci;
        this.contrsenia = password;
        this.nombreCompleto = nombre;
        this.saldoActual = saldoActual;
        this.saldoMinimo = saldoMinimo;
        this.estado = estado;
        this.saldoActual = saldoActual;
        this.saldoMinimo = saldoMinimo;
        this.estado = estado;
    }

    // #endregion

    // #region getters

    public int getCi() {
        return ci;
    }

    public String getEstado() {
        return estado.toString();
    }

    public String getNombre() {
        return nombreCompleto;
    }

    public int getSaldoActual() {
        return this.saldoActual;
    }

    public List<Bonificacion> getBonificaciones() {
        return bonificaciones;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculo(Vehiculo vehiculos) {
        this.vehiculos.add(vehiculos);
    }

    public void setNotificacion(Notificacion notificacion) {
        this.notificaciones.add(notificacion);
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    // #endregion

    // #region agregarBonificacion

    public void agregarBonificacion(Bonificacion bonificacion) throws BonificacionException {
<<<<<<< HEAD
        verificarBonificacionNoNula(bonificacion);
        verificarBonificacion(bonificacion);
        bonificaciones.add(bonificacion);
    }

    private void verificarBonificacionNoNula(Bonificacion bonificacion) throws BonificacionException {
        if (bonificacion == null) {
            throw new BonificacionException("La bonificación no puede ser nula");
        }
        if (bonificacion.getPuesto() == null) {
            throw new BonificacionException("Debe especificar un puesto");
=======
        try {
            verificarBonificacion(bonificacion);
            bonificaciones.add(bonificacion);
        } catch (BonificacionException e) {
            throw e;
>>>>>>> 75d07e40df1e97477e3f35a449e5ac5559f9757b
        }
    }

    private void verificarBonificacion(Bonificacion bonificacion) throws BonificacionException {
        for (Bonificacion b : bonificaciones) {
            if (b.equals(bonificacion)) {
                throw new BonificacionException("La bonificación ya existe para este propietario.");
<<<<<<< HEAD
            }
            if (b.getPuesto() == bonificacion.getPuesto()) {
                throw new BonificacionException("Ya existe una bonificación para este puesto.");
            }
=======
            } else {
            if (b.getPuesto() == bonificacion.getPuesto()) {
                throw new BonificacionException("Ya existe una bonificación para este puesto.");
            }
            }
>>>>>>> 75d07e40df1e97477e3f35a449e5ac5559f9757b
        }
    }

    // #endregion

    // #region Verificaciones

    public void verificarDatos() throws UsuarioException {

        ciMayorA0();

        nombreDistintoANull();

        passwordDistintoANull();

        saldoMayorA0();

    }

    public void nombreDistintoANull() throws UsuarioException {
        if (nombreCompleto == null || nombreCompleto.isEmpty()) {
            throw new UsuarioException("El nombre no puede estar vacío.");
        }
    }

    public void passwordDistintoANull() throws UsuarioException {
        if (contrsenia == null || contrsenia.isEmpty()) {
            throw new UsuarioException("La contraseña no puede estar vacía.");
        }
    }

    public void saldoMayorA0() throws UsuarioException {
        if (this.saldoMinimo < 0) {
            throw new UsuarioException("El saldo mínimo no puede ser negativo.");
        }
    }

    public void ciMayorA0() throws UsuarioException {
        if (ci <= 0) {
            throw new UsuarioException("La cédula de identidad no puede ser menor o igual a cero.");
        }
    }

    public void verificarDeshabilitado() throws UsuarioException {
<<<<<<< HEAD
        if (estado.getEstado().equals("deshabilitado")) {
            throw new UsuarioException("El propietario está deshabilitado y no puede recibir bonificaciones");
=======
        if (estado.getEstado() == "deshabilitado") {
            throw new UsuarioException("Usuario deshabilitado, no puede ingresar al sistema");
>>>>>>> 75d07e40df1e97477e3f35a449e5ac5559f9757b
        }
    }

    public void verficarSaldoMayorACosto(int costo) throws UsuarioException {

        saldoMayorA0();

        if (costo < 0)
            throw new UsuarioException("El costo debe ser mayor a 0");

        if (saldoActual < costo)
            throw new UsuarioException("Saldo insuficiente" + " su saldo actual es de: " + saldoActual);
    }

    public void verificarSuspension() throws UsuarioException {
<<<<<<< HEAD
        if ("suspendido".equals(estado.getEstado()))
=======
        if (estado.getEstado() == "suspendido")
>>>>>>> 75d07e40df1e97477e3f35a449e5ac5559f9757b
            throw new UsuarioException("El propietario esta suspendido, no puede realizar transitos");
    }

    // #endregion

<<<<<<< HEAD
    // #region Cambiar estado

    public void cambiarEstado(String nuevoEstado) throws UsuarioException {
        validarEstado(nuevoEstado);
        
        String estadoActual = this.estado.getEstado();
        
        if (estadoActual.equals(nuevoEstado)) {
            throw new UsuarioException("El propietario ya está en estado " + nuevoEstado);
        }
        
        this.estado = new EstadoPropietario(nuevoEstado) {};
    }

    private void validarEstado(String estado) throws UsuarioException {
        if (estado == null || estado.trim().isEmpty()) {
            throw new UsuarioException("El estado no puede estar vacío");
        }
        
        if (!estado.equals("habilitado") && !estado.equals("deshabilitado") && !estado.equals("suspendido")) {
            throw new UsuarioException("Estado inválido. Debe ser: habilitado, deshabilitado o suspendido");
        }
    }

    // #endregion

=======
>>>>>>> 75d07e40df1e97477e3f35a449e5ac5559f9757b
    @Override
    public String toString() {
        return this.getNombre() + " - CI: " + this.getCi() + " - Estado: " + this.getEstado() + " - Saldo Actual: "
                + this.getSaldoActual();
    }

    public void ValidarContraseña(String contrasenia) throws UsuarioException {
        if (!this.contrsenia.equals(contrasenia)) {
            throw new UsuarioException("Contraseña incorrecta");
        }
    }

    public void ValidarCi(int ci2) throws UsuarioException {
        if (this.ci != ci2) {
            throw new UsuarioException("Cédula de identidad incorrecta");
        }
    }

    public void borrarNotificaciones() {
        this.notificaciones.clear();
        System.out.println("Notificaciones borradas para el propietario: " + this.getNotificaciones().size());
    }
}
