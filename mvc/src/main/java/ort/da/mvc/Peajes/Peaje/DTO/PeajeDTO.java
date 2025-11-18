package ort.da.mvc.Peajes.Peaje.DTO;

import java.util.ArrayList;
import java.util.List;

import ort.da.mvc.Peajes.Peaje.PuestoPeaje;
import ort.da.mvc.Peajes.Tarifa.Tarifa;
import ort.da.mvc.Peajes.Tarifa.DTO.TarifaDTO;

public class PeajeDTO {
    private String nombre;
    private String direccion;
    private List<TarifaDTO> tarifas;

    
    public PeajeDTO(PuestoPeaje p) {
        this.nombre = p.getNombre();
        this.direccion = p.getDireccion();
        this.tarifas = convertirListaTarifasDTO(p.getTarifas());
    }

    private List<TarifaDTO> convertirListaTarifasDTO(List<Tarifa> tarifas) {
        List<TarifaDTO> listaTarifasDTO = new ArrayList<>();
        if (tarifas == null) {
            return listaTarifasDTO;
        }
        for (Tarifa t : tarifas) {
            if (t != null) {
                listaTarifasDTO.add(new TarifaDTO(t));
            }
        }
        return listaTarifasDTO;
    }

    // Getters required for JSON serialization
    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<TarifaDTO> getTarifas() {
        return tarifas;
    }
}
