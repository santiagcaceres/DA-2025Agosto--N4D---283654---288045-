package ort.da.mvc.Peajes.Tarifa.DTO;

import ort.da.mvc.Peajes.Tarifa.Tarifa;

public class TarifaDTO {
    private int monto;

    public TarifaDTO(Tarifa t) {
        this.monto = t.getMonto();
    }

    // Getter for serialization
    public int getMonto() {
        return monto;
    }

}
