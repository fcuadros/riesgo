package com.tp2.modulo.sgr.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CalcularNivelRiesgoResponse {

	private int nivelRiesgo;
	private List<String> mes;
	private List<Integer> nivelRiesgoHistorico;
	
	public int getNivelRiesgo() {
		return nivelRiesgo;
	}
	public void setNivelRiesgo(int nivelRiesgo) {
		this.nivelRiesgo = nivelRiesgo;
	}
	public List<String> getMes() {
		return mes;
	}
	public void setMes(List<String> mes) {
		this.mes = mes;
	}
	public List<Integer> getNivelRiesgoHistorico() {
		return nivelRiesgoHistorico;
	}
	public void setNivelRiesgoHistorico(List<Integer> nivelRiesgoHistorico) {
		this.nivelRiesgoHistorico = nivelRiesgoHistorico;
	}
}
