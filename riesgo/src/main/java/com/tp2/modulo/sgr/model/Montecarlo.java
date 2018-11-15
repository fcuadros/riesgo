package com.tp2.modulo.sgr.model;

import java.util.List;

public class Montecarlo {

	private List<Integer> id;
	private List<Double> perdida;
	private double promedio;
	private double desvEstandar;
	
	public List<Integer> getId() {
		return id;
	}
	
	public void setId(List<Integer> id) {
		this.id = id;
	}
	
	public List<Double> getPerdida() {
		return perdida;
	}
	
	public void setPerdida(List<Double> perdida) {
		this.perdida = perdida;
	}
	
	public double getPromedio() {
		return promedio;
	}
	
	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}
	
	public double getDesvEstandar() {
		return desvEstandar;
	}
	
	public void setDesvEstandar(double desvEstandar) {
		this.desvEstandar = desvEstandar;
	}
}
