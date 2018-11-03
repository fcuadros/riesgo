package com.tp2.modulo.sgr.model;

import java.util.Date;

public class Riesgo {

	private int riesgoId;
	private String nombre;
	private String descripcion;
	private Date fechaRegistro;
	private int tipo;
	private double costo;
	private double probabilidad;
	private int nivelRiesgo;
	private String personaIdentificadora;
	private Date fechaModificacion;
	private int idTipoRiesgo;

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

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRiesgo) {
		this.fechaRegistro = fechaRiesgo;
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

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public int getIdTipoRiesgo() {
		return idTipoRiesgo;
	}

	public void setIdTipoRiesgo(int idTipoRiesgo) {
		this.idTipoRiesgo = idTipoRiesgo;
	}

	@Override
	public String toString() {
		return "Riesgo [riesgoId=" + riesgoId + ", nombre=" + nombre + ", descripcion="
				+ descripcion + "]";
	}
}
