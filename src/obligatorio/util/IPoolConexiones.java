package obligatorio.util;

import obligatorio.exceptions.PersistenciaException;

public interface IPoolConexiones {

	public IConexion obtenerConexion(boolean modifica)
			throws PersistenciaException;

	public void liberarConexion(IConexion ic, boolean ok)
			throws PersistenciaException;
}
