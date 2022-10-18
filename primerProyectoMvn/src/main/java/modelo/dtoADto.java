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
	//Metodo consulta alumnos
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
	
	//Metodo Alumnos + asignatura.nombre
	public static ArrayList<dtoAlumnoAsignatura> resultsetAdtoAlumnoAsignatura(ResultSet resultadoConsulta){
		
		System.out.println("[INFORMACIÓN-resultsetAdtoAlumnoAsignatura-resultsetAdtoAlumnoAsignatura] Entrando en resultsetAdtoAlumnoAsignatura");
		ArrayList<dtoAlumnoAsignatura> listAlumnosAsignaturas = new ArrayList<>();
		
		//Leemos el resultado de la consulta hasta que no queden filas y lo ordenamos
		try {
			while ( resultadoConsulta.next() ) {
				
				listAlumnosAsignaturas.add(new dtoAlumnoAsignatura(resultadoConsulta.getInt(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), resultadoConsulta.getString(4), resultadoConsulta.getString(5)));
			}		
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listAlumnosAsignaturas;
		
	}
	
	//Metodo para recoger las Asignaturas
	public static ArrayList<dtoAsignatura> resultsetAdtoAsignatura(ResultSet resultadoConsulta){
		
		System.out.println("[INFORMACIÓN-resultsetAdtoAsignatura-resultsetAdtoAsignatura] Entrando en resultsetAdtoAsignatura");
		ArrayList<dtoAsignatura> listAsignaturas = new ArrayList<>();
		
		//Leemos el resultado de la consulta hasta que no queden filas y lo ordenamos
		try {
			while ( resultadoConsulta.next() ) {
				
				listAsignaturas.add(new dtoAsignatura(resultadoConsulta.getInt("id_asignatura"), resultadoConsulta.getString("nombre")));
			}		
			System.out.println("Tabla de asignaturas cargada");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listAsignaturas;
		
	}
	
	//Metodo para recoger relacion Alumnos_Asignaturas
		public static ArrayList<dtorel_Alum_Asig> resultsetAdtorel_Alum_Asig(ResultSet resultadoConsulta){
			
			System.out.println("[INFORMACIÓN-resultsetAdtorel_Alum_Asig-resultsetAdtorel_Alum_Asig] Entrando en resultsetAdtoAsignatura");
			ArrayList<dtorel_Alum_Asig> listAlum_Asig = new ArrayList<>();
			
			//Leemos el resultado de la consulta hasta que no queden filas y lo ordenamos
			try {
				while ( resultadoConsulta.next() ) {
					
					listAlum_Asig.add(new dtorel_Alum_Asig(resultadoConsulta.getInt("idalumno_asignatura"), resultadoConsulta.getInt("id_alumno"), resultadoConsulta.getInt("id_asignatura")));
				}		
				System.out.println("Cargada tabla rel_Alum_Asig");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return listAlum_Asig;
			
		}

}
