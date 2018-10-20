package com.tp2.modulo.sgr.service;

import java.util.ArrayList;

import com.tp2.modulo.sgr.dao.ActividadDAO;
import com.tp2.modulo.sgr.model.Riesgo;
import com.tp2.modulo.sgr.model.proyeccion.Actividad;
import com.tp2.modulo.sgr.model.proyeccion.Proyecto;
import com.tp2.modulo.sgr.model.proyeccion.RevisionXActividad;

public class ProyeccionService {

	ActividadDAO actividadDAO = new ActividadDAO();

	public ArrayList<Proyecto> getProyectos() {
		ArrayList<Proyecto> listaProyectos = actividadDAO.getProyectos();

		return listaProyectos;
	}

	public ArrayList<Actividad> getActividadesByProyecto(Integer proyectoId) {
		ArrayList<Actividad> listaActividades = actividadDAO.getActividadesByProyecto(proyectoId);

		return listaActividades;
	}

	public ArrayList<Riesgo> getRiesgosByActividad(Integer actividadId) {
		ArrayList<Riesgo> listaRiesgos = actividadDAO.getRiesgosByActividad(actividadId);

		return listaRiesgos;
	}

	public ArrayList<RevisionXActividad> getRevisionesByActividad(Integer actividadId) {

		ArrayList<RevisionXActividad> listaRevisiones = actividadDAO.getRevisionesByActividad(actividadId);

		return listaRevisiones;

	}

	public Double calcularPronostico(int tipoActividad, int actividadId) {

		ArrayList<RevisionXActividad> listaRevisiones = actividadDAO.getRevisionesByActividad(actividadId);
		Double pronostico = null;

		if (listaRevisiones != null && !listaRevisiones.isEmpty()) {

			RevisionXActividad revision = listaRevisiones.get(0);

			switch (tipoActividad) {
			// Segun Proporcion Presupuestada
			// AC (Costo Actual) + [BAC (presupuesto a la culminación) - EVC (Valor Ganado)]

			case 1:
				pronostico = revision.getRevision().getCostoactual() + revision.getActividad().getBac()
						- revision.getRevision().getValorGanadoC();
				break;

			// Segun CPI
			// EAC = [BAC (Presupuesto a la culminación) / CPI (Índice de desempeño de costos)]
			case 2:
				pronostico = (revision.getActividad().getBac() / revision.getRevision().getCpi());
				break;

			// Segun ambos factores
			// EAC = [AC (Costo Actual)] + [[BAC (Presupuesto a la culminación) - EVC (Valor Ganado)] / [CPI (Índice de
			// desempeño de costos) * SPI (Índice de desempeño del cronograma)]]

			case 3:
				pronostico = revision.getRevision().getCostoactual()
						+ (revision.getActividad().getBac() - revision.getRevision().getCostoactual())
								/ (revision.getRevision().getCpi() * revision.getRevision().getSpi());
				break;
			default:
				return null;

			}

		} else {// Fin de comprobacion de lista vacia
			System.out.println("listaRevisiones VACIA");
		}

		return pronostico;

	}

}
