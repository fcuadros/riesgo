package com.tp2.modulo.sgr.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ActualizarNivelRiesgoRequest {

	private int idRiesgo;
	private int nivelRiesgo;
	private double probabilidad;
	private double impacto;
	
	public int getIdRiesgo() {
		return idRiesgo;
	}
	public void setIdRiesgo(int idRiesgo) {
		this.idRiesgo = idRiesgo;
	}
	public int getNivelRiesgo() {
		return nivelRiesgo;
	}
	public void setNivelRiesgo(int nivelRiesgo) {
		this.nivelRiesgo = nivelRiesgo;
	}
	public double getProbabilidad() {
		return probabilidad;
	}
	public void setProbabilidad(double probabilidad) {
		this.probabilidad = probabilidad;
	}
	public double getImpacto() {
		return impacto;
	}
	public void setImpacto(double impacto) {
		this.impacto = impacto;
	}
}
