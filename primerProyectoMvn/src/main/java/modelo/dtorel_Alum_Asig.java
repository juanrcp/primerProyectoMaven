package modelo;

public class dtorel_Alum_Asig {
	
	//Atributos
	int id_rel_Alum_Asig;
	int id_alumno;
	int id_asignatura;
	
	//Propiedades
	public int getId_rel_Alum_Asig() {
		return id_rel_Alum_Asig;
	}
	public void setId_rel_Alum_Asig(int id_rel_Alum_Asig) {
		this.id_rel_Alum_Asig = id_rel_Alum_Asig;
	}
	public int getId_alumno() {
		return id_alumno;
	}
	public void setId_alumno(int id_alumno) {
		this.id_alumno = id_alumno;
	}
	public int getId_asignatura() {
		return id_asignatura;
	}
	public void setId_asignatura(int id_asignatura) {
		this.id_asignatura = id_asignatura;
	}
	
	//Constructor
	public dtorel_Alum_Asig(int id_rel_Alum_Asig, int id_alumno, int id_asignatura) {
		super();
		this.id_rel_Alum_Asig = id_rel_Alum_Asig;
		this.id_alumno = id_alumno;
		this.id_asignatura = id_asignatura;
	}	

}
