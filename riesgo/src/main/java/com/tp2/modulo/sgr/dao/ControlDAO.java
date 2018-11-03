package com.tp2.modulo.sgr.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tp2.modulo.sgr.database.ConnectionJDBC;
import com.tp2.modulo.sgr.model.Control;

public class ControlDAO {

	ConnectionJDBC jdbc = new ConnectionJDBC();
	
	public ArrayList<Control> getControles() {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<Control> listaControles = new ArrayList<Control>();
		
		String sql = "select * from tbl_control";
		
		try {
			ps = jdbc.getConnection().prepareStatement(sql);
			System.out.println("QUERY getControles:" + System.lineSeparator() + sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Control control = new Control();
				control.setIdControl(rs.getInt("cod_Control"));
				control.setDescripcion(rs.getString("tx_descripcion"));
				control.setTipo(rs.getInt("tx_tipo"));
				control.setResponsable(rs.getString("tx_responsable"));
				control.setEstadoImplementacion(rs.getInt("tx_estadoImplemtacion"));
				control.setEquipoResponsable(rs.getString("tx_equipoResponsable"));
				control.setFechaImplementacion(rs.getDate("fe_implementacion"));
				control.setCosto(rs.getDouble("tx_costo"));
				listaControles.add(control);
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
		
		return listaControles;
	}
	
	public boolean registrarControl(Control control) {
		
		PreparedStatement ps = null;
		boolean respuesta = false;
		String sql = "insert into tbl_control(tx_descripcion, tx_tipo, tx_responsable, tx_estadoImplemtacion, tx_equipoResponsable, fe_implementacion, tx_costo, cod_riesgo) "
				+ "values (?,?,?,?,?,?,?,?)";
		Date fechaImplementacion = new Date(new java.util.Date().getTime());
		
		try {
			ps = jdbc.getConnection().prepareStatement(sql);
			ps.setString(1, control.getDescripcion());
			ps.setInt(2, control.getTipo());
			ps.setString(3, control.getResponsable());
			ps.setInt(4, control.getEstadoImplementacion());
			ps.setString(5, control.getEquipoResponsable());
			ps.setDate(6, fechaImplementacion);
			ps.setDouble(7, control.getCosto());
			ps.setInt(8, control.getRIESGO_riesgoId());
			System.out.println("QUERY registrarControl: " + System.lineSeparator() + sql);
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
	
	public boolean actualizarControl(Control control) {
		
		PreparedStatement ps = null;
		boolean respuesta = false;
		String sql = "update tbl_control set tx_descripcion=?, tx_tipo=?, tx_responsable=?, tx_estadoImplemtacion=?, tx_equipoResponsable=?, "
				+ "fe_implementacion=?, tx_costo=? where cod_Control=?";
		Date fechaImplementacion = new Date(new java.util.Date().getTime());
		
		try {
			ps = jdbc.getConnection().prepareStatement(sql);
			ps.setString(1, control.getDescripcion());
			ps.setInt(2, control.getTipo());
			ps.setString(3, control.getResponsable());
			ps.setInt(4, control.getEstadoImplementacion());
			ps.setString(5, control.getEquipoResponsable());
			ps.setDate(6, fechaImplementacion);
			ps.setDouble(7, control.getCosto());
			ps.setInt(8, control.getIdControl());
			System.out.println("QUERY actualizarControl: " + System.lineSeparator() + sql);
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
	
	public boolean eliminarControl(int idControl) {
		
		PreparedStatement ps = null;
		boolean respuesta = false;
		String sql = "delete from tbl_control where cod_Control=?";
		
		try {
			ps = jdbc.getConnection().prepareStatement(sql);
			ps.setInt(1, idControl);
			System.out.println("QUERY eliminarControl: " + System.lineSeparator() + sql);
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
