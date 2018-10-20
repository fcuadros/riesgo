package com.tp2.modulo.sgr.service;

import java.util.ArrayList;
import java.util.List;

import com.tp2.modulo.sgr.dao.RiesgoDAO;
import com.tp2.modulo.sgr.model.ActualizarNivelRiesgoRequest;
import com.tp2.modulo.sgr.model.ActualizarNivelRiesgoResponse;
import com.tp2.modulo.sgr.model.CalcularNivelRiesgoRequest;
import com.tp2.modulo.sgr.model.CalcularNivelRiesgoResponse;
import com.tp2.modulo.sgr.model.NivelRiesgoHistorico;
import com.tp2.modulo.sgr.model.ObtenerNivelRiesgoHistoricoResponse;
import com.tp2.modulo.sgr.util.Utilitario;

public class RiesgoService {

	RiesgoDAO riesgoDAO = new RiesgoDAO();
	
	public CalcularNivelRiesgoResponse calcularNivelRiesgo(CalcularNivelRiesgoRequest request) {
		CalcularNivelRiesgoResponse response = new CalcularNivelRiesgoResponse();
		CalcularNivelRiesgoResponse respuestaNivelRiesgo = new CalcularNivelRiesgoResponse();
		ObtenerNivelRiesgoHistoricoResponse respuestaNivelRiesgoHistorico = new ObtenerNivelRiesgoHistoricoResponse();
		String mes = "";
		List<String> meses = new ArrayList<String>();
		
		if (null != request) {
			double resultado = 0.0;
			resultado = request.getProbabilidad() * request.getImpacto();
			
			respuestaNivelRiesgo = riesgoDAO.obtenerNivelRiesgo(request, resultado);
			respuestaNivelRiesgoHistorico = riesgoDAO.obtenerNivelRiesgoHistorico(request);
			
			for (NivelRiesgoHistorico nivelRiesgoHistorico: respuestaNivelRiesgoHistorico.getListaNivelRiesgoHistorico()) {
				mes = Utilitario.concatenarMesAnio(nivelRiesgoHistorico.getMes(), nivelRiesgoHistorico.getAnio());
				meses.add(mes);
			}
			
			response.setNivelRiesgo(respuestaNivelRiesgo.getNivelRiesgo());
			response.setMes(meses);
			response.setNivelRiesgoHistorico(respuestaNivelRiesgoHistorico.getNivelesRiesgoHistorico());
			
			return response;
		}
		return response;
	}
	
	public ActualizarNivelRiesgoResponse actualizarNivelRiesgo(ActualizarNivelRiesgoRequest request) {
		ActualizarNivelRiesgoResponse response = new ActualizarNivelRiesgoResponse();
		
		boolean respuestaActualizarNivelRiesgo = false;
		boolean respuestaActualizarImpacto = false;
		boolean respuestaRegistrarRiesgoHistorico = false;
		ObtenerNivelRiesgoHistoricoResponse respuestaNivelRiesgoHistorico = new ObtenerNivelRiesgoHistoricoResponse();
		CalcularNivelRiesgoRequest calcularNivelRiesgoRequest = new CalcularNivelRiesgoRequest();
		String mes = "";
		List<String> meses = new ArrayList<String>();
		
		calcularNivelRiesgoRequest.setIdRiesgo(request.getIdRiesgo());
		
		respuestaActualizarNivelRiesgo = riesgoDAO.actualizarNivelRiesgo(request);
		respuestaActualizarImpacto = riesgoDAO.actualizarImpacto(request);
		respuestaRegistrarRiesgoHistorico = riesgoDAO.registrarRiesgoHistorico(request);
		respuestaNivelRiesgoHistorico = riesgoDAO.obtenerNivelRiesgoHistorico(calcularNivelRiesgoRequest);
		
		if (respuestaActualizarNivelRiesgo && respuestaActualizarImpacto && respuestaRegistrarRiesgoHistorico) {
			response.setCodigoRespuesta("0");
			response.setMensajeRespuesta("Exito");
			
			for (NivelRiesgoHistorico nivelRiesgoHistorico: respuestaNivelRiesgoHistorico.getListaNivelRiesgoHistorico()) {
				mes = Utilitario.concatenarMesAnio(nivelRiesgoHistorico.getMes(), nivelRiesgoHistorico.getAnio());
				meses.add(mes);
			}
			
			response.setMes(meses);
			response.setNivelRiesgoHistorico(respuestaNivelRiesgoHistorico.getNivelesRiesgoHistorico());
		} else {
			response.setCodigoRespuesta("1");
			response.setMensajeRespuesta("Error");
		}
		return response;
	}
}