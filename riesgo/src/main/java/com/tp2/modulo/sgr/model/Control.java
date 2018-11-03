package com.tp2.modulo.sgr.model;

import java.util.Date;

public class Control {

	private int idControl;
	private String descripcion;
	private int tipo;
	private String responsable;
	private int estadoImplementacion;
	private String equipoResponsable;
	private Date fechaImplementacion;
	private double costo;
	private int RIESGO_riesgoId;
	
	public int getIdControl() {
		return idControl;
	}
	
	public void setIdControl(int idControl) {
		this.idControl = idControl;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getTipo() {
		return tipo;
	}
	
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public String getResponsable() {
		return responsable;
	}
	
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	
	public int getEstadoImplementacion() {
		return estadoImplementacion;
	}
	
	public void setEstadoImplementacion(int estadoImplementacion) {
		this.estadoImplementacion = estadoImplementacion;
	}
	
	public String getEquipoResponsable() {
		return equipoResponsable;
	}
	
	public void setEquipoResponsable(String equipoResponsable) {
		this.equipoResponsable = equipoResponsable;
	}
	
	public Date getFechaImplementacion() {
		return fechaImplementacion;
	}
	
	public void setFechaImplementacion(Date fechaImplementacion) {
		this.fechaImplementacion = fechaImplementacion;
	}
	
	public double getCosto() {
		return costo;
	}
	
	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	public int getRIESGO_riesgoId() {
		return RIESGO_riesgoId;
	}
	
	public void setRIESGO_riesgoId(int rIESGO_riesgoId) {
		RIESGO_riesgoId = rIESGO_riesgoId;
	}
}
