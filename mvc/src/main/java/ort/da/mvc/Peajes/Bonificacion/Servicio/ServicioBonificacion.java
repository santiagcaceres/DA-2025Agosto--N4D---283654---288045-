package ort.da.mvc.Peajes.Bonificacion.Servicio;

import java.util.ArrayList;
import java.util.List;

import ort.da.mvc.Peajes.Bonificacion.Bonificacion;

public class ServicioBonificacion {

    private List<Bonificacion> definidas;

    public ServicioBonificacion(){
        this.definidas = new ArrayList<>();
    }

    public List<Bonificacion> getDefinidas(){
        return definidas;
    }

    public void agregarBonificacionDefinida(Bonificacion b){
        definidas.add(b);
    }
}
