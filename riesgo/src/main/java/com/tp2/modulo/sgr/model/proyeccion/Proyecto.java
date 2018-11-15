package com.tp2.modulo.sgr.model.proyeccion;

public class Proyecto {
	
	private int idProyecto;
	private String descripcion;
	private String rubro;
	private java.util.Date fechaIncio;
	private java.util.Date fechaFin;

	
	
	public int getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}

	public java.util.Date getFechaIncio() {
		return fechaIncio;
	}

	public void setFechaIncio(java.util.Date fechaIncio) {
		this.fechaIncio = fechaIncio;
	}

	public java.util.Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(java.util.Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getDescripcion(){
		return descripcion;
	}

	public void setDescripcion(String descripcion){
		this.descripcion=descripcion;
	}

	public String getRubro(){
		return rubro;
	}

	public void setRubro(String rubro){
		this.rubro=rubro;
	}

	public java.util.Date getFechaincio(){
		return fechaIncio;
	}

	public void setFechaincio(java.util.Date fechaIncio){
		this.fechaIncio=fechaIncio;
	}

	public java.util.Date getFechafin(){
		return fechaFin;
	}

	public void setFechafin(java.util.Date fechaFin){
		this.fechaFin=fechaFin;
	}

	@Override
	public String toString() {
		return "Proyecto [idProyecto=" + idProyecto + ", descripcion=" + descripcion + ", rubro=" + rubro
				+ ", fechaIncio=" + fechaIncio + ", fechaFin=" + fechaFin + "]";
	}
	
	

}
