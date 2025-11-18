package ort.da.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ort.da.mvc.Peajes.Utils.PrecargaDeDatos;

@SpringBootApplication
public class OblDdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OblDdaApplication.class, args);
		PrecargaDeDatos precarga = new PrecargaDeDatos();
		precarga.ejecutarPrecarga();
	}

}