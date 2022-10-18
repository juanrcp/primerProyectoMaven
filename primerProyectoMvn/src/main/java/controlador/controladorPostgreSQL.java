package controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.conexionPostgresql;
import modelo.dtoAlumno;
import modelo.dtoAsignatura;
import modelo.consultasPostgreSQL;
import util.CierraConexion;
import util.variablesConexionPostgreSQL;

/** controladorPostgreSQL - Controlador que gestiona todos los métodos de PostgreSQL
 * @author garfe
 *
 */
public class controladorPostgreSQL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//CONSTANTES
		final String HOST = variablesConexionPostgreSQL.getHost();
		final String PORT = variablesConexionPostgreSQL.getPort();
		final String DB = variablesConexionPostgreSQL.getDb();
		final String USER = variablesConexionPostgreSQL.getUser();
		final String PASS = variablesConexionPostgreSQL.getPass();
		ArrayList<dtoAlumno> listAlumnos = new ArrayList<>();

		
		/*Se crea una instancia de la clase en la que estamos para poder generar la conexión a PostgreSQL
		*utilizando el método generaConexion
		*/
		System.out.println("[INFORMACIÓN-controladorPortgreSQL-main] Lamada generaConexion");
		conexionPostgresql conexionPostgresql = new conexionPostgresql();
		Connection conexionGenerada = conexionPostgresql.generaConexion(HOST,PORT,DB,USER,PASS);
		
		if(conexionGenerada != null) {
			
			/*Llamar al método que ejecuta la consulta que nos interesa en este caso todos los alumnos, con todos sus datos y su de asignatura
			usando un join
			*/
			System.out.println("[INFORMACIÓN-controladorPortgreSQL-main] Lamada selectAllAlumnos");
			listAlumnos = consultasPostgreSQL.selectAllAlumnos("select alumnos.id_alumno, alumnos.nombre, alumnos.apellidos, alumnos.email, asignaturas.nombre from proyectoeclipse.alumnos left join proyectoeclipse.rel_alum_asig ON rel_alum_asig.id_alumno = alumnos.id_alumno left JOIN proyectoeclipse.asignaturas on asignaturas.id_asignatura = rel_alum_asig.id_asignatura;", conexionGenerada);
			int i = listAlumnos.size();
			System.out.println("[INFORMACIÓN-controladorPortgreSQL-main] Número alumnos: "+i);
			
			//Llamamos al metodo para la insercion de un nuevo alumno y comprobamos con el metodo anterior la inserción
			consultasPostgreSQL.insertNuevoAlumno("INSERT INTO proyectoeclipse.alumnos (id_alumno, nombre, apellidos, email) VALUES (6, 'Carlos', 'Perez', 'DSFSD@GMAIL.COM');", conexionGenerada);
			//listAlumnos = consultasPostgreSQL.selectAllAlumnos("select alumnos.nombre, asignaturas.nombre from proyectoeclipse.alumnos join proyectoeclipse.rel_alum_asig ON rel_alum_asig.id_alumno = alumnos.id_alumno JOIN proyectoeclipse.asignaturas on asignaturas.id_asignatura = rel_alum_asig.id_asignatura", conexionGenerada);
			
			//Llamamos a este metodo para no usar un join 
			consultasPostgreSQL.uneTablas(conexionGenerada);
			
			//Llamamos al metodo para borrar el campo creado
			consultasPostgreSQL.deleteAlumno("delete from proyectoeclipse.alumnos where alumnos.id_alumno = '6';", conexionGenerada);
			
			//Llamamos al metodo para modificar
			consultasPostgreSQL.updateAlumno("update proyectoeclipse.alumnos set email = 'CAMBIO' where alumnos.id_alumno = '3';", conexionGenerada);
			
			//Llamamos al metodo para crear tablas. 
			//consultasPostgreSQL.CreateTable("create table \"proyectoEclipse\".profesores (\"id_profesor\" serial, \"nombre\" character VARYING, \"apellidos\" character VARYING, \"email\" character VARYING);", conexionGenerada);

			
		}
		//Al terminar todas las operaciones cerramos la conexion. 		
		CierraConexion.Cierrar(conexionGenerada);
	
	}

}
