package util;

/** variablesConexionPostgreSQL - Clase que recoger las variables de conexión a PostgreSQL.
 * @author garfe
 * 29/09/2022
 */
public class variablesConexionPostgreSQL {
	
	//Datos de conexión a PostgreSQL
	static final String USER = "postgres";
	static final String PASS = "fp13DAW";
	static final String PORT = "5432";
	static final String HOST = "localhost";
	static final String DB = "EjemploInicial";	
	
	//DATOS DE CONEXION PARA MYSQL
	/*static final String USER = "root";
	static final String PASS = "miprimerabasededatos";
	static final String PORT = "3306";
	static final String HOST = "localhost";
	static final String DB = "proyectoeclipse";	
	*/
	//PROPIEDADES
	public static String getUser() {
		return USER;
	}
	public static String getPass() {
		return PASS;
	}
	public static String getPort() {
		return PORT;
	}
	public static String getHost() {
		return HOST;
	}
	public static String getDb() {
		return DB;
	}

}
