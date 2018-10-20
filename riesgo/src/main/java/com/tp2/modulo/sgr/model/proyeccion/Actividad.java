package com.tp2.modulo.sgr.model.proyeccion;

public class Actividad {
	
	private int idActividad;
	private String nombre;
	private String Responsable;
	private java.util.Date fechaInicio;
	private java.util.Date fechaFin;
	private double duracion;
	private double porcentajeAvance;
	private int PAQUETE_TRABAJO_idPaqueteTrabajo;
	private double valorPlanificado;
	private double bac;

	
	
	public int getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(int idActividad) {
		this.idActividad = idActividad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getResponsable() {
		return Responsable;
	}

	public void setResponsable(String Responsable) {
		this.Responsable = Responsable;
	}

	public java.util.Date getFechainicio() {
		return fechaInicio;
	}

	public void setFechainicio(java.util.Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public java.util.Date getFechafin() {
		return fechaFin;
	}

	public void setFechafin(java.util.Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public double getPorcentajeavance() {
		return porcentajeAvance;
	}

	public void setPorcentajeavance(double porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
	}

	public int getPaquete_trabajo_idpaquetetrabajo() {
		return PAQUETE_TRABAJO_idPaqueteTrabajo;
	}

	public void setPaquete_trabajo_idpaquetetrabajo(int PAQUETE_TRABAJO_idPaqueteTrabajo) {
		this.PAQUETE_TRABAJO_idPaqueteTrabajo = PAQUETE_TRABAJO_idPaqueteTrabajo;
	}

	public double getValorplanificado() {
		return valorPlanificado;
	}

	public void setValorplanificado(double valorPlanificado) {
		this.valorPlanificado = valorPlanificado;
	}

	public double getBac() {
		return bac;
	}

	public void setBac(double bac) {
		this.bac = bac;
	}

	public java.util.Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(java.util.Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public java.util.Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(java.util.Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public double getPorcentajeAvance() {
		return porcentajeAvance;
	}

	public void setPorcentajeAvance(double porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
	}

	public int getPAQUETE_TRABAJO_idPaqueteTrabajo() {
		return PAQUETE_TRABAJO_idPaqueteTrabajo;
	}

	public void setPAQUETE_TRABAJO_idPaqueteTrabajo(int pAQUETE_TRABAJO_idPaqueteTrabajo) {
		PAQUETE_TRABAJO_idPaqueteTrabajo = pAQUETE_TRABAJO_idPaqueteTrabajo;
	}

	public double getValorPlanificado() {
		return valorPlanificado;
	}

	public void setValorPlanificado(double valorPlanificado) {
		this.valorPlanificado = valorPlanificado;
	}

	@Override
	public String toString() {
		return "Actividad [nombre=" + nombre + ", Responsable=" + Responsable + ", fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + ", duracion=" + duracion + ", porcentajeAvance=" + porcentajeAvance
				+ ", PAQUETE_TRABAJO_idPaqueteTrabajo=" + PAQUETE_TRABAJO_idPaqueteTrabajo + ", valorPlanificado="
				+ valorPlanificado + ", bac=" + bac + "]";
	}

}