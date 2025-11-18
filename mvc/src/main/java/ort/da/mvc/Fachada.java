package ort.da.mvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ort.da.mvc.Peajes.Bonificacion.Bonificacion;
import ort.da.mvc.Peajes.Peaje.PuestoPeaje;
import ort.da.mvc.Peajes.Peaje.Servicio.ServicioPeaje;
import ort.da.mvc.Peajes.Tarifa.Tarifa;
import ort.da.mvc.Peajes.Transito.Transito;
import ort.da.mvc.Peajes.Transito.Servicio.ServicioTransito;
import ort.da.mvc.Peajes.Usuarios.Administrador.Administrador;
import ort.da.mvc.Peajes.Usuarios.Administrador.Servicio.ServicioAdministrador;
import ort.da.mvc.Peajes.Usuarios.Propietario.Propietario;
import ort.da.mvc.Peajes.Usuarios.Propietario.Servicio.ServicioPropietario;
import ort.da.mvc.Peajes.Utils.ServicioAccesos;
import ort.da.mvc.Peajes.Utils.Exceptions.UsuarioException;
import ort.da.mvc.Peajes.Utils.Exceptions.VehiculoException;
import ort.da.mvc.Peajes.Utils.Exceptions.PuestoException;
import ort.da.mvc.Peajes.Vehiculo.Vehiculo;
import ort.da.mvc.Peajes.Vehiculo.Servicio.ServicioVehiculo;

public class Fachada /*extends ort.da.mvc.Peajes.Utils.Observador.Observable*/{

//#region Singleton & Servicios

    public enum Eventos{cambioListaSesiones};

    private static Fachada instancia;
    private final ServicioPropietario servicioPropietario;
    private final ServicioPeaje servicioPeaje;
    private final ServicioVehiculo servicioVehiculo;
    private final ServicioAdministrador servicioAdministrador;
    private final ServicioTransito servicioTransito;
    private final ServicioAccesos servicioAccesos;
    private final ort.da.mvc.Peajes.Bonificacion.Servicio.ServicioBonificacion servicioBonificacion;

    private Fachada(ServicioAdministrador servicioAdministrador,
                          ServicioPropietario servicioPropietario,
                          ServicioVehiculo servicioVehiculo,
                          ServicioPeaje servicioPeaje,
                          ServicioAccesos servicioAccesos,
                          ServicioTransito servicioTransito) {
        this.servicioPeaje = servicioPeaje;
        this.servicioAdministrador = servicioAdministrador;
        this.servicioPropietario = servicioPropietario;
        this.servicioVehiculo = servicioVehiculo;
        this.servicioAccesos = servicioAccesos;
        this.servicioTransito = servicioTransito;
        this.servicioBonificacion = new ort.da.mvc.Peajes.Bonificacion.Servicio.ServicioBonificacion();
    }

    private Fachada() {
        this.servicioPeaje = new ServicioPeaje();
        this.servicioAdministrador = new ServicioAdministrador();
        this.servicioPropietario = new ServicioPropietario();
        this.servicioVehiculo = new ServicioVehiculo();
        this.servicioAccesos = new ServicioAccesos();
        this.servicioBonificacion = new ort.da.mvc.Peajes.Bonificacion.Servicio.ServicioBonificacion();
        this.servicioTransito = new ServicioTransito();
    }

    public static Fachada getInstancia() {
        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }

//#endregion

//#region getters

    public ServicioPropietario getServicioPropietario() {
        return servicioPropietario;
    }

    public ort.da.mvc.Peajes.Bonificacion.Servicio.ServicioBonificacion getServicioBonificacion(){
        return servicioBonificacion;
    }

    public ServicioPeaje getServicioPeaje() {
        return servicioPeaje;
    }

    public ServicioVehiculo getServicioVehiculo() {
        return servicioVehiculo;
    }

    public ServicioAdministrador getServicioAdministrador() {
        return servicioAdministrador;
    }

//#endregion

//#region Propietario

    //#region login propietario

    public Propietario loginPropietario(int ci, String contrasenia) throws UsuarioException {
        return servicioAccesos.loginPropietario(ci, contrasenia);
    }
    
    //#endregion

    //#region crear propietario

    public String crearPropietario(Propietario propietario) {
        return servicioAccesos.agregarPropietario(propietario); // CONSULTAR YA QUE ESTO DEBERIA ESTAR EN LOS SERVICIOS DE CADA TIPO DE USUARIO
    }

    //#endregion

//#endregion

//#region administrador

    //#region login admin

    public Administrador loginAdministrador(int ci, String contrasenia) throws UsuarioException {
        System.out.println("ingreso a login admin");
        return servicioAccesos.loginAdministrador(ci, contrasenia);
    }

    //#endregion

    //#region crear admin

    public String crearAdminstrador(Administrador administrador) {
        return servicioAccesos.crearAdministrador(administrador);
    }

    //#endregion

//#endregion

//#region Peaje

    public PuestoPeaje getPuestoNombre(String nombrePuesto) throws PuestoException {
        return servicioPeaje.getPuestoNombre(nombrePuesto);
    }

    public String crearPuestoPeaje(PuestoPeaje pp2) {
        return servicioPeaje.crearPuestoPeaje(pp2);
    }

//#endregion

//#region Vehiculo


    public void crearVehiculo(Vehiculo v) {
        servicioVehiculo.crearVehiculo(v);
    }
    
    public Vehiculo buscarVehiculoPorMatricula(String matricula) throws VehiculoException {
        return servicioVehiculo.buscarVehiculoPorMatricula(matricula);
    }
    

//#endregion

//#region Transito


    public void crearTransito(Transito t) {
        servicioTransito.crearTransito(t);
    }

    public void emularTransito(String matricula, String puesto) throws PuestoException, VehiculoException {
       servicioTransito.emularTransito(matricula, puesto, null);
    }   
    
    public int getCostoEnTransitosPorVehiculo(Vehiculo vehiculo) {
        return servicioTransito.getCostoEnTransitosPorVehiculo(vehiculo);
    }

    public ArrayList<Transito> getTransitosPorPropietario(Propietario propietario) {
        return (ArrayList<Transito>) servicioTransito.getTransitosPorPropietario(propietario);
    }

    public List<Transito> getTransitosPorVehiculo(Vehiculo vehiculo) {
        return servicioTransito.getTransitosPorVehiculo(vehiculo);
    }


//#endregion

    public Object getSesionesActivasInfo() {
        return servicioAccesos.getSesionesActivasInfo();
    }

    public int calcularCosto(Vehiculo vehiculo, PuestoPeaje puestoPeaje) {
        return 00/*servicioTransito.calcularCosto(vehiculo, puestoPeaje)*/;
    }
    
//#region bonificaciones

    public ArrayList<Bonificacion> getBonificacionesDePropietario(Propietario p) {
        return servicioPropietario.getBonificacionesDePropietario(p);
    }

    public Propietario getPropietarioByCI(int ci) throws UsuarioException {
        return servicioAccesos.getPropietarioPorCI(ci);
    }

    public List<Propietario> getPropietarios(){
        return this.servicioAccesos.getPropietarios();
    }

//#endregion

//#region puestos peaje

    public List<PuestoPeaje> getPuestos() throws PuestoException {
        return servicioPeaje.getPuestos();
    }

    public List<Tarifa> getTarifasDePuesto(String param) throws PuestoException {
        return servicioPeaje.getTarifasDePuesto(param);
    }


//#endregion

}
