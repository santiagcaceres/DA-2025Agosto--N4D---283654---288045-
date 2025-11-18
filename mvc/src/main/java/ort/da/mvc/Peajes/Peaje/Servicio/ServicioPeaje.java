package ort.da.mvc.Peajes.Peaje.Servicio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ort.da.mvc.Peajes.Peaje.PuestoPeaje;
import ort.da.mvc.Peajes.Tarifa.Tarifa;
import ort.da.mvc.Peajes.Usuarios.Administrador.Administrador;
import ort.da.mvc.Peajes.Utils.Exceptions.PuestoException;
import ort.da.mvc.Peajes.Utils.Exceptions.UsuarioException;

public class ServicioPeaje {
    private List<PuestoPeaje> puestos;

    public ServicioPeaje() {
        this.puestos = new ArrayList<>();
    }

    public PuestoPeaje getPuestoNombre(String nombrePuesto) throws PuestoException {
        if (puestos.isEmpty())
            throw new PuestoException("No hay puestos registrados");
        for (PuestoPeaje puestoPeaje : puestos) {
            if (nombrePuesto.equals(puestoPeaje.getNombre())) {
                return puestoPeaje;
            }
        }
        throw new PuestoException("No hay un puesto con ese nombre"); // preguntar profe

    }

    public void emularTransito(PuestoPeaje puesto, Date fecha, String matricula) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'emularTransito'");
    }

    public String crearPuestoPeaje(PuestoPeaje pp) {
        try {
            //HAY QUE VERIFICAR QUE NO EXISTA OTRO PUESTO CON EL MISMO NOMBRE
            pp.verificarDatos();
            puestos.add(pp);
        } catch (PuestoException e) {
            return e.getMessage();
        }
        return "Puesto de peaje creado con éxito.";
    }

    public List<PuestoPeaje> getPuestos() throws PuestoException {
        if (puestos.size() > 0) {
            return puestos;
        }
        throw new PuestoException("No hay puestos registrados");
    }

    public List<Tarifa> getTarifasDePuesto(String param) throws PuestoException {

        for(PuestoPeaje p : puestos){
            if(p.getNombre().equals(param)){
                if(p.getTarifas().size() > 0){
                    return p.getTarifas();
                }
                throw new PuestoException("no hay tartifas registradas para este puesto");
            }
        }
        throw new PuestoException("No se encontro el puesto solicitado");
    }

}
