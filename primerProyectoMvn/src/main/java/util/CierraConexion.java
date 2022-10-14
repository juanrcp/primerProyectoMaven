package util;

import java.sql.Connection;
import java.sql.SQLException;

public class CierraConexion {
	
	public static void Cierrar(Connection conexion) {
		try {
			conexion.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
