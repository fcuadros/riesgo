package com.tp2.modulo.sgr.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.tp2.modulo.sgr.model.ActualizarNivelRiesgoRequest;
import com.tp2.modulo.sgr.model.ActualizarNivelRiesgoResponse;
import com.tp2.modulo.sgr.model.CalcularNivelRiesgoRequest;
import com.tp2.modulo.sgr.model.CalcularNivelRiesgoResponse;
import com.tp2.modulo.sgr.service.RiesgoService;

@Path("/nivelRiesgo")
public class NivelRiesgoController {

	RiesgoService nivelRiesgoService = new RiesgoService();
	
//	@POST
//	@Path("/calcularNivelRiesgo")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public CalcularNivelRiesgoResponse calcularNivelRiesgo(CalcularNivelRiesgoRequest request) {
//		return nivelRiesgoService.calcularNivelRiesgo(request);
//	}
	
	@GET
	@Path("/calcularNivelRiesgo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CalcularNivelRiesgoResponse calcularNivelRiesgo(@QueryParam("idRiesgo") int idRiesgo,
														   @QueryParam("probabilidad") double probabilidad,
														   @QueryParam("impacto") double impacto,
														   @QueryParam("categoria") String categoria) {
		CalcularNivelRiesgoRequest request = new CalcularNivelRiesgoRequest();
		request.setIdRiesgo(idRiesgo);
		request.setProbabilidad(probabilidad);
		request.setImpacto(impacto);
		request.setCategoria(categoria);
		return nivelRiesgoService.calcularNivelRiesgo(request);
	}
	
//	@POST
//	@Path("/actualizarNivelRiesgo")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ActualizarNivelRiesgoResponse actualizarNivelRiesgo(ActualizarNivelRiesgoRequest request) {
//		return nivelRiesgoService.actualizarNivelRiesgo(request);
//	}
	
	@GET
	@Path("/actualizarNivelRiesgo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ActualizarNivelRiesgoResponse actualizarNivelRiesgo(@QueryParam("idRiesgo") int idRiesgo,
			   												   @QueryParam("nivelRiesgo") int nivelRiesgo,
			   												   @QueryParam("probabilidad") double probabilidad,
			   												   @QueryParam("impacto") double impacto) {
		ActualizarNivelRiesgoRequest request = new ActualizarNivelRiesgoRequest();
		request.setIdRiesgo(idRiesgo);
		request.setNivelRiesgo(nivelRiesgo);
		request.setProbabilidad(probabilidad);
		request.setImpacto(impacto);
		return nivelRiesgoService.actualizarNivelRiesgo(request);
	}
}
