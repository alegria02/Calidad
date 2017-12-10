package com.proceso.servlet;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implementation class CargaLog4j
 */
@WebServlet(name = "CargaLog4j", urlPatterns = {"/cargalog4j"}, 
initParams = {@WebInitParam(name = "log4jPropertiesFile",
value = "/home/planillas/conf/log4j.properties")}, loadOnStartup = 1)
public class CargaLog4j extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    @Override
    public void init(ServletConfig config) throws ServletException{
        
        // Obtiene el parametro de inicio
        String log4jFile = config.getInitParameter("log4jPropertiesFile");
        
        // Obtiene la ruta real del archivo (ruta absoluta)
        ServletContext context = config.getServletContext();
        log4jFile = context.getRealPath(log4jFile);
        
        // Carga el log4j.properties si existe y sino carga BasicConfigurator
        if (new File(log4jFile).isFile()) {
            PropertyConfigurator.configure(log4jFile);
        } 
        else {
            BasicConfigurator.configure();
        }
 
        super.init(config);
    }


}
