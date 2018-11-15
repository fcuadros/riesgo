package com.tp2.modulo.sgr.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.utils.SystemProperty;

@Path("/database")
public class DatabaseController {

	@Path("/conexion")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String testConexion()
		throws IOException {
		
		String res = null;
		String url = null;
		
		try {
			if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
				Class.forName("com.mysql.jdbc.GoogleDriver");
				url = System.getProperty("cloudsql.url");
			} else {
				Class.forName("com.mysql.jdbc.Driver");
				url = System.getProperty("cloudsql.url.dev");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Connection conn = DriverManager.getConnection(url,"root","@AccesoTP3");
		
			try {
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("SHOW DATABASES");
				while (rs.next()) {
					res = res + " " + rs.getString(1); 
				}
				res = res + " " + "-- done --";
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			res = "SQLException: " + e.getMessage();
		}    
		return res;
	}
	
}
