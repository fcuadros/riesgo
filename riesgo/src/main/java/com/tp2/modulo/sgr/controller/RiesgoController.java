package com.tp2.modulo.sgr.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.tp2.modulo.sgr.model.Montecarlo;
import com.tp2.modulo.sgr.model.RespuestaResponse;
import com.tp2.modulo.sgr.model.Riesgo;
import com.tp2.modulo.sgr.model.TipoRiesgo;
import com.tp2.modulo.sgr.service.RiesgoService;

@Path("/riesgos")
public class RiesgoController{
	
	RiesgoService riesgoService = new RiesgoService();
	Gson gson = new Gson();
	
	//	GET /riesgos - Devuelve una lista de riesgos
	//	GET /riesgos/12 - Devuelve un riesgo especifico #12
	//	POST /riesgos - Crea un nuevo riesgo
	//	PUT /riesgos/12 - Actualiza un riesgo #12
	//	PATCH /riesgos/12 - Actualiza parcialmente un riesgo #12
	//	DELETE /riesgos/12 - Elimina un riesgo #12
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getRiesgos() {
		
		ArrayList<Riesgo> listaRiesgos = riesgoService.getRiesgos();
		String json = gson.toJson(listaRiesgos);
		
		return json;
	}
	
	@GET
	@Path("/{riesgoId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRiesgo(@PathParam("riesgoId") int idRiesgo) {
		
		Riesgo riesgo = riesgoService.getRiesgo(idRiesgo);
		String json = gson.toJson(riesgo);
		
		return json;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String registrarRiesgo(Riesgo riesgo) {
		
		RespuestaResponse response = riesgoService.registrarRiesgo(riesgo);
		String json = gson.toJson(response);
		
		return json;
	}
	
	@PUT
	@Path("/{riesgoId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String actualizarRiesgo(@PathParam("riesgoId") int idRiesgo, Riesgo riesgo) {
		riesgo.setRiesgoId(idRiesgo);
		RespuestaResponse response = riesgoService.actualizarRiesgo(riesgo);
		String json = gson.toJson(response);
		
		return json;
	}
	
	@DELETE
	@Path("/{riesgoId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String eliminarRiesgo(@PathParam("riesgoId") int idRiesgo) {
		RespuestaResponse response = riesgoService.eliminarRiesgo(idRiesgo);
		String json = gson.toJson(response);		
		return json;
	}
	
	@GET
	@Path("/obtenerNumeroRiegosPorNivel")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String obtenerNumeroRiesgosPorNivel(@QueryParam("anio") int anio, @QueryParam("tipoRiesgo") int tipoRiesgo, @QueryParam("mes") int mes)
	{
		Map<String, Integer> map = riesgoService.obtenerNumeroRiesgosPorNivel(Integer.valueOf(anio), Integer.valueOf(mes), Integer.valueOf(tipoRiesgo));		    
		String json = gson.toJson(map);		    
		return json;
	}
	
	@GET
	@Path("/obtenerDashboardRiesgosControl")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String obtenerDashboardRiesgosControl(@QueryParam("anio") int anio){	
		
			List<?> riesgosControl = riesgoService.obtenerCantRiesgosXControl(anio);
		
			String json = gson.toJson(riesgosControl);		    
			return json;
		
	}
		  
	@GET
	@Path("/getTipoRiesgo")	  
	@Produces(MediaType.APPLICATION_JSON)
	public String getTipoRiesgo()
	{
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();		    
		ArrayList<TipoRiesgo> listaTipoRiesgo = riesgoService.getListaTipoRiesgo();		    
		map.put("listaRevisiones", listaTipoRiesgo);
		String json = gson.toJson(map);
		return json;
	}
	
	@POST
	@Path("/simulacion/{cantSimulacion}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String obtenerPerdidaRiesgos(@PathParam("cantSimulacion") int cantSimulacion) {
		Montecarlo response = riesgoService.obtenerPerdidaRiesgos(cantSimulacion);
		
		String json = gson.toJson(response);
		
		return json;
	}
	
}
