package ort.da.mvc.Peajes.Transito.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import ort.da.mvc.Fachada;
import ort.da.mvc.Peajes.Peaje.PuestoPeaje;
import ort.da.mvc.Peajes.Peaje.DTO.PeajeDTO;
import ort.da.mvc.Peajes.Tarifa.Tarifa;
import ort.da.mvc.Peajes.Tarifa.DTO.TarifaDTO;
import ort.da.mvc.Peajes.Usuarios.Administrador.Administrador;
import ort.da.mvc.Peajes.Usuarios.Propietario.Propietario;
import ort.da.mvc.Peajes.Utils.Respuesta;
import ort.da.mvc.Peajes.Utils.Exceptions.PuestoException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping ("/transito")
@Scope("session")
public class TransitoController {

    @GetMapping("/vistaConectada")
    public List<Respuesta> inicializarVista(@SessionAttribute(name = "administrador", required = false) Administrador a) {

        if (a == null) {
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
        }

        System.out.println();

        return Respuesta.lista(obtenerPuestos(), new Respuesta("EmularTransito", "emularTransito.html"));
    }

    private Respuesta obtenerPuestos(){
        try{
            List<PuestoPeaje> puestos = Fachada.getInstancia().getPuestos(); //PREGUNTAR SI ROMPE DIV LOGICA -- CONSULTA
            
            List<PeajeDTO> puestosDTO = new ArrayList<PeajeDTO>();  
            
            for(PuestoPeaje p : puestos){
                puestosDTO.add(new PeajeDTO(p));
            }
            
            System.out.println("Puestos obtenidos: " + puestosDTO.size());

            return new Respuesta("PuestosPeaje", puestosDTO);

        }catch(PuestoException e){
            return new Respuesta("error", e.getMessage());
        }
    }

    @GetMapping("/tarifasDePuesto")
    public List<Respuesta> tarifasDePuesto(@RequestParam String param) {
        List<TarifaDTO> tarifasDTO = new ArrayList<TarifaDTO>();
        try{
            Fachada.getInstancia().getTarifasDePuesto(param);

            for(Tarifa t : Fachada.getInstancia().getTarifasDePuesto(param)){
                tarifasDTO.add(new TarifaDTO(t));
            }

            System.out.println("Tarifas obtenidas: " + tarifasDTO.size());
            
            return Respuesta.lista(new Respuesta("TarifasDePuesto", tarifasDTO));

        }catch(PuestoException e){
            return Respuesta.lista(new Respuesta("error", e.getMessage()));
        }
    }

    @PostMapping("/emularTransito")
    public List<Respuesta> emularTransito(@RequestParam String matricula, @RequestParam String puesto, @RequestParam String fechaHora) {
        try{
            System.out.println("Emulando tránsito para matrícula: " + matricula + ",  puesto: " + puesto + ", fechaHora: " + fechaHora);
            Fachada.getInstancia().emularTransito(matricula,  puesto);
            return Respuesta.lista(new Respuesta("EmularTransito", "Tránsito emulado con éxito."));
        }catch(Exception e){
            return Respuesta.lista(new Respuesta("error", e.getMessage()));
        }
    }
    
}
