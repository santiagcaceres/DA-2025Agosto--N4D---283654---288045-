package ort.da.mvc.Peajes.Utils;

import java.util.Date;
import java.util.Set;

import ort.da.mvc.Fachada;
import ort.da.mvc.Peajes.Bonificacion.Bonificacion;
import ort.da.mvc.Peajes.Bonificacion.BonificacionExonerada;
import ort.da.mvc.Peajes.Bonificacion.BonificacionFrecuente;
import ort.da.mvc.Peajes.Bonificacion.BonificacionTrabajdor;
import ort.da.mvc.Peajes.Notificacion.Notificacion;
import ort.da.mvc.Peajes.Peaje.PuestoPeaje;
import ort.da.mvc.Peajes.Tarifa.Tarifa;
import ort.da.mvc.Peajes.Transito.Transito;
import ort.da.mvc.Peajes.Usuarios.Administrador.Administrador;
import ort.da.mvc.Peajes.Usuarios.Propietario.EstadoPropietario;
import ort.da.mvc.Peajes.Usuarios.Propietario.Propietario;
import ort.da.mvc.Peajes.Utils.Exceptions.BonificacionException;
import ort.da.mvc.Peajes.Vehiculo.CategoriaVehiculo;
import ort.da.mvc.Peajes.Vehiculo.Vehiculo;

public class PrecargaDeDatos {

    private Fachada f = Fachada.getInstancia();

