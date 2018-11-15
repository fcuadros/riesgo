package com.tp2.modulo.sgr.model;

public class TipoRiesgo {

	int codTipoRiesgo;
	String nombreTipoRiesgo;
	
	public TipoRiesgo(){		
	}
	
	public TipoRiesgo(int codTipoRiesgo, String nombreTipoRiesgo) {
		super();
		this.codTipoRiesgo = codTipoRiesgo;
		this.nombreTipoRiesgo = nombreTipoRiesgo;
	}

	public int getCodTipoRiesgo() {
		return codTipoRiesgo;
	}

	public void setCodTipoRiesgo(int codTipoRiesgo) {
		this.codTipoRiesgo = codTipoRiesgo;
	}

	public String getNombreTipoRiesgo() {
		return nombreTipoRiesgo;
	}

	public void setNombreTipoRiesgo(String nombreTipoRiesgo) {
		this.nombreTipoRiesgo = nombreTipoRiesgo;
	}

	@Override
	public String toString() {
		return "TipoRiesgo [codTipoRiesgo=" + codTipoRiesgo + ", nombreTipoRiesgo=" + nombreTipoRiesgo + "]";
	}
	
	
	
	
	
}
