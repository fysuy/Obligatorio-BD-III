package obligatorio.logica;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Properties;

import obligatorio.exceptions.DueñoException;
import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.MascotaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.valueObjects.VODueño;
import obligatorio.logica.valueObjects.VOMascota;
import obligatorio.persistencia.IAbstractFactory;
import obligatorio.persistencia.daos.IDaoDueños;
import obligatorio.util.IConexion;
import obligatorio.util.IPoolConexiones;

public class Fachada extends UnicastRemoteObject implements IFachada {

	public interface IFachada {

	}

	private static final long serialVersionUID = 1L;
	private static Fachada instance = null;
	private IDaoDueños dueños;
	private IPoolConexiones ipool;

	public Fachada() throws LogicaException, RemoteException {

		Properties p = new Properties();
		String nomArch = "config/config.properties";

		try {
			p.load(new FileInputStream(nomArch));
			String pool = p.getProperty("logica.pool");
			String concreteFactory = p.getProperty("logica.factory");
			this.ipool = (IPoolConexiones) (Class.forName(pool).newInstance());

			IAbstractFactory factory = (IAbstractFactory) (Class
					.forName(concreteFactory).newInstance());
			this.dueños = factory.crearDaoDuenios();

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | IOException e) {
			throw new LogicaException(e.getMessage());
		}
	}

	public static Fachada getInstance() throws LogicaException, RemoteException {
		if (instance == null) {
			instance = new Fachada();
		}

		return instance;
	}

	public void nuevoDueño(VODueño dueño) throws DueñoException,
			PersistenciaException, LogicaException {
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
					"Error: ya existe el dueño con la cédula ingresada.");
		}

		ipool.liberarConexion(icon, true);
	}

	public void nuevaMascota(VOMascota pMascota) throws PersistenciaException,
			DueñoException, MascotaException, LogicaException {
		String apodo = pMascota.getApodo();
		int cedulaDueño = pMascota.getCedulaDueño();
		String raza = pMascota.getRaza();

		IConexion icon = ipool.obtenerConexion(true);

		if (dueños.member(icon, cedulaDueño)) {

			Dueño dueño = dueños.find(icon, cedulaDueño);

			if (!dueño.tieneMascota(icon, apodo)) {
				Mascota mascota = new Mascota(raza, apodo);
				dueño.addMascota(icon, mascota);
			} else {
				ipool.liberarConexion(icon, true);
				throw new MascotaException(
						"Error: este dueño ya tiene una mascota registrada con ese apodo.");
			}

		} else {
			ipool.liberarConexion(icon, true);
			throw new DueñoException("Error: no existe el dueño");
		}

		ipool.liberarConexion(icon, true);
	}

	public List<VODueño> listarDueños() throws PersistenciaException {
		IConexion icon = ipool.obtenerConexion(true);
		List<VODueño> result;

		result = dueños.listarDueños(icon);
		ipool.liberarConexion(icon, true);

		return result;
	}

	public List<VOMascota> listarMascotas(int cedulaDueño)
			throws PersistenciaException, DueñoException, LogicaException {
		IConexion icon = ipool.obtenerConexion(true);
		List<VOMascota> result;

		if (dueños.member(icon, cedulaDueño)) {
			result = dueños.find(icon, cedulaDueño).listarMascotas(icon);
		} else {
			ipool.liberarConexion(icon, true);
			throw new DueñoException("Error: no existe el dueño.");
		}

		ipool.liberarConexion(icon, true);
		return result;
	}

	public void borrarDueñoMascotas(int cedulaDueño)
			throws PersistenciaException, DueñoException, LogicaException {
		IConexion icon = ipool.obtenerConexion(true);

		if (dueños.member(icon, cedulaDueño)) {
			dueños.find(icon, cedulaDueño).borrarMascotas(icon);
			dueños.delete(icon, cedulaDueño);
		} else {
			ipool.liberarConexion(icon, true);
			throw new DueñoException("Error: no existe dueño");
		}

		ipool.liberarConexion(icon, true);
	}
}
