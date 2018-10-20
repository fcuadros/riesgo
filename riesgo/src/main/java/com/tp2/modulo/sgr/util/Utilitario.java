package com.tp2.modulo.sgr.util;

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
}
