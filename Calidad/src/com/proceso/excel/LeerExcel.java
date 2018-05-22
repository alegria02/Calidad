package com.proceso.excel;

import com.proceso.mail.EnviarMail;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import jxl.common.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LeerExcel {
	static List<List<String>> arreglo = new ArrayList<>();
	private static final long MILLSECS_PER_DAY = 86400000;
	private static Logger log = Logger.getLogger(LeerExcel.class);

	public static List<List<String>> leerArchivoExcelAntiguo(String archivoDestino) {
		log.debug("leerExcelAntiguo");
		HSSFWorkbook workbook = null;
		
		try {
			FileInputStream file = new FileInputStream(new File(archivoDestino));

			workbook = new HSSFWorkbook(file);

			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			
			int filas = 0;
			
			while (rowIterator.hasNext()) {
				log.debug("Leyendo fila numero: ["+filas+"]");
				if (filas != 0) {
					
					
					Row row = (Row) rowIterator.next();

					List<String> datos = new ArrayList<>();

					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell celda = (Cell) cellIterator.next();
						
						int columna = celda.getColumnIndex();
						if (columna == 1) {
							String codApp = evaluaApp(celda.toString());
							log.debug("Codigo aplicativo obtenido ["+codApp+"]");
							datos.add(codApp);
						}
						if (columna == 4) {
							datos.add(celda.toString());
						}
						if ((columna == 5) && (row.getRowNum() > 0)) {
							SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

							SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");

							Date date = parseador.parse(celda.toString());

							String fecha = formateador.format(date);

							log.debug("Formato fecha [" + fecha + "]");
							datos.add(fecha);
						}
						if (columna == 7) {
							datos.add(celda.toString());
						}
						if (columna == 8) {
							datos.add(celda.toString());
						}
						if (columna == 9) {
							datos.add(celda.toString());
						}
						if (columna == 10) {
							datos.add(celda.toString());
						}
						if (columna == 11) {
							datos.add(celda.toString());
						}
					}
					arreglo.add(datos);
				} else {
					filas++;
					rowIterator.next();
				}
				
			}
				
			return arreglo;
		} catch (Exception e) {
			log.error("Error leyendo excel " + e.getMessage(), e);
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				log.error("Error cerrando excel " + e.getMessage(), e);
			}
		}
		return arreglo;
	}

	public List<List<String>> leerArchivoExcelNuevo(String archivoDestino) {
		log.debug("Leer excel nuevo");
		try {
			FileInputStream file = new FileInputStream(new File(archivoDestino));

			XSSFWorkbook workbook = new XSSFWorkbook(file);

			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = (Row) rowIterator.next();

				Iterator<Cell> cellIterator = row.cellIterator();

				List<String> lista = new ArrayList<>();
				while (cellIterator.hasNext()) {
					Cell celda = (Cell) cellIterator.next();

					int columna = celda.getColumnIndex();
					if ((!celda.toString().trim().equals("")) || (celda.toString().trim() != null)) {
						if (columna == 1) {
							lista.add(celda.toString().trim());
						}
						if (columna == 2) {
							log.debug("Demanda " + celda.toString().trim());
							lista.add(celda.toString().trim());
						}
						if (columna == 7) {
							lista.add(celda.toString().trim());
						}
						if (columna == 8) {
							lista.add(celda.toString().trim());
						}
						if (columna == 15) {
							lista.add(celda.toString().trim());
						}
					}
				}
				arreglo.add(lista);
			}
			workbook.close();

			return arreglo;
		} catch (Exception e) {
			log.error("Error leyendo excel " + e.getMessage(), e);
		}
		return arreglo;
	}

	public void calcularFecha(String stream, String bloque, String aplicacion, String descripcion, String fecha) {
		try {
			Date fechaFinal = new SimpleDateFormat("dd-MMM-yyyy").parse(fecha);

			GregorianCalendar gc = new GregorianCalendar();
			int dia = gc.get(5);
			int mes = gc.get(2);
			int year = gc.get(1);

			Date fechaActual = new SimpleDateFormat("dd/MM/yyyy").parse(dia + "/" + (mes + 1) + "/" + year);

			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(fechaActual);

			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(fechaFinal);

			long diferencia = (fechaFinal.getTime() - fechaActual.getTime()) / MILLSECS_PER_DAY;

			log.debug("Diferencia de dias: " + diferencia);
			if ((diferencia == 3) || (diferencia == 0)) {
				log.debug("Stream " + stream);
				log.debug("Bloque " + bloque);
				log.debug("App " + aplicacion);
				log.debug("Descr " + descripcion);
				log.debug("Fecha " + fecha);

				String cuerpo = " Quedan " + diferencia + " d�a(s) para terminar el desarrollo " + "<FONT FACE="
						+ "Verdana" + " Size=" + "7" + " COLOR=" + "#FF0000" + ">" + stream + " - " + descripcion
						+ "</FONT> . Favor medir calidad";
				String asunto = "Calidad - " + stream;

				log.debug("Cuerpo del mensaje: " + cuerpo);
				log.debug("Asunto del mensaje: " + asunto);

				new EnviarMail(asunto, cuerpo);
			}
		} catch (Exception e) {
			log.error("Error en metodo calculoFechas " + e.getMessage(), e);
		}
	}

	private static String evaluaApp(String app) {
		log.debug("Evaluando Nombre aplicativo ["+app+"]");
		try {
			switch (app) {
			case "Gestión de Datos Temáticos":
				return "17";
			case "Datamart Prepago":
				return "11";
			case "DTH - Gestión":
				return "14";

			case "DIRNORM (Dirección Normalizada)":
				return "13";

			case "Módulo Alternativo de Despacho para @tiempo ST":
				return "23";

			case "Coordinador Logístico":
				return "9";

			case "AFAC":
				return "3";

			case "ANRI":
				return "5";

			case "APEL":
				return "6";
				
			case "Autentica":
				return "7";
				
			case "CHER":
				return "8";

			case "DICA":
				return "12";

			case "GACP":
				return "15";

			case "MIGA":
				return "2";

			case "Datamart de Negocio":
				return "10";

			case "SLA Manager":
				return "30";

			case "SIGEO":
				return "29";

			case "Gestión de Equipos Robados GER - GLN":
				return "18";

			case "Gestión Directa":
				return "19";

			case "Provisión Convergente":
				return "26";

			case "PUTYWEB":
				return "27";

			case "PMS (Provisionador Multi Servicio)":
				return "25";

			case "GENESIS":
				return "16";

			case "Accounting":
				return "2";

			case "T-Ayudo (ex Web de Quiebre 2.0)":
				return "31";

			case "Reportes MKT":
				return "28";

			case "MEDTRAF":
				return "20";

			case "Normalizacion de Direcciones Believe":
				return "24";

			case "AGENDA":
				return "4";

			case "MEGA: Monitor End to end de Gestión de Altas":
				return "21";
				
			case "@Tiempo VPI":
				return "1";
			}
		} catch (Exception e) {
			log.error("Error comparando registros --> " + e, e);
			return "0";
		}
		return "0";
	}
}