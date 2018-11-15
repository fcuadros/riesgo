package com.tp2.modulo.sgr.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CalcularNivelRiesgoRequest {

	private int idRiesgo;
	private double probabilidad;
	private double impacto;
	private String categoria;
	
	public int getIdRiesgo() {
		return idRiesgo;
	}
	public void setIdRiesgo(int idRiesgo) {
		this.idRiesgo = idRiesgo;
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
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
