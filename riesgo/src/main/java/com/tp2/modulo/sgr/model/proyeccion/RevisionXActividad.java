package com.tp2.modulo.sgr.model.proyeccion;

import java.util.Date;

public class RevisionXActividad {

	private Revision revision;
	private Actividad actividad;

	private Date fechaPlanificada;
	private double variacionCronograma;
	private double variacionCosto;
	private String descripcionPronostico;
	
	public Revision getRevision() {
		return revision;
	}
	public void setRevision(Revision revision) {
		this.revision = revision;
	}
	public Actividad getActividad() {
		return actividad;
	}
	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	public Date getFechaPlanificada() {
		return fechaPlanificada;
	}
	public void setFechaPlanificada(Date fechaPlanificada) {
		this.fechaPlanificada = fechaPlanificada;
	}
	public double getVariacionCronograma() {
		return variacionCronograma;
	}
	public void setVariacionCronograma(double variacionCronograma) {
		this.variacionCronograma = variacionCronograma;
	}
	public double getVariacionCosto() {
		return variacionCosto;
	}
	public void setVariacionCosto(double variacionCosto) {
		this.variacionCosto = variacionCosto;
	}
	public String getDescripcionPronostico() {
		return descripcionPronostico;
	}
	public void setDescripcionPronostico(String descripcionPronostico) {
		this.descripcionPronostico = descripcionPronostico;
	}
	
	
	

}
