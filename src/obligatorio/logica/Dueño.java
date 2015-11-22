package obligatorio.logica;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.valueObjects.VOMascota;
import obligatorio.persistencia.IAbstractFactory;
import obligatorio.persistencia.daos.IDaoMascotas;
import obligatorio.util.IConexion;

public class Dueño {
	private int cedula;
	private String nombre;
	private String apellido;
	private IDaoMascotas mascotas;

	public Dueño(int cedula, String nombre, String apellido)
			throws LogicaException {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;

		try {
			Properties p = new Properties();
			String nomArch = "config/config.properties";
			p.load(new FileInputStream(nomArch));
			String concreteFactory = p.getProperty("logica.factory");

			IAbstractFactory factory = (IAbstractFactory) (Class
					.forName(concreteFactory).newInstance());
			this.mascotas = factory.crearDaoMascotas(this.cedula);
		} catch (FileNotFoundException e) {
			throw new LogicaException(e.getMessage());
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | IOException e) {
			throw new LogicaException(e.getMessage());
		}
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
			throws PersistenciaException {
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
