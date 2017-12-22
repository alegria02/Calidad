package com.proceso.conexion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ConnectionFactory {

	private static final String ERROR_DE_CONEXION = "Error de Conexion: ";
	private static Logger log = Logger.getLogger(ConnectionFactory.class);
	private static final String nomAp = "select nombre from Aplicacion";
	private static final String consultaHistorial = "select R.fecha, R.stream, R.eficiencia, R.mantenibilidad, R.portabilidad, R.fiabilidad, R.seguridad, R.medidor, R.observacion FROM Reporte as R  inner join Aplicacion as A on A.idAplicacion = R.idAplicacion where A.nombre = ? ";
	private static final String mayorMedicion = "select MAX(eficiencia) as eficiencia, MAX(mantenibilidad) as mantenibilidad, MAX(portabilidad) as portabilidad, MAX(fiabilidad) as fiabilidad, MAX(seguridad) as seguridad, A.nombre  FROM Reporte as R inner join Aplicacion as A on A.idAplicacion=R.idAplicacion group by R.idAplicacion";
	private static final String consultaExclusiones = "select * from Exclusiones  as E inner join Aplicacion as A on A.idAplicacion = E.idAplicacion where A.nombre = ?";
	private static final String mejorMedicion = "select MAX(eficiencia) as eficiencia, MAX(mantenibilidad) as mantenibilidad, MAX(portabilidad) as portabilidad, MAX(fiabilidad) as fiabilidad, MAX(seguridad) as seguridad, A.nombre FROM Reporte as R inner join Aplicacion as A on A.idAplicacion = R.idAplicacion where A.nombre = ?";
	private static final String insertarRegistros = "INSERT INTO Planilla.Reporte VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String validarUsuario = "Select * from Usuario where nombre=? and password=?";
	private static final String insertarInforme = "INSERT INTO informe.Reporte VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	Connection conexion = null;
	PreparedStatement ps = null;
	Statement s = null;
	ResultSet rs = null;

	public List<String> nombreAplicativos() {
		log.debug("consultando nombreAplicativos");
		List<String> lista = new ArrayList<>();

		try {

			conexion = DataSource.conexion();

			s = conexion.createStatement();
			rs = s.executeQuery(nomAp);

			while (rs.next()) {
				lista.add(rs.getString(1));
			}

			return lista;

		} catch (Exception e) {
			log.error(ERROR_DE_CONEXION + e, e);
			return null;
		} finally {
			cierreConexiones();
		}
	}

	public List<List<String>> informe() {
		log.debug("consultando informe");
		List<List<String>> lista = new ArrayList<>();

		try {

			conexion = DataSource.conexion();

			s = conexion.createStatement();
			rs = s.executeQuery(mayorMedicion);

			while (rs.next()) {

				List<String> uno = new ArrayList<>();

				uno.add(rs.getString(1));
				uno.add(rs.getString(2));
				uno.add(rs.getString(3));
				uno.add(rs.getString(4));
				uno.add(rs.getString(5));
				uno.add(rs.getString(6));

				lista.add(uno);
			}

			return lista;

		} catch (Exception e) {
			log.error(ERROR_DE_CONEXION + e.getMessage(), e);
			return null;
		} finally {
			cierreConexiones();
		}
	}

	public List<String> exclusionesApp(String idAplicacion) {
		log.debug("consultando exclusionesApp");
		ArrayList<String> lista = new ArrayList<>();

		try {
			conexion = DataSource.conexion();

			ps = conexion.prepareStatement(consultaExclusiones);

			ps.setString(1, idAplicacion);

			rs = ps.executeQuery();

			while (rs.next()) {
				lista.add(rs.getString(2));
			}

			return lista;

		} catch (Exception e) {
			log.error(ERROR_DE_CONEXION + e.getMessage(), e);
			return null;
		} finally {
			cierreConexiones();
		}
	}

	public List<List<String>> mejorMedicionApp(String nombreApp) {
		log.debug("consultando mejorMedicionApp");
		List<List<String>> lista = new ArrayList<>();

		try {

			conexion = DataSource.conexion();

			ps = conexion.prepareStatement(mejorMedicion);
			ps.setString(1, nombreApp);

			rs = ps.executeQuery();

			while (rs.next()) {

				List<String> uno = new ArrayList<>();

				uno.add(rs.getString(1));
				uno.add(rs.getString(2));
				uno.add(rs.getString(3));
				uno.add(rs.getString(4));
				uno.add(rs.getString(5));
				uno.add(rs.getString(6));

				lista.add(uno);
			}

			return lista;

		} catch (Exception e) {
			log.error(ERROR_DE_CONEXION + e.getMessage(), e);
		} finally {
			cierreConexiones();
		}
		return lista;
	}

	public int insertarRegistros(List<List<String>> lista) {

		try {

			conexion = DataSource.conexion();

			int ress = 0;
			for (int i = 1; i < lista.size(); i++) {

				// Date fecha = java.sql.Date.valueOf(lista.get(i).get(2));
				String fecha = lista.get(i).get(2);
				String stream = lista.get(i).get(1);
				String nombreApp = lista.get(i).get(0);
				double efi = Double.parseDouble(lista.get(i).get(5));
				double man = Double.parseDouble(lista.get(i).get(3));
				double por = Double.parseDouble(lista.get(i).get(7));
				double fia = Double.parseDouble(lista.get(i).get(4));
				double seg = Double.parseDouble(lista.get(i).get(6));

				log.debug(fecha + "-" + stream + "-" + nombreApp + "-" + efi + "-" + man + "-" + por + "-" + fia + "-" + seg);

				ps = conexion.prepareStatement(insertarRegistros);

				ps.setString(1, fecha);// fecha
				ps.setString(2, stream);// stream
				ps.setDouble(3, efi);// efi
				ps.setDouble(4, man);// man
				ps.setDouble(5, por);// por
				ps.setDouble(6, fia);// fia
				ps.setDouble(7, seg);// seg
				ps.setString(8, "Kiuwan");
				ps.setString(9, "Sin Anomalias");
				ps.setString(10, nombreApp);

				ress = ps.executeUpdate();

			}
			log.info("======================== Fin insercion de registros ========================");
			return ress;

		} catch (Exception e) {
			log.error("Error insertando registros " + e, e);
			return 0;
		} finally {
			cierreConexiones();
		}
	}

	public List<List<String>> historialApp(String nombreApp) {
		log.debug("consultando historialApp");
		List<List<String>> lista = new ArrayList<>();

		try {

			conexion = DataSource.conexion();

			ps = conexion.prepareStatement(consultaHistorial);
			ps.setString(1, nombreApp);

			rs = ps.executeQuery();

			while (rs.next()) {

				List<String> uno = new ArrayList<>();

				uno.add(rs.getString(1));
				uno.add(rs.getString(2));
				uno.add(rs.getString(3));
				uno.add(rs.getString(4));
				uno.add(rs.getString(5));
				uno.add(rs.getString(6));
				uno.add(rs.getString(7));
				uno.add(rs.getString(8));
				uno.add(rs.getString(9));

				lista.add(uno);
			}

			return lista;

		} catch (Exception e) {
			log.error(ERROR_DE_CONEXION + e.getMessage(), e);
			return null;
		} finally {
			cierreConexiones();
		}
	}

	public int validarUsuario(String usuario, String clave) {
		log.debug("consultando validarUsuario");
		try {

			conexion = DataSource.conexion();

			ps = conexion.prepareStatement(validarUsuario);

			ps.setString(1, usuario);
			ps.setString(2, clave);

			rs = ps.executeQuery();

			log.debug("existe usuario?: " + rs.first());

			return (rs.first() ? 1 : 0);

		} catch (Exception e) {
			log.error(ERROR_DE_CONEXION + e.getMessage(), e);
			return 0;
		} finally {
			cierreConexiones();
		}
	}

	public int insertarInforme(String fecha, String stream, double efi, double man, double por, double fia, double seg, String obs, int app) {
		log.debug("consultando insertarInforme");

		try {

			conexion = DataSource.conexion();

			ps = conexion.prepareStatement(insertarInforme);

			ps.setDate(1, Date.valueOf(fecha));
			ps.setString(2, stream);
			ps.setDouble(3, efi);
			ps.setDouble(4, man);
			ps.setDouble(5, por);
			ps.setDouble(6, fia);
			ps.setDouble(7, seg);
			ps.setString(8, "Kiuwan");
			ps.setString(9, obs);
			ps.setInt(10, app);

			int ress = ps.executeUpdate();

			log.debug("respuesta query: " + rs);

			return (ress > 0 ? 1 : 0);

		} catch (Exception e) {
			log.error(ERROR_DE_CONEXION + e.getMessage(), e);
			return 0;
		} finally {
			cierreConexiones();
		}
	}

	private void cierreConexiones() {
		try {
			DataSource.cerrarConexion(conexion);
			if (ps != null) {
				ps.close();
			}
			if (s != null) {
				s.close();
			}
			if (rs != null) {
				rs.close();
			}

		} catch (Exception e) {
			log.error("Error Cerrando conexion " + e, e);
		}
	}

}
