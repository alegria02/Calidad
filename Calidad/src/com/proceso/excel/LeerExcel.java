package com.proceso.excel;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.proceso.mail.EnviarMail;

import jxl.common.Logger;


public class LeerExcel {

	static List<List<String>> arreglo = new ArrayList<>();
	private static final long MILLSECS_PER_DAY = 86400000; //Milisegundos al día 
	private static Logger log = Logger.getLogger(LeerExcel.class);
	
	public static List<List<String>> leerArchivoExcelAntiguo(String archivoDestino) {
		log.debug("leerExcelAntiguo");
		try {
			FileInputStream file = new FileInputStream(new File(archivoDestino));

			HSSFWorkbook workbook = new HSSFWorkbook(file);

			// se obtiene la primera HOJA
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row;
			
			while (rowIterator.hasNext()) {

				row = rowIterator.next();
				// Obtenemos el iterator que permite recorres todas las celdas
				// de una fila
				List<String> datos = new ArrayList<>();
				
				Iterator<Cell> cellIterator = row.cellIterator();
				
				Cell celda;

				// Dependiendo del formato de la celda el valor se debe mostrar
				// como String, Fecha, boolean, entero...
				while (cellIterator.hasNext()) {

					celda = cellIterator.next();
					
					int columna = celda.getColumnIndex();
					
					if (columna == 1) {
						datos.add(celda.toString());
					}
					if (columna == 4) {
						datos.add(celda.toString());
					}
					if (columna == 5) {
						datos.add(celda.toString());
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

			}

			return arreglo;

		} catch (Exception e) {
			log.error("Error leyendo excel " + e.getMessage(), e);
		}
		return arreglo;

	}
	
	public List<List<String>> leerArchivoExcelNuevo(String archivoDestino) {
		log.debug("Leer excel nuevo");
		try {
			FileInputStream file = new FileInputStream(new File(archivoDestino));

			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// se obtiene la primera HOJA
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row;
			
			while (rowIterator.hasNext()) {

				row = rowIterator.next();
				// Obtenemos el iterator que permite recorres todas las celdas
				// de una fila

				Iterator<Cell> cellIterator = row.cellIterator();

				Cell celda;
				
				List<String> lista = new ArrayList<>();
				// Dependiendo del formato de la celda el valor se debe mostrar
				// como String, Fecha, boolean, entero...
				while (cellIterator.hasNext()) {

					celda = cellIterator.next();

					int columna = celda.getColumnIndex();
					
					if (!celda.toString().trim().equals("") || celda.toString().trim() != null) {
						if (columna == 1) {
							//Bloque
							lista.add(celda.toString().trim());
						}
						if (columna == 2) {
							//Stream
							log.debug("Demanda " +celda.toString().trim());
							lista.add(celda.toString().trim());						
						}
						if (columna == 7) {
							//Aplicacion
							lista.add(celda.toString().trim());
						}
						if (columna == 8) {
							//Descripcion
							lista.add(celda.toString().trim());
						}
						if (columna == 15) {
							//Fecha termino construccion
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
	
	public void calcularFecha(String stream,String  bloque,String  aplicacion,String  descripcion, String fecha) {
		try {
			
				Date fechaFinal = new SimpleDateFormat("dd-MMM-yyyy").parse(fecha);

				
				GregorianCalendar gc = new GregorianCalendar();
				int dia = gc.get(Calendar.DAY_OF_MONTH);
				int mes = gc.get(Calendar.MONTH);
				int year = gc.get(Calendar.YEAR);
				
				Date fechaActual = new SimpleDateFormat("dd/MM/yyyy").parse(dia+"/"+(mes+1)+"/"+year);
				
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(fechaActual);
				
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(fechaFinal);
				
				long diferencia = (fechaFinal.getTime() - fechaActual.getTime())/ MILLSECS_PER_DAY;
				

				log.debug("Diferencia de dias: " + diferencia);
				

				if (diferencia == 3 || diferencia == 0) {
					
					log.debug("Stream " +stream);
					log.debug("Bloque " +bloque);
					log.debug("App " +aplicacion);
					log.debug("Descr " +descripcion);
					log.debug("Fecha " +fecha);
					
					String cuerpo = " Quedan " + diferencia +" día(s) para terminar el desarrollo "+"<FONT FACE="+"Verdana"+" Size="+"7"+" COLOR="+"#FF0000"+">" + stream + " - " + descripcion + "</FONT> . Favor medir calidad";
					String asunto = "Calidad - " + stream;
				
					log.debug("Cuerpo del mensaje: " + cuerpo);
					log.debug("Asunto del mensaje: " + asunto);
					
					new EnviarMail(asunto, cuerpo);
				}
				
		} catch (Exception e) {
			log.error("Error en metodo calculoFechas " + e.getMessage(), e);
		}
		
	}			
}