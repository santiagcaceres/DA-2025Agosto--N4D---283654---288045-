package ort.da.mvc.Peajes.Usuarios.Propietario.DTO;

import ort.da.mvc.Peajes.Usuarios.Propietario.Propietario;

public class PropietarioInfoDTO {

	private String nombre;
	private String estado;

	public PropietarioInfoDTO(Propietario p){
		this.nombre = p.getNombre();
		this.estado = p.getEstado();
	}

	public String getNombre(){
		return nombre;
	}

	public String getEstado(){
		return estado;
	}
}
