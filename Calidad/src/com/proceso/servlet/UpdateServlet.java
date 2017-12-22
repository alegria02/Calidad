package com.proceso.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.proceso.test.main.Test;
import com.proceso.utiles.Constantes;

import jxl.common.Logger;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet(name = "UpdateServlet", urlPatterns = { "/UpdateServlet" })
@MultipartConfig(location = "/home/planillas/")
public class UpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UpdateServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("entre a doGet UpdateServlet");

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {

			Collection<Part> parts = request.getParts();
			for (Part part : parts) {
				part.write(part.getName() + ".xlsx");
				log.debug("leyendo archivo excel: " + part.getName() + ".xlsx");
			}

			log.debug("Ruta archivo excel: " + Constantes.RUTA_PLANILLA);

			Test test = new Test();
			test.cargaPlanilla(Constantes.RUTA_PLANILLA);

			String mensaje = "Planilla cargada exitosamente";

			request.setAttribute("mensaje", mensaje);
			log.debug("Mensaje cargado: " + mensaje);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cargaDocumento.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			log.error("Error leyendo archivo: " + e, e);
			out.print(e);
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
