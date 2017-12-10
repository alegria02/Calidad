package com.proceso.test.main;

import java.util.List;

import com.proceso.conexion.ConnectionFactory;
import com.proceso.excel.LeerExcel;

public class Test {
	
	public int cargaPlanilla(String ruta) throws Exception{
		List<List<String>> lectura = LeerExcel.leerArchivoExcelAntiguo(ruta);
		
		ConnectionFactory cn = new ConnectionFactory();
		return cn.insertarRegistros(lectura);
		
	}
	

}
