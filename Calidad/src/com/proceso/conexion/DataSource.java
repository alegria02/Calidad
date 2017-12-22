package com.proceso.conexion;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.proceso.servlet.ingreso.NuevaInformacionServlet;
import com.proceso.utiles.Constantes;

public class DataSource {

	private static Logger log = Logger.getLogger(NuevaInformacionServlet.class);

	public static Connection conexion() {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			
			Properties conf = new Properties();
			conf.load(new FileReader(Constantes.PROPERTIES_CONFIGURACION));
			
			String server = conf.getProperty("SERVER");
			String bd = conf.getProperty("BD");
			String puerto = conf.getProperty("PUERTO");
			String pass = conf.getProperty("PASS");
			String user = conf.getProperty("USER");
			
			String cadena = server +"/" + bd;
			
			//"jdbc:mysql://45.7.230.227/Planilla"
			Connection conexion = DriverManager.getConnection(cadena, user, pass);
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
