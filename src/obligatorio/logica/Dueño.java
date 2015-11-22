package obligatorio.logica;

import java.io.IOException;
import java.util.List;

import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.valueObjects.VOMascota;
import obligatorio.persistencia.daos.IDaoMascotas;
import obligatorio.persistencia.daos.Archivos.DaoMascotasArchivo;
//import obligatorio.persistencia.daos.MySQL.DaoMascotasSQL;
import obligatorio.util.IConexion;

public class Due�o {
	private int cedula;
	private String nombre;
	private String apellido;
	private IDaoMascotas mascotas;

	public Due�o(int cedula, String nombre, String apellido) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		
		//TODO: cambiarlo por la factory!
		this.mascotas = new DaoMascotasArchivo(cedula);
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

	public boolean tieneMascota(IConexion icon, String apodo)
			throws PersistenciaException, IOException {
		return mascotas.member(icon, apodo);
	}

	public void addMascota(IConexion icon, Mascota mascota)
			throws PersistenciaException {
		mascotas.insert(icon, mascota);
	}

	public List<VOMascota> listarMascotas(IConexion icon)
			throws PersistenciaException {
		return mascotas.listarMascotas(icon);
	}

	public void borrarMascotas(IConexion icon) throws PersistenciaException {
		mascotas.borrarMascotas(icon);
	}
}
