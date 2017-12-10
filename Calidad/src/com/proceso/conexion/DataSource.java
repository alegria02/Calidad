package com.proceso.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

import com.proceso.servlet.ingreso.NuevaInformacionServlet;


public class DataSource {

	private static Logger log = Logger.getLogger(NuevaInformacionServlet.class);
	
	public static Connection conexion() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			   
	   		Connection conexion = DriverManager.getConnection ("jdbc:mysql://45.7.230.227/Planilla","planilla", "1qazxsw2");
			return conexion;
			
		} catch (Exception e) {
			log.error("Error conectando a BD" + e, e);
			return null;
		}
		
		
	}
	
	public static void cerrarConexion(Connection conexion) {
		try {
			
			if (conexion != null) {
				conexion.close();
			}
			
		} catch (Exception e) {
			log.error("Error cerrando conexion " + e, e);
		}
		
		
	}
}
