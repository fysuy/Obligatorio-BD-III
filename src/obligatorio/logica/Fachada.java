package obligatorio.logica;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import obligatorio.exceptions.Due�oException;
import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.MascotaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.valueObjects.VODue�o;
import obligatorio.logica.valueObjects.VOMascota;
import obligatorio.persistencia.daos.IDaoDue�os;
import obligatorio.util.IConexion;
import obligatorio.util.IPoolConexiones;

public class Fachada {
	private static Fachada instance = null;
	private IDaoDue�os due�os;
	private IPoolConexiones ipool;

	protected Fachada() throws LogicaException {

		Properties p = new Properties();
		String nomArch = "config/config.properties";

		try {
			p.load(new FileInputStream(nomArch));
			String pool = p.getProperty("logica.pool");
			String daoDue�o = p.getProperty("logica.daoDueno");
			this.ipool = (IPoolConexiones) (Class.forName(pool).newInstance());

			// TODO: cambiarlo por la factory!
			this.due�os = (IDaoDue�os) (Class.forName(daoDue�o).newInstance());
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

	public void nuevoDue�o(VODue�o due�o) throws Due�oException,
			PersistenciaException {
		IConexion icon;
		int ced = due�o.getCedula();
		String nom = due�o.getNombre();
		String ape = due�o.getApellido();

		icon = ipool.obtenerConexion(true);

		if (!due�os.member(icon, ced)) {
			Due�o d = new Due�o(ced, nom, ape);
			due�os.insert(icon, d);
		} else {
			ipool.liberarConexion(icon, true);
			throw new Due�oException(
					"Error: ya existe el due�o con la c�dula ingresada.");
		}

		ipool.liberarConexion(icon, true);
	}

	public void nuevaMascota(VOMascota pMascota) throws PersistenciaException,
			Due�oException, MascotaException, IOException {
		String apodo = pMascota.getApodo();
		int cedulaDue�o = pMascota.getCedulaDue�o();
		String raza = pMascota.getRaza();

		IConexion icon = ipool.obtenerConexion(true);

		if (due�os.member(icon, cedulaDue�o)) {

			Due�o due�o = due�os.find(icon, cedulaDue�o);

			if (!due�o.tieneMascota(icon, apodo)) {
				Mascota mascota = new Mascota(raza, apodo);
				due�o.addMascota(icon, mascota);
			} else {
				ipool.liberarConexion(icon, true);
				throw new MascotaException(
						"Error: este due�o ya tiene una mascota registrada con ese apodo.");
			}

		} else {
			ipool.liberarConexion(icon, true);
			throw new Due�oException("Error: no existe el due�o");
		}

		ipool.liberarConexion(icon, true);
	}

	public List<VODue�o> listarDue�os() throws PersistenciaException {
		IConexion icon = ipool.obtenerConexion(true);
		List<VODue�o> result;

		result = due�os.listarDue�os(icon);
		ipool.liberarConexion(icon, true);

		return result;
	}

	public List<VOMascota> listarMascotas(int cedulaDue�o)
			throws PersistenciaException, Due�oException {
		IConexion icon = ipool.obtenerConexion(true);
		List<VOMascota> result;

		if (due�os.member(icon, cedulaDue�o)) {
			result = due�os.find(icon, cedulaDue�o).listarMascotas(icon);
		} else {
			ipool.liberarConexion(icon, true);
			throw new Due�oException("Error: no existe el due�o.");
		}

		ipool.liberarConexion(icon, true);
		return result;
	}

	public void borrarDue�oMascotas(int cedulaDue�o)
			throws PersistenciaException, Due�oException {
		IConexion icon = ipool.obtenerConexion(true);

		if (due�os.member(icon, cedulaDue�o)) {
			due�os.find(icon, cedulaDue�o).borrarMascotas(icon);
			due�os.delete(icon, cedulaDue�o);
		} else {
			throw new Due�oException("Error: no existe due�o");
		}

		ipool.liberarConexion(icon, true);
	}
}
