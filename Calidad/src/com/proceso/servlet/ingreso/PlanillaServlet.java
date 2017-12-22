package com.proceso.servlet.ingreso;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.proceso.excel.LeerExcel;
import com.proceso.utiles.Constantes;

/**
 * Servlet implementation class PlanillaServlet
 */
@WebServlet(name = "PlanillaServlet", urlPatterns = { "/PlanillaServlet" })
@MultipartConfig(location = "/home/planillas/")
public class PlanillaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(PlanillaServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlanillaServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("entre a doGet PlanillaServlet");

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {

			Collection<Part> parts = request.getParts();
			for (Part part : parts) {
				part.write(part.getName() + ".xlsx");
				log.debug("leyendo archivo excel: " + part.getName() + ".xlsx");
			}

			LeerExcel leer = new LeerExcel();

			log.debug("Ruta archivo excel: " + Constantes.RUTA_EXCEL);

			List<List<String>> recepcion = leer.leerArchivoExcelNuevo(Constantes.RUTA_EXCEL);

			Iterator<List<String>> nombreIterator = recepcion.iterator();

			while (nombreIterator.hasNext()) {
				List<String> elemento = nombreIterator.next();

				String bloque = (String) elemento.get(0);
				String stream = (String) elemento.get(1);
				String aplicacion = (String) elemento.get(2);
				String descripcion = (String) elemento.get(3);
				String fecha = (String) elemento.get(4);

				if (bloque.equals("Aprovisionamiento")) {
					log.debug("Entrando a calcularFecha: " + stream + " " + bloque + " " + aplicacion + " " + descripcion + " " + fecha);
					leer.calcularFecha(stream, bloque, aplicacion, descripcion, fecha);
				}

			}

			String mensaje = "Planilla cargada exitosamente";

			request.setAttribute("mensaje", mensaje);
			log.debug("Mensaje cargado: " + mensaje);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cargaDocumento.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
