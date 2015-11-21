package obligatorio.logica.valueObjects;

import java.io.Serializable;

public class VODueño implements Serializable {

	private static final long serialVersionUID = 1L;

	private int cedula;
	private String nombre;
	private String apellido;

	public VODueño(int ced, String nom, String ape) {
		super();
		this.cedula = ced;
		this.nombre = nom;
		this.apellido = ape;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
}