    public void ejecutarPrecarga() {

        // #region Propietarios

        Propietario p1 = new Propietario(1234567, "juan123", "Juan Perez", 3200, 500,
                new EstadoPropietario("habilitado") {
                });
        Propietario p2 = new Propietario(2345678, "ana234", "Ana Gomez", 4000, 500,
                new EstadoPropietario("habilitado") {
                });
        Propietario p3 = new Propietario(3456789, "luis345", "Luis Rodriguez", 1300, 500,
                new EstadoPropietario("deshabilitado") {
                });
        Propietario p4 = new Propietario(23456789, "prop.123", "Usuario Propietario", 2000, 500,
                new EstadoPropietario("deshabilitado") {
                });

        // #endregion

        //#region notificaciones

        Notificacion n1 = new Notificacion("Se ha agregado saldo a su cuenta.", new Date());
        p1.setNotificacion(n1);

        //#endregion

        // #region Puesto Peaje

        PuestoPeaje pp1 = new PuestoPeaje("Puesto 1", "Calle 123");
        System.out.println("puesto1 - " + f.crearPuestoPeaje(pp1));
        PuestoPeaje pp2 = new PuestoPeaje("Puesto 2", "Calle 456");
        System.out.println("puesto2 - " + f.crearPuestoPeaje(pp2));
        PuestoPeaje pp3 = new PuestoPeaje("Puesto 3", "Calle 789");
        System.out.println("puesto3 - " + f.crearPuestoPeaje(pp3));

        // #endregion

        //#region Tarifas

        
        Tarifa ta1 = new Tarifa(400);
        pp1.setTarifas(ta1);
        System.out.println("tarifa1 - " + ta1.toString());
        Tarifa ta2 = new Tarifa(250);
        pp2.setTarifas(ta2);
        System.out.println("tarifa2 - " + ta2.toString());
        Tarifa ta3 = new Tarifa(150);        
        pp3.setTarifas(ta3);
        System.out.println("tarifa3 - " + ta3.toString());   
        Tarifa ta4 = new Tarifa(600);
        pp1.setTarifas(ta4);
        System.out.println("tarifa4 - " + ta4.toString());
        Tarifa ta5 = new Tarifa(350);
        pp2.setTarifas(ta5);
        System.out.println("tarifa5 - " + ta5.toString());
        Tarifa ta6 = new Tarifa(200);        
        pp3.setTarifas(ta6);
        System.out.println("tarifa6 - " + ta6.toString());

        //#endregion

        // #region Bonificaciones

            Bonificacion b1 = new BonificacionExonerada(pp1, "Bonificacion A", new Date());
            System.out.println("boni1 - " + b1.toString());

            Bonificacion b2 = new BonificacionFrecuente(pp2, "Bonificacion B", new Date());
            System.out.println("boni2 - " + b2.toString());

            Bonificacion b3 = new BonificacionTrabajdor(pp3, "Bonificacion C", new Date());
            System.out.println("boni3 - " + b3.toString());

            Bonificacion b4 = new BonificacionExonerada(pp1, "Bonificacion D", new Date());
            System.out.println("boni4 - " + b4.toString());

            Bonificacion b5 = new BonificacionFrecuente(pp2, "Bonificacion F", new Date());
            System.out.println("boni5 - " + b5.toString());

            Bonificacion b6 = new BonificacionTrabajdor(pp3, "Bonificacion G", new Date());
            System.out.println("boni6 - " + b6.toString());

        try {
            // registrar bonificaciones definidas
            f.getServicioBonificacion().agregarBonificacionDefinida(b1);
            f.getServicioBonificacion().agregarBonificacionDefinida(b2);
            f.getServicioBonificacion().agregarBonificacionDefinida(b3);
            f.getServicioBonificacion().agregarBonificacionDefinida(b4);
            f.getServicioBonificacion().agregarBonificacionDefinida(b5);
            f.getServicioBonificacion().agregarBonificacionDefinida(b6);
            System.out.println("Asignando bonificaciones...");
            p1.agregarBonificacion(b2);
            System.out.println("Bonificación 2 asignada correctamente.");
            p1.agregarBonificacion(b1);
            System.out.println("Bonificación 1 asignada correctamente.");
            p2.agregarBonificacion(b3);
            System.out.println("Bonificación 3 asignada correctamente.");
            p2.agregarBonificacion(b4);
            System.out.println("Bonificación 4 asignada correctamente.");
            p3.agregarBonificacion(b5);
            System.out.println("Bonificación 5 asignada correctamente.");
            p3.agregarBonificacion(b6);
            System.out.println("Bonificación 6 asignada correctamente.");
            p4.agregarBonificacion(b1);
            System.out.println("Bonificación 1 asignada correctamente.");
            p4.agregarBonificacion(b6);
        } catch (BonificacionException e) {
            System.out.println(e.getMessage());
        }

        // #endregion

        // #region Administrador

        Administrador admin1 = new Administrador(12345678, "admin.123", "Usuario Administrador");
        f.crearAdminstrador(admin1);
        System.out.println("admin1 - " + admin1.toString());
        Administrador admin2 = new Administrador(33442123, "admin.233", "Usuario Administrador 2");
        f.crearAdminstrador(admin2);
        System.out.println("admin2 - " + admin2.toString());

        // #endregion

        // #region Vehículos

        Vehiculo v1 = new Vehiculo("ABC123", "Toyota Corolla", "Rojo", new CategoriaVehiculo("Automóvil"), p1);
        f.crearVehiculo(v1);
        p1.setVehiculo(v1);
        System.out.println("vehiculo1 - " + v1.toString());
        Vehiculo v2 = new Vehiculo("DEF456", "Honda Civic", "Azul", new CategoriaVehiculo("Automóvil"), p2);
        f.crearVehiculo(v2);
        p2.setVehiculo(v2);
        System.out.println("vehiculo2 - " + v2.toString());
        Vehiculo v3 = new Vehiculo("GHI789", "Yamaha YZF-R3", "Negro", new CategoriaVehiculo("Moto"), p3);
        f.crearVehiculo(v3);
        p3.setVehiculo(v3);
        System.out.println("vehiculo3 - " + v3.toString());
        Vehiculo v4 = new Vehiculo("JKL012", "Ford F-150", "Blanco", new CategoriaVehiculo("Camión"), p1);
        f.crearVehiculo(v4);
        p4.setVehiculo(v4);
        System.out.println("vehiculo4 - " + v4.toString());
        Vehiculo v5 = new Vehiculo("MNO345", "Chevrolet Spark", "Amarillo", new CategoriaVehiculo("Automóvil"), p2);    
        f.crearVehiculo(v5);
        p4.setVehiculo(v5);
        System.out.println("vehiculo5 - " + v5.toString());
        Vehiculo v6 = new Vehiculo("PQR678", "Ducati Monster", "Rojo", new CategoriaVehiculo("Moto"), p3);
        f.crearVehiculo(v6);
        p1.setVehiculo(v6);
        System.out.println("vehiculo6 - " + v6.toString());

        // #endregion
        
        //#region Transitos

        Transito t1 = new Transito( v1, new Date(), 280);
        f.crearTransito(t1);
        System.out.println("transito1 - " + t1.toString());
        Transito t2 = new Transito( v2, new Date(), 150);
        f.crearTransito(t2);
        System.out.println("transito2 - " + t2.toString());
        Transito t3 = new Transito( v3, new Date(), 90);
        f.crearTransito(t3);
        System.out.println("transito3 - " + t3.toString());
        Transito t4 = new Transito( v4, new Date(), 500);
        f.crearTransito(t4);
        System.out.println("transito4 - " + t4.toString());
        Transito t5 = new Transito( v5, new Date(), 200);
        f.crearTransito(t5);
        System.out.println("transito5 - " + t5.toString());
        Transito t6 = new Transito( v6, new Date(), 120);
        f.crearTransito(t6);
        System.out.println("transito6 - " + t6.toString());
        Transito t7 = new Transito( v1, new Date(), 300);
        f.crearTransito(t7);
        System.out.println("transito7 - " + t7.toString());
        Transito t8 = new Transito( v2, new Date(), 160);
        f.crearTransito(t8);
        System.out.println("transito8 - " + t8.toString());
        Transito t9 = new Transito( v3, new Date(), 100);
        f.crearTransito(t9);
        System.out.println("transito9 - " + t9.toString());

        //#endregion

        f.crearPropietario(p1);
        System.out.println("prop1 - " + p1.toString());
        f.crearPropietario(p2);
        System.out.println("prop2 - " + p2.toString());
        f.crearPropietario(p3);
        System.out.println("prop3 - " + p3.toString());
        f.crearPropietario(p4);
        System.out.println("prop4 - " + p4.toString());

    }
}
