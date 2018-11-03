package com.tp2.modulo.sgr.service;

import java.util.ArrayList;

import com.tp2.modulo.sgr.dao.ControlDAO;
import com.tp2.modulo.sgr.model.Control;
import com.tp2.modulo.sgr.model.RespuestaResponse;

public class ControlService {

	ControlDAO controlDAO = new ControlDAO();
	
	public ArrayList<Control> getControles() {
		ArrayList<Control> listaControles = controlDAO.getControles();
		
		return listaControles;
	}
	
	public RespuestaResponse registrarControl(Control control) {
		
		RespuestaResponse respuestaResponse = new RespuestaResponse();
		boolean respuestaRegistrarControl = false;
		
		respuestaRegistrarControl = controlDAO.registrarControl(control);
		
		if (respuestaRegistrarControl) {
			respuestaResponse.setCodigoRespuesta("0");
			respuestaResponse.setMensajeRespuesta("Exito");
		} else {
			respuestaResponse.setCodigoRespuesta("1");
			respuestaResponse.setMensajeRespuesta("Error");
		}
		
		return respuestaResponse;
	}
	
	public RespuestaResponse actualizarControl(Control control) {
		
		RespuestaResponse respuestaResponse = new RespuestaResponse();
		boolean respuestaActualizarControl = false;
		
		respuestaActualizarControl = controlDAO.actualizarControl(control);
		
		if (respuestaActualizarControl) {
			respuestaResponse.setCodigoRespuesta("0");
			respuestaResponse.setMensajeRespuesta("Exito");
		} else {
			respuestaResponse.setCodigoRespuesta("1");
			respuestaResponse.setMensajeRespuesta("Error");
		}
		
		return respuestaResponse;
	}
	
	public RespuestaResponse eliminarControl(int idControl) {
		
		RespuestaResponse respuestaResponse = new RespuestaResponse();
		boolean respuestaEliminarControl = false;
		
		respuestaEliminarControl = controlDAO.eliminarControl(idControl);
		
		if (respuestaEliminarControl) {
			respuestaResponse.setCodigoRespuesta("0");
			respuestaResponse.setMensajeRespuesta("Exito");
		} else {
			respuestaResponse.setCodigoRespuesta("1");
			respuestaResponse.setMensajeRespuesta("Error");
		}
		
		return respuestaResponse;
	}
}
