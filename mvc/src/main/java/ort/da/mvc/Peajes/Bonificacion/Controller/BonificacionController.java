package ort.da.mvc.Peajes.Bonificacion.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import ort.da.mvc.Fachada;
import ort.da.mvc.Peajes.Bonificacion.Bonificacion;
import ort.da.mvc.Peajes.Bonificacion.BonificacionExonerada;
import ort.da.mvc.Peajes.Bonificacion.BonificacionFrecuente;
import ort.da.mvc.Peajes.Bonificacion.BonificacionTrabajdor;
<<<<<<< HEAD
import ort.da.mvc.Peajes.Bonificacion.DTO.BonificacionDTO;
=======
>>>>>>> 75d07e40df1e97477e3f35a449e5ac5559f9757b
import ort.da.mvc.Peajes.Bonificacion.DTO.BonificacionDisponibleDTO;
import ort.da.mvc.Peajes.Peaje.PuestoPeaje;
import ort.da.mvc.Peajes.Peaje.DTO.PeajeDTO;
import ort.da.mvc.Peajes.Usuarios.Administrador.Administrador;
import ort.da.mvc.Peajes.Usuarios.Propietario.DTO.PropietarioDTO;
<<<<<<< HEAD
import ort.da.mvc.Peajes.Usuarios.Propietario.DTO.PropietarioInfoDTO;
import ort.da.mvc.Peajes.Usuarios.Propietario.Propietario;
import ort.da.mvc.Peajes.Notificacion.Notificacion;
=======
import ort.da.mvc.Peajes.Usuarios.Propietario.Propietario;
>>>>>>> 75d07e40df1e97477e3f35a449e5ac5559f9757b
import ort.da.mvc.Peajes.Utils.Respuesta;
import ort.da.mvc.Peajes.Utils.Exceptions.BonificacionException;
import ort.da.mvc.Peajes.Utils.Exceptions.PuestoException;
import ort.da.mvc.Peajes.Utils.Exceptions.UsuarioException;

@RestController
@RequestMapping("/bonificacion")
@Scope("session")
public class BonificacionController {

    @GetMapping("/vistaConectada")
    public List<Respuesta> inicializarVista(@SessionAttribute(name = "administrador", required = false) Administrador a) {

        if (a == null) {
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
        }

        List<Respuesta> respuestas = new ArrayList<>();

        // Bonificaciones definidas (disponibles)
        List<Bonificacion> definidas = Fachada.getInstancia().getServicioBonificacion().getDefinidas();
        List<BonificacionDisponibleDTO> disponibles = new ArrayList<>();
        for (int i = 0; i < definidas.size(); i++) {
            Bonificacion b = definidas.get(i);
            disponibles.add(new BonificacionDisponibleDTO(String.valueOf(i), b.getNombre()));
        }

        respuestas.add(new Respuesta("BonificacionesDisponibles", disponibles));

        // Puestos de peaje
        try {
            List<PuestoPeaje> puestos = Fachada.getInstancia().getPuestos();
            List<PeajeDTO> puestosDTO = new ArrayList<>();
            for (PuestoPeaje p : puestos) {
                puestosDTO.add(new PeajeDTO(p));
            }
            respuestas.add(new Respuesta("PuestosPeaje", puestosDTO));
        } catch (PuestoException e) {
            respuestas.add(new Respuesta("error", e.getMessage()));
        }

        // Propietarios
        List<PropietarioDTO> propietariosDTO = new ArrayList<>();
        for (Propietario p : Fachada.getInstancia().getPropietarios()) {
            propietariosDTO.add(new PropietarioDTO(p));
        }
        respuestas.add(new Respuesta("Propietarios", propietariosDTO));

        return respuestas;
    }

