package com.tp2.modulo.sgr.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ActualizarNivelRiesgoResponse {

	private String codigoRespuesta;
	private String mensajeRespuesta;
	private List<String> mes;
	private List<Integer> nivelRiesgoHistorico;
	
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}
	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
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
