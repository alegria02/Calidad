package com.proceso.validar.acceso;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.proceso.conexion.ConnectionFactory;

/**
 * Servlet implementation class Acceso
 */
@WebServlet("/Acceso")
public class Acceso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Acceso() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String usuario = request.getParameter("user");
		String clave = request.getParameter("pass");
		
		ConnectionFactory conn = new ConnectionFactory();
		
		int respuesta = conn.validarUsuario(usuario, clave);
		
		
		if (respuesta == 1) {
			HttpSession sesion = request.getSession();

			sesion.setAttribute("usuario", usuario);
			
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
