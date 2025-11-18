package ort.da.mvc.Peajes.Bonificacion.Servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import ort.da.mvc.Peajes.Bonificacion.BonificacionExonerada;
import ort.da.mvc.Peajes.Peaje.PuestoPeaje;

class ServicioBonificacionTest {

    @Test
    void agregarYListarBonificaciones() {
        ServicioBonificacion sb = new ServicioBonificacion();
        PuestoPeaje p = new PuestoPeaje("P", "C");

        BonificacionExonerada b = new BonificacionExonerada(p, "Test", new Date());

        sb.agregarBonificacionDefinida(b);

        assertEquals(1, sb.getDefinidas().size());
        assertEquals("Test", sb.getDefinidas().get(0).getNombre());
    }

}
