package com.tp2.modulo.sgr.model;

public class Riesgo {

	private int riesgoId;
	private String riesgoNombre;
	private String riesgoDescripcion;

	public int getRiesgoId() {
		return riesgoId;
	}

	public void setRiesgoId(int riesgoId) {
		this.riesgoId = riesgoId;
	}

	public String getRiesgoDescripcion() {
		return riesgoDescripcion;
	}

	public void setRiesgoDescripcion(String riesgoDescripcion) {
		this.riesgoDescripcion = riesgoDescripcion;
	}

	public String getRiesgoNombre() {
		return riesgoNombre;
	}

	public void setRiesgoNombre(String riesgoNombre) {
		this.riesgoNombre = riesgoNombre;
	}

	@Override
	public String toString() {
		return "Riesgo [riesgoId=" + riesgoId + ", riesgoNombre=" + riesgoNombre + ", riesgoDescripcion="
				+ riesgoDescripcion + "]";
	}

}
