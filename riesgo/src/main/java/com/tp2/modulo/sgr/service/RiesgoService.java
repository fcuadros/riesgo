package com.tp2.modulo.sgr.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.tp2.modulo.sgr.dao.RiesgoDAO;
import com.tp2.modulo.sgr.model.ActualizarNivelRiesgoRequest;
import com.tp2.modulo.sgr.model.ActualizarNivelRiesgoResponse;
import com.tp2.modulo.sgr.model.CalcularNivelRiesgoRequest;
import com.tp2.modulo.sgr.model.CalcularNivelRiesgoResponse;
import com.tp2.modulo.sgr.model.NivelRiesgoHistorico;
import com.tp2.modulo.sgr.model.ObtenerNivelRiesgoHistoricoResponse;
import com.tp2.modulo.sgr.model.RespuestaResponse;
import com.tp2.modulo.sgr.model.Riesgo;
import com.tp2.modulo.sgr.model.TipoRiesgo;
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
	
	public ArrayList<Riesgo> getRiesgos() {
		ArrayList<Riesgo> listaRiesgos = riesgoDAO.getRiesgos();
		
		return listaRiesgos;
	}
	
	public RespuestaResponse registrarRiesgo(Riesgo riesgo) {
		
		RespuestaResponse respuestaResponse = new RespuestaResponse();
		boolean respuestaRegistrarRiesgo = false;
		
		respuestaRegistrarRiesgo = riesgoDAO.registrarRiesgo(riesgo);
		
		if (respuestaRegistrarRiesgo) {
			respuestaResponse.setCodigoRespuesta("0");
			respuestaResponse.setMensajeRespuesta("Exito");
		} else {
			respuestaResponse.setCodigoRespuesta("1");
			respuestaResponse.setMensajeRespuesta("Error");
		}
		
		return respuestaResponse;
	}
	
	public RespuestaResponse actualizarRiesgo(Riesgo riesgo) {
		
		RespuestaResponse respuestaResponse = new RespuestaResponse();
		boolean respuestaActualizarRiesgo = false;
		
		respuestaActualizarRiesgo = riesgoDAO.actualizarRiesgo(riesgo);
		
		if (respuestaActualizarRiesgo) {
			respuestaResponse.setCodigoRespuesta("0");
			respuestaResponse.setMensajeRespuesta("Exito");
		} else {
			respuestaResponse.setCodigoRespuesta("1");
			respuestaResponse.setMensajeRespuesta("Error");
		}
		
		return respuestaResponse;
	}
	
	public RespuestaResponse eliminarRiesgo(int idRiesgo) {
		
		RespuestaResponse respuestaResponse = new RespuestaResponse();
		boolean respuestaEliminarRiesgo = false;
		
		respuestaEliminarRiesgo = riesgoDAO.eliminarRiesgo(idRiesgo);
		
		if (respuestaEliminarRiesgo) {
			respuestaResponse.setCodigoRespuesta("0");
			respuestaResponse.setMensajeRespuesta("Exito");
		} else {
			respuestaResponse.setCodigoRespuesta("1");
			respuestaResponse.setMensajeRespuesta("Error");
		}
		
		return respuestaResponse;
	}
	
	public Map<String,Integer> obtenerNumeroRiesgosPorNivel(Integer anio, Integer mes, Integer tipoRiesgo){
		Map<Integer,Integer> numeroRiegosPorNivel = riesgoDAO.obtenerNumeroRiesgosPorNivelProcedure(anio, mes, tipoRiesgo);
		
		Map<String,Integer> numeroRiegosPorNivelString = new LinkedHashMap<String,Integer>();
		
		Map<String,Object> numeroRiesgosPorNivelMap = new HashMap<String, Object>();
		
		List<String> nivelRiesgoLiteral = new ArrayList<String>();
		
		
		
		
		numeroRiesgosPorNivelMap = new HashMap<String, Object>();
		
		for (Integer integer : numeroRiegosPorNivel.keySet()) {
			
			switch (integer) {
			case 1:
				nivelRiesgoLiteral.add("Bajo");
				numeroRiegosPorNivelString.put("Bajo",numeroRiegosPorNivel.get(1));
				break;
			case 2:
				nivelRiesgoLiteral.add("Medio");				
				numeroRiegosPorNivelString.put("Medio",numeroRiegosPorNivel.get(2));
				break;
			case 3:
				nivelRiesgoLiteral.add("Alto");
				numeroRiegosPorNivelString.put("Alto",numeroRiegosPorNivel.get(3));
				break;
			
			}
			
		}
		
		
		numeroRiesgosPorNivelMap.put("nivelRiesgo",nivelRiesgoLiteral);
		numeroRiesgosPorNivelMap.put("cantidadRiesgo", numeroRiegosPorNivel.values());
		
		return numeroRiegosPorNivelString;
	}

	public ArrayList<TipoRiesgo> getListaTipoRiesgo() {

		ArrayList<TipoRiesgo> listaTipoRiesgo = riesgoDAO.getListaTipoRiesgo();
		
		return listaTipoRiesgo;
		
		
	}
}
