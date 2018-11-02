package com.tp2.modulo.sgr.model;

import java.util.Date;

public class Riesgo {

	private int riesgoId;
	private String nombre;
	private String descripcion;
	private Date fechaRiesgo;
	private int tipo;
	private double costo;
	private double probabilidad;
	private int nivelRiesgo;
	private String personaIdentificadora;
	private int ESTRATEGIA_idEstrategiaRespuesta;

	public int getRiesgoId() {
		return riesgoId;
	}

	public void setRiesgoId(int riesgoId) {
		this.riesgoId = riesgoId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaRiesgo() {
		return fechaRiesgo;
	}

	public void setFechaRiesgo(Date fechaRiesgo) {
		this.fechaRiesgo = fechaRiesgo;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getProbabilidad() {
		return probabilidad;
	}

	public void setProbabilidad(double probabilidad) {
		this.probabilidad = probabilidad;
	}

	public int getNivelRiesgo() {
		return nivelRiesgo;
	}

	public void setNivelRiesgo(int nivelRiesgo) {
		this.nivelRiesgo = nivelRiesgo;
	}

	public String getPersonaIdentificadora() {
		return personaIdentificadora;
	}

	public void setPersonaIdentificadora(String personaIdentificadora) {
		this.personaIdentificadora = personaIdentificadora;
	}

	public int getESTRATEGIA_idEstrategiaRespuesta() {
		return ESTRATEGIA_idEstrategiaRespuesta;
	}

	public void setESTRATEGIA_idEstrategiaRespuesta(int eSTRATEGIA_idEstrategiaRespuesta) {
		ESTRATEGIA_idEstrategiaRespuesta = eSTRATEGIA_idEstrategiaRespuesta;
	}

	@Override
	public String toString() {
		return "Riesgo [riesgoId=" + riesgoId + ", nombre=" + nombre + ", descripcion="
				+ descripcion + "]";
	}
}
