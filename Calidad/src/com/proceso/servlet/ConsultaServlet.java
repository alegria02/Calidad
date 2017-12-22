package com.proceso.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proceso.conexion.ConnectionFactory;

import jxl.common.Logger;

/**
 * Servlet implementation class ConsultaServlet
 */
@WebServlet("/ConsultaServlet")
public class ConsultaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ConsultaServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultaServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("doGet ConsultaServlet");
		String nombreApp = request.getParameter("nombreApp");
		ConnectionFactory cnn = new ConnectionFactory();

		List<List<String>> historial = cnn.historialApp(nombreApp);
		List<List<String>> medicion = cnn.mejorMedicionApp(nombreApp);
		List<String> datos = cnn.nombreAplicativos();

		log.debug("todo ok en ConsultaServlet");

		request.setAttribute("historialApp", historial);
		request.setAttribute("medicionApp", medicion);
		request.setAttribute("listaNombresApp", datos);
		request.setAttribute("nombreApp", nombreApp);

		try {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Aplicativo.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			log.error("Error " + e.getMessage(), e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
