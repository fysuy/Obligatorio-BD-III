package obligatorio.util;

import java.sql.SQLException;

public interface IPoolConexiones {

	public IConexion obtenerConexion(boolean modifica)
			throws InterruptedException, SQLException;

	public void liberarConexion(IConexion ic, boolean ok);
}
