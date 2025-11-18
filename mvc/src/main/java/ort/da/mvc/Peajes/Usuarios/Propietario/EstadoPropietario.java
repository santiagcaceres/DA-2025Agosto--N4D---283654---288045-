package ort.da.mvc.Peajes.Usuarios.Propietario;

public abstract class EstadoPropietario {
    private String estado;

    public EstadoPropietario(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return estado;
    }

    public String getEstado(){
        return estado;
    }
}