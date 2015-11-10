package obligatorio.util;

public interface IPoolConexiones {

	public IConexion obtenerConexion (boolean modifica);
	
	public void liberarConexion (IConexion ic, boolean ok);
}
