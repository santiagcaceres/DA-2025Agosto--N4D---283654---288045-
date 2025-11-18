package ort.da.mvc.Peajes.Usuarios.Propietario.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import ort.da.mvc.Fachada;
import ort.da.mvc.Peajes.Bonificacion.Bonificacion;
import ort.da.mvc.Peajes.Bonificacion.DTO.BonificacionDTO;
import ort.da.mvc.Peajes.Notificacion.Notificacion;
import ort.da.mvc.Peajes.Notificacion.DTO.NotificacionDTO;
import ort.da.mvc.Peajes.Transito.Transito;
import ort.da.mvc.Peajes.Transito.TransitoDTO.TransitoDTO;
import ort.da.mvc.Peajes.Usuarios.Propietario.Propietario;
import ort.da.mvc.Peajes.Utils.Respuesta;
//import ort.da.mvc.Peajes.Utils.Observador.Observable;
//import ort.da.mvc.Peajes.Utils.Observador.Observador;
import ort.da.mvc.Peajes.Utils.Sesion.Sesion;
import ort.da.mvc.Peajes.Vehiculo.Vehiculo;
import ort.da.mvc.Peajes.Vehiculo.DTO.VehiculoDTO;

@RestController
@RequestMapping("/Propietario")
@Scope("session")
public class PropietarioController {

    private Propietario propietario;
    private List<Bonificacion> bonificacionesPropietario = new ArrayList<>();

    /*
     * private final ConexionNavegador conexionNavegador;
     * 
     * public PropietarioController(@Autowired ConexionNavegador conexionNavegador)
     * {
     * this.conexionNavegador = conexionNavegador;
     * }
     * 
     * @GetMapping(value = "/registrarSSE", produces =
     * MediaType.TEXT_EVENT_STREAM_VALUE)
     * public SseEmitter registrarSSE() {
     * conexionNavegador.conectarSSE();
     * return conexionNavegador.getConexionSSE();
     * 
     * }
     */

    @GetMapping("/vistaConectada")
    public List<Respuesta> inicializarVista(@SessionAttribute(name = "propietario", required = false) Propietario p) {

        if (p == null) {
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
        }

        propietario = p;
        return Respuesta.lista(nombrePropietario(), estadoPropietario(), saldoActualPropietario(),
            bonificacionesPropietario(), vehiculoPropietario(), notificacionesPropietario());

    }

    // #region Parametros

    private Respuesta nombrePropietario() {
        return new Respuesta("nombrePropietario", propietario.getNombre());
    }

    private Respuesta estadoPropietario() {
        return new Respuesta("estadoPropietario", propietario.getEstado());
    }

    private Respuesta saldoActualPropietario() {
        return new Respuesta("saldoActualPropietario", propietario.getSaldoActual());
    }

    private Respuesta bonificacionesPropietario() {

        bonificacionesPropietario = propietario.getBonificaciones();

        List<BonificacionDTO> bonificacionesDTO = new ArrayList<BonificacionDTO>();

        for (Bonificacion b : bonificacionesPropietario) {
            bonificacionesDTO.add(new BonificacionDTO(b));
        }

        System.out.println("Bonificaciones del propietario: " + bonificacionesDTO.size());

        return new Respuesta("BonificacionesPropietario", bonificacionesDTO);
    }

    private Respuesta notificacionesPropietario() {

        //FALTA ORDENARLAS POR FECHA DESCENDENTE -- CONSULTA

        List<Notificacion> notificacionesPropietario = propietario.getNotificaciones();

        List<NotificacionDTO> notificacionesDTO = new ArrayList<NotificacionDTO>();

        for (Notificacion n : notificacionesPropietario) {
            notificacionesDTO.add(new NotificacionDTO(n));
        }

        System.out.println("notificaciones del propietario: " + notificacionesDTO.size());

        return new Respuesta("NotificacionesPropietario", notificacionesDTO);
    }

    private Respuesta vehiculoPropietario() {

        List<Vehiculo> vehiculosPropietario = propietario.getVehiculos();

        List<VehiculoDTO> vehiculosDTO = new ArrayList<VehiculoDTO>();

        for (Vehiculo v : vehiculosPropietario) {
            int Suma = Fachada.getInstancia().getCostoEnTransitosPorVehiculo(v);
            int cant = Fachada.getInstancia().getTransitosPorVehiculo(v).size();
            System.out.println("Suma: " + Suma + " Cantidad: " + cant);
            vehiculosDTO.add(new VehiculoDTO(v, cant, Suma));
        }

        System.out.println("Vehiculos del propietario: " + vehiculosDTO);

        return new Respuesta("VehiculosPropietario", vehiculosDTO);

    }

    private Respuesta transitoPropietario() {   
        
        //CONSULTAR SI NO ROMPE LA DIVISION LOGICA LLAMAR A FACHADA AQUI -- CONSULTA

        // HAY QUE ORDENAR POR FECHA DESCENDENTE, PREGUNTAR .SORT() -- CONSULTA

        //HAY QUE AGREGAR MONTO DE LA BONIFICACION 

        List<Transito> transitosPropietario = Fachada.getInstancia().getTransitosPorPropietario(propietario);

        List<TransitoDTO> transitosDTO = new ArrayList<TransitoDTO>();

        for (Transito t : transitosPropietario) {
            transitosDTO.add(new TransitoDTO(t));
        }

        System.out.println("Transitos del propietario: " + transitosDTO);

        return new Respuesta("TransitosPropietario", "transitosDTO");

    }

    // #endregion

    //#region Borrar notificaciones

    @PostMapping("/borrarNotificaciones")
    public List<Respuesta> borrarNotificaciones(@SessionAttribute(name = "propietario", required = false) Propietario p) {
        if (p == null) {
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
        }
        p.borrarNotificaciones();
        return Respuesta.lista(new Respuesta("notificacionesBorradas", "No hay notificaciones para borrar"));
    }

    //#endregion


    @GetMapping("/TableroDeControlPropietrario")
    public List<Respuesta> TableroDeControl(@SessionAttribute(name = "propietario", required = false) Sesion s) {
        if (s == null) {
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
        }
        return Respuesta.lista(new Respuesta("nombreCompleto", s.getUsuario()));
    }

    public Respuesta sesiones() {
        return new Respuesta("sesiones", Fachada.getInstancia().getSesionesActivasInfo()); // CAMBIAR ESTO
    }

    /*
     * @Override
     * public void actualizar(Object evento, Observable origen) {
     * if (evento.equals(Fachada.Eventos.cambioListaSesiones)) {
     * conexionNavegador.enviarJSON(Respuesta.lista(sesiones()));
     * System.out.println("LISTA SESIONES!!!");
     * }
     * }
     */

}
