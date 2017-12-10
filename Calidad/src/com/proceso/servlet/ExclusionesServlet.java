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

/**
 * Servlet implementation class ExclusionesServlet
 */
@WebServlet("/ExclusionesServlet")
public class ExclusionesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExclusionesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionFactory conn = new ConnectionFactory();
		
		String nombreApp = request.getParameter("app");
		List<String> datos = conn.exclusionesApp(nombreApp);
		List<String> datos2 = conn.nombreAplicativos();
		
		request.setAttribute("nombreApp", nombreApp);
		request.setAttribute("exclusiones", datos);
		request.setAttribute("listaNombresApp", datos2);
		
		try {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Exclusiones.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