    @GetMapping("/cargarBonificaciones")
    public List<Respuesta> cargarBonificaciones(@RequestParam String cedulaPropietario) {
        try {
            int ci = Integer.parseInt(cedulaPropietario);
            Propietario p = Fachada.getInstancia().getPropietarioByCI(ci);

            List<Bonificacion> bonis = Fachada.getInstancia().getBonificacionesDePropietario(p);
<<<<<<< HEAD
            List<BonificacionDTO> bDTO = new ArrayList<>();
            for (Bonificacion b : bonis) {
                bDTO.add(new BonificacionDTO(b));
            }

            PropietarioInfoDTO infoPropietario = new PropietarioInfoDTO(p);
            
            return Respuesta.lista(
                new Respuesta("BonificacionesAsignadas", bDTO), 
                new Respuesta("PropietarioInfo", infoPropietario)
            );
        } catch (UsuarioException e) {
            return Respuesta.lista(new Respuesta("error", e.getMessage()));
        } catch (NumberFormatException e) {
            return Respuesta.lista(new Respuesta("error", "Formato de cédula inválido"));
        } catch (Exception e) {
            return Respuesta.lista(new Respuesta("error", "Error al buscar propietario: " + e.getMessage()));
=======
            List<ort.da.mvc.Peajes.Bonificacion.DTO.BonificacionDTO> bDTO = new ArrayList<>();
            for (Bonificacion b : bonis) {
                bDTO.add(new ort.da.mvc.Peajes.Bonificacion.DTO.BonificacionDTO(b));
            }

            return Respuesta.lista(new Respuesta("BonificacionesAsignadas", bDTO), new Respuesta("PropietarioInfo", p.toString()));
        } catch (UsuarioException e) {
            return Respuesta.lista(new Respuesta("error", e.getMessage()));
>>>>>>> 75d07e40df1e97477e3f35a449e5ac5559f9757b
        }
    }

