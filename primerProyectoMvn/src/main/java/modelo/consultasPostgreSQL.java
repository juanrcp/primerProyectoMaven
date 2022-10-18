package modelo;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.CierraConexion;

/** consultasPostgreSQL - Definición de las posibles consultas a ejecutar sobre la BD.
 * @author garfe
 *
 */
public class consultasPostgreSQL {
	
	public static ArrayList<dtoAlumno> selectAllAlumnos(String query, Connection conexionGenerada) {
		
		System.out.println("[INFORMACIÓN-consultasPostgreSQL-selectAllAlumnos] Entra en selectAllAlumnos");
		Statement declaracionSQL = null;
		ResultSet resultadoConsulta = null;
		ArrayList<dtoAlumno> listAlumnos = new ArrayList<>();
		
		ArrayList<dtoAlumnoAsignatura> listAlmAsig = new ArrayList<>();
		
		try {
			
			//Se abre una declaración
			declaracionSQL = conexionGenerada.createStatement();
			//Se define la consulta de la declaración y se ejecuta
			resultadoConsulta = declaracionSQL.executeQuery("SELECT * FROM \"proyectoEclipse\".alumnos");
		    
			//Llamada a la conversión a dtoAlumno
			listAlumnos = dtoADto.resultsetAdtoAlumno(resultadoConsulta);
			int i = listAlumnos.size();
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-selectAllAlumnos] Número alumnos: "+i);
			
			//Ejecutamos la consulta que nos interese, la recogemos en un ResultSet y la almacenamos en una lista
			resultadoConsulta = null;
			resultadoConsulta = declaracionSQL.executeQuery(query);
			listAlmAsig = dtoADto.resultsetAdtoAlumnoAsignatura(resultadoConsulta);
			
			//Aqui mostramos la lista
			for (dtoAlumnoAsignatura dtoAlumnoAsignatura : listAlmAsig) {
				System.out.println("Registro alumno: " + dtoAlumnoAsignatura.getNombre_alumno() + " " + dtoAlumnoAsignatura.getApellidos_alumno() + " " + dtoAlumnoAsignatura.getEmail_alumno() + " " + dtoAlumnoAsignatura.getNombre_asignatura());
			}
			
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-selectAllAlumnos] Cierre conexión, declaración y resultado");				
		    resultadoConsulta.close();
		    declaracionSQL.close();

			
		} catch (SQLException e) {
			
			System.out.println("[ERROR-conexionPostgresql-main] Error generando la declaracionSQL: " + e);
			return listAlumnos;
			
		}
		return listAlumnos;
		
	}
	
	public static void insertNuevoAlumno(String query, Connection conexionGenerada) {
		
		//Declaramos un Statement donde recogeremos la query de la insercion 
		System.out.println("[INFORMACIÓN-consultasPostgreSQL-insertNuevoAlumno] Entra en insertNuevoAlumno");
		Statement declaracionSQL = null;
		
		try {
			declaracionSQL = conexionGenerada.createStatement();
			declaracionSQL.execute(query);
			
			//Cerramos la declaracion
			declaracionSQL.close();
			
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-insertNuevoAlumno] Insercion realizada.");
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-insertNuevoAlumno] Cierre declaración.");
			
		} catch (SQLException e) {
			
			System.out.println("[ERROR-consultasPostgreSQL-insertNuevoAlumno] Error al insertar alumno: " + e);
		
		}
		
	}
	
	public static void deleteAlumno (String query, Connection conexionGenerada) {
		
		//Declaramos un Statement donde recogeremos la query del alumno que queremos eliminar.  
		System.out.println("[INFORMACIÓN-consultasPostgreSQL-deleteAlumno] Entra en deleteAlumno");
		Statement declaracionSQL = null;
		
		try {
			
			//Declaramos la query que queramos eliminar. 
			declaracionSQL = conexionGenerada.createStatement();
			declaracionSQL.execute(query);
			
			//Cerramos la declaracion
			declaracionSQL.close();
			
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-deleteAlumno] Borrado con exito realizada!!!!!!!!!!");
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-deleteAlumno] Cierre declaración.");
			
		} catch (SQLException e) {
			
			System.out.println("[ERROR-consultasPostgreSQL-deleteAlumno] Error al Borrar alumno: " + e);
		
		}		
	}
	
	public static void updateAlumno (String query, Connection conexionGenerada) {
		
		//Declaramos un Statement donde recogeremos la query del alumno que queremos modificar.  
		System.out.println("[INFORMACIÓN-consultasPostgreSQL-updateAlumno] Entra en updateAlumno");
		Statement declaracionSQL = null;
		
		try {
			
			//Declaramos la query correspondiente al alumno que queramos modificar y la ejecutamos. 
			declaracionSQL = conexionGenerada.createStatement();
			declaracionSQL.execute(query);
			
			//Cerramos la declaracion
			declaracionSQL.close();
			
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-updateAlumno] Modificacion realizada con exito realizada!!!!!!!!!!");
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-updateAlumno] Cierre declaración.");
			
		} catch (SQLException e) {
			
			System.out.println("[ERROR-consultasPostgreSQL-updateAlumno] Error al Editar alumno: " + e);
		
		}
		
	}
	
	
	public static void CreateTable (String query, Connection conexionGenerada) {
		
		//Declaramos un Statement donde recogeremos la query de la tabla que queremos crear.  
		System.out.println("[INFORMACIÓN-consultasPostgreSQL-CreateTable] Entra en CreateTable");
		Statement declaracionSQL = null;
		
		try {
			
			//Declaramos la query correspondiente a la tabla que queremos modificar. 
			declaracionSQL = conexionGenerada.createStatement();
			declaracionSQL.execute(query);
			
			//Cerramos la declaracion
			declaracionSQL.close();
			
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-CreateTable] Tabla creada con exito realizada!!!!!!!!!!");
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-CreateTable] Cierre declaración.");
			
		} catch (SQLException e) {
			
			System.out.println("[ERROR-consultasPostgreSQL-CreateTable] Error al crear tabla.");
		
		}
		
	}

}
