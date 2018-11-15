package com.tp2.modulo.sgr.model;

import java.util.List;

public class ObtenerNivelRiesgoHistoricoResponse {

	private List<String> fechasActualizacion;
	private List<Integer> nivelesRiesgoHistorico;
	private List<NivelRiesgoHistorico> listaNivelRiesgoHistorico;
	
	public List<String> getFechasActualizacion() {
		return fechasActualizacion;
	}
	public void setFechasActualizacion(List<String> fechasActualizacion) {
		this.fechasActualizacion = fechasActualizacion;
	}
	public List<Integer> getNivelesRiesgoHistorico() {
		return nivelesRiesgoHistorico;
	}
	public void setNivelesRiesgoHistorico(List<Integer> nivelesRiesgoHistorico) {
		this.nivelesRiesgoHistorico = nivelesRiesgoHistorico;
	}
	public List<NivelRiesgoHistorico> getListaNivelRiesgoHistorico() {
		return listaNivelRiesgoHistorico;
	}
	public void setListaNivelRiesgoHistorico(List<NivelRiesgoHistorico> listaNivelRiesgoHistorico) {
		this.listaNivelRiesgoHistorico = listaNivelRiesgoHistorico;
	}
}
