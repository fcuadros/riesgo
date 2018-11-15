package com.tp2.modulo.sgr.controller;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.tp2.modulo.sgr.model.Control;
import com.tp2.modulo.sgr.model.RespuestaResponse;
import com.tp2.modulo.sgr.service.ControlService;

@Path("/controles")
public class ControlController {

	ControlService controlService = new ControlService();
	Gson gson = new Gson();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getControles() {
		
		ArrayList<Control> listaControles = controlService.getControles();
		String json = gson.toJson(listaControles);
		
		return json;
	}
	
	@GET
	@Path("/{idControl}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getControl() {
		
		ArrayList<Control> listaControles = controlService.getControles();
		String json = gson.toJson(listaControles);
		
		return json;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String registrarControl(Control control) {
		
		RespuestaResponse response = controlService.registrarControl(control);
		String json = gson.toJson(response);
		
		return json;
	}
	
	@PUT
	@Path("/{idControl}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String actualizarControl(@PathParam("idControl") int idControl, Control control) {
		control.setIdControl(idControl);
		RespuestaResponse response = controlService.actualizarControl(control);
		String json = gson.toJson(response);
		
		return json;
	}
	
	@DELETE
	@Path("/{idControl}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String eliminarControl(@PathParam("idControl") int idControl) {
		RespuestaResponse response = controlService.eliminarControl(idControl);
		String json = gson.toJson(response);
		
		return json;
	}
}
