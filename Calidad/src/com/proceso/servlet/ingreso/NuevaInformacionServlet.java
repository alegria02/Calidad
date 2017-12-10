package com.proceso.servlet.ingreso;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proceso.conexion.ConnectionFactory;

import jxl.common.Logger;

/**
 * Servlet implementation class NuevaInformacionServlet
 */
@WebServlet("/NuevaInformacionServlet")
public class NuevaInformacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(NuevaInformacionServlet.class);
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuevaInformacionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fecha = request.getParameter("fecha");
		String stream = request.getParameter("dda");
		double efi = Double.parseDouble(request.getParameter("efi"));
		double man = Double.parseDouble(request.getParameter("man"));
		double por = Double.parseDouble(request.getParameter("por"));
		double fia = Double.parseDouble(request.getParameter("fia"));
		double seg = Double.parseDouble(request.getParameter("seg"));
		String obs = request.getParameter("obs");
		int app = Integer.parseInt(request.getParameter("aplicativo"));
		
		ConnectionFactory conn = new ConnectionFactory();
				
		log.debug(fecha + "|"+ stream + "|" + app);
		
		
		int respuesta = conn.insertarInforme(fecha, stream, efi, man, por, fia, seg, obs, app);
		
		
		if (respuesta == 1) {

			String datos = "Ingreso exitoso";
			request.setAttribute("datos", datos);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cargaDocumento.jsp");
			dispatcher.forward(request, response);
			
		} else if (respuesta == 0) {
			PrintWriter pr = response.getWriter();
			pr.println("Error capa 8");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
}
