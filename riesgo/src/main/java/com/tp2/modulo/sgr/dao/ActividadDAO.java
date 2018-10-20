package com.tp2.modulo.sgr.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tp2.modulo.sgr.database.ConnectionJDBC;
import com.tp2.modulo.sgr.model.Riesgo;
import com.tp2.modulo.sgr.model.proyeccion.Actividad;
import com.tp2.modulo.sgr.model.proyeccion.Proyecto;
import com.tp2.modulo.sgr.model.proyeccion.Revision;
import com.tp2.modulo.sgr.model.proyeccion.RevisionXActividad;

public class ActividadDAO {

	ConnectionJDBC jdbc = new ConnectionJDBC();

	public ArrayList<Proyecto> getProyectos() {

		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Proyecto> listaProyectos = new ArrayList<Proyecto>();

		String sql = "select idProyecto, descripcion from proyecto";

		try {
			ps = jdbc.getConnection().prepareStatement(sql);
			System.out.println("QUERY getProyectos:" + System.lineSeparator() + sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Proyecto proyecto = new Proyecto();
				proyecto.setIdProyecto(rs.getInt("idProyecto"));
				proyecto.setDescripcion(rs.getString("descripcion"));
				listaProyectos.add(proyecto);
			}

			return listaProyectos;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				jdbc.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaProyectos;

	}

	public ArrayList<Actividad> getActividadesByProyecto(Integer proyectoId) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Actividad> listaActividades = new ArrayList<Actividad>();

		String sql = "select a.idActividad, a.nombre from actividad A join paquete_trabajo PT on A.PAQUETE_TRABAJO_idPaqueteTrabajo=PT.PROYECTO_idProyecto join proyecto P on PT.PROYECTO_idProyecto=P.idProyecto where P.idProyecto=? ";

		try {
			ps = jdbc.getConnection().prepareStatement(sql);
			ps.setInt(1, proyectoId);
			System.out.println("QUERY:" + System.lineSeparator() + sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				Actividad actividad = new Actividad();
				actividad.setIdActividad(rs.getInt("idActividad"));
				actividad.setNombre(rs.getString("nombre"));
				listaActividades.add(actividad);
			}

			return listaActividades;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				jdbc.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaActividades;

	}

	public ArrayList<Riesgo> getRiesgosByActividad(Integer actividadId) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Riesgo> listaRiesgos = new ArrayList<Riesgo>();

		String sql = "select r.idRiesgo, r.nombre from riesgo R join riesgo_actividad RA on R.idRiesgo=RA.RIESGO_idRiesgo join actividad A on RA.ACTIVIDAD_idActividad=A.idActividad where A.idActividad=?";

		try {
			ps = jdbc.getConnection().prepareStatement(sql);
			ps.setInt(1, actividadId);
			System.out.println("QUERY:" + System.lineSeparator() + sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				Riesgo riesgo = new Riesgo();
				riesgo.setRiesgoId(rs.getInt("idRiesgo"));
				riesgo.setRiesgoNombre(rs.getString("idRiesgo"));
				listaRiesgos.add(riesgo);
				return listaRiesgos;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				jdbc.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaRiesgos;

	}

	public ArrayList<RevisionXActividad> getRevisionesByActividad(Integer actividadId) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<RevisionXActividad> listaRevisiones = new ArrayList<RevisionXActividad>();

		String sql = "SELECT R.IdRevision, R.fecha, A.valorPlanificado AS PV, R.valorGanadoC AS EVC, R.valorGanadoS AS EVS, R.costoActual AS AC, r.SPI, r.CPI,  A.bac AS BAC , A.valorPlanificado - R.costoActual  AS VARIACION_COSTO , R.valorGanadoS - A.valorPlanificado  as  SV ,  DATE_ADD(fecha, INTERVAL cast(a.duracion  as int) DAY) as  CULMINACION_PRONOSTICADA from revision R inner join actividad A on R.ACTIVIDAD_idActividad= A.idActividad WHERE A.idActividad = ? order by R.Fecha desc";

		try {
			ps = jdbc.getConnection().prepareStatement(sql);
			ps.setInt(1, actividadId);
			System.out.println("QUERY:" + System.lineSeparator() + sql);
			rs = ps.executeQuery();

			if (rs.next()) {

				RevisionXActividad revisionActividad = new RevisionXActividad();

				Revision rev = new Revision();
				Actividad actividad = new Actividad();

				rev.setIdRevision(rs.getInt("idRevision"));
				rev.setFecha(rs.getDate("fecha"));
				rev.setValorGanadoC(rs.getDouble("EVC"));
				rev.setValorGanadoS(rs.getDouble("EVS"));
				rev.setCostoActual(rs.getDouble("AC"));
				rev.setSpi(rs.getDouble("SPI"));
				rev.setCpi(rs.getDouble("CPI"));

				actividad.setValorPlanificado(rs.getDouble("PV"));
				actividad.setBac(rs.getDouble("BAC"));

				// revision.setFechaPlanificada(new java.util.Date(rs.getDate("CULMINACION_PROONOSTICADA").getTime()));
				revisionActividad.setFechaPlanificada(rs.getDate("CULMINACION_PROONOSTICADA"));
				revisionActividad.setVariacionCosto(rs.getDouble("variacionCosto"));
				revisionActividad.setVariacionCronograma(rs.getDouble("variacionCronograma"));

				revisionActividad.setRevision(rev);
				revisionActividad.setActividad(actividad);
				listaRevisiones.add(revisionActividad);
			}
			return listaRevisiones;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				jdbc.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return listaRevisiones;

	}

}
