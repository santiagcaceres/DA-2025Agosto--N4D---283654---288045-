package ort.da.mvc.Peajes.Bonificacion.DTO;

public class BonificacionDisponibleDTO {
    private String id;
    private String nombre;

    public BonificacionDisponibleDTO(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
}
