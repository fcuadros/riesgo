package com.tp2.modulo.sgr.model.proyeccion;

public class PaqueteTrabajo {
	
	private String nombre;
	private int idControl;
	private int PROYECTO_idProyecto;

	public String getNombre(){
		return nombre;
	}

	public void setNombre(String nombre){
		this.nombre=nombre;
	}

	public int getIdcontrol(){
		return idControl;
	}

	public void setIdcontrol(int idControl){
		this.idControl=idControl;
	}

	public int getProyecto_idproyecto(){
		return PROYECTO_idProyecto;
	}

	public void setProyecto_idproyecto(int PROYECTO_idProyecto){
		this.PROYECTO_idProyecto=PROYECTO_idProyecto;
	}

	@Override
	public String toString() {
		return "PaqueteTrabajo [nombre=" + nombre + ", idControl=" + idControl + ", PROYECTO_idProyecto="
				+ PROYECTO_idProyecto + "]";
	}
	
	

}
