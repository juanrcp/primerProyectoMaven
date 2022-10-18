package modelo;

public class dtoAsignatura {
	
	int id_asignatura;
	String nombre;
	
	public int getId_asignatura() {
		return id_asignatura;
	}
	public void setId_asignatura(int id_asignatura) {
		this.id_asignatura = id_asignatura;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public dtoAsignatura(int id_asignatura, String nombre) {
		super();
		this.id_asignatura = id_asignatura;
		this.nombre = nombre;
	}	

}
