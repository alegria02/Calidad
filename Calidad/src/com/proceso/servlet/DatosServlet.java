package com.proceso.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.proceso.conexion.ConnectionFactory;

/**
 * Servlet implementation class DatosServlet
 */
@WebServlet("/DatosServlet")
public class DatosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(DatosServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DatosServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			log.info("entre a doGet DatosServlet");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dashboard.jsp");

			ConnectionFactory consulta = new ConnectionFactory();
			log.debug("pase por aqui");
			List<String> datos = consulta.nombreAplicativos();
			List<List<String>> informe = consulta.informe();
			request.setAttribute("listaNombresApp", datos);
			request.setAttribute("mediciones", informe);

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
