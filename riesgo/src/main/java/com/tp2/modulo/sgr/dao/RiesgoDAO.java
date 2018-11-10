package com.tp2.modulo.sgr.dao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tp2.modulo.sgr.database.ConnectionJDBC;
import com.tp2.modulo.sgr.model.ActualizarNivelRiesgoRequest;
import com.tp2.modulo.sgr.model.CalcularNivelRiesgoRequest;
import com.tp2.modulo.sgr.model.CalcularNivelRiesgoResponse;
import com.tp2.modulo.sgr.model.NivelRiesgoHistorico;
import com.tp2.modulo.sgr.model.ObtenerNivelRiesgoHistoricoResponse;
import com.tp2.modulo.sgr.model.Riesgo;
import com.tp2.modulo.sgr.model.TipoRiesgo;

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
	
	public ArrayList<Riesgo> getRiesgos() {
		
		ArrayList<Riesgo> listaRiesgos = new ArrayList<Riesgo>();
		
		try (CallableStatement cs = jdbc.getConnection().prepareCall("{call INDRASS_Riesgo()}");) {
			
			boolean hadResults = cs.execute();
			
			System.out.println("Stored procedure called successfully!");
			
			while (hadResults) {
				ResultSet resultSet = cs.getResultSet();
				
				while (resultSet.next()) {
					Riesgo riesgo = new Riesgo();
					
					riesgo.setRiesgoId(resultSet.getInt("cod_riesgo"));
					riesgo.setNombre(resultSet.getString("no_riesgo"));
					riesgo.setDescripcion(resultSet.getString("descripcion"));
					riesgo.setFechaRegistro(resultSet.getDate("fe_registro"));
					riesgo.setTipo(resultSet.getInt("tx_tipo"));
					riesgo.setCosto(resultSet.getDouble("nu_costo"));
					riesgo.setProbabilidad(resultSet.getDouble("nu_probabilidad"));
					riesgo.setNivelRiesgo(resultSet.getInt("nu_nivelRiesgo"));
					riesgo.setPersonaIdentificadora(resultSet.getString("tx_personaIdentificadora"));
					riesgo.setFechaModificacion(resultSet.getDate("fe_modificacion"));
					
					listaRiesgos.add(riesgo);
				}
				
				hadResults = cs.getMoreResults();
			}
			
			cs.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				jdbc.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listaRiesgos;
	}
	
	public boolean registrarRiesgo(Riesgo riesgo) {
		
		boolean respuesta = false;
		Date fechaRegistro = new Date(new java.util.Date().getTime());
		
		try (CallableStatement cs = jdbc.getConnection().prepareCall("{call INDRASI_Riesgo(?,?,?,?,?,?,?,?,?,?)}");) {
			
			cs.setString(1, riesgo.getNombre());
			cs.setString(2, riesgo.getDescripcion());
			cs.setDate(3, fechaRegistro);
			cs.setInt(4, riesgo.getTipo());
			cs.setDouble(5, riesgo.getCosto());
			cs.setDouble(6, riesgo.getProbabilidad());
			cs.setInt(7, riesgo.getNivelRiesgo());
			cs.setString(8, riesgo.getPersonaIdentificadora());
			cs.setDate(9, fechaRegistro);
			cs.setInt(10, riesgo.getIdTipoRiesgo());
			cs.execute();
			System.out.println("Stored procedure called successfully!");
			cs.close();
			respuesta = true;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				jdbc.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return respuesta;
	}
	
	public boolean actualizarRiesgo(Riesgo riesgo) {
		
		boolean respuesta = false;
		Date fechaModificacion = new Date(new java.util.Date().getTime());
		
		try (CallableStatement cs = jdbc.getConnection().prepareCall("{call INDRASU_Riesgo(?,?,?,?,?,?,?,?,?,?)}");) {
			cs.setInt(1, riesgo.getRiesgoId());
			cs.setString(2, riesgo.getNombre());
			cs.setString(3, riesgo.getDescripcion());
			cs.setInt(4, riesgo.getTipo());
			cs.setDouble(5, riesgo.getCosto());
			cs.setDouble(6, riesgo.getProbabilidad());
			cs.setInt(7, riesgo.getNivelRiesgo());
			cs.setString(8, riesgo.getPersonaIdentificadora());
			cs.setDate(9, fechaModificacion);
			cs.setInt(10, riesgo.getIdTipoRiesgo());
			cs.execute();
			System.out.println("Stored procedure called successfully!");
			cs.close();
			respuesta = true;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				jdbc.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return respuesta;
		}
	
	public List<?> obtenerNumeroRiesgosPorNivelSQL(Integer anio, Integer mes, ArrayList<Integer> nivelRiesgo) {
		
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<Riesgo> listaRiesgos = new ArrayList<Riesgo>();
		
		String sql = "select * from riesgo";
		
		try {
			ps = jdbc.getConnection().prepareStatement(sql);
			System.out.println("QUERY getRiesgos:" + System.lineSeparator() + sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Riesgo riesgo = new Riesgo();
				riesgo.setRiesgoId(rs.getInt("idRiesgo"));
				riesgo.setNombre(rs.getString("nombre"));
				riesgo.setDescripcion(rs.getString("descripcion"));
				riesgo.setFechaRegistro(rs.getDate("fechaRiesgo"));
				riesgo.setTipo(rs.getInt("tipo"));
				riesgo.setCosto(rs.getDouble("costo"));
				riesgo.setProbabilidad(rs.getDouble("probabilidad"));
				riesgo.setNivelRiesgo(rs.getInt("nivelRiesgo"));
				riesgo.setPersonaIdentificadora(rs.getString("personaIdentificadora"));
				listaRiesgos.add(riesgo);
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
	
	public HashMap<Integer,Integer> obtenerNumeroRiesgosPorNivelProcedure(Integer anio, Integer mes, Integer tipoRiesgo){
		
		HashMap<Integer,Integer> listaRiegosPorNivel = new HashMap<Integer,Integer>();
		
		try (CallableStatement cs = jdbc.getConnection().prepareCall("{call s_NumeroRiesgosPorNivelBeta(?,?,?)}");) {
	 
			cs.setInt(1,anio);
			cs.setInt(2,mes);
			
			
			
			//System.out.println(nivelRiesgo.stream().collect(Collectors.joining(","));
			//System.out.println(String.join(",", nivelRiesgo.toArray()));
			//String nivelRiesgoString = nivelRiesgo.stream().collect(Collectors.joining(","));
			
			cs.setInt(3, tipoRiesgo);
			
			System.out.println("anio:" + anio);
			System.out.println("mes: " + mes);
			System.out.println("tipoRiesgo: " + tipoRiesgo);
			
			
			boolean hadResults = cs.execute();
			
			
	            
	        System.out.println("Stored procedure called successfully!");
	        
	        //String nivelRiesgoStringLiteral;
	        
	        while (hadResults) {
                ResultSet resultSet = cs.getResultSet();
 
                // process result set
                while (resultSet.next()) {
                	
                	
                	                	
                	listaRiegosPorNivel.put(resultSet.getInt("nivel") , resultSet.getInt("suma"));
                   
                }
 
                hadResults = cs.getMoreResults();
            }
	        
	        
 
            cs.close();
	        
            
	       
	 
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        } finally{
	        	try {
					jdbc.getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        }
		
		
		
		return listaRiegosPorNivel;
		
	}
	
	public ArrayList<TipoRiesgo> getListaTipoRiesgo() {
		
		
		ArrayList<TipoRiesgo>  listaTipoRiesgo = new ArrayList<TipoRiesgo>();

		try (CallableStatement cs = jdbc.getConnection().prepareCall("{call S_tipoRiesgo()}");) {

			// System.out.println(nivelRiesgo.stream().collect(Collectors.joining(","));
			// System.out.println(String.join(",", nivelRiesgo.toArray()));
			// String nivelRiesgoString = nivelRiesgo.stream().collect(Collectors.joining(","));

			boolean hadResults = cs.execute();

			System.out.println("Stored procedure called successfully!");

			// String nivelRiesgoStringLiteral;

			while (hadResults) {
				ResultSet resultSet = cs.getResultSet();

				// process result set
				while (resultSet.next()) {
					
					TipoRiesgo tipoRiesgo = new TipoRiesgo();

					tipoRiesgo.setCodTipoRiesgo(resultSet.getInt("codigo"));
					tipoRiesgo.setNombreTipoRiesgo(resultSet.getString("nombre"));

					listaTipoRiesgo.add(tipoRiesgo);
				}

				hadResults = cs.getMoreResults();
			}

			cs.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				jdbc.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaTipoRiesgo;		
	}
	
	public boolean eliminarRiesgo(int idRiesgo) {
		
		boolean respuesta = false;
		
		try (CallableStatement cs = jdbc.getConnection().prepareCall("{call INDRASD_Riesgo(?)}");) {
			cs.setInt(1, idRiesgo);
			cs.execute();
			System.out.println("Stored procedure called successfully!");
			cs.close();
			respuesta = true;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
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
