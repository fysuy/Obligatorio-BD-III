package obligatorio.logica.valueObjects;

import java.io.Serializable;

public class VOMascota implements Serializable {

	private static final long serialVersionUID = 1L;

	private String raza;
	private String apodo;
	private int cedulaDueño;

	public VOMascota(String raza, String apodo, int cedulaDueño) {
		super();
		this.raza = raza;
		this.apodo = apodo;
		this.cedulaDueño = cedulaDueño;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public int getCedulaDueño() {
		return cedulaDueño;
	}

	public void setCedulaDueño(int cedulaDueño) {
		this.cedulaDueño = cedulaDueño;
	}
}
