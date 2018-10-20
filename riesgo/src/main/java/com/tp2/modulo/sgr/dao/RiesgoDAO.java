package com.tp2.modulo.sgr.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tp2.modulo.sgr.database.ConnectionJDBC;
import com.tp2.modulo.sgr.model.ActualizarNivelRiesgoRequest;
import com.tp2.modulo.sgr.model.CalcularNivelRiesgoRequest;
import com.tp2.modulo.sgr.model.CalcularNivelRiesgoResponse;
import com.tp2.modulo.sgr.model.NivelRiesgoHistorico;
import com.tp2.modulo.sgr.model.ObtenerNivelRiesgoHistoricoResponse;

public class RiesgoDAO {

	ConnectionJDBC jdbc = new ConnectionJDBC();
	
	public CalcularNivelRiesgoResponse obtenerNivelRiesgo(CalcularNivelRiesgoRequest request, double probabilidadImpacto) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		CalcularNivelRiesgoResponse response = new CalcularNivelRiesgoResponse();
		
		String sql = "select * from matriz where objetivoProyecto=? "
				+ "and ? between rangoImpactoProbabilidadInicio and rangoImpactoProbabilidadFin";
		try {
			ps = jdbc.getConnection().prepareStatement(sql);
			ps.setString(1, request.getCategoria());
			ps.setDouble(2, probabilidadImpacto);
			System.out.println("QUERY obtenerNivelRiesgo: " + sql);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				response.setNivelRiesgo(Integer.parseInt(rs.getString("nivelRiesgo")));
				return response;
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
		return response;
	}
	
	public ObtenerNivelRiesgoHistoricoResponse obtenerNivelRiesgoHistorico(CalcularNivelRiesgoRequest request) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ObtenerNivelRiesgoHistoricoResponse response = new ObtenerNivelRiesgoHistoricoResponse();
		List<Integer> nivelesRiesgo = new ArrayList<Integer>();
		NivelRiesgoHistorico nivelRiesgoHistorico = null;
		List<NivelRiesgoHistorico> listaNivelRiesgoHistorico = new ArrayList<NivelRiesgoHistorico>();
		List<String> fechasActualizacion = new ArrayList<String>();
		String anio = "";
		String mes = "";
		int nivelRiesgo = 0;
		String fechaActualizacion = "";
		
		String sql = "SELECT year(fecha) as anio, month(fecha) as mes, d.nivelRiesgo as nivelRiesgo, a.fecha as fecha " + 
		"FROM nivel_riesgo_hist d " + 
		"INNER JOIN (" + 
		"  SELECT year(fecha_actualizacion) as anio, month(fecha_actualizacion) as mon, max(fecha_actualizacion) as fecha " + 
		"  FROM nivel_riesgo_hist " + 
		"  GROUP BY year(fecha_actualizacion), month(fecha_actualizacion) " + 
		"  ) a ON a.anio = year(d.fecha_actualizacion) AND a.mon = month(d.fecha_actualizacion) " + 
		"WHERE d.fecha_actualizacion=a.fecha " + 
		"AND idRiesgo=? AND fecha_actualizacion > DATE_SUB(NOW(), INTERVAL 12 MONTH) ORDER BY fecha";
		
		try {
			ps = jdbc.getConnection().prepareStatement(sql);
			ps.setInt(1, request.getIdRiesgo());
			System.out.println("QUERY obtenerNivelRiesgoHistorico: " + sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				nivelRiesgoHistorico = new NivelRiesgoHistorico();
				anio = rs.getString("anio");
				mes = rs.getString("mes");
				fechaActualizacion = rs.getString("fecha");
				nivelRiesgo = Integer.parseInt(rs.getString("nivelRiesgo"));
				fechasActualizacion.add(fechaActualizacion);
				nivelRiesgoHistorico.setAnio(anio);
				nivelRiesgoHistorico.setMes(mes);
				listaNivelRiesgoHistorico.add(nivelRiesgoHistorico);
				nivelesRiesgo.add(nivelRiesgo);
			}
			response.setFechasActualizacion(fechasActualizacion);
			response.setNivelesRiesgoHistorico(nivelesRiesgo);
			response.setListaNivelRiesgoHistorico(listaNivelRiesgoHistorico);
			
			return response;
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
		return response;
	}
	
	public boolean actualizarNivelRiesgo(ActualizarNivelRiesgoRequest request) {
		PreparedStatement ps = null;
		boolean respuesta = false;
		String sql = "update riesgo set probabilidad=?, nivelRiesgo=? "
				+ "where idRiesgo=?";
		try {
			ps = jdbc.getConnection().prepareStatement(sql);
			ps.setDouble(1, request.getProbabilidad());
			ps.setDouble(2, request.getNivelRiesgo());
			ps.setInt(3, request.getIdRiesgo());
			System.out.println("QUERY actualizarNivelRiesgo: " + sql);
			ps.execute();
			ps.close();
			respuesta = true;
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
		return respuesta;
	}
	
	public boolean actualizarImpacto(ActualizarNivelRiesgoRequest request) {
		PreparedStatement ps = null;
		boolean respuesta = false;
		String sql = "update impacto set factorImpacto=? where id_riesgo=?";
		try {
			ps = jdbc.getConnection().prepareStatement(sql);
			ps.setDouble(1, request.getImpacto());
			ps.setInt(2, request.getIdRiesgo());
			System.out.println("QUERY actualizarImpacto: " + sql);
			ps.execute();
			ps.close();
			respuesta = true;
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
		return respuesta;
	}
	
	public boolean registrarRiesgoHistorico(ActualizarNivelRiesgoRequest request) {
		PreparedStatement ps = null;
		boolean respuesta = false;
		String sql = "insert into nivel_riesgo_hist(idRiesgo, nivelRiesgo, fecha_actualizacion) "
				+ "values (?,?,NOW())";
		try {
			ps = jdbc.getConnection().prepareStatement(sql);
			ps.setInt(1, request.getIdRiesgo());
			ps.setDouble(2, request.getNivelRiesgo());
			System.out.println("QUERY registrarRiesgoHistorico: " + sql);
			ps.execute();
			ps.close();
			respuesta = true;
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
		return respuesta;
	}
}
