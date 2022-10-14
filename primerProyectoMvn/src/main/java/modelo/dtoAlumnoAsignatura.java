package modelo;

public class dtoAlumnoAsignatura {
	
	private String nombre_alumno;
	private String nombre_asignatura;
	
	public String getNombre_alumno() {
		return nombre_alumno;
	}

	public void setNombre_alumno(String nombre_alumno) {
		this.nombre_alumno = nombre_alumno;
	}

	public String getNombre_asignatura() {
		return nombre_asignatura;
	}

	public void setNombre_asignatura(String nombre_asignatura) {
		this.nombre_asignatura = nombre_asignatura;
	}

	
	public dtoAlumnoAsignatura(String nombre_alumno, String nombre_asignatura) {
		super();
		this.nombre_alumno = nombre_alumno;
		this.nombre_asignatura = nombre_asignatura;
	}

}
