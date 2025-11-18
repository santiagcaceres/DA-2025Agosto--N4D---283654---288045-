package ort.da.mvc.Peajes.Usuarios.Propietario.DTO;

import ort.da.mvc.Peajes.Usuarios.Propietario.Propietario;

public class PropietarioDTO {

	private int cedula;
	private String nombre;

	public PropietarioDTO(Propietario p){
		this.cedula = p.getCi();
		this.nombre = p.getNombre();
	}

	public int getCedula(){
		return cedula;
	}

	public String getNombre(){
		return nombre;
	}
}
