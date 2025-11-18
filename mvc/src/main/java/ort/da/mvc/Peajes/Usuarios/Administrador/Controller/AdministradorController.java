package ort.da.mvc.Peajes.Usuarios.Administrador.Controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import ort.da.mvc.ConexionNavegador;
import ort.da.mvc.Fachada;
import ort.da.mvc.Observador.Observable;
import ort.da.mvc.Observador.Observador;
import ort.da.mvc.Peajes.Usuarios.Administrador.Administrador;
import ort.da.mvc.Peajes.Utils.Respuesta;

@RestController
@RequestMapping ("/administrador")
@Scope("session")
public class AdministradorController implements Observador {

    private Administrador administrador;
    
    //#region observador SSE

    
    private final ConexionNavegador conexionNavegador;

    public AdministradorController(@Autowired ConexionNavegador conexionNavegador) {
        this.conexionNavegador = conexionNavegador;
    }

    @GetMapping(value = "/registrarSSE", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter registrarSSE() {
        conexionNavegador.conectarSSE();
        return conexionNavegador.getConexionSSE();

    }

    @Override
    public void actualizar(Object evento, Observable origen) {
        if (evento.equals(Fachada.Eventos.cambioListaSesiones)) {
            conexionNavegador.enviarJSON(Respuesta.lista(sesiones()));
            System.out.println("LISTA SESIONES!!!");
        }
    }
    
    //#endregion

    @GetMapping("/vistaConectada")
    public Respuesta inicializarVista(@SessionAttribute(name = "administrador", required = false) Administrador a) {

        if (a == null) {
            return new Respuesta("usuarioNoAutenticado", "index.html");
        }

        administrador = a;

        return new Respuesta("sesionesActivas", Fachada.getInstancia().getSesionesActivasInfo());
    }

    public Respuesta sesiones() {
        return new Respuesta("sesionesActivas", Fachada.getInstancia().getSesionesActivasInfo()); //   CAMBIAR ESTO
    }

<<<<<<< HEAD
    @GetMapping("/cambiarEstado")
    public Respuesta cambiarEstado(@SessionAttribute(name = "administrador", required = false) Administrador a) {
        if (a == null) {
            return new Respuesta("usuarioNoAutenticado", "index.html");
        }
        return new Respuesta("redirigirCambiarEstado", "cambiarEstadoPropietario.html");
    }

=======
>>>>>>> 75d07e40df1e97477e3f35a449e5ac5559f9757b
}
