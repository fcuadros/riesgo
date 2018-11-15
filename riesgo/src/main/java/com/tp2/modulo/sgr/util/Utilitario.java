package com.tp2.modulo.sgr.util;

import java.util.List;

public class Utilitario {

	public static String concatenarMesAnio(String mes, String anio) {
		String cadenaRespuesta = "";
		
		if ("1".equals(mes)) {
			cadenaRespuesta = "Enero".concat("-").concat(anio);
		} else if ("2".equals(mes)) {
			cadenaRespuesta = "Febrero".concat("-").concat(anio);
		} else if ("3".equals(mes)) {
			cadenaRespuesta = "Marzo".concat("-").concat(anio);
		} else if ("4".equals(mes)) {
			cadenaRespuesta = "Abril".concat("-").concat(anio);
		} else if ("5".equals(mes)) {
			cadenaRespuesta = "Mayo".concat("-").concat(anio);
		} else if ("6".equals(mes)) {
			cadenaRespuesta = "Junio".concat("-").concat(anio);
		} else if ("7".equals(mes)) {
			cadenaRespuesta = "Julio".concat("-").concat(anio);
		} else if ("8".equals(mes)) {
			cadenaRespuesta = "Agosto".concat("-").concat(anio);
		} else if ("9".equals(mes)) {
			cadenaRespuesta = "Setiembre".concat("-").concat(anio);
		} else if ("10".equals(mes)) {
			cadenaRespuesta = "Octubre".concat("-").concat(anio);
		} else if ("11".equals(mes)) {
			cadenaRespuesta = "Noviembre".concat("-").concat(anio);
		} else if ("12".equals(mes)) {
			cadenaRespuesta = "Diciembre".concat("-").concat(anio);
		}
		return cadenaRespuesta;
	}
	
	public static String concatenarMes(String mes) {
		String cadenaRespuesta = "";
		
		if ("1".equals(mes)) {
			cadenaRespuesta = "Enero";
		} else if ("2".equals(mes)) {
			cadenaRespuesta = "Febrero";
		} else if ("3".equals(mes)) {
			cadenaRespuesta = "Marzo";
		} else if ("4".equals(mes)) {
			cadenaRespuesta = "Abril";
		} else if ("5".equals(mes)) {
			cadenaRespuesta = "Mayo";
		} else if ("6".equals(mes)) {
			cadenaRespuesta = "Junio";
		} else if ("7".equals(mes)) {
			cadenaRespuesta = "Julio";
		} else if ("8".equals(mes)) {
			cadenaRespuesta = "Agosto";
		} else if ("9".equals(mes)) {
			cadenaRespuesta = "Setiembre";
		} else if ("10".equals(mes)) {
			cadenaRespuesta = "Octubre";
		} else if ("11".equals(mes)) {
			cadenaRespuesta = "Noviembre";
		} else if ("12".equals(mes)) {
			cadenaRespuesta = "Diciembre";
		}
		return cadenaRespuesta;
	}
	
	public static double calcularPromedio(List<Double> listaPerdida, int contador) {
		Double sumaPerdida = 0.0;
		double promedio = 0.0;
		
		for (Double perdida : listaPerdida) {
			sumaPerdida += perdida;
		}
		
		promedio = sumaPerdida/contador;
		
		return promedio;
	}
	
	public static double calcularDesviacionEstandar(List<Double> listaPerdida, int contador) {
		double promedio = Utilitario.calcularPromedio(listaPerdida, contador);
		double varianza = 0.0;
		double desvEstandar = 0.0;
		
		for (Double perdida : listaPerdida) {
			double rango;
			rango = Math.pow(perdida - promedio, 2f);
			varianza = varianza + rango;
		}
		
		varianza = varianza / contador;
		
		desvEstandar = Math.sqrt(varianza);
		
		return desvEstandar;
	}
}
