package obligatorio.persistencia.daos;

import java.util.List;

import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.Mascota;
import obligatorio.logica.valueObjects.VOMascota;
import obligatorio.util.IConexion;

public interface IDaoMascotas {
	public boolean member(IConexion ic, String apodo)
			throws PersistenciaException;

	public int insert(IConexion ic, Mascota mascota)
			throws PersistenciaException;

	public void borrarMascotas(IConexion ic) throws PersistenciaException;

	public List<VOMascota> listarMascotas(IConexion ic)
			throws PersistenciaException;
}
