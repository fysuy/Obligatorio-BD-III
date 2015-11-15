package obligatorio.logica;

import java.sql.SQLException;
import java.util.List;

import obligatorio.logica.valueObjects.VOMascota;
import obligatorio.persistencia.daos.DaoMascotas;
import obligatorio.util.IConexion;

public class Dueño {
	private int cedula;
	private String nombre;
	private String apellido;
	private DaoMascotas mascotas;
	
	public Dueño(int cedula, String nombre, String apellido){
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	public Dueño(int cedula, String nombre, String apellido, DaoMascotas mascotas) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.mascotas = mascotas;
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
	
	public boolean tieneMascota(IConexion icon, String apodo) throws SQLException {
		return mascotas.member(icon, apodo);
	}

	public void addMascota(IConexion icon, Mascota mascota) throws SQLException {
		mascotas.insert(icon, mascota);
	}
	
	public List<VOMascota> listarMascotas(IConexion icon) throws SQLException {
		return mascotas.listarMascotas(icon);
	}
	
	public void borrarMascotas(IConexion icon) throws SQLException {
		mascotas.borrarMascotas(icon);
	}
}
