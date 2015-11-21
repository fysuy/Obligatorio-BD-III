package obligatorio.logica;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import obligatorio.exceptions.DueñoException;
import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.valueObjects.VODueño;
import obligatorio.logica.valueObjects.VOMascota;
import obligatorio.persistencia.daos.IDaoDueños;
import obligatorio.util.IConexion;
import obligatorio.util.IPoolConexiones;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Fachada {
	private static Fachada instance = null;
	private IDaoDueños dueños;
	private IPoolConexiones ipool;

	protected Fachada() throws LogicaException {

		Properties p = new Properties();
		String nomArch = "config/config.properties";

		try {
			p.load(new FileInputStream(nomArch));
			String pool = p.getProperty("logica.pool");
			String daoDueño = p.getProperty("logica.daoDueno");
			this.ipool = (IPoolConexiones) (Class.forName(pool).newInstance());

			// TODO: cambiarlo por la factory!
			this.dueños = (IDaoDueños) (Class.forName(daoDueño).newInstance());
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | IOException e) {
			throw new LogicaException(e.getMessage());
		}
	}

	public static Fachada getInstance() throws LogicaException {
		if (instance == null) {
			instance = new Fachada();
		}

		return instance;
	}

	public void nuevoDueño(VODueño dueño) throws DueñoException,
			PersistenciaException {
		IConexion icon;
		int ced = dueño.getCedula();
		String nom = dueño.getNombre();
		String ape = dueño.getApellido();

		icon = ipool.obtenerConexion(true);

		if (!dueños.member(icon, ced)) {
			Dueño d = new Dueño(ced, nom, ape);
			dueños.insert(icon, d);
		} else {
			ipool.liberarConexion(icon, true);
			throw new DueñoException(
					"Error: ya existe el dueño con el numero de cedula ingresado.");
		}

		ipool.liberarConexion(icon, true);
	}

	public void nuevaMascota(VOMascota pMascota) throws PersistenciaException,
			DueñoException {
		String apodo = pMascota.getApodo();
		int cedulaDueño = pMascota.getCedulaDueño();
		String raza = pMascota.getRaza();

		IConexion icon = ipool.obtenerConexion(true);

		if (dueños.member(icon, cedulaDueño)) {
			Dueño dueño = dueños.find(icon, cedulaDueño);
			Mascota mascota = new Mascota(raza, apodo);

			dueño.addMascota(icon, mascota);
		} else {
			throw new DueñoException("Error: no existe dueño");
		}

		ipool.liberarConexion(icon, true);
	}

	public List<VODueño> listarDueños() throws PersistenciaException {
		IConexion icon = ipool.obtenerConexion(true);
		return dueños.listarDueños(icon);
	}

	public List<VOMascota> listarMascotas(int cedulaDueño) {
		throw new NotImplementedException();
		// IConexion icon = pool.obtenerConexion(true);
		//
		// if (dueños.member(icon, cedulaDueño)) {
		// return dueños.find(icon, cedulaDueño).listarMascotas(icon);
		// } else {
		// throw new DueñoException("Error: no existe dueño");
		// }
	}

	public void borrarDueñoMascotas(int cedulaDueño) {
		throw new NotImplementedException();
		// IConexion icon = pool.obtenerConexion(true);
		//
		// if (dueños.member(icon, cedulaDueño)) {
		// dueños.find(icon, cedulaDueño).borrarMascotas(icon);
		// dueños.delete(icon, cedulaDueño);
		// } else {
		// throw new DueñoException("Error: no existe dueño");
		// }
	}
}
