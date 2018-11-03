package com.tp2.modulo.sgr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.tp2.modulo.sgr.model.Riesgo;
import com.tp2.modulo.sgr.model.TipoRiesgo;
import com.tp2.modulo.sgr.model.proyeccion.Actividad;
import com.tp2.modulo.sgr.model.proyeccion.Proyecto;
import com.tp2.modulo.sgr.model.proyeccion.RevisionXActividad;
import com.tp2.modulo.sgr.service.ProyeccionService;

@Path("/proyeccion")
public class ProyeccionResource {

	ProyeccionService proyeccionService = new ProyeccionService();
	Gson gson = new Gson();

	@GET
	@Path("/getProyectos")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProyectos() {

		ArrayList<Proyecto> listaProyectos = proyeccionService.getProyectos();

		String json = gson.toJson(listaProyectos);

		return json;
	}

	@GET
	@Path("/getActividadesByProyecto")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getActividadesByProyecto(@QueryParam("idProyecto") int idProyecto) {

		

		
		ArrayList<Actividad> listaActividad = proyeccionService.getActividadesByProyecto(idProyecto);
		String json = gson.toJson(listaActividad);
		return json;

	}

	@GET
	@Path("/getRiesgosByActividad")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getRiesgosByActividad(@QueryParam("idActividad") int idActividad) {

		ArrayList<Riesgo> listaRiesgos = proyeccionService.getRiesgosByActividad(idActividad);
		String json = gson.toJson(listaRiesgos);
		return json;

	}

	@GET
	@Path("/getRevisionesByActividad")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getRevisionesByActividad(@QueryParam("idActividad") int idActividad) {

		HashMap<String, Object> map = new LinkedHashMap<>();
		
		
		
		ArrayList<RevisionXActividad> listaRevisiones = proyeccionService.getRevisionesByActividad(idActividad);
		
		map.put("listaRevisiones", listaRevisiones);
		String json = gson.toJson(map);
		return json;

	}	

	@GET
	@Path("/calcularPronostico")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String calcularPronostico(@QueryParam("tipoPronostico") int tipoPronostico,
			@QueryParam("idActividad") int idActividad) {

		Double pronostico = proyeccionService.calcularPronostico(tipoPronostico, idActividad);
		String json = gson.toJson(pronostico);
		return json;

	}
}
