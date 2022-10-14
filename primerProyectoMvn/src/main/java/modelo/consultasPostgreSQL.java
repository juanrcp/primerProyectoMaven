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
	
	public static ArrayList<dtoAlumno> selectAllAlumnos(Connection conexionGenerada) {
		
		System.out.println("[INFORMACIÓN-consultasPostgreSQL-selectAllAlumnos] Entra en selectAllAlumnos");
		Statement declaracionSQL = null;
		ResultSet resultadoConsulta = null;
		ArrayList<dtoAlumno> listAlumnos = new ArrayList<>();
		
		ArrayList<dtoAlumnoAsignatura> listAlmAsig = new ArrayList<>();
		
		try {
			
			//Se abre una declaración
			declaracionSQL = conexionGenerada.createStatement();
			//Se define la consulta de la declaración y se ejecuta
			resultadoConsulta = declaracionSQL.executeQuery("SELECT * FROM proyectoeclipse.alumnos");
		    
			//Llamada a la conversión a dtoAlumno
			listAlumnos = dtoADto.resultsetAdtoAlumno(resultadoConsulta);
			int i = listAlumnos.size();
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-selectAllAlumnos] Número alumnos: "+i);
			
			resultadoConsulta = null;
			resultadoConsulta = declaracionSQL.executeQuery("select alumnos.nombre, asignaturas.nombre from proyectoeclipse.alumnos join proyectoeclipse.alumno_asignatura on alumnos.id_alumno = alumno_asignatura.id_alumno join proyectoeclipse.asignaturas on asignaturas.idasignaturas = alumno_asignatura.id_asignatura");
			listAlmAsig = dtoADto.resultsetAdtoAlumnoAsignatura(resultadoConsulta);
			for (dtoAlumnoAsignatura dtoAlumnoAsignatura : listAlmAsig) {
				System.out.println("Registro alumno: " + dtoAlumnoAsignatura.getNombre_alumno() + " " + dtoAlumnoAsignatura.getNombre_asignatura());
			}
			
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-selectAllAlumnos] Cierre conexión, declaración y resultado");				
		    resultadoConsulta.close();
		    declaracionSQL.close();
		    //conexionGenerada.close();
		    CierraConexion.Cierrar(conexionGenerada);
			
		} catch (SQLException e) {
			
			System.out.println("[ERROR-conexionPostgresql-main] Error generando la declaracionSQL: " + e);
			return listAlumnos;
			
		}
		return listAlumnos;
		
	}
	
	public static void insertNuevoAlumno(String query, Connection conexionGenerada) {
		
		System.out.println("[INFORMACIÓN-consultasPostgreSQL-insertNuevoAlumno] Entra en insertNuevoAlumno");
		Statement declaracionSQL = null;
		
		try {
			
			declaracionSQL = conexionGenerada.createStatement();
			declaracionSQL.execute(query);
			declaracionSQL.close();
			
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-insertNuevoAlumno] Cierre declaración.");
			
		} catch (SQLException e) {
			
			System.out.println("[ERROR-consultasPostgreSQL-insertNuevoAlumno] Error al insertar alumno: " + e);
		
		}
		
	}

}