    @PostMapping("/asignarBonificacion")
<<<<<<< HEAD
    public List<Respuesta> asignarBonificacion(
            @RequestParam String cedula, 
            @RequestParam String idBonificacion, 
            @RequestParam String puesto, 
            @RequestParam(required = false) String fechaInicio, 
            @RequestParam(required = false) String hora) {

        try {
            int ci = Integer.parseInt(cedula);
            Propietario p = Fachada.getInstancia().getPropietarioByCI(ci);

            // Verificar que el propietario no esté deshabilitado
            p.verificarDeshabilitado();

            // Obtener bonificación de las definidas
            int idx = Integer.parseInt(idBonificacion);
            List<Bonificacion> definidas = Fachada.getInstancia().getServicioBonificacion().getDefinidas();
            
            if (idx < 0 || idx >= definidas.size()) {
                throw new BonificacionException("Bonificación no encontrada");
=======
    public List<Respuesta> asignarBonificacion(@RequestParam String cedula, @RequestParam(required = false) String idBonificacion, @RequestParam(required = false) String puesto, @RequestParam(required = false) String fechaInicio, @RequestParam(required = false) String hora) {

        try {
            if (idBonificacion == null || idBonificacion.isEmpty()) {
                return Respuesta.lista(new Respuesta("error", "Debe especificar una bonificación"));
            }

            if (puesto == null || puesto.isEmpty() || puesto.equals("-1")) {
                return Respuesta.lista(new Respuesta("error", "Debe especificar un puesto"));
            }

            int ci = Integer.parseInt(cedula);
            Propietario p = Fachada.getInstancia().getPropietarioByCI(ci);

            // Build date from fechaInicio + hora when provided
            Date fechaAsignacion = new Date(System.currentTimeMillis());
            // TODO: parse fechaInicio + hora if provided.

            // Identify the type from definidas
            int idx = Integer.parseInt(idBonificacion);
            List<Bonificacion> definidas = Fachada.getInstancia().getServicioBonificacion().getDefinidas();
            if (idx < 0 || idx >= definidas.size()) {
                return Respuesta.lista(new Respuesta("error", "Bonificación no encontrada"));
>>>>>>> 75d07e40df1e97477e3f35a449e5ac5559f9757b
            }

            Bonificacion plantilla = definidas.get(idx);

<<<<<<< HEAD
            // Obtener puesto
            PuestoPeaje pSeleccionado = Fachada.getInstancia().getPuestoNombre(puesto);

            // TODO: parsear fechaInicio + hora si se proporciona
            Date fechaAsignacion = new Date(System.currentTimeMillis());

            // Crear nueva bonificación del mismo tipo que la plantilla
            Bonificacion nueva;
=======
            // Get puesto
            PuestoPeaje pSeleccionado = Fachada.getInstancia().getPuestoNombre(puesto);

            Bonificacion nueva;
            // Create same subclass
>>>>>>> 75d07e40df1e97477e3f35a449e5ac5559f9757b
            if (plantilla instanceof BonificacionExonerada) {
                nueva = new BonificacionExonerada(pSeleccionado, plantilla.getNombre(), fechaAsignacion);
            } else if (plantilla instanceof BonificacionFrecuente) {
                nueva = new BonificacionFrecuente(pSeleccionado, plantilla.getNombre(), fechaAsignacion);
            } else if (plantilla instanceof BonificacionTrabajdor) {
                nueva = new BonificacionTrabajdor(pSeleccionado, plantilla.getNombre(), fechaAsignacion);
            } else {
<<<<<<< HEAD
                nueva = plantilla;
            }

            // Agregar bonificación (aquí se valida en el dominio)
=======
                nueva = plantilla; // fallback (shouldn't happen)
            }

            // Verificar si el propietario esta deshabilitado
            p.verificarDeshabilitado();

>>>>>>> 75d07e40df1e97477e3f35a449e5ac5559f9757b
            p.agregarBonificacion(nueva);

            return Respuesta.lista(new Respuesta("BonificacionAsignada", "Bonificación asignada con éxito."));

<<<<<<< HEAD
        } catch (UsuarioException | PuestoException | BonificacionException e) {
            return Respuesta.lista(new Respuesta("error", e.getMessage()));
        } catch (NumberFormatException e) {
            return Respuesta.lista(new Respuesta("error", "Formato de datos inválido"));
        } catch (Exception e) {
            return Respuesta.lista(new Respuesta("error", "Error al asignar bonificación: " + e.getMessage()));
        }
    }

    @PostMapping("/cambiarEstadoPropietario")
    public List<Respuesta> cambiarEstadoPropietario(
            @RequestParam String cedula,
            @RequestParam String nuevoEstado) {
        
        try {
            int ci = Integer.parseInt(cedula);
            Propietario p = Fachada.getInstancia().getPropietarioByCI(ci);
            
            // Guardar estado actual antes del cambio
            String estadoActual = p.getEstado();
            
            // Cambiar estado
            p.cambiarEstado(nuevoEstado);
            
            // Registrar notificación al propietario
            String mensajeNotificacion = "Se ha cambiado el estado de tu cuenta. Estado anterior: " + 
                                        estadoActual + ". Estado actual: " + nuevoEstado + ".";
            Notificacion notificacion = new Notificacion(mensajeNotificacion, new Date());
            p.setNotificacion(notificacion);
            
            return Respuesta.lista(
                new Respuesta("ResultadoCambioEstado", "Estado cambiado exitosamente a: " + nuevoEstado)
            );
            
        } catch (UsuarioException e) {
            return Respuesta.lista(new Respuesta("error", e.getMessage()));
        } catch (NumberFormatException e) {
            return Respuesta.lista(new Respuesta("error", "Formato de cédula inválido"));
        } catch (Exception e) {
            return Respuesta.lista(new Respuesta("error", "Error al cambiar estado: " + e.getMessage()));
=======
        } catch (UsuarioException e) {
            return Respuesta.lista(new Respuesta("error", e.getMessage()));
        } catch (PuestoException e) {
            return Respuesta.lista(new Respuesta("error", e.getMessage()));
        } catch (BonificacionException e) {
            return Respuesta.lista(new Respuesta("error", e.getMessage()));
        } catch (Exception e) {
            return Respuesta.lista(new Respuesta("error", e.getMessage()));
>>>>>>> 75d07e40df1e97477e3f35a449e5ac5559f9757b
        }
    }

}
