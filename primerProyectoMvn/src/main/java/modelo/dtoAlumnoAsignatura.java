package modelo;

public class dtoAlumnoAsignatura {
	
	//ATRIBUTOS
	private String nombre_alumno;
	private String apellidos_alumno;
	private String email_alumno;
	private String nombre_asignatura;
	
	//PROPIEDADES
	public String getNombre_alumno() {
		return nombre_alumno;
	}
	public void setNombre_alumno(String nombre_alumno) {
		this.nombre_alumno = nombre_alumno;
	}
	public String getApellidos_alumno() {
		return apellidos_alumno;
	}
	public void setApellidos_alumno(String apellidos_alumno) {
		this.apellidos_alumno = apellidos_alumno;
	}
	public String getEmail_alumno() {
		return email_alumno;
	}
	public void setEmail_alumno(String email_alumno) {
		this.email_alumno = email_alumno;
	}
	public String getNombre_asignatura() {
		return nombre_asignatura;
	}
	public void setNombre_asignatura(String nombre_asignatura) {
		this.nombre_asignatura = nombre_asignatura;
	}
	
	//CONSTRUCTOR
	public dtoAlumnoAsignatura(String nombre_alumno, String apellidos_alumno, String email_alumno,
			String nombre_asignatura) {
		super();
		this.nombre_alumno = nombre_alumno;
		this.apellidos_alumno = apellidos_alumno;
		this.email_alumno = email_alumno;
		this.nombre_asignatura = nombre_asignatura;
	}
	
	

}
