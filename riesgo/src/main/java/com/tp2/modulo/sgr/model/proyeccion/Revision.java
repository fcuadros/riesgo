package com.tp2.modulo.sgr.model.proyeccion;

public class Revision {

	private int idRevision;
	private java.util.Date fecha;
	private double costoActual;
	private double valorGanadoS;
	private int ACTIVIDAD_idActividad;
	private double cpi;
	private double spi;
	private double valorGanadoC;

	public int getIdRevision() {
		return idRevision;
	}

	public void setIdRevision(int idRevision) {
		this.idRevision = idRevision;
	}

	public double getCostoActual() {
		return costoActual;
	}

	public void setCostoActual(double costoActual) {
		this.costoActual = costoActual;
	}

	public double getValorGanadoS() {
		return valorGanadoS;
	}

	public void setValorGanadoS(double valorGanadoS) {
		this.valorGanadoS = valorGanadoS;
	}

	public int getACTIVIDAD_idActividad() {
		return ACTIVIDAD_idActividad;
	}

	public void setACTIVIDAD_idActividad(int aCTIVIDAD_idActividad) {
		ACTIVIDAD_idActividad = aCTIVIDAD_idActividad;
	}

	public double getValorGanadoC() {
		return valorGanadoC;
	}

	public void setValorGanadoC(double valorGanadoC) {
		this.valorGanadoC = valorGanadoC;
	}

	public java.util.Date getFecha() {
		return fecha;
	}

	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}

	public double getCostoactual() {
		return costoActual;
	}

	public void setCostoactual(double costoActual) {
		this.costoActual = costoActual;
	}

	public double getValorganados() {
		return valorGanadoS;
	}

	public void setValorganados(double valorGanadoS) {
		this.valorGanadoS = valorGanadoS;
	}

	public int getActividad_idactividad() {
		return ACTIVIDAD_idActividad;
	}

	public void setActividad_idactividad(int ACTIVIDAD_idActividad) {
		this.ACTIVIDAD_idActividad = ACTIVIDAD_idActividad;
	}

	public double getCpi() {
		return cpi;
	}

	public void setCpi(double cpi) {
		this.cpi = cpi;
	}

	public double getSpi() {
		return spi;
	}

	public void setSpi(double spi) {
		this.spi = spi;
	}

	public double getValorganadoc() {
		return valorGanadoC;
	}

	public void setValorganadoc(double valorGanadoC) {
		this.valorGanadoC = valorGanadoC;
	}

	@Override
	public String toString() {
		return "Revision [fecha=" + fecha + ", costoActual=" + costoActual + ", valorGanadoS=" + valorGanadoS
				+ ", ACTIVIDAD_idActividad=" + ACTIVIDAD_idActividad + ", cpi=" + cpi + ", spi=" + spi
				+ ", valorGanadoC=" + valorGanadoC + "]";
	}

}
