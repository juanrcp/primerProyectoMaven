package modelo;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/** dtoADto - Métodos para convertir los ResultSet/Transacciones en DTOs.
 * @author garfe
 *
 */
public class dtoADto {
	
	public static ArrayList<dtoAlumno> resultsetAdtoAlumno(ResultSet resultadoConsulta){
		
		System.out.println("[INFORMACIÓN-resultsetAdtoAlumno-resultsetAdtoAlumno] Entrando en resultsetAdtoAlumno");
		ArrayList<dtoAlumno> listAlumnos = new ArrayList<>();
		
		//Leemos el resultado de la consulta hasta que no queden filas
		try {
			while ( resultadoConsulta.next() ) {
				
				listAlumnos.add(new dtoAlumno(resultadoConsulta.getInt("id_alumno"),
						resultadoConsulta.getString("nombre"),
						resultadoConsulta.getString("apellidos"),
						resultadoConsulta.getString("email"))
						);
			}
			
			int i = listAlumnos.size();
			System.out.println("[INFORMACIÓN-resultsetAdtoAlumno-resultsetAdtoAlumno] Número alumnos: "+i);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listAlumnos;
		
	}
	
	public static ArrayList<dtoAlumnoAsignatura> resultsetAdtoAlumnoAsignatura(ResultSet resultadoConsulta){
		
		System.out.println("[INFORMACIÓN-resultsetAdtoAlumnoAsignatura-resultsetAdtoAlumnoAsignatura] Entrando en resultsetAdtoAlumnoAsignatura");
		ArrayList<dtoAlumnoAsignatura> listAlumnosAsignaturas = new ArrayList<>();
		
		//Leemos el resultado de la consulta hasta que no queden filas
		try {
			while ( resultadoConsulta.next() ) {
				
				listAlumnosAsignaturas.add(new dtoAlumnoAsignatura(resultadoConsulta.getString(1),
						resultadoConsulta.getString(2), resultadoConsulta.getString(3), resultadoConsulta.getString(1)));
			}		
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listAlumnosAsignaturas;
		
	}

}
