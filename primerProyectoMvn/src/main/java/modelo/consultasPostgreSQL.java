package modelo;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import util.CierraConexion;

/** consultasPostgreSQL - Definición de las posibles consultas a ejecutar sobre la BD.
 * @author garfe
 *
 */
public class consultasPostgreSQL {
	
	//Metodo que muestra todos los alumnos y su asignatura si la tienen
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
				System.out.println("Registro alumno: " + dtoAlumnoAsignatura.getId_alumno() + " " + dtoAlumnoAsignatura.getNombre_alumno() + " " + dtoAlumnoAsignatura.getApellidos_alumno() + " " + dtoAlumnoAsignatura.getEmail_alumno() + " " + dtoAlumnoAsignatura.getNombre_asignatura());
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
	
	//Metodo para unir dos tablas sin tener que hacer un join 
	public static ArrayList<dtoAlumno> uneTablas(Connection conexionGenerada) {
		
		System.out.println("[INFORMACIÓN-consultasPostgreSQL-uneTablas] Entra en uneTablas");
		Statement declaracionSQL = null;
		ResultSet resultadoConsulta = null;
		ArrayList<dtoAlumno> listAlumnos = new ArrayList<>();		
		ArrayList<dtoAsignatura> listAsig = new ArrayList<>();
		ArrayList<dtorel_Alum_Asig> listrel_Alum_Asig = new ArrayList<>();
		dtoAlumnoAsignatura alumno_asignatura = null;
		
		
		try {
			
			//Se abre una declaración
			declaracionSQL = conexionGenerada.createStatement();
			//Se define la consulta de la declaración y se ejecuta
			resultadoConsulta = declaracionSQL.executeQuery("SELECT * FROM \"proyectoEclipse\".alumnos");
		    
			//Llamada a la conversión a dtoAlumno
			listAlumnos = dtoADto.resultsetAdtoAlumno(resultadoConsulta);
			int i = listAlumnos.size();
			System.out.println("Cargada tabla alumnos");
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-uneTablas] Número alumnos: "+i);
			
			//Ejecutamos la consulta que nos interese, la recogemos en un ResultSet y la almacenamos en una lista
			resultadoConsulta = null;
			resultadoConsulta = declaracionSQL.executeQuery("SELECT * FROM \"proyectoEclipse\".asignaturas");
			listAsig = dtoADto.resultsetAdtoAsignatura(resultadoConsulta);
			
			//Hacemos con la relacion Alumnos_Asignaturas lo mismo que la anterior.
			resultadoConsulta = null;
			resultadoConsulta = declaracionSQL.executeQuery("SELECT * FROM \"proyectoEclipse\".rel_alum_asig;");
			listrel_Alum_Asig = dtoADto.resultsetAdtorel_Alum_Asig(resultadoConsulta);			
			
			//Aquí mostramos la lista directamente 
			/*
			for (dtoAlumno dtoAlumno : listAlumnos) {								
				
				System.out.println("Registro alumno: " + dtoAlumno.getId_alumno() + " " + dtoAlumno.getNombre() + " " + dtoAlumno.getApellidos() + " " + dtoAlumno.getEmail() + " ");				
				
				for(int j = 0; j < listrel_Alum_Asig.size(); j++) {					
					
					if(dtoAlumno.getId_alumno() == listrel_Alum_Asig.get(j).getId_alumno()) {
						
						System.out.println(listAsig.get(listrel_Alum_Asig.get(j).getId_asignatura()-1).getNombre());					
						
					}
					else {
						System.out.println();
					}
				}
			}
			*/
			
			//Aqui contruimos un objeto con alumno y asignatura y lo mostramos.
			for (int j = 0; j < listrel_Alum_Asig.size(); j++) {				
				alumno_asignatura = new dtoAlumnoAsignatura(listrel_Alum_Asig.get(j).getId_alumno(), listAlumnos.get(listrel_Alum_Asig.get(j).getId_alumno() - 1).getNombre(), listAlumnos.get(listrel_Alum_Asig.get(j).getId_alumno()-1).getApellidos(), listAlumnos.get(listrel_Alum_Asig.get(j).getId_alumno()-1).getEmail(), (listrel_Alum_Asig.get(j).getId_alumno() == listAlumnos.get(j).getId_alumno()) ? (listAsig.get(listrel_Alum_Asig.get(j).getId_asignatura() - 1).getNombre()):null);
				
				System.out.println("Registro alumno: " + alumno_asignatura.getId_alumno() + " " + alumno_asignatura.getNombre_alumno() + " " + alumno_asignatura.getApellidos_alumno() + " " + alumno_asignatura.getEmail_alumno() + " " + alumno_asignatura.getNombre_asignatura());
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
	
	//Metodo para insertar a un nuevo alumno
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
	
	//Metodo para borrar a un alumno
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
	
	//Metodo para actualizar o modificar los datos de una tabla. 
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
	
	//Metodo que crea una tabla 
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
